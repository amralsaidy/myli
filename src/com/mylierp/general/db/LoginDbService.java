/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mylierp.general.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mylierp.general.bean.AccountsBean;
import com.mylierp.system.bean.UsersBean;

/**
 *
 * @author Administrator
 */
public class LoginDbService {

    private final Connection conn;

    public LoginDbService() {
        conn = ConnectionManager.getConnection();
    }

    public UsersBean[] selectUsers() {
        UsersBean item;
        ArrayList<UsersBean> list = new ArrayList<>();
        try {
            Statement stmnt = conn.createStatement();
            ResultSet resultSet = stmnt.executeQuery("SELECT * from sys_users");
            while (resultSet.next()) {
                item = new UsersBean();
                item.setUser_id(resultSet.getString("user_id"));
                item.setUser_name(resultSet.getString("user_name"));
                item.setUser_password(resultSet.getString("user_password"));
                list.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDbService.class.getName()).log(Level.SEVERE, null, ex);
        }
        UsersBean[] arr = new UsersBean[list.size()];
        list.toArray(arr);
        return arr;
    }

    public boolean isUserExist(String userName) {
        boolean bool = false;
        try {
            Statement stmnt = conn.createStatement();
            ResultSet resultSet = stmnt.executeQuery("SELECT * FROM sys_users WHERE user_name = '"+ userName +"'");
            while (resultSet.next()) {
            if (userName.equals(resultSet.getString("user_name"))) {
                bool = true;
            } else {
                bool = false;
            }
        }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDbService.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return bool;
    }
    
    public boolean isCorrectPassword(String userPassword){
                boolean bool = false;
        try {
            Statement stmnt = conn.createStatement();
            ResultSet resultSet = stmnt.executeQuery("SELECT * FROM sys_users WHERE user_password = '"+ userPassword +"'");
            while (resultSet.next()) {
            if (userPassword.equals(resultSet.getString("user_password"))) {
                bool = true;
            } else {
                bool = false;
            }
        }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDbService.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return bool;
    }
}
