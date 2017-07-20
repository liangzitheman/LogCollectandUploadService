package com.example.liangzi.logcollectanduploadservice.model;

/**
 * Created by GXL on 17-7-7.
 */
public class LogDataBean {
    /**
     * ID : 1
     * JankData_ID : 1
     * ProductandVersion : MYA-AL10Maya-AL10C00B022
     * LOGNAME : MYA-AL10_Maya-AL10C00B022_0123456789ABCDEF_20170608161651_jank_cnt4.zip
     * TestDate : 2017/6/8 16:22:35
     * LogPath:log文件存放的地址
     * UploadType:代表文件是否上传的标志，0未上传，1上传
     */
    public int ID;
    public int JankData_ID;
    public String ProductandVersion;
    public String LOGNAME;
    public String TestDate;
    public String LogPath;
    public int UploadType;

    public LogDataBean(){

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getJankData_ID() {
        return JankData_ID;
    }

    public void setJankData_ID(int jankData_ID) {
        this.JankData_ID = jankData_ID;
    }

    public String getProductandVersion() {
        return ProductandVersion;
    }

    public void setProductandVersion(String productandVersion) {
        this.ProductandVersion = productandVersion;
    }

    public String getLOGNAME() {
        return LOGNAME;
    }

    public void setLOGNAME(String LOGNAME) {
        this.LOGNAME = LOGNAME;
    }

    public String getTestDate() {
        return TestDate;
    }

    public void setTestDate(String testDate) {
        this.TestDate = testDate;
    }

    public String getLogPath(){
        return LogPath;
    }

    public void setLogPath(String logPath){
        this.LogPath = logPath;
    }

    public int getUploadType(){
        return UploadType;
    }

    public void setUploadType(int uploadType){
        this.UploadType = uploadType;
    }

}

