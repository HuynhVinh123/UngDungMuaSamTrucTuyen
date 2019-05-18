package com.example.huynhvinh.applazada_java.view.ManHinhChao;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.huynhvinh.applazada_java.R;
import com.example.huynhvinh.applazada_java.view.TrangChu.TrangChuActivity;

import static java.lang.Thread.sleep;

public class ManHinhChaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinhchao_layout);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    sleep(500);
                    Intent iTrangChu = new Intent(ManHinhChaoActivity.this, TrangChuActivity.class);
                    startActivity(iTrangChu);
                }
                catch (Exception e){

                }
            }
        });
        thread.start();
    }
}
