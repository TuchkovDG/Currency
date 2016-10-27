package com.example.qwert.merger;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class CurrencyItemAdapter extends BaseAdapter {
    private ArrayList mData;
    private Context context;


    public CurrencyItemAdapter(Context context, Map<String, Double> map) {
        this.context = context;
        mData = new ArrayList();
        mData.addAll(map.entrySet());
    }

    private Context getContext() {
        return context;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Map.Entry<String, Double> getItem(int position) {
        return (Map.Entry) mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO implement you own logic with ID
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CurrencyItemAdapter.ViewHolder holder;
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            rowView = layoutInflater.inflate(R.layout.item_course, parent, false);

            holder = new ViewHolder();
            holder.currName = (TextView) rowView.findViewById(R.id.item_cur_name);
            holder.currDescription = (TextView) rowView.findViewById(R.id.item_cur_description);
            holder.course = (TextView) rowView.findViewById(R.id.item_course);
            holder.image = (ImageView) rowView.findViewById(R.id.item_imageView);

            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }

        Map.Entry<String, Double> item = getItem(position);
        String name = item.getKey();
        String course = String.valueOf(item.getValue());
        String sname = CurrenciesList.getInstance().getSname(name);

        holder.currName.setText(name);
        holder.currDescription.setText(sname);
        holder.course.setText(course);
        holder.image.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(),
                getContext().getResources().getIdentifier(name.toLowerCase(), "drawable", "com.example.qwert.merger")));

        return rowView;

//        Map.Entry<String, Double> item = getItem(position);
//
//        // TODO replace findViewById by ViewHolder
//        ((TextView) result.findViewById(android.R.id.text1)).setText(item.getKey());
//        ((TextView) result.findViewById(android.R.id.text2)).setText(item.getValue());
//
//        return result;
    }

    private static class ViewHolder {
        public TextView currName;
        public TextView currDescription;
        public TextView course;
        public ImageView image;
    }
}

//public class CurrencyItemAdapter extends ArrayAdapter<HashMap<String, Double>> {
//
//    public CurrencyItemAdapter(Context context, HashMap<String, Double> map) {
//        super(context, R.layout.item_course, map);
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        CurrencyItemAdapter.ViewHolder holder;
//        View rowView = convertView;
//
//        if (rowView == null) {
//            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//            rowView = layoutInflater.inflate(R.layout.item_course, parent, false);
//
//            holder = new ViewHolder();
//            holder.currName = (TextView) rowView.findViewById(R.id.item_cur_name);
//            holder.currDescription = (TextView) rowView.findViewById(R.id.item_cur_description);
//            holder.course = (TextView) rowView.findViewById(R.id.item_course);
//            holder.image = (ImageView) rowView.findViewById(R.id.item_imageView);
//
//            rowView.setTag(holder);
//        } else {
//            holder = (ViewHolder) rowView.getTag();
//        }
//
//        Currency curr = (Currency) getItem(position);
//
//        holder.currName.setText(curr.base);
//        holder.currDescription.setText(curr.sname);
//        holder.course.setText();
//        holder.image.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(),
//                getContext().getResources().getIdentifier(curr.base.toLowerCase(), "drawable", "com.example.qwert.merger")));
//
//        return rowView;
//    }
//
//    private static class ViewHolder {
//        public TextView currName;
//        public TextView currDescription;
//        public TextView course;
//        public ImageView image;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        LayoutInflater inflater = (LayoutInflater) getContext().
//                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        View rowView = inflater.inflate(R.layout.item_course, parent, false);
//        TextView name = (TextView) rowView.findViewById(R.id.item_cur_name);
//        TextView description = (TextView) rowView.findViewById(R.id.item_cur_description);
//        TextView course = (TextView) rowView.findViewById(R.id.item_course);
//        ImageView image = (ImageView) rowView.findViewById(R.id.item_imageView);
//
//
//        return rowView;
//    }
//
//}
