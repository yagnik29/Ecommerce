package demo.example.com.ecommerce;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Yash on 3/31/2017.
 */

public class GridAdapter extends BaseAdapter {

    String [] itemNameList;
    Context context;
    int [] itemImage;
    private static LayoutInflater inflater=null;

    public GridAdapter (Context context, String [] itemNameList, int[] itemImage){
        this.itemNameList = itemNameList;
        this.itemImage = itemImage;
        this.context = context;
        inflater = (LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return itemNameList.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public  class Holder{
        TextView gridtextView;
        ImageView  gridimageView;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Holder holder = new Holder();

        View rawView;
        rawView = inflater.inflate(R.layout.customgrid_layout,null);
        holder.gridtextView= (TextView) rawView.findViewById(R.id.gridText);
        holder.gridimageView = (ImageView) rawView.findViewById(R.id.gridImage);

        holder.gridtextView.setText(itemNameList[i]);
        holder.gridimageView.setImageResource(itemImage[i]);

        rawView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "This is what you clicked", Toast.LENGTH_SHORT).show();
            }
        });
        return rawView;
    }
}
