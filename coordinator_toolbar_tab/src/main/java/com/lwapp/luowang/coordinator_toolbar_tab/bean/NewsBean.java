package com.lwapp.luowang.coordinator_toolbar_tab.bean;

/**
 * 新闻的实体类
 * Created by luowang on 2016/10/30.
 */
public class NewsBean {
    private String title;//新闻标题
    private String digest;//小内容
    private String imgsrc;//图片路径

    public NewsBean() {
    }

    public NewsBean(String title, String digest, String imgsrc) {
        this.title = title;
        this.digest = digest;
        this.imgsrc = imgsrc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }
}
