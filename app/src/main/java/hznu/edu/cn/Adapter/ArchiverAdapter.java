package hznu.edu.cn.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hznu.edu.cn.Components.ArchiveItemCardView;
import hznu.edu.cn.Components.TagView;
import hznu.edu.cn.blog.Article;
import hznu.edu.cn.blog.R;
import hznu.edu.cn.model.ArchiveModel;
import hznu.edu.cn.model.MyArticle;

/**
 * Created by Alander on 2017/12/2.
 */

public class ArchiverAdapter extends RecyclerView.Adapter<ArchiverAdapter.ViewHolder> {

    private Context ctxt;
    private List<ArchiveModel> archives;

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView archiveDate;
        LinearLayout archiveItems;
        public ViewHolder(View itemView) {
            super(itemView);
            archiveDate = itemView.findViewById(R.id.archive_date);
            archiveItems = itemView.findViewById(R.id.archive_item_wrapper);
        }
    }

    public ArchiverAdapter(List<ArchiveModel> archives) {
        this.archives = archives;
    }

    @Override
    public ArchiverAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (ctxt == null) {
            ctxt = parent.getContext();
        }
        View view = LayoutInflater.from(ctxt).inflate(R.layout.archive_item, parent, false);
        return new ArchiverAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArchiverAdapter.ViewHolder holder, int position) {
        ArchiveModel archive = archives.get(position);
        holder.archiveDate.setText(archive.getDate());
        for (final MyArticle article : archive.getArticles()) {
            ArchiveItemCardView card = new ArchiveItemCardView(ctxt, null);
            card.setTitle(article.getTitle());
            // 如果有标签就添加标签
            if (article.getTypes() != null) {
                List<TagView> tags = new ArrayList<>();
                for (String tag : article.getTypes()) {
                    TagView tagView = new TagView(ctxt, null);
                    tagView.setTag(tag);
                    tags.add(tagView);
                }
                card.setTags(tags);
            }

            // 文章卡片点击事件，进入详情界面
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ctxt, Article.class);
                    intent.putExtra("articleId", article.getArticleId());
                    ctxt.startActivity(intent);
                }
            });

            holder.archiveItems.addView(card);
        }
    }

    @Override
    public int getItemCount() {
        return archives.size();
    }
}
