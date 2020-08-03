package com.example.safe;

import android.os.Build;
import android.os.FileUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.Adler32;
import java.util.zip.ZipFile;

import androidx.annotation.RequiresApi;

public class SaveUtils {
    /**
     * 加固工具的加密过程：
     * <p>
     * 将原项目和解密的项目分别编译，取原项目生成的apk文件和解密项目生成的dex文件，读入字节流；
     * 使用自己定义的对称加密算法加密原apk文件的数组流，生成新的数组；
     * 将原apk文件流和解密项目dex的文件流进行拼接，生成一个新的dex文件；
     * 修改新的dex文件的文件头，使得新的拼接而成的dex文件格式合法；
     *
     * @return
     * @throws Exception
     */
    public static String forceApk() throws Exception {
        File payloadSrcFile = new File(payloadSrcFilePath); // 需要加壳的程序
        System.out.println("input apk size:" + payloadSrcFile.length());
        File unShellDexFile = new File(unShellDexFilePath); // 解客dex
        byte[] payloadArray = encrpt(readFileBytes(payloadSrcFile));// 以二进制形式读出apk，并进行加密处理//对源Apk进行加密操作
        byte[] unShellDexArray = readFileBytes(unShellDexFile);// 以二进制形式读出dex
        int payloadLen = payloadArray.length;
        int unShellDexLen = unShellDexArray.length;
        int totalLen = payloadLen + unShellDexLen + 4;// 多出4字节是存放长度的。
        byte[] newdex = new byte[totalLen]; // 申请了新的长度
        // 添加解壳代码
        System.arraycopy(unShellDexArray, 0, newdex, 0, unShellDexLen);// 先拷贝dex内容
        // 添加加密后的解壳数据
        System.arraycopy(payloadArray, 0, newdex, unShellDexLen, payloadLen);// 再在dex内容后面拷贝apk的内容
        // 添加解壳数据长度
        System.arraycopy(intToByte(payloadLen), 0, newdex, totalLen - 4, 4);// 最后4为长度
        // 修改DEX file size文件头
        fixFileSizeHeader(newdex);
        // 修改DEX SHA1 文件头
        fixSHA1Header(newdex);
        // 修改DEX CheckSum文件头
        fixCheckSumHeader(newdex);
        File file = new File(outputDexFileName);
        if (file.delete() || !file.exists()) {
            file.createNewFile();
        }

        FileOutputStream localFileOutputStream = new FileOutputStream(
                outputDexFileName);
        localFileOutputStream.write(newdex);
        localFileOutputStream.flush();
        localFileOutputStream.close();
        return replaceDex(outputDexFileName);
    }

    private static byte[] encrpt(byte[] srcdata) {
        for (int i = 0; i < srcdata.length; i++) {
            srcdata[i] = (byte) (0xFF ^ srcdata[i]);
        }
        return srcdata;
    }

    /**
     * 原apk和壳文件写成了一个合法的dex文件
     * <p>
     * 修改dex头 sha1值
     */
    private static void fixCheckSumHeader(byte[] dexBytes) {
        Adler32 adler = new Adler32();
        adler.update(dexBytes, 12, dexBytes.length - 12);// 从12到文件末尾计算校验码
        long value = adler.getValue();
        int va = (int) value;
        byte[] newcs = intToByte(va);
        // 高位在前，低位在后
        byte[] recs = new byte[4];
        for (int i = 0; i < 4; i++) {
            recs[i] = newcs[newcs.length - 1 - i];
            // System.out.println(Integer.toHexString(newcs[i]));
        }
        System.arraycopy(recs, 0, dexBytes, 8, 4);// 效验码赋值（8-11）
    }

    /**
     * 修改dex头 sha1值
     */
    private static void fixSHA1Header(byte[] dexBytes)
            throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(dexBytes, 32, dexBytes.length - 32);// 从32位到结束计算sha--1
        byte[] newdt = md.digest();
        System.arraycopy(newdt, 0, dexBytes, 12, 20);// 修改sha-1值（12-31）
    }

    /**
     * 修改dex头 file_size值
     */
    private static void fixFileSizeHeader(byte[] dexBytes) {
        // 新文件长度
        byte[] newfs = new byte[dexBytes.length];
        // System.out.println(Integer.toHexString(dexBytes.length));
        byte[] refs = new byte[4];
        // 高位在前，低位在后
        for (int i = 0; i < 4; i++) {
            refs[i] = newfs[newfs.length - 1 - i];
        }
        System.arraycopy(refs, 0, dexBytes, 32, 4);// 修改（32-35）
    }


    /**
     * 回到加固过程，把生成的dex文件替换到壳apk中去，需要先签名  -------------APK签名
     *
     * @param apkPath
     * @return
     * @throws Exception
     */
    public static String sign(String apkPath) throws Exception {
        String nameFlag = apkPath.replace(".apk", "");
        String output = nameFlag + "_singed.apk";
        String shell = "jarsigner -verbose -digestalg SHA1 -sigalg MD5withRSA -keystore "
                + keystorePath + " -signedjar " + output + " " + apkPath + " " + alias + " -storepass " + storepass + " -keypass " + keypass;
        System.out.println(shell);
        callShell(shell);
        return output;
    }

    public static void callShell(String shellString) throws Exception {
        Process process = Runtime.getRuntime().exec(shellString);
        int exitValue = process.waitFor();
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        String result = sb.toString();
        System.out.println(result);
        if (0 != exitValue) {
            throw new Exception("call shell failed. error code is :"
                    + exitValue);
        }
    }


    /**
     * 美团多渠道打包
     *
     * @param zipFilename
     * @param channel
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static boolean changeChannel(final String zipFilename,
                                        final String channel) {
        try (FileSystem zipfs = FileUtils.createZipFileSystem(zipFilename, false)) {
            final Path root = zipfs.getPath("/META-INF/");
            ChannelFileVisitor visitor = new ChannelFileVisitor();
            Files.walkFileTree(root, visitor);
            Path existChannel = visitor.getChannelFile();
            Path newChannel = zipfs.getPath(CHANNEL_PREFIX + channel);
            if (existChannel != null) {
                Files.move(existChannel, newChannel, StandardCopyOption.ATOMIC_MOVE);
            } else {
                Files.createFile(newChannel);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * packer-ng多渠道打包
     *
     * @param file
     * @param comment
     */
    public static void writeApk(File file, String comment) {
        ZipFile zipFile = null;
        ByteArrayOutputStream outputStream = null;
        RandomAccessFile accessFile = null;
        try {
            zipFile = new ZipFile(file);
            String zipComment = zipFile.getComment();
            if (zipComment != null) {
                return;
            }
            byte[] byteComment = comment.getBytes();
            outputStream = new ByteArrayOutputStream();
            outputStream.write(byteComment);
            outputStream.write(short2Stream((short) byteComment.length));
            byte[] data = outputStream.toByteArray();
            accessFile = new RandomAccessFile(file, "rw");
            accessFile.seek(file.length() - 2);
            accessFile.write(short2Stream((short) data.length));
            accessFile.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (zipFile != null) {
                    zipFile.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (accessFile != null) {
                    accessFile.close();
                }
            } catch (Exception e) {
            }
        }
    }
}
