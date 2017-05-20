package com.study.android.zhangsht.hqs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private Fragment officeFragment, queueFragment, homeFragment;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragment_container, officeFragment).commit();
        currentFragment = officeFragment;

        Intent intent = getIntent();
        if (intent != null) {
            String status = intent.getStringExtra("status");
            if (status.equals("login")) {
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                initOffice();
            } else if (status.equals("register")) {
                Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                initOffice();
            }
        }
    }

    private void init() {
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        officeFragment = new OfficeFragment();
        homeFragment = new HomeFragment();
        queueFragment = new QueueFragment();
    }

    private void initOffice() {

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_office:
                    switchFragment(officeFragment);
                    return true;
                case R.id.navigation_queue:
                    switchFragment(queueFragment);
                    return true;
                case R.id.navigation_home:
                    switchFragment(homeFragment);
                    return true;
            }
            return false;
        }
    };


    private void switchFragment(Fragment fragment) {
        //判断当前显示的Fragment是不是切换的Fragment
        if (currentFragment != fragment) {
            //判断切换的Fragment是否已经添加过
            if (!fragment.isAdded()) {
                //如果没有，则先把当前的Fragment隐藏，把切换的Fragment添加上
                getSupportFragmentManager().beginTransaction().hide(currentFragment)
                        .add(R.id.fragment_container, fragment).commit();
            } else {
                //如果已经添加过，则先把当前的Fragment隐藏，把切换的Fragment显示出来
                getSupportFragmentManager().beginTransaction().hide(currentFragment).show(fragment).commit();
            }
            currentFragment = fragment;
        }
    }
}
