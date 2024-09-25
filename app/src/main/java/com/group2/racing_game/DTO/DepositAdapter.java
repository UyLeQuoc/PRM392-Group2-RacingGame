package com.group2.racing_game.DTO;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.group2.racing_game.R;
import com.group2.racing_game.entity.DepositInfor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

public class DepositAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<DepositInfor> list;

    public DepositAdapter(Context context, int layout, List<DepositInfor> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public List<DepositInfor> getList() {
        return list;
    }

    public void setList(List<DepositInfor> list) {
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
        return -1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);

        TextView txtAmount = (TextView) convertView.findViewById(R.id.txtAmount);
        TextView txtCreateAt = (TextView) convertView.findViewById(R.id.txtDatetime);

        DepositInfor item = list.get(position);

        txtAmount.setText(String.valueOf(item.getAmount()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        txtCreateAt.setText(item.getCreateAt().format(formatter));
        return convertView;
    }
}
