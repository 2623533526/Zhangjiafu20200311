package com.bawei.zhangjiafu20200311;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Fragment> list;
    private ViewPager vp;
    private RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp = findViewById(R.id.vp);
        rg = findViewById(R.id.rg);
        list = new ArrayList<>();
        list.add(MyFragment.getInstance(0));
        list.add(MyFragment.getInstance(1));
        list.add(MyFragment.getInstance(2));
        list.add(MyFragment.getInstance(3));
        list.add(MyFragment.getInstance(9));
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
              rg.check(rg.getChildAt(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.quanbu:
                    {
                        vp.setCurrentItem(0);
                    }break;
                    case R.id.daizhifu:
                    {
                        vp.setCurrentItem(1);
                    }break;
                    case R.id.daishouhuo:
                    {
                        vp.setCurrentItem(2);
                    }break;
                    case R.id.daipinglun:
                    {
                        vp.setCurrentItem(3);
                    }break;
                    case R.id.yiwancheng:
                    {
                        vp.setCurrentItem(4);
                    }break;


                }
            }
        });
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

    }
}
