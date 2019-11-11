package one.group.oneapp;

public class SpeedUpgrade extends AbstractUpgrade
{
    public SpeedUpgrade(){
        super(0.9,"Speed","Make vehicle move faster");
        this.lockValue = 5;
    }

    @Override
    public double getBonus(int level) {
        if(level == 0) return 0;
        return level*Math.log(level)/20 + 0.03;
    }

    @Override
    public String getText() {
        return String.format("%s by %d%",
                this.description,
                Math.round(this.getBonus(this.level+1)*100));
    }
}
