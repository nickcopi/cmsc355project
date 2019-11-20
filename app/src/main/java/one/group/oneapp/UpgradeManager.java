package one.group.oneapp;

import java.io.Serializable;

public class UpgradeManager implements Serializable {
    private SpeedUpgrade speedUpgrade;
    private SizeUpgrade sizeUpgrade;
    private HarvestUpgrade harvestUpgrade;
    private AbstractUpgrade upgrades[] = {speedUpgrade,sizeUpgrade,harvestUpgrade};

    public UpgradeManager(){
        this.speedUpgrade = new SpeedUpgrade();
        this.sizeUpgrade = new SizeUpgrade();
        this.harvestUpgrade = new HarvestUpgrade();

    }

    public SizeUpgrade getSizeUpgrade() {
        return sizeUpgrade;
    }

    public SpeedUpgrade getSpeedUpgrade() {
        return speedUpgrade;
    }

    public HarvestUpgrade getHarvestUpgrade() {
        return harvestUpgrade;
    }
}
