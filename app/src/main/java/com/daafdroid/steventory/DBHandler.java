/**
 * Created by davinator on 12/13/2015.
 */

package com.daafdroid.steventory;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DB_inventory.db";
    private static final String TABLE_PRODUCTS = "products";

    public static final String ROW_ID = "_id";
    public static final String ROW_PRODUCTNAME = "productname";
    public static final String ROW_MANUFACTURER = "manufacturer";
    public static final String ROW_BARCODE = "barcode";
    public static final String ROW_QUANTITY = "quantity";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    //This method creates a new table "products" in our database if it doesn't already exist

          //Set up query
          String CREATE_PRODUCTS_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TABLE_PRODUCTS + "("
                    + ROW_ID + " INTEGER PRIMARY KEY,"
                    + ROW_MANUFACTURER + " TEXT,"
                    + ROW_PRODUCTNAME + " TEXT,"
                    + ROW_BARCODE + " INTEGER,"
                    + ROW_QUANTITY + " INTEGER"
                + ");";

        //Execute query
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    //This method is called when the database version is updated

        //Drop old table if it exists and call onCreate method to recreate it
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    public void addProduct(Product product) {
    //This method adds a new product record to our database

        ContentValues values = new ContentValues();

        //No need to supply product ID because it is auto-incremented
        values.put(ROW_PRODUCTNAME, product.getProductName());
        values.put(ROW_BARCODE, product.getBarcode());
        values.put(ROW_QUANTITY, product.getQuantity());
        values.put(ROW_MANUFACTURER, product.getManufacturer());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }
}