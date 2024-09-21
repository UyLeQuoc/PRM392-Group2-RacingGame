package com.group2.racing_game.DAO;

import com.group2.racing_game.entity.User;

import java.util.List;

public class UserDAO {
    private static UserDAO instance;
    private static List<User> userList;

    private UserDAO() {
        userList.add(new User(1, "uydev", "123456", 100.0));
        userList.add(new User(2, "namtruong", "123456", 100.0));
        userList.add(new User(3, "giahuan", "123456", 100.0));
        userList.add(new User(4, "nguyenvu", "123456", 100.0));
        userList.add(new User(5, "quangbui", "123456", 100.0));
        userList.add(new User(6, "minhtien", "123456", 100.0));
    }

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    private User S(String username, String password){
        for (int i=0; i<userList.size();i++){
            User currentUser = userList.get(i);
            if(currentUser.getUsername().contains(username) && currentUser.getPassword().contains(password)){
                return currentUser;
            }
        }
        return null;
    }

}
