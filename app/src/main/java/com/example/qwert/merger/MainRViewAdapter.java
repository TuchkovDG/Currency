package com.example.qwert.merger;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samik on 25.10.2016.
 */

public class MainRViewAdapter extends RecyclerView.Adapter<MainRViewAdapter.ViewHolder> {

    public interface OnRecyclerItemClickListener {
        void onItemClickListener(String item, int position);
    }

    private List<ArrayList<String>> list;
    private Context context;
    private OnRecyclerItemClickListener listener;

    public MainRViewAdapter(List<ArrayList<String>> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.ma_list_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.fullName.setText(list.get(i).get(1));
        viewHolder.abbName.setText(list.get(i).get(0));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setListener(OnRecyclerItemClickListener listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView fullName;
        TextView abbName;
        ImageView image;

        ViewHolder(View item) {
            super(item);
            fullName = (TextView) item.findViewById(R.id.ma_fullCurrency_name);
            abbName = (TextView) item.findViewById(R.id.ma_abbCurrency_name);
            image = (ImageView) item.findViewById(R.id.ma_currency_image);
            item.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                int position = getAdapterPosition();
                listener.onItemClickListener(list.get(0).get(position), position);
            } else {
                throw new RuntimeException("You must init OnRecyclerItemClickListener by calling " +
                        "setListener() method.");
            }
        }
    }
}

