package hznu.edu.cn.Components;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import hznu.edu.cn.blog.R;

/**
 * Created by Alander on 2017/12/2.
 */

public class ArchiveItemCardView extends CardView {

    private TextView title;
    private LinearLayout tags;


    public ArchiveItemCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.archive_item_card, this);
        title = findViewById(R.id.archive_item_card_title);
        tags = findViewById(R.id.archive_item_card_tags);
    }


    public void setTitle (String str) {
        title.setText(str);
    }

    public void setTags (List<TagView> tagViews) {
        for (TagView tag : tagViews) {
            tags.addView(tag);
        }
    }



}
