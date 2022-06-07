package com.example.ql_hoaqua;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "QL_HoaQua.db";

    public static final String TABLE_NAME = "hoaqua";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String LOAI = "loai";
    public static final String DVT = "dvt";
    public static final String DG = "don_gia";
    public static final String NOISX = "noi_sx";


    private static int VERSION = 1;
    private Context context;



    public Database(Context context) {
        super(context,DATABASE_NAME,null,VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_NAME + " ( "
                + ID +" integer primary key autoincrement , "
                + NAME +" text,"
                + LOAI +" text,"
                + DVT + " text,"
                + DG +" integer,"
                +NOISX+" text"
                +" )";

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


    public void addHQ(HoaQua hoaQua){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, hoaQua.getName());
        values.put(LOAI, hoaQua.getLoai());
        values.put(DVT, hoaQua.getDvt());
        values.put(DG, hoaQua.getDongia());
        values.put(NOISX, hoaQua.getNsx());

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public void updateHQ(HoaQua hoaQua){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, hoaQua.getName());
        values.put(LOAI, hoaQua.getLoai());
        values.put(DVT, hoaQua.getDvt());
        values.put(DG, hoaQua.getDongia());
        values.put(NOISX, hoaQua.getNsx());

        db.update(TABLE_NAME,values,ID+"=?",new String[]{String.valueOf(hoaQua.getId())});
        db.close();
    }

    public int delHQ(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        int b = db.delete(TABLE_NAME, ID+" = ?", new String[]{String.valueOf(id)});
        db.close();
        return b;
    }


    public List<HoaQua> getAllHQ(){
        List<HoaQua> listHQ = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(sql,null);

        if (cursor.moveToFirst()){
            do {
                HoaQua hoaQua = new HoaQua();
                hoaQua.setId(cursor.getInt(0));
                hoaQua.setName(cursor.getString(1));
                hoaQua.setLoai(cursor.getString(2));
                hoaQua.setDvt(cursor.getString(3));
                hoaQua.setDongia(cursor.getInt(4));
                hoaQua.setNsx(cursor.getString(5));
                listHQ.add(hoaQua);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return listHQ;
    }


    public HoaQua findById(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,new String[]{ID,NAME,LOAI,DVT,DG,NOISX},ID+"=?",new String[]{String.valueOf(id)},null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        HoaQua hoaQua = new HoaQua(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4),cursor.getString(5));
        cursor.close();
        db.close();
        return hoaQua;
    }

}
