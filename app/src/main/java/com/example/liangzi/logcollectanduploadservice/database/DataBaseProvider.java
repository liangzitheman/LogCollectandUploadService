package com.example.liangzi.logcollectanduploadservice.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;


/**
 * Created by liangzi on 2017/7/17.
 */


public class DataBaseProvider extends ContentProvider {

    public static final int JankDataBean_DIR = 0;
    public static final int JankDataBean_ITEM = 1;
    public static final int LogDataBean_DIR = 2;
    public static final int LogDataBean_ITEM = 3;


    public static final String AUTHORITY = "com.example.liangzi.logcollectanduploadservice.database.DataBaseProvider";
    private static UriMatcher uriMatcher;

    protected DataBaseOpenHelper dbHelper;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "JankDataBean", JankDataBean_DIR);
        uriMatcher.addURI(AUTHORITY, "JankDataBean/#", JankDataBean_ITEM);
        uriMatcher.addURI(AUTHORITY, "LogDataBean", LogDataBean_DIR);
        uriMatcher.addURI(AUTHORITY, "LogDataBean/#", LogDataBean_ITEM);
    }

    public boolean onCreate(){
        dbHelper = new DataBaseOpenHelper(getContext(),"DataBean.db",null,1);
        return  true;
    }

    public Cursor query(Uri uri, String [] projection, String selection, String [] selectionArgs, String sortOrder){
        //search for data
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)){
            case JankDataBean_DIR:
                cursor = db.query("JankDataBean",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case JankDataBean_ITEM:
                String JackData_Id = uri.getPathSegments().get(1);
                cursor = db.query("JankDataBean",projection,"Id=?",new String[] {JackData_Id},null,null,sortOrder);
                break;
            case LogDataBean_DIR:
                cursor = db.query("LogDataBean",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case LogDataBean_ITEM:
                String LogData_Id = uri.getPathSegments().get(1);
                cursor = db.query("LogDataBean",projection,"Id=?",new String[] {LogData_Id},null,null,sortOrder);
                break;
            default:
                break;

        }
        return cursor;

    }//end of query

    public Uri insert(Uri uri, ContentValues values){
        //add data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri urireturn = null;
        switch (uriMatcher.match(uri)){
            case JankDataBean_DIR:
                //判断表里是否已经存在相同的CaseName 和 AppName,如果已经存在，则字段Log+1,不存在，则加入一条新的记录
                //Long NewJackData_Id = db.insert("JackDataBean",null, values);
                String  CaseName = values.getAsString("CaseName");
                Log.d("databaseprovider",CaseName);
                String  AppName = values.getAsString("AppName");
                Log.d("databaseprovider",AppName);

                Cursor cursor = db.query("JankDataBean",null,"CaseName=? and AppName=?",
                        new String[] {CaseName,AppName},null,null,null);

                if (!cursor.moveToFirst()){
                    Log.d("databaseprovider","no same CaseName and AppName");
                    Long NewJackData_Id = db.insert("JankDataBean",null, values);
                    urireturn = Uri.parse("content://"+AUTHORITY+"/JankDataBean/"+NewJackData_Id);
                }else{
                    Log.d("databaseprovider","found same CaseName and AppName");
                    //while(cursor.moveToFirst()){
                        ContentValues values1 = new ContentValues();
                        //long NewJackData_Id = cursor.getInt(cursor.getColumnIndex("Id"));
                        int Log = cursor.getInt(cursor.getColumnIndex("Log"));
                        Log = Log + 1;
                        values1.put("Log",Log);
                        long NewJankData_Id = db.update("JankDataBean",values1,"CaseName=? and AppName=?", new String[] {CaseName,AppName});
                        urireturn = Uri.parse("content://"+AUTHORITY+"/JankDataBean/"+NewJankData_Id);
//
                    }
                    cursor.close();
                //}
                break;
            case LogDataBean_DIR:
                Long NewLogData_Id = db.insert("LogDataBean",null, values);
                urireturn = Uri.parse("content://"+AUTHORITY+"/LogDataBean/"+NewLogData_Id);
                break;
            default:
                break;
        }
        return urireturn;

    }//end of insert

    public int update(Uri uri,ContentValues values,String selection, String[] selectionArgs){
        //update data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int updaterow = 0;
        switch (uriMatcher.match(uri)){
            case JankDataBean_DIR:
                updaterow = db.update("JankDataBean",values,selection,selectionArgs);
                break;
            case JankDataBean_ITEM:
                String JackData_id = uri.getPathSegments().get(1);
                updaterow = db.update("JankDataBean",values,"Id=?",new String[] {JackData_id});
                break;
            case LogDataBean_DIR:
                updaterow = db.update("LogDataBean",values,selection,selectionArgs);
                break;
            case LogDataBean_ITEM:
                String LogData_id = uri.getPathSegments().get(1);
                updaterow = db.update("LogDataBean",values,"Id=?",new String[] {LogData_id});
                break;
            default:
                break;
        }
        return updaterow;

    }//end of update

    public int delete(Uri uri,String selection,String[] selectionArgs){
        //delete data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deleterow = 0;
        switch (uriMatcher.match(uri)){
            case JankDataBean_DIR:
                deleterow = db.delete("JankDataBean",selection,selectionArgs);
                break;
            case JankDataBean_ITEM:
                //String JankData_id = uri.getPathSegments().get(1);
                String JankData_id = uri.getLastPathSegment();

//                deleterow = db.delete("JankDataBean","Id=?",JankData_id);
                deleterow = db.delete("JankDataBean","Id=?",new String[] {JankData_id});
                break;
            case LogDataBean_DIR:
                deleterow = db.delete("LogDataBean",selection,selectionArgs);
                break;
            case LogDataBean_ITEM:
                String LogData_id = uri.getPathSegments().get(1);
                deleterow = db.delete("LogDataBean","Id=?",new String[] {LogData_id});
                break;
            default:
                break;
        }
        return deleterow;

    }//end of delete

    public String getType(Uri uri){
        switch (uriMatcher.match(uri)){
            case JankDataBean_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.liangzi.logcollectanduploadservice.DataBaseProvider";
            case JankDataBean_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.liangzi.logcollectanduploadservice.DataBaseProvider";
            case LogDataBean_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.liangzi.logcollectanduploadservice.DataBaseProvider";
            case LogDataBean_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.liangzi.logcollectanduploadservice.DataBaseProvider";
        }
        return null;

    }//end of gettype


}//end of class


