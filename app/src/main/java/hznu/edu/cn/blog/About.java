package hznu.edu.cn.blog;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.mittsu.markedview.MarkedView;

public class About extends AppCompatActivity {

    private MarkedView mv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // 设置标题栏
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        mv = (MarkedView) findViewById(R.id.activity_about_mv);
        mv.setMDText("````\n" +
                "{\n" +
                "  \"Name\": \"Alander\",\n" +
                "  \"Hobbies\": [\"WEB\", \"Dota2\"],\n" +
                "  \"Location\": \"Hangzhou\",\n" +
                "  \"School\": \"HZNU\",\n" +
                "  \"Contacts\": {\n" +
                "    \"Email\": \"AlanderLt@163.com\",\n" +
                "    \"Github\": \"@LhrAlander\"\n" +
                "  }\n" +
                "}" +
                "````");

    }
}
