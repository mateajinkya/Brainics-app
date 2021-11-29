package com.example.brainics;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.brainics.schedulerContract.*;

import androidx.annotation.Nullable;

public class schedulerDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "schedulerlist.db";
    public static final int DATABASE_VERSION = 1;

    public schedulerDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_SCHEDULER_TABLE = "CREATE TABLE " +
                schedulerEntry.TABLE_NAME + " (" +
                schedulerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                schedulerEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                schedulerEntry.COLUMN_AMOUNT + " INTEGER NOT NULL, " +
                schedulerEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";
        db.execSQL(SQL_CREATE_SCHEDULER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + schedulerEntry.TABLE_NAME);
        onCreate(db);

    }
}
