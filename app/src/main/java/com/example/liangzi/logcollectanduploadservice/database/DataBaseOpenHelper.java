package com.example.liangzi.logcollectanduploadservice.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by liangzi on 2017/7/17.
 */

public class DataBaseOpenHelper extends SQLiteOpenHelper{

    public static final String CREATE_JANKDATABEAN = "create table JankDataBean ("
            + "Id integer primary key autoincrement, "
            +"Product text,"
            +"Version text,"
            +"IMEI text,"
            +"UserName text,"
            +"TestType text,"
            +"CaseName text,"
            +"AppName text,"
            +"Log integer,"
            +"Conclusion text,"
            +"Remark text,"
            +"Severity text,"
            +"UploadTime text)";


    public static final String CREATE_LOGDATABEAN = "create table LogDataBean ("
            + "Id integer primary key autoincrement, "
            +"JankData_Id integer,"
            +"ProductandVersion text,"
            +"LogName text,"
            +"TestDate text,"
            +"LogPath text,"
            +"UploadType integer)";

    private Context mcontext;

    public DataBaseOpenHelper(Context context, String name,
                              SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
        mcontext = context;
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_JANKDATABEAN);
        db.execSQL(CREATE_LOGDATABEAN);
    }

    public void onUpgrade(SQLiteDatabase db,int old_version,int new_version){
        switch (old_version){
            case 1:
                break;
            case 2:
                break;
            default:
                break;
        }


    }
}

