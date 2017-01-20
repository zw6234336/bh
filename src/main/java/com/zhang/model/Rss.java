package com.zhang.model;

import java.io.Serializable;

public class Rss implements Serializable {
	
    private Integer id;

    private String url;

    private String des;

    private String rssEncode;

    private String createtime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des == null ? null : des.trim();
    }

    public String getRssEncode() {
        return rssEncode;
    }

    public void setRssEncode(String rssEncode) {
        this.rssEncode = rssEncode == null ? null : rssEncode.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }
}