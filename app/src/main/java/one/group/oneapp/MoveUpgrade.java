package one.group.oneapp;

public class MoveUpgrade extends AbstractUpgrade
{
    public MoveUpgrade(){
        super(100,"Automove","Double tap to toggle automatic movement.");
        this.lockValue = 1;
    }
    @Override
    public double getBonus(int level) {
        if(level == 0) return 0;
        return 2;
    }

    @Override
    public String getText() {
        return this.description;
    }
}
