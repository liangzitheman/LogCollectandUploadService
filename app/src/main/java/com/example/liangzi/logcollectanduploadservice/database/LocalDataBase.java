package com.example.liangzi.logcollectanduploadservice.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.liangzi.logcollectanduploadservice.model.JankDataBean;
import com.example.liangzi.logcollectanduploadservice.model.LogDataBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liangzi on 2017/7/19.
 */

public class LocalDataBase {

    /**
     * 数据库名
     */
    public static final String DB_NAME = "DataBean.db";
    /**
     * 数据库版本
     */
    public static final int VERSION = 1;

    private static LocalDataBase localDataBase;
    private SQLiteDatabase db;

    /**
     * 将构造方法私有化
     */
    private LocalDataBase(Context context) {
        DataBaseOpenHelper dbHelper = new DataBaseOpenHelper(context,
                DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    /**
     * 获取LocalDataBase的实例。
     */
    public synchronized static LocalDataBase getInstance(Context context) {
        if (localDataBase == null) {
            localDataBase = new LocalDataBase(context);
        }
        return localDataBase;
    }

    /**
     * 将JackDataBean实例存储到数据库表JackDataBean。
     * 判断是否有重复的AppName和Casename，若没有则插入表格
     * 若有，则更新log数目+1
     */
    public int InsertJankDataBean(JankDataBean jankDataBean) {

        if (jankDataBean != null) {

            Cursor cursor = db.query("JankDataBean", null, "CaseName=? and AppName=?",
                    new String[]{jankDataBean.CASENAME, jankDataBean.APPNAME}, null, null, null);
            if (!cursor.moveToFirst()) {

                ContentValues values = new ContentValues();
                values.put("Product", jankDataBean.getProduct());
                values.put("Version", jankDataBean.getVersion());
                values.put("IMEI", jankDataBean.getIMEI());
                values.put("UserName", jankDataBean.getUSERNAME());
                values.put("TestType", jankDataBean.getTESTTYPE());
                values.put("CaseName", jankDataBean.getCASENAME());
                values.put("AppName", jankDataBean.getAPPNAME());
                values.put("Log", jankDataBean.getLog());
                values.put("Conclusion", jankDataBean.getRemark());
                values.put("Severity", jankDataBean.getSeverity());
                values.put("UploadTime", jankDataBean.getUploadTIME());

                db.insert("JankDataBean", null, values);

            } else {
//                while(cursor.moveToFirst()){
                //ContentValues values1 = new ContentValues();
                int JackData_Id = cursor.getInt(cursor.getColumnIndex("Id"));
                int Log = cursor.getInt(cursor.getColumnIndex("Log"));
                Log = Log + 1;
                //values1.put("Log",Log);
                //db.update("JackDataBean",values1,"CaseName=?,AppName=?", new String[] {CaseName,AppName});
                updateJankDataBean(Log, JackData_Id);
            }
            cursor.close();
        }

    }


    /**
     * 将LogDataBean实例存到数据库表LogDataBean。
     */
    public void InsertLogDataBean(LogDataBean logDataBean) {
        if (logDataBean != null) {
            ContentValues values = new ContentValues();
            values.put("JankData_Id", logDataBean.getJankData_ID());
            values.put("ProductandVersion", logDataBean.getProductandVersion());
            values.put("LogName", logDataBean.getLOGNAME());
            values.put("TestDate", logDataBean.getTestDate());
            values.put("LogPath", logDataBean.getLogPath());
            values.put("UploadType", logDataBean.getUploadType());


            db.insert("LogDataBean", null, values);
        }
    }

    /**
     * 从数据库表格JackDataBean读取所有信息。
     */
    public List<JankDataBean> loadJankDataBean() {
        List<JankDataBean> list = new ArrayList<JankDataBean>();
        Cursor cursor = db
                .query("JankDataBean", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                JankDataBean jankDataBean = new JankDataBean();
                jankDataBean.setID(cursor.getInt(cursor.getColumnIndex("Id")));
                jankDataBean.setProduct(cursor.getString(cursor
                        .getColumnIndex("Product")));
                jankDataBean.setVersion(cursor.getString(cursor
                        .getColumnIndex("Version")));
                jankDataBean.setIMEI(cursor.getString(cursor.getColumnIndex("IMEI")));
                jankDataBean.setUSERNAME(cursor.getString(cursor
                        .getColumnIndex("UserName")));
                jankDataBean.setTESTTYPE(cursor.getString(cursor
                        .getColumnIndex("TestType")));
                jankDataBean.setCASENAME(cursor.getString(cursor.getColumnIndex("CaseName")));
                jankDataBean.setAPPNAME(cursor.getString(cursor
                        .getColumnIndex("AppName")));
                jankDataBean.setLog(cursor.getInt(cursor
                        .getColumnIndex("Log")));
                jankDataBean.setConclusion(cursor.getString(cursor.getColumnIndex("Conclusion")));
                jankDataBean.setRemark(cursor.getString(cursor
                        .getColumnIndex("Remark")));
                jankDataBean.setSeverity(cursor.getString(cursor
                        .getColumnIndex("Severity")));
                jankDataBean.setUploadTIME(cursor.getString(cursor
                        .getColumnIndex("UploadTime")));

                list.add(jankDataBean);
            } while (cursor.moveToNext());
        }
        return list;
    }


    /**
     * 从数据库表格LogDataBean读取所有日志信息。
     */
    public List<LogDataBean> loadLogDataBean() {
        List<LogDataBean> list = new ArrayList<LogDataBean>();
        Cursor cursor = db
                .query("LogDataBean", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                LogDataBean logDataBean = new LogDataBean();
                logDataBean.setID(cursor.getInt(cursor.getColumnIndex("Id")));
                logDataBean.setJankData_ID(cursor.getInt(cursor.getColumnIndex("JankData_Id")));
                logDataBean.setProductandVersion(cursor.getString(cursor
                        .getColumnIndex("ProductandVersion")));
                logDataBean.setLOGNAME(cursor.getString(cursor
                        .getColumnIndex("LogName")));
                logDataBean.setTestDate(cursor.getString(cursor
                        .getColumnIndex("TestDate")));
                logDataBean.setLogPath(cursor.getString(cursor.getColumnIndex("LogPath")));
                logDataBean.setUploadType(cursor.getInt(cursor.getColumnIndex("UploadType")));

                list.add(logDataBean);
            } while (cursor.moveToNext());
        }
        return list;
    }



    /**
     * 更新数据库表格JackDataBean中的记录。
     * 更新发生在appname,casename均相同的情况下，此时，只需对Log+1得到    public void updateJackDataBean(int newLog,int updateRow){

     */

    public void updateJankDataBean(int newLog,int updateRow){

        ContentValues values = new ContentValues();
        values.put("Log",newLog);

        db.update("JankDataBean", values,"Id=?", new String[] { Integer.toString(updateRow) });
    }


//    /**
//     * 更新数据库表格LogDataBean中的记录。
//     */

//    public void updateLogDataBean(int upload_finished){
//        /**
//         * 根据upload_finished标志来更新数据
//         */
////        String delerow = Integer.toString(deleterow);
//        ContentValues values = new ContentValues();
//        values.put("UploadType",);
//        db.update("LogDataBean", "Id=?", new String[] { delerow });
//    }



    /**
     * 从数据库表格JankDataBean中根据Id删除某条记录。
     */

    public void deletefromJankDataBean(int deleterow){

        db.delete("JankDataBean", "Id=?", new String[] { Integer.toString(deleterow) });
    }


    /**
     * 从数据库表格LogDataBean中根据Id删除某条记录。
     */

    public void deletefromLogDataBean(int deleterow){

        db.delete("LogDataBean", "Id=?", new String[] { Integer.toString(deleterow) });
    }

}
