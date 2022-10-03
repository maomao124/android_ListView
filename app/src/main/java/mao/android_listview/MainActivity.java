package mao.android_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
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
        final int[] count = {100};

        ListViewAdapter listViewAdapter = new ListViewAdapter(this, list);

        listView.setAdapter(listViewAdapter);
        //listView.setDivider(getResources().getDrawable(R.color.purple_200));
        //listView.setDividerHeight(20);
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

        final boolean[] canRefresh = {false};
        final long[] firstRefreshTime = {System.currentTimeMillis()};

        listView.setOnScrollListener(new AbsListView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState)
            {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
            {
                if (firstVisibleItem == 0)
                {
                    View first_view = listView.getChildAt(0);
                    if (first_view != null && first_view.getTop() == 0)
                    {
                        if (canRefresh[0])
                        {

                            if (System.currentTimeMillis() - firstRefreshTime[0] > 3000)
                            {
                                toastShow("再次上划进行刷新页面");
                                firstRefreshTime[0] = System.currentTimeMillis();
                                return;
                            }
                            toastShow("正在刷新...");
                            list.clear();
                            for (int i = 1; i <= 100; i++)
                            {
                                ListViewInfo listViewInfo = new ListViewInfo()
                                        .setIcon(R.drawable.ic_launcher_foreground)
                                        .setTitle("新的标题" + i)
                                        .setContent("新的内容" + i + ".........");
                                list.add(listViewInfo);
                            }
                            count[0] = 100;
                            listViewAdapter.notifyDataSetChanged();
                            canRefresh[0] = false;
                        }
                    }
                    return;
                }
                if (firstVisibleItem > 0)
                {
                    canRefresh[0] = true;
                }
                if (firstVisibleItem + visibleItemCount == totalItemCount)
                {
                    View last_view = listView.getChildAt(listView.getChildCount() - 1);
                    if (last_view != null && last_view.getBottom() == listView.getHeight())
                    {
                        toastShow("滑动到底部了，正在加载新内容");
                        for (int i = count[0] + 1; i <= count[0] + 100; i++)
                        {
                            ListViewInfo listViewInfo = new ListViewInfo()
                                    .setIcon(R.drawable.ic_launcher_foreground)
                                    .setTitle("标题" + i)
                                    .setContent("内容" + i + ".........");
                            list.add(listViewInfo);
                        }
                        count[0] = count[0] + 100;
                        listViewAdapter.notifyDataSetChanged();
                    }
                }
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