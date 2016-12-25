package carton.fmy.com.yuanmanhua.bean;

/**
 * 用于显示每页漫画内容
 * Created by 范明毅 on 2016/12/24.
 */

public class ShowBean {
    /**
     * name : 星期一的丰满
     * title : 月曜日Ⅰ
     * icon : http://biggame1.b0.upaiyun.com/imgv/bc1fe2f2e5591c00c425c7ee95190dcd.jpg
     * page : 1
     * number : 49376
     */

    private String name;
    private String title;
    private String icon;
    private String page;
    private String number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
