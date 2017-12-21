package hznu.edu.cn.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import hznu.edu.cn.Util.TagAdapterClickListener;
import hznu.edu.cn.blog.R;

/**
 * Created by Alander on 2017/12/3.
 */

public class TagItemAdapter extends RecyclerView.Adapter<TagItemAdapter.ViewHolder> {

    private Context ctxt;
    private List<String> tags;
    private TextView lastClickView;
    private TagAdapterClickListener listener;
    private Boolean isFirst;



    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tagTV;
        public ViewHolder(View itemView) {
            super(itemView);
            tagTV = itemView.findViewById(R.id.tag_tag_item_tv);
        }
    }


    public TagItemAdapter(List<String> tags, TagAdapterClickListener listener) {
        this.tags = tags;
        this.listener = listener;
        isFirst = true;

    }

    @Override
    public TagItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (ctxt == null) {
            ctxt = parent.getContext();
        }
        View view = LayoutInflater.from(ctxt).inflate(R.layout.tag_item, parent, false);
        return new TagItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TagItemAdapter.ViewHolder holder, int position) {
        final String tag = tags.get(position);
        holder.tagTV.setText(tag);
        if (isFirst && position == 0) {
            holder.tagTV.setTextColor(0xff000000);
            lastClickView = holder.tagTV;
        }
        holder.tagTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lastClickView != view) {
                    lastClickView.setTextColor(0xffb6b6b6);
                    ((TextView)view).setTextColor(0xff000000);
                    lastClickView = (TextView) view;
                }
                listener.onClick(tag);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }
}
