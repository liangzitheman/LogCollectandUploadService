package com.example.liangzi.logcollectanduploadservice.model;

/**
 * Created by GXL on 17-7-7.
 */

public class JankDataBean {
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
        this.Product = product;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        this.Version = version;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getTESTTYPE() {
        return TESTTYPE;
    }

    public void setTESTTYPE(String TESTTYPE) {
        this.TESTTYPE = TESTTYPE;
    }

    public String getCASENAME() {
        return CASENAME;
    }

    public void setCASENAME(String CASENAME) {
        this.CASENAME = CASENAME;
    }

    public String getAPPNAME() {
        return APPNAME;
    }

    public void setAPPNAME(String APPNAME) {
        this.APPNAME = APPNAME;
    }

    public int getLog() {
        return log;
    }

    public void setLog(int log) {
        this.log = log;
    }

    public String getConclusion() {
        return Conclusion;
    }

    public void setConclusion(String conclusion) {
        this.Conclusion = conclusion;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        this.Remark = remark;
    }

    public String getSeverity() {
        return Severity;
    }

    public void setSeverity(String severity) {
        this.Severity = severity;
    }

    public String getUploadTIME() {
        return UploadTIME;
    }

    public void setUploadTIME(String uploadTIME) {
        this.UploadTIME = uploadTIME;
    }


    /**
     * ID : 1
     * Product : MYA-AL10
     * Version : Maya-AL10C00B022
     * IMEI : 0123456789ABCDEF
     * USERNAME : FOT样机
     * TESTTYPE : 开发调试
     * CASENAME : CONTACTS_CONTACT_LAUNCH
     * APPNAME :
     * log : 1
     * Conclusion : null

     * Remark :
     * Severity : General
     * UploadTIME : 2017/6/8 16:22:35
     */
    public int ID;

    public String Product;

    public String Version;

    public String IMEI;

    public String USERNAME;

    public String TESTTYPE;

    public String CASENAME;

    public String APPNAME;

    public int log;

    public String Conclusion;

    public String Remark;

    public String Severity;

    public String UploadTIME;

//    public LogDataBean[] getLogDataBeanArray() {
//        return LogDataBeanArray;
//    }
//
//    public void setLogDataBeanArray(LogDataBean[] logDataBeanArray) {
//        LogDataBeanArray = logDataBeanArray;
//    }
//
//    public LogDataBean[] LogDataBeanArray;

    public JankDataBean() {
    }
}

