package com.group2.racing_game.DTO;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.group2.racing_game.R;
import com.group2.racing_game.entity.BetHistory;
import com.group2.racing_game.entity.DepositInfor;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class BetHistoryAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<BetHistory> itemList;

    public BetHistoryAdapter(Context context, int layout, List<BetHistory> itemList) {
        this.context = context;
        this.layout = layout;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);
        // anh sa tu lay out
        TextView txtRound = (TextView) convertView.findViewById(R.id.tvRoundInfo);
        TextView txtCash = (TextView) convertView.findViewById(R.id.tvCash);
        TextView txtResult = (TextView) convertView.findViewById(R.id.isWinOrLose);
        ImageView imgHinh = (ImageView) convertView.findViewById(R.id.imgProduct);

        // Gán giá trị cho các view từ đối tượng TraiCay
        BetHistory item = itemList.get(position);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //   String info = "Round:"+String.valueOf(item.getRound().getId())+" " ;
        txtRound.setText("Round: "+String.valueOf(item.getRound().getId()) +"");

        txtCash.setText("Bet: "+String.format("%.2f", item.getBetCash()));
        String result = item.isWin() ? "Won" : "Lose";
        txtResult.setText("Result: "+result);


        imgHinh.setImageResource(item.getCar().getIcon());

        return convertView;
    }
}
