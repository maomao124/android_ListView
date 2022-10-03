package mao.android_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mao.android_listview.adapter.ListViewAdapter;
import mao.android_listview.entity.ListViewInfo;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.ListView);

        List<ListViewInfo> list = new ArrayList<>(30);

        for (int i = 1; i <= 100; i++)
        {
            ListViewInfo listViewInfo = new ListViewInfo()
                    .setIcon(R.drawable.ic_launcher_foreground)
                    .setTitle("标题" + i)
                    .setContent("内容" + i + ".........");
            list.add(listViewInfo);
        }

        ListViewAdapter listViewAdapter = new ListViewAdapter(this, list);

        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                toastShow("第" + (position + 1) + "个被点击");
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {
                toastShow("第" + (position + 1) + "个被长按了");
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("删除提示")
                        .setMessage("是否删除？")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                list.remove(position);
                                listViewAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNeutralButton("取消", null)
                        .create()
                        .show();
                return true;
            }
        });
    }

    /**
     * 显示消息
     *
     * @param message 消息
     */
    private void toastShow(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}