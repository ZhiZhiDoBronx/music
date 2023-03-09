package com.zhizhidobronx.music.domain;

import java.util.Date;

/**
 * @program: music
 * @description: 歌曲管理
 * @author: ZhiZhio_O
 * @create: 2023-03-09 20:38
 * @other:
 **/
public class Song {
    /*主键*/
    private Integer id;
    /*歌手id*/
    private Integer singerId;
    /*歌名*/
    private String name;
    /*创建时间*/
    private Date createDate;
    /*更新时间*/
    private Date updateDate;
    /*歌曲图片*/
    private String pic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSingerId() {
        return singerId;
    }

    public void setSingerId(Integer singerId) {
        this.singerId = singerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /*简介*/
    private String introduction;
    /*歌词*/
    private String lyric;
    /*歌曲地址*/
    private  String url;
}
