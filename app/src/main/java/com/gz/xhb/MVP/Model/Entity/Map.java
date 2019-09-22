package com.gz.xhb.MVP.Model.Entity;

/**
 * Created by xjj on 2018/6/22.
 */
public class Map {

    /**
     * longitude : 114.00

     * latitude : 41.00
     * content : 深州市ABC地址：A乡B村组织结构代码：联系人：联系电话：关注度：其他在线状态：掉线
     */

    private String longitude;
    private String latitude;
    private String content;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
