package com.example.qwert.merger;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adapter extends ArrayAdapter {

    public Adapter(Context context, List<Currency> str) {
        super(context, R.layout.list_item, str);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            rowView = layoutInflater.inflate(R.layout.list_item, parent, false);

            holder = new ViewHolder();
            holder.currName = (TextView) rowView.findViewById(R.id.li_currency_name);
            holder.currLongName = (TextView) rowView.findViewById(R.id.li_currency_long_name);
            holder.currImage = (ImageView) rowView.findViewById(R.id.li_currency_image);

            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }

        Currency curr = (Currency) getItem(position);

        holder.currName.setText(curr.base);
        holder.currLongName.setText(curr.sname);
        holder.currImage.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(),
                getContext().getResources().getIdentifier(curr.base.toLowerCase(), "drawable", "com.example.qwert.merger")));

        return rowView;
    }

    private static class ViewHolder {
        public TextView currName;
        public TextView currLongName;
        public ImageView currImage;
    }
}
