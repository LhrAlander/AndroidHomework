package hznu.edu.cn.blog;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hznu.edu.cn.Adapter.MyArticleAdapter;
import hznu.edu.cn.Adapter.TagItemAdapter;
import hznu.edu.cn.Util.GetArticles;
import hznu.edu.cn.Util.GetTypes;
import hznu.edu.cn.Util.OkHttpHelper;
import hznu.edu.cn.Util.StatusCode;
import hznu.edu.cn.Util.TagAdapterClickListener;
import hznu.edu.cn.model.MyArticle;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class Tags extends AppCompatActivity {

    List<String> tags;
    List<MyArticle> articles;
    private TextView tagTitle;
    private RecyclerView tagArticlesRV;
    private MyArticleAdapter myArticleAdapter;

    // 当前标签
    private String currentTag;

    // 处理线程回调
    private Handler handler = new Handler(){
        public void handleMessage (Message msg) {
            switch (msg.what) {
                case StatusCode.TYPE_GET:
                    getTypeArticles();
                    break;
                case StatusCode.ARTICLE_UPDATE:
                    updatePage();
                    break;
            }
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tags);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tagTitle = (TextView) findViewById(R.id.activity_tags_tag_title);
        tagArticlesRV = (RecyclerView) findViewById(R.id.tag_main_rv);
        // 获取数据
        getTypeFromServer();
    }



    private void getTypeFromServer () {
        String url = "http://www.alanderlight.club/api/gettypelist.do";
        FormBody.Builder builder = new FormBody.Builder();
        RequestBody requestBody = builder.add("articleId", "1").build();
        OkHttpHelper.postHttp(url, requestBody, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.getStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                tags = GetTypes.getTypes(responseData);
                Message message = new Message();
                message.what = StatusCode.TYPE_GET;
                handler.sendMessage(message);
            }
        });
    }

    private void getTypeArticles () {
        RecyclerView tagItemRv = (RecyclerView) findViewById(R.id.tag_tag_item_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        tagItemRv.setLayoutManager(layoutManager);
        tagTitle.setText("Tag: " + tags.get(0));
        currentTag = tags.get(0) + "tag";
        // 标签点击回调函数
        TagItemAdapter adapter = new TagItemAdapter(tags, new TagAdapterClickListener() {
            @Override
            public void onClick(String tag) {
                tagTitle.setText("Tag: " + tag);
                getArticlesFromServer(tag);
            }
        });
        tagItemRv.setAdapter(adapter);
        getArticlesFromServer (tags.get(0));
    }


    private void getArticlesFromServer (final String type) {
        if (currentTag != null && currentTag.equals(type)) {
        }
        else {
            String url = "http://www.alanderlight.club/api/getDisplayArticles.do";
            FormBody.Builder builder = new FormBody.Builder();
            RequestBody requestBody = builder.add("tag", type).add("page", "1").add("limit", "10").build();
            OkHttpHelper.postHttp(url, requestBody, new okhttp3.Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.getStackTrace();
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseData = response.body().string();
                    JSONObject articlesObj = null;
                    try {
                        articlesObj = new JSONObject(responseData);
                        if (articles != null) {
                            articles.clear();
                            articles.addAll(GetArticles.GetArticlesByStr(articlesObj.getString("articles")));
                        }
                        else {
                            articles = GetArticles.GetArticlesByStr(articlesObj.getString("articles"));
                        }
                        Message message = new Message();
                        message.what = StatusCode.ARTICLE_UPDATE;
                        handler.sendMessage(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }



    private void updatePage () {
        for (MyArticle article : articles)
        if (myArticleAdapter != null) {
            myArticleAdapter.notifyDataSetChanged();
        }
        else {
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            tagArticlesRV.setLayoutManager(layoutManager);
            myArticleAdapter = new MyArticleAdapter(articles);
            tagArticlesRV.setAdapter(myArticleAdapter);
        }

    }

}
