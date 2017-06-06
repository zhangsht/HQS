package com.study.android.zhangsht.hqs.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.android.zhangsht.hqs.R;
import com.study.android.zhangsht.hqs.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class QueueFragment extends Fragment {
    private ViewPager viewPager;
    private List<View> viewList;
    private List<String> titleList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_queue, container, false);
        final FragmentActivity activity = getActivity();
        viewPager = (ViewPager)contentView.findViewById(R.id.officeVP);
        viewList = new ArrayList<>();
        titleList = new ArrayList<>();
        View firstTreat = View.inflate(activity, R.layout.office_first_view, null);
        View secTreat = View.inflate(activity, R.layout.office_2_view, null);
        View disTreat = View.inflate(activity, R.layout.office_ditribute_view, null);

        viewList.add(firstTreat);
        viewList.add(secTreat);
        viewList.add(disTreat);
        titleList.add("首诊");
        titleList.add("2诊");
        titleList.add("分诊");
        ViewPagerAdapter adapter = new ViewPagerAdapter(viewList, titleList);
        viewPager.setAdapter(adapter);
        return contentView;
    }
}
