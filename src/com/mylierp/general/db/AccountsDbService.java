package com.mylierp.general.db;

import com.mylierp.general.bean.AccountsBean;
import com.mylierp.general.bean.AccountsVwBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class AccountsDbService {

    private Connection conn = null;
    PreparedStatement psInsertAccount;

    public AccountsDbService() {
        conn = ConnectionManager.getConnection();
    }

    public AccountsBean[] selectAll() {
        try {
            AccountsBean[] arr;
            ArrayList<AccountsBean> list = new ArrayList<>();
            AccountsBean item;
            Statement stmnt = conn.createStatement();
            ResultSet resultSet = stmnt.executeQuery("SELECT * from gen_accounts");
            while (resultSet.next()) {
                item = new AccountsBean();
                item.setAccount_code(resultSet.getString("account_code"));
                item.setAccount_name(resultSet.getString("account_name"));
                item.setIs_group(resultSet.getBoolean("is_group"));
                item.setGroup_code(resultSet.getString("group_code"));
                list.add(item);
            }
            arr = new AccountsBean[list.size()];
            list.toArray(arr);
            return arr;
        } catch (SQLException ex) {
            return null;
        }
    }

    public String sumColumn(String column) {
        Statement stmnt;
        String s = "";
        int ss = 0;
        try {
            stmnt = conn.createStatement();
            ResultSet resultSet = stmnt.executeQuery("SELECT " + column + " from vw_gen_accounts");
            while (resultSet.next()) {
                ss += Integer.parseInt(resultSet.getString(column));
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountsDbService.class.getName()).log(Level.SEVERE, null, ex);
        }
        s = Integer.toString(ss);
        return s;
    }

    public AccountsVwBean[] selectVwAll() {
        try {
            AccountsVwBean[] arr;
            ArrayList<AccountsVwBean> list = new ArrayList<>();
            AccountsVwBean item;
            Statement stmnt = conn.createStatement();
            ResultSet resultSet = stmnt.executeQuery("SELECT * from vw_gen_accounts");
            while (resultSet.next()) {
                item = new AccountsVwBean();
                item.setAccount_code(resultSet.getString("account_code"));
                item.setAccount_name(resultSet.getString("account_name"));
                item.setBalance_dr(resultSet.getString("balance_dr"));
                item.setBalance_cr(resultSet.getString("balance_cr"));
                item.setCurrent_balance_dr(resultSet.getString("current_balance_dr"));
                item.setCurrent_balance_cr(resultSet.getString("current_balance_cr"));
                item.setFinal_balance_dr(resultSet.getString("final_balance_dr"));
                item.setFinal_balance_cr(resultSet.getString("final_balance_cr"));
                list.add(item);
            }
            arr = new AccountsVwBean[list.size()];
            list.toArray(arr);
            return arr;
        } catch (SQLException ex) {
            return null;
        }
    }

    public void insertAccount(AccountsBean account) {
        try {
            psInsertAccount = conn.prepareStatement(
                    "INSERT INTO gen_accounts ( account_code, account_name, group_code, group_name, is_group ) "
                    + "VALUES ( ? , ? , ? , ? , ? )");
            psInsertAccount.setString(1, account.getAccount_code());
            psInsertAccount.setString(2, account.getAccount_name());
            psInsertAccount.setString(3, account.getGroup_code());
            psInsertAccount.setString(4, account.getGroup_name());
            psInsertAccount.setBoolean(5, account.getIs_group());
            psInsertAccount.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
