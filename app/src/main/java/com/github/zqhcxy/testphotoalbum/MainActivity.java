package com.github.zqhcxy.testphotoalbum;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 读取多媒体 视频与图片的目录列表
 * @author zqhcxy
 */
public class MainActivity extends AppCompatActivity {
    List<Backet> ablumList = new ArrayList<>();
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt1 = (Button) findViewById(R.id.bt1);
        tv1=(TextView)findViewById(R.id.tv1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ablumList = CommentUtil.getMediaAblum(MainActivity.this,CommentUtil.IMAGES);
                for (int i=0;i<ablumList.size();i++){
                    tv1.append("\n"+ablumList.get(i).getFileName()+"    "+ablumList.get(i).count);
                }

            }
        });
    }
}
