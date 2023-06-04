package org.example.containers;

import org.example.models.User;
import org.example.models.UsersList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainContainer extends JFrame implements ActionListener {

    private UsersList usersList;
    private Container container;
    private JLabel name, lastName, phone, address;
    private JTextField nameField, lastNameField, phoneField, addressField;
    private JButton add, delete, clearList, update;
    private JList<String> namesList;
    private DefaultListModel<String> model;
    private JScrollPane scrollList;

    public MainContainer(){
        usersList = new UsersList();
        initializeUI();
        setTitle("Usuarios");
        setSize(450, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        add(container);
    }

    private void initializeUI(){
        container = new Container();
        container.setLayout(null);

        initFields();
        initButtons();
        initScrollList();
    }

    private void initFields(){
        name = new JLabel();
        name.setText("Nombre:");
        name.setBounds(20, 30, 135, 23);
        nameField = new JTextField();
        nameField.setBounds(105, 30, 170, 23);
        container.add(name);
        container.add(nameField);

        lastName = new JLabel();
        lastName.setText("Apellido:");
        lastName.setBounds(20, 60, 135, 23);
        lastNameField = new JTextField();
        lastNameField.setBounds(105, 60, 170, 23);
        container.add(lastName);
        container.add(lastNameField);

        phone = new JLabel();
        phone.setText("Tel\u00e9fono:");
        phone.setBounds(20, 90, 135, 23);
        phoneField = new JTextField();
        phoneField.setBounds(105, 90, 170, 23);
        container.add(phone);
        container.add(phoneField);

        address = new JLabel();
        address.setText("Direcci\u00f3n:");
        address.setBounds(20, 120, 135, 23);
        addressField = new JTextField();
        addressField.setBounds(105, 120, 170, 23);
        container.add(address);
        container.add(addressField);
    }

    private void initButtons(){
        add = new JButton();
        add.setText("A\u00f1adir");
        add.setBounds(105, 150, 80, 23);
        add.addActionListener(this);
        container.add(add);

        update = new JButton();
        update.setText("Editar");
        update.setBounds(195, 150, 80, 23);
        update.addActionListener(this);
        container.add(update);

        delete = new JButton();
        delete.setText("Eliminar");
        delete.setBounds(20, 280, 80, 23);
        delete.addActionListener(this);
        container.add(delete);

        clearList = new JButton();
        clearList.setText("Borrar Lista");
        clearList.setBounds(120, 280, 120, 23);
        clearList.addActionListener(this);
        container.add(clearList);
    }

    private void initScrollList(){
        namesList = new JList<>();
        namesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        namesList.addListSelectionListener(listSelectionEvent -> {
            int index = namesList.getSelectedIndex();
            if(index >= 0){
                User selected = usersList.getUser(index);
                nameField.setText(selected.getName());
                lastNameField.setText(selected.getLastName());
                phoneField.setText(selected.getPhone());
                addressField.setText(selected.getAddress());
            }
        });
        model = new DefaultListModel<String>();

        scrollList = new JScrollPane();
        scrollList.setBounds(20, 190 ,220, 80);
        scrollList.setViewportView(namesList);
        container.add(scrollList);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == add) {
            addUser();
        }
        if (event.getSource() == delete) {
            deleteName(namesList.getSelectedIndex());
        }

        if (event.getSource() == update) {
            updateName(namesList.getSelectedIndex());
        }

        if (event.getSource() == clearList) {
            clearList();
        }
    }

    private User getFormValues(){
        User u = new User(nameField.getText(),
                lastNameField.getText(),
                phoneField.getText(),
                addressField.getText());
        return u;
    }

    private String getFormValuesAsString(){
        String element = nameField.getText() + "-" + lastNameField.getText() +
                "-" + phoneField.getText() + "-" + addressField. getText();
        return element;
    }

    private void addUser(){
        User p = getFormValues();
        if(!usersList.validateExists(p.getName(), p.getLastName())){
            usersList.addUser(p);

            String element = nameField.getText() + "-" + lastNameField.getText() +
                    "-" + phoneField.getText() + "-" + addressField. getText();
            model.addElement(element);
            namesList.setModel(model);

            nameField.setText("");
            lastNameField.setText("");
            phoneField.setText("");
            addressField.setText("");
        }else{
            JOptionPane.showMessageDialog(null,
                    "Ya existe un usuario con este mismo nombre y apellido","Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteName(int index){
        if (index >= 0) {
            model.removeElementAt(index);
            usersList.deleteUser(index);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Debe seleccionar un usuario","Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateName(int index){
        if (index >= 0) {
            User p = getFormValues();
            model.setElementAt(getFormValuesAsString(), index);
            usersList.updateUserAt(p, index);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Debe seleccionar un usuario","Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearList(){
        usersList.clearList();
        model.clear();
    }
}
