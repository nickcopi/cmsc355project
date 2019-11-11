package one.group.oneapp;

import java.io.Serializable;

public class UpgradeManager implements Serializable {
    private SpeedUpgrade speedUpgrade;
    private SizeUpgrade sizeUpgrade;
    private AbstractUpgrade upgrades[] = {speedUpgrade,sizeUpgrade};

    public UpgradeManager(){
        this.speedUpgrade = new SpeedUpgrade();
        this.sizeUpgrade = new SizeUpgrade();

    }

    public SizeUpgrade getSizeUpgrade() {
        return sizeUpgrade;
    }

    public SpeedUpgrade getSpeedUpgrade() {
        return speedUpgrade;
    }
}
