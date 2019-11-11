package one.group.oneapp;

public class SizeUpgrade extends AbstractUpgrade
{
    public SizeUpgrade(){
        super(1,"Size","Make vehicle bigger");
        this.lockValue = 3;
    }
    @Override
    public double getBonus(int level) {
        if(level == 0) return 0;
        return  level*Math.log(level)/20 + 0.03;
    }

    @Override
    public String getText() {
        return String.format("%s by %d%",
                this.description,
                Math.round(this.getBonus(this.level+1)*100));
    }
}
