package hznu.edu.cn.Components;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import hznu.edu.cn.blog.About;
import hznu.edu.cn.blog.Archive;
import hznu.edu.cn.blog.MainActivity;
import hznu.edu.cn.blog.R;
import hznu.edu.cn.blog.Tags;

/**
 * Created by Alander on 2017/12/1.
 */

public class SideBar extends LinearLayout implements View.OnClickListener {

    private LinearLayout sideBar;
    private LinearLayout sideHome;
    private LinearLayout sideArchive;
    private LinearLayout sideAbout;
    private LinearLayout sideTags;

    public SideBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.side_bar_cnt, this);
        // 初始化资源
        init();
    }

    private void init () {
        sideBar = (LinearLayout) findViewById(R.id.base_side_cnt);
        sideHome = (LinearLayout) findViewById(R.id.base_side_home);
        sideArchive = (LinearLayout) findViewById(R.id.base_side_archive);
        sideAbout = (LinearLayout) findViewById(R.id.base_side_about);
        sideTags = (LinearLayout) findViewById(R.id.base_side_tags);

        sideHome.setBackgroundColor(333);

        sideHome.setOnClickListener(this);
        sideArchive.setOnClickListener(this);
        sideAbout.setOnClickListener(this);
        sideTags.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.base_side_home:
                if (getContext().toString().indexOf("MainActivity") == -1) {
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    getContext().startActivity(intent);
                }
                break;
            case R.id.base_side_archive:
                if (getContext().toString().indexOf("Archive") == -1) {
                    Intent intent = new Intent(getContext(), Archive.class);
                    getContext().startActivity(intent);
                }
                break;
            case R.id.base_side_about:
                if (getContext().toString().indexOf("About") == -1) {
                    Intent intent = new Intent(getContext(), About.class);
                    getContext().startActivity(intent);
                }
                break;
            case R.id.base_side_tags:
                if (getContext().toString().indexOf("Tag") == -1) {
                    Intent intent = new Intent(getContext(), Tags.class);
                    getContext().startActivity(intent);
                }
                break;
        }
    }
}
