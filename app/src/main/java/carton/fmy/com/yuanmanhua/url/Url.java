package carton.fmy.com.yuanmanhua.url;

/**
 * Created by Administrator on 2016/7/18 0018.
 */
public class Url {
    //资讯-头条页面的接口
    public static final String game_headline_path = "http://a121.baopiqi.com/api/mh/getConsultation.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&page=0&limit=20";
    //资讯-头条页面分页加载的接口
    public static String getHeadPagingPath(int index){
        String path = "http://a121.baopiqi.com/api/mh/getConsultation.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&page="+index+"&limit=20";
        return path;
    }
    //资讯-头条页面跳转页面的接口
    public static String getHeadDetailsPath(String id){
        String path = "http://manhua007.com/index.php/Index/zxdetail1/id/"+id+".html";
        return path;
    }
    //资讯-动漫页面的接口
    public static final String game_cartoon_path = "http://a121.baopiqi.com/api/mh/getVideoClassification.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&page=0&limit=12";
    //资讯-动漫页面分页加载的接口
    public static String getCarttonPagingPath(int index){
        String path = "http://a121.baopiqi.com/api/mh/getVideoClassification.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&page="+index+"&limit=12";
        return path;
    }
    //资讯-动漫页面跳转页面的接口
    public static String getCarttonDetailsPath(String id){
        String path = "http://a121.baopiqi.com/api/mh/getVideo.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&id="+id+"&page=0&limit=20";
        return path;
    }
    //资讯-图片页面的接口
    public static final String game_picture_path = "http://a121.baopiqi.com/api/mh/getCartoonWallpaper.php?id=1&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&page=0&limit=12";
    //资讯-图片页面分页加载的接口
    public static String getPicturePagingPath(int index){
        String path = "http://a121.baopiqi.com/api/mh/getCartoonWallpaper.php?id=1&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&page="+index+"&limit=12";
        return path;
    }
    //资讯-段子页面的接口
    public static final String game_joke_path = "http://a121.baopiqi.com/api/mh/getJokesAll.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&page=0&limit=20";
    //资讯-段子页面分页加载的接口
    public static String getJokePadingPaht(int index){
        String path = "http://a121.baopiqi.com/api/mh/getJokesAll.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&page="+index+"&limit=20";
        return path;
    }
	



//第一页的分类
//
//第二页的搜索
    public  static  String getSerchByStr(int pageNum,String str){
        String url= "http://a121.baopiqi.com/api/mh/getSearchCartoon.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&limit=20&page="+pageNum+"&key="+str;
        return url;
    }

//http://a121.baopiqi.com/api/mh/getSearchCartoon.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&limit=20&page=1&key=%E4%BC%A0%E8%AF%B4
//
//
//http://a121.baopiqi.com/api/mh/getSearchCartoon.php&appname=E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&limit=20&page=1&key=爱情
//
//
//详情页  作者的连接
//
//http://a121.baopiqi.com/api/mh/getSearchAuthor.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&limit=20&page=1&key=%E5%87%89%E8%97%A4
//boutique_url   soft_gridview  soft_changeview

    //首页精品
    public static final String BOUTIQUE_URL = "http://a121.baopiqi.com/api/mh/getCartoonHomePageAll.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7";
   //搜索  类别
    public static final String SOFT_GRIDVIEW = "http://a121.baopiqi.com/api/mh/getSearch.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7";
    public static final String SOFT_CHANGEVIEW = "http://a121.baopiqi.com/api/mh/getBoutique.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&page=0&limit=100000000";
    public static  final  String RANKING="http://a121.baopiqi.com/api/mh/getCartoonRankingMore.php?type=4&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&page=0&limit=20";

    public static String  getRankingBypage(int index) {
         return  "http://a121.baopiqi.com/api/mh/getCartoonRankingMore.php?type=4&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&page="+index+"&limit=20";
    }

    public static final int PAGE_SIZE = 20;
    public static String getRecommendMore(int pageNum, int type) {
        String url = null;
        switch (type) {
            case 1:
                //推荐
//                url = "http://a121.baopiqi.com/api/mh/getBoutique.php";
                url = "http://a121.baopiqi.com/api/mh/getBoutique.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&page=" + pageNum + "&limit=20";
                break;
            case 2:
                //热门
//                url = "http://a121.baopiqi.com/api/mh/getCartoonRankingMore.php";
                url = "http://a121.baopiqi.com/api/mh/getCartoonRankingMore.php?type=1&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&page=" + pageNum + "&limit=20";
                break;
            case 3:
                //最新
//                url = "http://a121.baopiqi.com/api/mh/getCartoonNew.php";
                url = "http://a121.baopiqi.com/api/mh/getCartoonNew.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&page=" + pageNum + "&limit=20";
                break;
            case 4:
                url="http://a121.baopiqi.com/api/mh/getCartoonRankingMore.php?type=4&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&page="+pageNum+"&limit=20";
                break;
        }
        return url;
    }

    //分类和搜索
    public static String getTypeData(int pageNum, String type) {
        return "http://a121.baopiqi.com/api/mh/getSearchCartoon.php";
    }
    public static String getAuthorData(int pageNum, String type) {
        return "http://a121.baopiqi.com/api/mh/getSearchAuthor.php?";
    }
    //书的详细内容
    public static String getChapterData(String id, String number) {
        return "http://a121.baopiqi.com/api/mh/getCartoonChapter.php?number="+number+"&id="+id+"&page=0&limit=1000000&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7";
    }
    //书目录
    public static final String BOOK_DAIL_URL="http://a121.baopiqi.com/api/mh/getCartoonInfo.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&id=";

    public static  final String CHAPTER_DAIL_URL="http://a121.baopiqi.com/api/mh/getCartoonChapter.php?appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&number=47225";

}
