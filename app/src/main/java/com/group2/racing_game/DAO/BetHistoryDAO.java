package com.group2.racing_game.DAO;

import com.group2.racing_game.entity.BetHistory;
import com.group2.racing_game.entity.User;

import java.util.ArrayList;
import java.util.List;

public class BetHistoryDAO {
    private static BetHistoryDAO instance;
    private static List<BetHistory> betHistories = new ArrayList<>();

    private BetHistoryDAO() {

    }

    public static BetHistoryDAO getInstance() {
        if (instance == null) {
            instance = new BetHistoryDAO();
        }
        return instance;
    }


    public static List<BetHistory> getBetHistoriesByUserId(int UserId) {
        List<BetHistory> resultList = new ArrayList<>();
        for (int i=0; i<betHistories.size();i++){
            BetHistory current = betHistories.get(i);
            if(current.getUser().getId() == UserId){
                resultList.add(betHistories.get(i));
            }
        }
        return resultList;
    }

    public void addBetHistoryIntoCurrentList(List<BetHistory> _betHistories){
        for (BetHistory betH:_betHistories) {
            betHistories.add(betH);
        }
    }
}
