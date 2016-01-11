package com.hsns.research;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TabLayoutActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);

        init();

        setupTabViewPager();
    }

    private void setupTabViewPager() {
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab1"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab2"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab3"));
        mViewPager.setAdapter(new TestPagerAdapter());
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void init() {
        mTabLayout = (TabLayout) findViewById(R.id.view);
        mViewPager = (ViewPager) findViewById(R.id.view2);
    }

    class TestPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView textView = new TextView(TabLayoutActivity.this);
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(30);
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            textView.setText(String.valueOf(position));

            LinearLayout layout = new LinearLayout(TabLayoutActivity.this);
            layout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            layout.setBackgroundColor(Color.BLUE);
            layout.setLayoutParams(layoutParams);
            layout.addView(textView);

            return layout;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout)object);
        }
    }
}
