package one.group.oneapp;

import java.io.Serializable;

public class Wallet implements Serializable {
    private  int money;
    public Wallet(){
        this.money = 100000;
    }

    public int getMoney() {
        return money;
    }

    public void addMoney(int money) {
        this.money += money;
    }
    public void spendMoney(int money) {
        this.money -= money;
    }

    public void setMoney(int money) {this.money = money;}
}
