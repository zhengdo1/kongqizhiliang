package com.gz.xhb_zhongtie.util.CheckUpdate;

/**
 * @author zdj
 * @date 2019/10/12.
 * description：
 */
public class PgyerUpdateVersionInfo {

    /**
     * code : 0
     * message :
     * data : {"lastBuild":"4","forceUpdateVersion":"","forceUpdateVersionNo":"","needForceUpdate":false,"downloadURL":"http://www.pgyer.com/app/installUpdate/53649a7bf8af1a09fd066919cbce600e?sig=6UZpYbE5DS8wvV5BndIw0FHxn%2Fb5EeCglcj3f9vTfAXtlPtZYfIPEw9rweSJ%2BvOE","haveNewVersion":true,"updateDeny":false,"versionCode":"2","versionName":"0.0.4","appUrl":"http://www.pgyer.com/coal","build":"4","releaseNote":"优化页面"}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * lastBuild : 4
         * forceUpdateVersion :
         * forceUpdateVersionNo :
         * needForceUpdate : false
         * downloadURL : http://www.pgyer.com/app/installUpdate/53649a7bf8af1a09fd066919cbce600e?sig=6UZpYbE5DS8wvV5BndIw0FHxn%2Fb5EeCglcj3f9vTfAXtlPtZYfIPEw9rweSJ%2BvOE
         * haveNewVersion : true
         * updateDeny : false
         * versionCode : 2
         * versionName : 0.0.4
         * appUrl : http://www.pgyer.com/coal
         * build : 4
         * releaseNote : 优化页面
         */

        private String lastBuild;
        private String forceUpdateVersion;
        private String forceUpdateVersionNo;
        private boolean needForceUpdate;
        private String downloadURL;
        private boolean haveNewVersion;
        private boolean updateDeny;
        private String versionCode;
        private String versionName;
        private String appUrl;
        private String build;
        private String releaseNote;

        public String getLastBuild() {
            return lastBuild;
        }

        public void setLastBuild(String lastBuild) {
            this.lastBuild = lastBuild;
        }

        public String getForceUpdateVersion() {
            return forceUpdateVersion;
        }

        public void setForceUpdateVersion(String forceUpdateVersion) {
            this.forceUpdateVersion = forceUpdateVersion;
        }

        public String getForceUpdateVersionNo() {
            return forceUpdateVersionNo;
        }

        public void setForceUpdateVersionNo(String forceUpdateVersionNo) {
            this.forceUpdateVersionNo = forceUpdateVersionNo;
        }

        public boolean isNeedForceUpdate() {
            return needForceUpdate;
        }

        public void setNeedForceUpdate(boolean needForceUpdate) {
            this.needForceUpdate = needForceUpdate;
        }

        public String getDownloadURL() {
            return downloadURL;
        }

        public void setDownloadURL(String downloadURL) {
            this.downloadURL = downloadURL;
        }

        public boolean isHaveNewVersion() {
            return haveNewVersion;
        }

        public void setHaveNewVersion(boolean haveNewVersion) {
            this.haveNewVersion = haveNewVersion;
        }

        public boolean isUpdateDeny() {
            return updateDeny;
        }

        public void setUpdateDeny(boolean updateDeny) {
            this.updateDeny = updateDeny;
        }

        public String getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(String versionCode) {
            this.versionCode = versionCode;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public String getAppUrl() {
            return appUrl;
        }

        public void setAppUrl(String appUrl) {
            this.appUrl = appUrl;
        }

        public String getBuild() {
            return build;
        }

        public void setBuild(String build) {
            this.build = build;
        }

        public String getReleaseNote() {
            return releaseNote;
        }

        public void setReleaseNote(String releaseNote) {
            this.releaseNote = releaseNote;
        }
    }
}
