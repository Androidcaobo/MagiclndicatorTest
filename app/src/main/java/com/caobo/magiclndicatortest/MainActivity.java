package com.caobo.magiclndicatortest;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    //MagicIndicator指示器数据,Arrays.asList(channels)返回一个Arraylist,并将数据存到mDataList
    String[] channels = new String[]{"CUPCAKE", "DONUT", "ECLAIR", "GINGERBREAD", "HONEYCOMB", "ICE_CREAM_SANDWICH", "JELLY_BEAN", "KITKAT", "LOLLIPOP", "M", "NOUGAT"};
    private List<String> mDataList = Arrays.asList(channels);



    //testArray，viewPager要显示的数据
    String[] classmate = new String[]{"曹博", "张汪洋", "金威", "智慧", "李庄", "草纸", "销毁", "张自豪感", "棒棒糖", "嚣张", "导员"};

    //ViewPager实例
    private ViewPager mViewPager;

    //自定义适配器实例
    private MypagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //生成自定义pagerAdapter对象，构造函数是自己写的，重构的，结构更好
        mAdapter =new MypagerAdapter(classmate);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(mAdapter);

        //MagicIndicator指示器部分
        final MagicIndicator magicIndicator = (MagicIndicator) findViewById(R.id.magic_indicator);
        final CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                //mDataList == null条件，为真返回0；
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);

                clipPagerTitleView.setText(mDataList.get(index));
                clipPagerTitleView.setTextColor(Color.parseColor("#f2c4c4"));
                clipPagerTitleView.setClipColor(Color.WHITE);

                clipPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return clipPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;    // 没有指示器，因为title的指示作用已经很明显了
            }
        });
        magicIndicator.setNavigator(commonNavigator);


        //指示器滑动跟随的效果
        ViewPagerHelper.bind(magicIndicator, mViewPager);
    }
}
