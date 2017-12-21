package hznu.edu.cn.Components;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import hznu.edu.cn.blog.R;

/**
 * Created by Alander on 2017/12/1.
 */

public class TagView extends LinearLayout {
    private TextView tagTV;
    public TagView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.tag_view, this);
        tagTV = findViewById(R.id.tag_tv);
    }

    public void setTag (String tag) {
        tagTV.setText(tag);
    }

    public void setColor (int color) {
        tagTV.setBackgroundColor(color);
    }

    public String getTag () {
        return tagTV.getText() + "";
    }

}
