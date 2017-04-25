package demo.example.com.ecommerce;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 4/25/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "mycart";
    public static final int VERSION = 1;
    public static final String ID = "id";
    public static final String IMAGE = "itemImage";
    public static final String ITEMNAME = "itemName";
    public static final String ITEMPRICE = "itemPrice";
    public static final String ITEMDESC = "itemDescription";
    public static final String TABLENAME = "carttable";


    public DBHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "create table " + TABLENAME + "(" + ID + " integer primary key autoincrement," + IMAGE + " text,"
                + ITEMNAME + " text," + ITEMPRICE + " text," + ITEMDESC + " text)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertData(Cart_getset cart_getset) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ID,cart_getset.getID());
        Log.e("========Image======", cart_getset.getItemImage());
        values.put(IMAGE, cart_getset.getItemImage());
        Log.e("========Image======", cart_getset.getItemImage());

        values.put(ITEMNAME, cart_getset.getItemName());
        values.put(ITEMPRICE, cart_getset.getItemPrice());
        values.put(ITEMDESC, cart_getset.getItemDesc());

        database.insert(TABLENAME, ID, values);
        database.close();

    }

    public List<Cart_getset> show (){
        ArrayList<Cart_getset> arrayList  = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String column[] = {ID, IMAGE, ITEMNAME, ITEMPRICE, ITEMDESC};

        Cursor cursor = database.query(TABLENAME, column, null, null, null, null, null);

        while (cursor.moveToNext()){

            int id = cursor.getInt(0);
            String img = cursor.getString(1);
            String name = cursor.getString(2);
            String price= cursor.getString(3);
            String desc= cursor.getString(4);

            Cart_getset cart_getset  = new Cart_getset();
            cart_getset.setID(id);
            cart_getset.setItemImage(img);
            cart_getset.setItemName(name);
            cart_getset.setItemPrice(price);
            cart_getset.setItemDesc(desc);

            arrayList.add(cart_getset);
        }

//        Log.d("ArrayList =++++++", String.valueOf(arrayList));

        return arrayList;
    }

    public void deletedata(int id) {
        SQLiteDatabase db = getWritableDatabase();
        Log.d("id+++", String.valueOf(id));
        String where = ID + "=" + id;
        db.delete(TABLENAME, where, null);
    }
}
