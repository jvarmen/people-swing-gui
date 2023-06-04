package org.example.models;

import java.util.Vector;

public class UsersList {
    Vector<User> usersList;

    public UsersList(){
        usersList = new Vector();
    }

    public User getUser(int i){
        return usersList.get(i);
    }

    public void updateUserAt(User u, int i){
        usersList.setElementAt(u, i);
    }

    public void addUser(User u){
        usersList.add(u);
    }

    public void deleteUser(int i){
        usersList.removeElementAt(i);
    }

    public void clearList(){
        usersList.removeAllElements();
    }

    public boolean validateExists(String name, String lastName){
        var result = usersList.stream().anyMatch(
                s -> (s.getName().equals(name) && s.getLastName().equals(lastName))
        );
        return result;
    }
}
