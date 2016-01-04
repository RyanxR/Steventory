/**
 * Created by davinator on 12/13/2015.
 */

package com.daafdroid.steventory;

public class Product {

    //private int id; //id is only needed in DB not in class ?
    private String productName;
    private long barcode;
    private int quantity;
    private String manufacturer;

    //Constructor method
    public Product(String productName, long barcode, int quantity, String manufacturer) {
        //this.id = id;
        this.productName = productName;
        this.barcode = barcode;
        this.quantity = quantity;
        this.manufacturer = manufacturer;
    }

   //public int getID() { return this.id; }

    public void setProductName(String productName) { this.productName = productName; }
    public String getProductName() { return this.productName; }

    public String getManufacturer() { return this.manufacturer; }
    public long getBarcode() { return this.barcode; }

    public int getQuantity() { return this.quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
