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
    Context context;

    ArrayList<ModelPropert> list;

    public PropertListAdapter(Context context, ArrayList<ModelPropert> list) {
        this.context = context;
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
        ViewHolder holder = null;
        if(row == null){
            LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.propert_item,parent,false);
            holder = new ViewHolder();
            holder.title = (TextView)row.findViewById(R.id.pi_title);
            holder.fee =(TextView)row.findViewById(R.id.pi_fee);
            holder.date =(TextView)row.findViewById(R.id.pi_date);
            holder.type =(TextView)row.findViewById(R.id.pi_tyoe);
            holder.imageView =(ImageView)row.findViewById(R.id.pi_image);
            row.setTag(holder);

        }
        else{
            holder =(ViewHolder)row.getTag();
        }
        ModelPropert modelPropert = list.get(position);
        holder.title.setText(modelPropert.getTitle());
        holder.fee.setText(modelPropert.getFee());
        holder.date.setText(modelPropert.getDate());
        byte[] propetImage = modelPropert.getImages();
        Bitmap bitmap = BitmapFactory.decodeByteArray(propetImage,0,propetImage.length);
        holder.imageView.setImageBitmap(bitmap);
        return row;
    }
}
