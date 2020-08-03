package com.example.compoent;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.common.service.IJavaTestService;
import com.example.common.service.IKotlinService;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_java_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/java/com/example/MainActivity").withString("keyJava", "我是从app壳工程来的").navigation();
            }
        });
        findViewById(R.id.tv_java_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IJavaTestService service = ARouter.getInstance().navigation(IJavaTestService.class);
//                IJavaTestService service = (IJavaTestService) ARouter.getInstance().build("/com/example/java/JavaService").navigation();
                if (service != null) {
                    Toast.makeText(MainActivity.this, service.outContent("从app壳工程服务交互"), Toast.LENGTH_LONG).show();
                }
            }
        });
        findViewById(R.id.tv_kotlin_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/kotlin/com/example/MainActivity").withString("keyJava", "我是从app壳工程来的").navigation();

            }
        });
        findViewById(R.id.tv_kotlin_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2020/5/29 再续
                IKotlinService service = ARouter.getInstance().navigation(IKotlinService.class);
                if (service != null) {
                    Toast.makeText(MainActivity.this, service.outContent("从app壳工程服务交互ing"), Toast.LENGTH_LONG).show();
                }
            }
        });
        findViewById(R.id.tv_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/video/com/example/TestActivity").withString("keyJava", "我是从app壳工程来的").navigation();
            }
        });
        findViewById(R.id.tv_live).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/livemoudle/com/example/TestActivity").withString("keyJava", "我是从app壳工程来的").navigation();
            }
        });
    }
}
