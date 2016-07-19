package com.zhengyu.Hello_IM.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.gigamole.navigationtabbar.ntb.NavigationTabBar;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.zhengyu.Hello_IM.R;
import com.zhengyu.Hello_IM.adapter.HomeFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends FragmentActivity {
    private ViewPager viewPager;
    private FragmentManager fm;
    private HomeFragmentAdapter homeFragmentAdapter;
    private List<Fragment> fragmentList;
    private EaseChatFragment chatFragment;
    private EaseContactListFragment easeContactListFragment;
    private EaseConversationListFragment easeConversationListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fragmentList = new ArrayList<Fragment>();
        viewPager = (ViewPager) findViewById(R.id.shouye_title_viewpager);

        fm = getSupportFragmentManager();
        homeFragmentAdapter = new HomeFragmentAdapter(fm);

        easeContactListFragment = new EaseContactListFragment();
        easeConversationListFragment = new EaseConversationListFragment();
        easeConversationListFragment.setConversationListItemClickListener(new EaseConversationListFragment.EaseConversationListItemClickListener() {
            @Override
            public void onListItemClicked(EMConversation conversation) {
                startActivity(new Intent(HomeActivity.this, ChatActivity.class)
                        .putExtra(EaseConstant.EXTRA_USER_ID, conversation.getUserName()));
            }
        });


        fragmentList.add(easeContactListFragment);
        fragmentList.add(easeConversationListFragment);

        homeFragmentAdapter.setFragmentList(fragmentList);
        viewPager.setAdapter(homeFragmentAdapter);
        init();
    }

    private void init() {
        final String[] colors = getResources().getStringArray(R.array.vertical_ntb);
        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_vertical);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();

        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_first),
                        Color.parseColor(colors[0]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_second),
                        Color.parseColor(colors[1]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_third),
                        Color.parseColor(colors[2]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .build()
        );
        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
