package com.branchmessenger.dynamicviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // keep track of name list
    private ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list.add("1");
        list.add("2");
        list.add("3");

//        final MyArrayFragmentPagerAdapter adapter = new MyArrayFragmentPagerAdapter(getSupportFragmentManager(), list);
        final MyArrayFragmentStatePagerAdapter adapter = new MyArrayFragmentStatePagerAdapter(getSupportFragmentManager(), list);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        final SmartTabLayout smartTabLayout = (SmartTabLayout) findViewById(R.id.viewpagertab);
        smartTabLayout.setViewPager(viewPager);

        Button insert = (Button) findViewById(R.id.btn_insert);
        Button delete = (Button) findViewById(R.id.btn_delete);
        Button replace = (Button) findViewById(R.id.btn_replace);
        final EditText position = (EditText) findViewById(R.id.position);
        final EditText name = (EditText) findViewById(R.id.name);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pos = position.getText().toString();
                String names = name.getText().toString();
                if (!TextUtils.isEmpty(pos) && !TextUtils.isEmpty(names)) {
                    list.add(Integer.parseInt(pos), names);
                    adapter.add(Integer.parseInt(pos), names);
                }
                smartTabLayout.setViewPager(viewPager);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pos = position.getText().toString();
                adapter.remove(Integer.parseInt(pos));
                list.remove(Integer.parseInt(pos));
                smartTabLayout.setViewPager(viewPager);
            }
        });

        replace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pos = position.getText().toString();
                String names = name.getText().toString();
                if (!TextUtils.isEmpty(pos) && !TextUtils.isEmpty(names)) {
                    list.set(Integer.parseInt(pos), names);
                    adapter.replace(Integer.parseInt(pos), names);
                }
                smartTabLayout.setViewPager(viewPager);
            }
        });
    }

    private class MyArrayFragmentPagerAdapter extends ArrayFragmentPagerAdapter<String> {

        public MyArrayFragmentPagerAdapter(FragmentManager fm, ArrayList<String> datas) {
            super(fm, datas);
        }

        @Override
        public Fragment getFragment(String item, int position) {
            if (item.equals("1"))
                return FragmentOne.newInstance();
            else if (item.equals("2"))
                return new FragmentTwo();
            else
                return new FragmentThree();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position);
        }
    }

    private class MyArrayFragmentStatePagerAdapter extends ArrayFragmentStatePagerAdapter<String> {
        public MyArrayFragmentStatePagerAdapter(FragmentManager fm, ArrayList<String> datas) {
            super(fm, datas);
        }

        @Override
        public Fragment getFragment(String item, int position) {
            if (item.equals("1"))
                return FragmentOne.newInstance();
            else if (item.equals("2"))
                return new FragmentTwo();
            else
                return new FragmentThree();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position);
        }
    }

}
