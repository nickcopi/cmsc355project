package one.group.oneapp;

public class HarvestUpgrade extends AbstractUpgrade
{
    public HarvestUpgrade(){
        super(0.8,"Harvest","Increase chance for 2x harvest");
        this.lockValue = 1;
    }
    @Override
    public double getBonus(int level) {
        if(level == 0) return 0;
        return level * level/100.0 + 0.03;
    }

    @Override
    public String getText() {
        return this.description + " by " +
                Math.round(this.getBonus(this.level+1) * 100)+ "%";
    }
}
