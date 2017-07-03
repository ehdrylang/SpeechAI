package com.example.ambidext.speechai;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by PUREUM on 2017-07-01.
 */

public class MyDBHelper extends SQLiteOpenHelper {
    Context context;
    private static final String DB_NAME="schedule2.db";
    private static final int DATABASE_VERSION = 1;

    public MyDBHelper(Context context) {
        super(context, DB_NAME, null,DATABASE_VERSION );
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE schedule("+
                "_id INTEGER NOT NULL PRIMARY KEY,"+
                "title text NULL,"+
                "location text NULL,"+
                "datetime DATETIME NULL"+
                ");";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS word");

        onCreate(db);
    }
}