package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PropertListAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<ModelPropert> list;

    public PropertListAdapter(Context context, int layout, ArrayList<ModelPropert> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private class ViewHolder{
        ImageView imageView;
        TextView title,fee,date,type;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder holder = new ViewHolder();
        if(row == null){
            LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);
            holder.title = (TextView)row.findViewById(R.id.pi_title);
            holder.fee =(TextView)row.findViewById(R.id.pi_fee);
            holder.date =(TextView)row.findViewById(R.id.pi_date);
            holder.type =(TextView)row.findViewById(R.id.pi_tyoe);
            holder.imageView =(ImageView)row.findViewById(R.id.pi_image);

        }
        else{
            holder =(ViewHolder)row.getTag();
        }
        ModelPropert modelPropert = list.get(position);
        holder.title.setText(modelPropert.getTitle());
        holder.fee.setText(modelPropert.getFee());
        holder.date.setText(modelPropert.getDate().toString());
        byte[] propetImage = modelPropert.getImages();
        Bitmap bitmap = BitmapFactory.decodeByteArray(propetImage,0,propetImage.length);
        holder.imageView.setImageBitmap(bitmap);




        return row;
    }
}
