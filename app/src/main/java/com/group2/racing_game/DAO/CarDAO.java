package com.group2.racing_game.DAO;

import com.group2.racing_game.entity.Car;
import com.group2.racing_game.R;

import java.util.ArrayList;
import java.util.List;

public class CarDAO {
    private static CarDAO instance;
    private static List<Car> carList = new ArrayList<>();

    private CarDAO() {
        carList.add(new Car(1, "Xe lăn", R.drawable.xe_lan, 0, 14, 10));
        carList.add(new Car(2, "Xe đạp", R.drawable.xe_dap, 5, 10, 2.5));
        carList.add(new Car(3, "Mô tô", R.drawable.mo_to, 7, 8, 2.5));
        carList.add(new Car(4, "Lamborghini", R.drawable.lamborghini, 1, 15, 1.5));
        carList.add(new Car(5, "F1", R.drawable.f1, 5, 11, 1.5));
    }

    public static CarDAO getInstance() {
        if (instance == null) {
            instance = new CarDAO();
        }
        return instance;
    }

    public List<Car> getCarList() {
        return carList;
    }
}
