package com.group2.racing_game.DAO;

import com.group2.racing_game.entity.DepositInfor;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class DepositInforDAO {
    private static ArrayList<DepositInfor> list = new ArrayList<>();

    public static ArrayList<DepositInfor> GetDeposits(){
        int userId = UserDAO.getCurrentUser().getId();
        return new ArrayList<DepositInfor>(list.stream().filter(x->x.getUserId() == userId).collect(Collectors.toList()));
    }

    public static void Add(DepositInfor infor){
        list.add(infor);
    }
}
