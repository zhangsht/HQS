package com.study.android.zhangsht.hqs.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.study.android.zhangsht.hqs.R;
import com.study.android.zhangsht.hqs.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyClinic extends AppCompatActivity {
    private ViewPager viewPager;
    private List<View> viewList;
    private List<String> titleList;
    private TextView clinicText;
    private TextView docText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_clinic);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("诊室");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Bundle extras = getIntent().getExtras();
        String doctorInfo = extras.getString("doctorInfo");
        Log.i("doctorInfo", doctorInfo);
        clinicText = (TextView)findViewById(R.id.clinicText);
        docText = (TextView)findViewById(R.id.docText);
        String []arr = doctorInfo.split(" ");
        clinicText.setText(arr[0]);
        docText.setText(arr[1]);

        viewPager = (ViewPager) findViewById(R.id.myClinicVP);
        viewList = new ArrayList<>();
        titleList = new ArrayList<>();
        View waitTreat = View.inflate(this, R.layout.myclinic_wait_view, null);
        View inTreat = View.inflate(this, R.layout.myclinic_in_view, null);
        View afterTreat = View.inflate(this, R.layout.myclinic_after_view, null);
        viewList.add(inTreat);
        viewList.add(waitTreat);
        viewList.add(afterTreat);
        titleList.add("就诊");
        titleList.add("候诊");
        titleList.add("完诊");
        ViewPagerAdapter adapter = new ViewPagerAdapter(viewList, titleList);
        viewPager.setAdapter(adapter);

        PagerTabStrip pagerTabStrip = (PagerTabStrip) findViewById(R.id.pagertab);
        pagerTabStrip.setTabIndicatorColor(Color.parseColor("#00008B"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_clinic, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
        switch (item.getItemId()){

        }
        return super.onOptionsItemSelected(item);
    }
}
