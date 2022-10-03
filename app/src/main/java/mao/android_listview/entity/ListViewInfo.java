package mao.android_listview.entity;

/**
 * Project name(项目名称)：android_ListView
 * Package(包名): mao.android_listview.entity
 * Class(类名): ListViewInfo
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/10/3
 * Time(创建时间)： 14:45
 * Version(版本): 1.0
 * Description(描述)： 无
 */


public class ListViewInfo
{
    /**
     * 图标
     */
    private int icon;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;

    /**
     * Instantiates a new List view info.
     */
    public ListViewInfo()
    {

    }

    /**
     * Instantiates a new List view info.
     *
     * @param icon    the icon
     * @param title   the title
     * @param content the content
     */
    public ListViewInfo(int icon, String title, String content)
    {
        this.icon = icon;
        this.title = title;
        this.content = content;
    }

    /**
     * Gets icon.
     *
     * @return the icon
     */
    public int getIcon()
    {
        return icon;
    }

    /**
     * Sets icon.
     *
     * @param icon the icon
     * @return the icon
     */
    public ListViewInfo setIcon(int icon)
    {
        this.icon = icon;
        return this;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     * @return the title
     */
    public ListViewInfo setTitle(String title)
    {
        this.title = title;
        return this;
    }

    /**
     * Gets content.
     *
     * @return the content
     */
    public String getContent()
    {
        return content;
    }

    /**
     * Sets content.
     *
     * @param content the content
     * @return the content
     */
    public ListViewInfo setContent(String content)
    {
        this.content = content;
        return this;
    }

    @Override
    @SuppressWarnings("all")
    public String toString()
    {
        final StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("icon：").append(icon).append('\n');
        stringbuilder.append("title：").append(title).append('\n');
        stringbuilder.append("content：").append(content).append('\n');
        return stringbuilder.toString();
    }
}
