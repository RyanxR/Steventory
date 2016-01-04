package com.daafdroid.steventory;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleCursorAdapter;

public class Inventory extends ListActivity {
    DBHandler myDBHandler = new DBHandler(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SQLiteDatabase database;
        SimpleCursorAdapter cursorAdapter;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory);

        database = myDBHandler.getWritableDatabase();

        //Fill cursor with some data
        Cursor dataCursor = database.rawQuery("SELECT * FROM products", null);

        //Define table columns we want to use to populate our ListView, as defined in DBHandler class
        String[] columns = new String[] {
                //myDBHandler.ROW_ID,
                myDBHandler.ROW_PRODUCTNAME,
                myDBHandler.ROW_MANUFACTURER,
                myDBHandler.ROW_QUANTITY,
        };

        //the XML defined views to populate with our data (Defined in entry.xml)
        int[] to = new int[] {
                R.id.item_name,
                R.id.item_manufacturer,
                R.id.item_quantity
        };

        //Create SimpleCursorAdapter to populate ListView with database records
        cursorAdapter = new SimpleCursorAdapter(this, R.layout.items, dataCursor, columns, to, 0);

        setListAdapter(cursorAdapter);

        addItemtoList();
    }

    private void addItemtoList() {
        Intent i = getIntent();
        if(i.getBooleanExtra("adding",false)) {
            String name = i.getStringExtra("input_name");
            String manu = i.getStringExtra("input_manufacturer");
            String q = i.getStringExtra("input_quantity");
            String code = i.getStringExtra("input_barcode");

            if( !(name.matches("")) || !(manu.matches("")) || !(q.matches("")) || !(code.matches("")) ) {
                Product product = new Product(name, Integer.parseInt(code), Integer.parseInt(q), manu);
                myDBHandler.addProduct(product);
            }
            i.putExtra("adding", false);
        }
    }

    public void addNewProduct(View view) {
    //This method is called when button with id "btn_new_product" is clicked
        Intent i = new Intent(getBaseContext(),AddProduct.class);
        startActivity(i);
        //Toast.makeText(Inventory.this, "BUTTON NEW PRODUCT CLICKED", Toast.LENGTH_SHORT).show();
    }
}