package com.group2.racing_game.DAO;

import com.group2.racing_game.DTO.BetDTO;
import com.group2.racing_game.entity.BetHistory;
import com.group2.racing_game.entity.Car;
import com.group2.racing_game.entity.Round;
import com.group2.racing_game.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoundDAO {
    private static RoundDAO instance;
    private static List<Round> roundList = new ArrayList<>();
    private static BetHistoryDAO betHistoryDAO = BetHistoryDAO.getInstance();

    private RoundDAO() {

    }

    public static RoundDAO getInstance() {
        if (instance == null) {
            instance = new RoundDAO();
        }
        return instance;
    }

    public static void DoneBetting(User User, List<BetDTO> betDTOS, Car carWinId){
        //Logic
        Round newRound = new Round(roundList.size()+1, null);
        //Xe nao win => GetCar
        Car carWin = null;
        // Set New Value
        newRound.setCarWin(carWin);
        //Add vo bet History
        List<BetHistory> betHistories = new ArrayList<>();
        for(BetDTO betDTO: betDTOS){
            boolean isWin = carWinId.getId() == betDTO.getCar().getId();
            Random random = new Random();
            int newId = random.nextInt(1000);
            BetHistory betHistory = new BetHistory(newId, newRound, User, betDTO.getCar(), betDTO.getCash(), isWin);
            betHistories.add(betHistory);
        }
        betHistoryDAO.addBetHistoryIntoCurrentList(betHistories);
    }
}
