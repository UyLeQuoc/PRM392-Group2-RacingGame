package com.group2.racing_game.DAO;

import com.group2.racing_game.entity.Car;
import com.group2.racing_game.R;

import java.util.ArrayList;
import java.util.List;

public class CarDAO {
    private static CarDAO instance;
    private static List<Car> carList = new ArrayList<>();

    private CarDAO() {
        carList.add(new Car(1, "Xe lăn", R.drawable.xe_lan, 5, 10, 5));
        carList.add(new Car(2, "Xe đạp", R.drawable.xe_dap, 0.5, 5.5, 4));
        carList.add(new Car(3, "Mô tô", R.drawable.mo_to, -3, 9, 6));
        carList.add(new Car(4, "Lamborghini", R.drawable.lamborghini, 3, 3.5, 1));
        carList.add(new Car(5, "F1", R.drawable.f1, 10, 50, 1.5));
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
