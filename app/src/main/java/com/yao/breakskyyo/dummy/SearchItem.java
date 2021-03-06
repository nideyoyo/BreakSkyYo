package com.yao.breakskyyo.dummy;

/**
 * 项目名称：BreakSkyYo
 * 类描述：
 * 创建人：yoyo
 * 创建时间：2015/10/16 21:25
 * 修改人：yoyo
 * 修改时间：2015/10/16 21:25
 * 修改备注：
 */
public class SearchItem {
    String id;
    String imgUrl;
    String hdtag;
    String url;
    String title;
    String score;

    public SearchItem(String id, String title, String imgUrl, String hdtag,String url, String score) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.hdtag = hdtag;
        this.url = url;
        this.title = title;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getHdtag() {
        return hdtag;
    }

    public void setHdtag(String hdtag) {
        this.hdtag = hdtag;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
