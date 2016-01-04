package com.daafdroid.steventory;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class AddProduct extends Activity {

    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        AddInfoFromScan();
        addListenerOnButton();
    }

    public void addListenerOnButton() {

        imageButton = (ImageButton) findViewById(R.id.scanbutton);

        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(getBaseContext(), Scanning.class);
                startActivity(i);
            }

        });

    }

    public void addProduct(View view) {
        EditText name = (EditText)findViewById(R.id.input_productname);
        EditText manu = (EditText)findViewById(R.id.input_manufacturer);
        EditText q = (EditText)findViewById(R.id.input_quantity);
        EditText code= (EditText)findViewById(R.id.input_barcode);


        String nameS = name.getText().toString();
        String manuS = manu.getText().toString();
        String qS = q.getText().toString();
        String codeS = code.getText().toString();

        if( (nameS.matches("")) || (manuS.matches("")) || (qS.matches("")) || (codeS.matches("")) ) {
            Toast.makeText(AddProduct.this, "Please fill in all the fields!", Toast.LENGTH_SHORT).show();
        } else {
            Intent i = new Intent(this, Inventory.class);
            i.putExtra("adding", true);
            i.putExtra("input_name", nameS);
            i.putExtra("input_manufacturer", manuS);
            i.putExtra("input_quantity", qS);
            i.putExtra("input_barcode", codeS);
            startActivity(i);
        }
    }

    public void AddInfoFromScan() {
        Intent mIntent = getIntent();
        boolean scanned = mIntent.getBooleanExtra("Scanned", false);
        if(scanned) {
            EditText editText = (EditText)findViewById(R.id.input_barcode);
            editText.setText(mIntent.getIntExtra("Scanned_barcode", 0));
        }

    }
}
