package one.group.oneapp;

import java.io.Serializable;

public abstract class AbstractUpgrade implements Serializable
{
    protected double baseCost;
    protected int level;
    protected int lockValue;
    protected String name;
    protected String description;
    protected boolean locked;


    AbstractUpgrade(double baseCost, String name, String description) {
        this.baseCost = baseCost;
        this.level = 1;
        this.name = name;
        this.description = description;
        this.lockValue = -1;
        this.locked = false;
    }
    AbstractUpgrade(){

    }

    public String getName() {
        return name;
    }

    public int getCost()
    {
        return (int) Math.round(this.baseCost * (this.level+1) * (this.level+1) + (20 * this.baseCost));
    }

//    public String getLockedText()  //Do we really need this method????
//    {
//        return ("Upgrade is locked!");
//    }

    public int getLevel()
    {
        return level;
    }

    public abstract String getText();

    public abstract double getBonus(int level);
    public double getBonus(){
        return getBonus(this.level);
    }
    public String buy(Wallet wallet)
    {
        if(this.locked) return "This upgrade is locked!";
        int price = this.getCost();
        if(wallet.getMoney() < price){
            return "Cannot afford. Need $" + (price-wallet.getMoney()) + " more!";
        }
        wallet.spendMoney(price);
        this.level++;
        if(this.lockValue != -1 && this.getBonus() > this.lockValue) this.locked = true;
        //if(this.lockValue && this.getBonus() > this.lockValue) this.locked = true;
        return "Upgrade purchased";
    }
}
