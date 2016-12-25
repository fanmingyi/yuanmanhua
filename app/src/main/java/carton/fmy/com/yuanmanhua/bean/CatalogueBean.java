package carton.fmy.com.yuanmanhua.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/23.
 */
//书籍目录bean
public class CatalogueBean {

    /**
     * id : 3757
     * name : 星期一的丰满
     * icon : http://biggame1.b0.upaiyun.com/imgv/0bd7e21fff867354682e57b1733d26fc.jpg
     * author : 比村奇石
     * theme : 欢乐向/爱情/治愈
     * region :
     * ranking : 1766774
     * state : 连载中
     * introduction : 比村老师的C89新本子。大家懂的。 比村老师为了社畜们周一能打起精神而在推特上发布的巨乳图集结而成的《星期一的丰满》，本作是献给月曜日从周末重返职场而倍感疲惫的社畜们的福利图集。（所以主角是各种一般会社员）
     * chapter : [{"title":"月曜日Ⅰ","number":"49376","order":"1"},{"title":"汉化版","number":"49381","order":"2"},{"title":"之61","number":"53012","order":"3"},{"title":"之63","number":"54019","order":"4"},{"title":"之65","number":"54516","order":"5"},{"title":"之66","number":"53280","order":"6"},{"title":"之67","number":"54681","order":"7"},{"title":"之68","number":"54832","order":"8"},{"title":"之69","number":"55182","order":"9"},{"title":"之70","number":"55386","order":"10"},{"title":"之71","number":"55799","order":"11"},{"title":"之72","number":"56055","order":"12"},{"title":"之73","number":"56366","order":"13"},{"title":"之74","number":"56723","order":"14"},{"title":"之75","number":"57019","order":"15"},{"title":"之76","number":"57267","order":"16"},{"title":"之77","number":"57535","order":"17"},{"title":"之78","number":"57773","order":"18"},{"title":"月曜日Ⅱ","number":"57938","order":"19"},{"title":"c90会场限定本","number":"57815","order":"20"},{"title":"之79","number":"58061","order":"21"},{"title":"之80","number":"58298","order":"22"},{"title":"之81","number":"58515","order":"23"},{"title":"之82","number":"58732","order":"24"},{"title":"之83","number":"58936","order":"25"},{"title":"之83.5(差分)","number":"58956","order":"26"},{"title":"之84","number":"59108","order":"27"},{"title":"之85","number":"59388","order":"28"},{"title":"之86","number":"59541","order":"29"},{"title":"之87","number":"59713","order":"30"},{"title":"之88","number":"59890","order":"31"},{"title":"之89","number":"60046","order":"32"},{"title":"之90","number":"60216","order":"33"},{"title":"之91","number":"60381","order":"34"},{"title":"之92","number":"60575","order":"35"},{"title":"之93","number":"60775","order":"36"},{"title":"之94","number":"60948","order":"37"},{"title":"C91日本红十字会 献血应援海报","number":"60980","order":"38"},{"title":"之95","number":"61126","order":"39"},{"title":"之96","number":"61317","order":"40"}]
     */

    private String id;
    private String name;
    private String icon;
    private String author;
    private String theme;
    private String region;
    private String ranking;
    private String state;
    private String introduction;
    private List<ChapterBean> chapter;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public List<ChapterBean> getChapter() {
        return chapter;
    }

    public void setChapter(List<ChapterBean> chapter) {
        this.chapter = chapter;
    }

    public static class ChapterBean {
        /**
         * title : 月曜日Ⅰ
         * number : 49376
         * order : 1
         */

        private String title;
        private String number;
        private String order;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }
    }
}
