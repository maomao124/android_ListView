package mao.android_listview.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mao.android_listview.R;
import mao.android_listview.entity.ListViewInfo;

/**
 * Project name(项目名称)：android_ListView
 * Package(包名): mao.android_listview.adapter
 * Class(类名): ListViewAdapter
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/10/3
 * Time(创建时间)： 14:46
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class ListViewAdapter extends BaseAdapter
{
    /**
     * 上下文
     */
    private final Context context;

    /**
     * 列表
     */
    private final List<ListViewInfo> list;

    public ListViewAdapter(Context context, List<ListViewInfo> list)
    {
        this.context = context;
        this.list = list;
    }

    /**
     * 得到集合的总数
     *
     * @return int
     */
    @Override
    public int getCount()
    {
        return list.size();
    }

    /**
     * 获取某个位置的ListViewInfo对象
     *
     * @param position 位置
     * @return {@link Object}
     */
    @Override
    public Object getItem(int position)
    {
        return list.get(position);
    }

    /**
     * 获取id
     *
     * @param position 位置
     * @return long
     */
    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ListViewHolder listViewHolder;

        if (convertView == null)
        {
            listViewHolder = new ListViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_baseadaper, null);
            listViewHolder.icon = convertView.findViewById(R.id.icon);
            listViewHolder.title = convertView.findViewById(R.id.title);
            listViewHolder.content = convertView.findViewById(R.id.content);
            listViewHolder.button = convertView.findViewById(R.id.Button);
            listViewHolder.button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    new AlertDialog.Builder(context)
                            .setTitle("提示")
                            .setMessage("按钮被点击了,位于第" + (position + 1) + "个")
                            .setPositiveButton("确定", null)
                            .create()
                            .show();

                }
            });
            convertView.setTag(listViewHolder);
        }
        else
        {
            listViewHolder = (ListViewHolder) convertView.getTag();
        }
        ListViewInfo listViewInfo = list.get(position);
        listViewHolder.icon.setImageResource(listViewInfo.getIcon());
        listViewHolder.title.setText(listViewInfo.getTitle());
        listViewHolder.content.setText(listViewInfo.getContent());
        listViewHolder.button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new AlertDialog.Builder(context)
                        .setTitle("提示")
                        .setMessage("按钮被点击了,位于第" + (position + 1) + "个")
                        .setPositiveButton("确定", null)
                        .create()
                        .show();

            }
        });
        return convertView;
    }


    private static class ListViewHolder
    {
        /**
         * 图标
         */
        public ImageView icon;
        /**
         * 标题
         */
        public TextView title;
        /**
         * 内容
         */
        public TextView content;
        /**
         * 按钮
         */
        public Button button;
    }
}
