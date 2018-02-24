package jjto.com.workportal.mvp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by jhon on 21/02/2018.
 */

public class Storage extends SQLiteOpenHelper {

    private static final String TAG = Storage.class.getSimpleName();

    @Inject
    public Storage(Context context){
        super(context, "users_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(CREATE_TABLE);
        } catch (SQLException e){
            Log.d(TAG, e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void addUser(Users userResponse){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PROCESSID, userResponse.getProcessId());
        values.put(PROCCESS, userResponse.getProcess());
        values.put(ACTIVITYID, userResponse.getActivityId());
        values.put(ACTIVITY, userResponse.getActivity());
        values.put(REQUESTDATE, userResponse.getRequestDate());
        values.put(EMPLOYEE, userResponse.getEmployee());
        values.put(BEGINDATE, userResponse.getBeginDate());
        values.put(ENDDATE, userResponse.getEndDate());
        values.put(LASTVACATIONON, userResponse.getLastVacationOn());
        values.put(APPROVED, userResponse.getApproved());

        try {
            db.insert(TABLE_NAME, null, values);
        } catch (SQLException e){
            Log.d(TAG, e.getMessage());
        }

        db.close();

    }

    public Users getSavedUsers(){
        Users userResponse = new Users();
        SQLiteDatabase db = this.getWritableDatabase();

        try{
            Cursor cursor = db.rawQuery(SELECT_QUERY, null);
            if (cursor != null){
                if (cursor.getCount() > 0){
                    if (cursor.moveToFirst()){
                        do {
                            Users user = new Users();
                            user.setProcessId(cursor.getString(cursor.getColumnIndex(PROCESSID)));
                            user.setProcess(cursor.getString(cursor.getColumnIndex(PROCCESS)));
                            user.setActivityId(cursor.getString(cursor.getColumnIndex(ACTIVITYID)));
                            user.setActivity(cursor.getString(cursor.getColumnIndex(ACTIVITY)));
                            user.setRequestDate(cursor.getString(cursor.getColumnIndex(REQUESTDATE)));
                            user.setEmployee(cursor.getString(cursor.getColumnIndex(EMPLOYEE)));
                            user.setBeginDate(cursor.getString(cursor.getColumnIndex(BEGINDATE)));
                            user.setEndDate(cursor.getString(cursor.getColumnIndex(ENDDATE)));
                            user.setLastVacationOn(cursor.getString(cursor.getColumnIndex(LASTVACATIONON)));
                            user.setApproved(cursor.getString(cursor.getColumnIndex(APPROVED)));
                        } while (cursor.moveToNext());
                    }
                }
            }
        } catch (SQLException e){
            Log.d(TAG, e.getMessage());
        }
        return userResponse;
    }

    private static final String PROCESSID = "processId";
    private static final String PROCCESS = "process";
    private static final String ACTIVITYID = "activityId";
    private static final String ACTIVITY = "activity";
    private static final String REQUESTDATE = "requestDate";
    private static final String EMPLOYEE = "employee";
    private static final String BEGINDATE = "beginDate";
    private static final String ENDDATE = "endDate";
    private static final String LASTVACATIONON = "lastVacationOn";
    private static final String APPROVED = "approved";

    private static final String TABLE_NAME = "users";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private static final String SELECT_QUERY = "SELECT * FROM " + TABLE_NAME;

    public static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" +
            ACTIVITYID + " text not null," +
            PROCESSID + " text not null," +
            PROCCESS + " text not null," +
            ACTIVITY + " text not null," +
            REQUESTDATE + " text not null," +
            EMPLOYEE + " text not null," +
            BEGINDATE + " text not null," +
            ENDDATE + " text not null," +
            LASTVACATIONON + " text not null," +
            APPROVED + " text not null)";

}
