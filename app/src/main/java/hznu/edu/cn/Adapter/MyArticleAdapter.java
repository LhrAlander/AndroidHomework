package hznu.edu.cn.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mittsu.markedview.MarkedView;

import java.util.ArrayList;
import java.util.List;

import hznu.edu.cn.Components.TagView;
import hznu.edu.cn.blog.Article;
import hznu.edu.cn.blog.R;
import hznu.edu.cn.model.MyArticle;

/**
 * Created by Alander on 2017/12/1.
 */

public class MyArticleAdapter extends RecyclerView.Adapter<MyArticleAdapter.ViewHolder> {
    private Context ctxt;
    private List<MyArticle> articles;


    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView articleTitle;
        TextView articleTime;
        MarkedView articleCnt;
        LinearLayout articleTags;
        List<TagView> tags;
        TextView more;


        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            articleCnt = itemView.findViewById(R.id.article_cnt);
            articleTime = itemView.findViewById(R.id.article_time);
            articleTitle = itemView.findViewById(R.id.article_title);
            articleTags = itemView.findViewById(R.id.article_tags);
            more = itemView.findViewById(R.id.article_more);
        }
    }

    public MyArticleAdapter(List<MyArticle> articles) {
        this.articles = articles;
    }

    @Override
    public MyArticleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (ctxt == null) {
            ctxt = parent.getContext();
        }
        View view = LayoutInflater.from(ctxt).inflate(R.layout.article_item, parent, false);
        return new MyArticleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyArticleAdapter.ViewHolder holder, int position) {
        final MyArticle article = articles.get(position);
        holder.articleTitle.setText(article.getTitle());
        holder.articleTime.setText(article.getCreateTime());
        holder.articleCnt.setMDText(article.getMarkedCnt());
            holder.tags = new ArrayList<>();
            for (String tag : article.getTypes()) {
                TagView tagView = new TagView(ctxt, null);
                tagView.setTag(tag);
                holder.tags.add(tagView);
            }
        holder.articleTags.removeAllViews();
        for (TagView tv : holder.tags) {
            holder.articleTags.removeView(tv);
            holder.articleTags.addView(tv);
        }

        holder.articleTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(article.getArticleId());
            }
        });
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(article.getArticleId());
            }
        });
    }

    private void startActivity(String articleID) {
        Intent intent = new Intent(ctxt, Article.class);
        intent.putExtra("articleId", articleID);
        ctxt.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
