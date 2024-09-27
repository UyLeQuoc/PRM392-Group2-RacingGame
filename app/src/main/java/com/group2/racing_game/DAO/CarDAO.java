package com.group2.racing_game.DAO;

import com.group2.racing_game.entity.Car;
import com.group2.racing_game.R;

import java.util.ArrayList;
import java.util.List;

public class CarDAO {
    private static CarDAO instance;
    private static List<Car> carList = new ArrayList<>();

    private CarDAO() {
        carList.add(new Car(1, "Xe lăn", R.drawable.seekbar_thumb, 1, 5, 1));
        carList.add(new Car(2, "Xe đạp", R.drawable.seekbar_thumb, 0.5, 5.5, 0.8));
        carList.add(new Car(3, "Mô tô", R.drawable.seekbar_thumb, -3, 9, 1.2));
        carList.add(new Car(4, "Lamborghini", R.drawable.seekbar_thumb, 3, 3.5, 0.2));
        carList.add(new Car(5, "F1", R.drawable.seekbar_thumb, -50, 50, 0.2));
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
