package one.group.oneapp;

public class SalesUpgrade extends AbstractUpgrade
{
    public SalesUpgrade(){
        super(0.75,"Sales","Items sell for");
        this.lockValue = 3;
    }
    @Override
    public double getBonus(int level) {
        if(level == 0) return 0;
        return level * Math.log(level)/50 + 0.04;
    }

    @Override
    public String getText() {
        return this.description + " " + Math.round(this.getBonus(this.level+1) * 100) + "% more. Sell "+ Math.round(this.getBonus(this.level+1) * 500) + " % larger stacks.";
    }
}
