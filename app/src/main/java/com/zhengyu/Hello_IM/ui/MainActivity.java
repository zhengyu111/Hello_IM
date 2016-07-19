package com.zhengyu.Hello_IM.ui;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhengyu.Hello_IM.R;
import com.zhengyu.Hello_IM.adapter.HomeFragmentAdapter;
import com.zhengyu.Hello_IM.fragment.MainLoginFragment;
import com.zhengyu.Hello_IM.fragment.MainRegisterFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity implements View.OnClickListener{
    private HomeFragmentAdapter adapter;
    private TextView tv_mulu;
    private TextView tv_fenlei;

    private FragmentManager fm;
    private ViewPager viewPager;

    private List<Fragment> fragmentList;

    private MainRegisterFragment mainRegisterFragment;
    private MainLoginFragment mainLoginFragment;

    private int bmpwidth;
    //偏移量
    private int offset;
    private int windowWidth;
    private ImageView iv_sliding;

    private TranslateAnimation translateAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        init();
        initbitmap();
        setlistener();
    }

    public void setlistener(){
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
            }
            int correntIndex=0;
            int one=bmpwidth*2+offset;
            @Override
            public void onPageSelected(int postion) {
                if(postion==0){
                    translateAnimation=new TranslateAnimation(550,10,0,0);
                }else{
                    translateAnimation=new TranslateAnimation(offset,550,0,0);
                }
                translateAnimation.setDuration(200);
                translateAnimation.setFillAfter(true);
                iv_sliding.startAnimation(translateAnimation);
                correntIndex=postion;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


    public void initbitmap(){
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //屏幕宽度
        windowWidth=dm.widthPixels;
        //图片宽度
        bmpwidth= BitmapFactory.decodeResource(getResources(), R.drawable.a).getWidth();
        offset=(windowWidth/2-bmpwidth)/2;
        Matrix matrix=new Matrix();
        matrix.postTranslate(offset,0);
        iv_sliding.setImageMatrix(matrix);
    }

    private void init() {
        viewPager= (ViewPager) findViewById(R.id.center_bar_vp);
        iv_sliding= (ImageView) findViewById(R.id.iv_sliding);

        viewPager = (ViewPager) findViewById(R.id.center_bar_vp);
        fm = getSupportFragmentManager();
        tv_mulu = (TextView) findViewById(R.id.tv_mulu);
        tv_fenlei = (TextView) findViewById(R.id.tv_fenlei);

        fragmentList = new ArrayList<Fragment>();

        mainRegisterFragment = new MainRegisterFragment();
        mainLoginFragment = new MainLoginFragment();

        adapter = new HomeFragmentAdapter(fm);

        fragmentList.add(mainRegisterFragment);
        fragmentList.add(mainLoginFragment);

        adapter.setFragmentList(fragmentList);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
    }
    
    
    
    
    
    
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_mulu:
                viewPager.setCurrentItem(0);
                break;
            case R.id.tv_fenlei:
                viewPager.setCurrentItem(1);
                break;
        }
    }
}
