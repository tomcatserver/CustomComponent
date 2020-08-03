package com.example.java;

import org.junit.Test;

public class IPTest {
    /**
     * 192.168.1.1
     */
    @Test
    public void main() {
        String ip = "192.168.1.1";
        boolean isSaveIP = isSaveIP(ip);
        System.out.println(" ip  -----isSaveIP=" + isSaveIP);

    }

    private boolean isSaveIP(String ip) {
        if (ip == null || ip == "") {
            return false;
        }
        String[] split = ip.split(".");
        if (split == null || split.length !=4) {
            return false;
        }
        int number = 0;
        for (int i = 0; i < split.length; i++) {
            String value = split[i];
            try {
                int i1 = Integer.parseInt(value);
                if (0 >= i1 || i1 > 255) {
                    return false;
                }
                number++;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        if (number == split.length - 1) {
            return true;
        }
        return false;
    }
}
