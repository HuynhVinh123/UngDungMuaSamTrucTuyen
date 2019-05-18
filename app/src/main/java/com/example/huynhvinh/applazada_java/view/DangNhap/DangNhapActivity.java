package com.example.huynhvinh.applazada_java.view.DangNhap;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.huynhvinh.applazada_java.Adapter.ViewPagerAdapterDangNhap;
import com.example.huynhvinh.applazada_java.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class DangNhapActivity extends AppCompatActivity  {

    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);

        anhXa();

        ViewPagerAdapterDangNhap viewPagerAdapterDangNhap = new ViewPagerAdapterDangNhap(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapterDangNhap);
        viewPagerAdapterDangNhap.notifyDataSetChanged();

        tabLayout.setupWithViewPager(viewPager);
    }

    private void anhXa() {

        tabLayout = (TabLayout) findViewById(R.id.tabDangNhap);
        viewPager = (ViewPager) findViewById(R.id.viewPagerDangNhap);
        toolbar = (Toolbar) findViewById(R.id.toolBarDangNhap);
        setSupportActionBar(toolbar);
    }

}
