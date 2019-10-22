package com.gz.xhb_zhongtie.MVP.Model.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zdj on 2018/6/22.
 */
public class PsListInfo implements Serializable{


    /**
     * ok : true
     * data : [{"pscode":"131182000001","psname":"深州市ABC","psaddress":"A乡B村","corporate":"","linkman":"","mobile":""},{"pscode":"131182000002","psname":"测试企业1","psaddress":"深州市A村","corporate":"","linkman":"","mobile":""}]
     */

    private boolean ok;
    private List<DataBean> data;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * pscode : 131182000001
         * psname : 深州市ABC
         * psaddress : A乡B村
         * corporate :
         * linkman :
         * mobile :
         */

        private String pscode;
        private String psname;
        private String psaddress;
        private String corporate;
        private String linkman;
        private String mobile;

        public String getPscode() {
            return pscode;
        }

        public void setPscode(String pscode) {
            this.pscode = pscode;
        }

        public String getPsname() {
            return psname;
        }

        public void setPsname(String psname) {
            this.psname = psname;
        }

        public String getPsaddress() {
            return psaddress;
        }

        public void setPsaddress(String psaddress) {
            this.psaddress = psaddress;
        }

        public String getCorporate() {
            return corporate;
        }

        public void setCorporate(String corporate) {
            this.corporate = corporate;
        }

        public String getLinkman() {
            return linkman;
        }

        public void setLinkman(String linkman) {
            this.linkman = linkman;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
