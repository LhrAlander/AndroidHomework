package hznu.edu.cn.blog;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.mittsu.markedview.MarkedView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import hznu.edu.cn.Util.GetArticles;
import hznu.edu.cn.Util.OkHttpHelper;
import hznu.edu.cn.Util.StatusCode;
import hznu.edu.cn.model.MyArticle;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Alander on 2017/12/1.
 */

public class Article extends AppCompatActivity {

    private MarkedView mv;
    private MyArticle article;
    private TextView titleTV;


    // 处理线程回调
    private Handler handler = new Handler(){
        public void handleMessage (Message msg) {
            switch (msg.what) {
                case StatusCode.ARTICLE_UPDATE:
                    updateArticlePage();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        mv = (MarkedView) findViewById(R.id.activity_article_article_cnt);
        titleTV = (TextView) findViewById(R.id.activity_article_article_title);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 获取文章详细内容
        getArticle();
    }




    private void getArticle () {
        Intent intent = getIntent();
        final String articleId = intent.getStringExtra("articleId");
        String url = "http://www.alanderlight.club/api/getArticleById.do";
        FormBody.Builder builder = new FormBody.Builder();
        RequestBody requestBody = builder.add("articleId", articleId).build();
        OkHttpHelper.postHttp(url, requestBody, new okhttp3.Callback(){
            @Override
            public void onFailure(Call call, IOException e) {}
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json  = response.body().string();
                JSONObject articlesObj = null;
                try {
                    articlesObj = new JSONObject(json);
                    article = GetArticles.GetArticlesByStr(articlesObj.getString("articles")).get(0);
                    Message message = new Message();
                    message.what = StatusCode.ARTICLE_UPDATE;
                    handler.sendMessage(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void updateArticlePage () {
        mv.setMDText(article.getMarkedCnt());
        titleTV.setText(article.getTitle());
    }

}
