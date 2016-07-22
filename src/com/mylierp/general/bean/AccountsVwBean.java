/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mylierp.general.bean;

/**
 *
 * @author Administrator
 */
public class AccountsVwBean {
    private String account_code;
    private String account_name;
    private String balance_dr;
    private String balance_cr;
    private String current_balance_dr;
    private String current_balance_cr;
    private String final_balance_dr;
    private String final_balance_cr;

    public String getAccount_code() {
        return account_code;
    }

    public void setAccount_code(String account_id) {
        this.account_code = account_id;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String Account_name) {
        this.account_name = Account_name;
    }

    public String getBalance_dr() {
        return balance_dr;
    }

    public void setBalance_dr(String balance_dr) {
        this.balance_dr = balance_dr;
    }

    public String getBalance_cr() {
        return balance_cr;
    }

    public void setBalance_cr(String balance_cr) {
        this.balance_cr = balance_cr;
    }

    public String getCurrent_balance_dr() {
        return current_balance_dr;
    }

    public void setCurrent_balance_dr(String current_balance_dr) {
        this.current_balance_dr = current_balance_dr;
    }

    public String getCurrent_balance_cr() {
        return current_balance_cr;
    }

    public void setCurrent_balance_cr(String current_balance_cr) {
        this.current_balance_cr = current_balance_cr;
    }

    public String getFinal_balance_dr() {
        return final_balance_dr;
    }

    public void setFinal_balance_dr(String final_balance_dr) {
        this.final_balance_dr = final_balance_dr;
    }

    public String getFinal_balance_cr() {
        return final_balance_cr;
    }

    public void setFinal_balance_cr(String final_balance_cr) {
        this.final_balance_cr = final_balance_cr;
    }
    
    
}
