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

    public List<Currency> currs;
    private Context context;
    private OnRecyclerItemClickListener listener;

    public interface OnRecyclerItemClickListener {
        void onItemClickListener(String item, int position);
    }

    public MainRViewAdapter(List<Currency> list, Context context) {
        this.currs = list;
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
        viewHolder.abbName.setText(currs.get(i).base);
        viewHolder.fullName.setText(currs.get(i).longSname);
    }

    @Override
    public int getItemCount() {
        return currs.size();
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
//            if (listener != null) {
//                int position = getAdapterPosition();
//                listener.onItemClickListener(currs., position);
//            } else {
//                throw new RuntimeException("You must init OnRecyclerItemClickListener by calling " +
//                        "setListener() method.");
//            }
        }
    }
}

