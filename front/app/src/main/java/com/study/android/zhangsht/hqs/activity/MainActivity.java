package com.study.android.zhangsht.hqs.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.study.android.zhangsht.hqs.R;
import com.study.android.zhangsht.hqs.fragment.HomeFragment;
import com.study.android.zhangsht.hqs.fragment.OfficeFragment;
import com.study.android.zhangsht.hqs.fragment.QueueFragment;
import com.study.android.zhangsht.hqs.model.ClinicItem;
import com.study.android.zhangsht.hqs.utils.HttpCallbackListener;
import com.study.android.zhangsht.hqs.utils.HttpTool;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private Fragment officeFragment, queueFragment, homeFragment;
    private Fragment currentFragment;

    private MyHandler handler;
    private String officeName = "";

    List<ClinicItem> clinicList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clinicList = new ArrayList<>();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        handler = new MyHandler();

        initOffice();
    }

    private void initFragment() {

        Bundle bundle = new Bundle();
        for (int i = 0; i < clinicList.size(); i++) {
            bundle.putString("clinic" + i, clinicList.get(i).getClinicName() + " " +
                    clinicList.get(i).getDoctorName());
        }
        bundle.putInt("size", clinicList.size());
        bundle.putString("officeName", officeName);
        officeFragment = new OfficeFragment();
        officeFragment.setArguments(bundle);
        homeFragment = new HomeFragment();
        queueFragment = new QueueFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragment_container, officeFragment).commit();
        currentFragment = officeFragment;
    }

    private void initOffice() {
        String url = "http://192.168.137.1:8080/iQueue/getClinicList";
        SharedPreferences sp = getSharedPreferences("iQueue", MODE_PRIVATE);
        String officeId = sp.getString("officeId", "");
        if (officeId.equals("")) {
            Toast.makeText(this, "科室信息不存在", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            String params = "opcode=" + URLEncoder.encode("getClinicList", "UTF-8") +
                    "&officeId=" + URLEncoder.encode(officeId, "UTF-8");

            HttpTool.sendRequest(url, params, new HttpCallbackListener() {
                @Override
                public void onFinish(String response) {
                    Message message = new Message();
                    message.what = 0;
                    message.obj = response.toString();
                    handler.sendMessage(message);
                }

                @Override
                public void onError(Exception e) {
                    Toast.makeText(MainActivity.this, "初始化失败", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("HttpURLConnection.POST", "login 请求失败");
        }
    }

    class MyHandler extends Handler {
        public MyHandler() {
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    String response = (String) msg.obj;
                    initClinicList(response);
                    initFragment();
                    break;
            }
        }
    }

    private void initClinicList(String response) {
        String status = "";

        try {
            JSONObject jsonObject = new JSONObject(response);
            status = jsonObject.getString("status");
            officeName = jsonObject.getString("officeName");
            JSONArray jsonArray = jsonObject.getJSONArray("clinicList");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject clinic = jsonArray.getJSONObject(i);
                String name = clinic.getString("name");
                String doctorName = clinic.getString("doctorName");
                ClinicItem item = new ClinicItem(name, doctorName);
                clinicList.add(item);
                Log.i("getClinicList",  officeName + item.display());
            }
        } catch (Exception e) {
            Toast.makeText(this, "响应解析失败", Toast.LENGTH_SHORT).show();
        }

        if (status.equals("success")) {
            Log.i("getClinicList",  officeName + clinicList.toString());
        }
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
