package one.group.oneapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class UpgradeMenu extends Activity {

    private Button speedButton;
    private TextView speedTextView;

    private Button harvestButton;
    private TextView harvestTextView;

    private Button sizeButton;
    private  TextView sizeTextView;

    private Button salesButton;
    private TextView salesTextView;

    private Button autoMoveButton;
    private TextView autoMoveTextView;

    private Wallet wallet;
    private UpgradeManager upgradeManager;


    private TextView money;
    private TextView output;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_upgrade_menu);

        money = (TextView) findViewById(R.id.money);
        output = (TextView) findViewById(R.id.output);

        speedButton = (Button) findViewById(R.id.SpeedButton);
        speedTextView = (TextView) findViewById(R.id.SpeedTextView);

        harvestButton = (Button) findViewById(R.id.HarvestButton);
        harvestTextView = (TextView) findViewById(R.id.HarvestTextView);


        sizeButton = (Button) findViewById(R.id.SizeButton);
        sizeTextView = (TextView) findViewById(R.id.SizeTextView);

        salesButton = (Button) findViewById(R.id.SalesButton);
        salesTextView = (TextView) findViewById(R.id.SalesTextView);

        autoMoveButton = (Button) findViewById(R.id.AutomoveButton);
        autoMoveTextView = (TextView) findViewById(R.id.AutomoveTextView);

        wallet = (Wallet) getIntent().getSerializableExtra("wallet");
        upgradeManager = (UpgradeManager) getIntent().getSerializableExtra("upgradeManager");
        updateButtons();
       // SalesButton.setText("Test" + game.getPlayer().getHeight());

    }

    public void updateButtons(){
        SpeedUpgrade speedUpgrade = upgradeManager.getSpeedUpgrade();
        speedButton.setText(getName(speedUpgrade));
        speedTextView.setText(speedUpgrade.getDescription());

        SizeUpgrade sizeUpgrade = upgradeManager.getSizeUpgrade();
        sizeButton.setText(getName(sizeUpgrade));
        sizeTextView.setText(sizeUpgrade.getDescription());

        HarvestUpgrade harvestUpgrade = upgradeManager.getHarvestUpgrade();
        harvestButton.setText(getName(harvestUpgrade));
        harvestTextView.setText(harvestUpgrade.getDescription());

        SalesUpgrade salesUpgrade = upgradeManager.getSalesUpgrade();
        salesButton.setText(getName(salesUpgrade));
        salesTextView.setText(salesUpgrade.getDescription());

        MoveUpgrade moveUpgrade = upgradeManager.getMoveUpgrade();
        autoMoveButton.setText(getName(moveUpgrade));
        autoMoveTextView.setText(moveUpgrade.getDescription());


        money.setText(getString(R.string.dollarMoney, wallet.getMoney()));
    }
    public void clickBack(View view) {
        Intent result = new Intent();
        result.putExtra("upgradeManager",upgradeManager);
        result.putExtra("wallet",wallet);
        setResult(RESULT_OK,result);
        finish();
    }


    public String getName(AbstractUpgrade upgrade){
        if(upgrade.isLocked()){
            return upgrade.getName();
        }
        return upgrade.getName() + " " + (upgrade.getLevel() + 1);
    }

    public void clickSize(View view){
        buyUpgrade(upgradeManager.getSizeUpgrade());
    }
    public void clickSpeed(View view){
        buyUpgrade(upgradeManager.getSpeedUpgrade());
    }

    public void clickHarvest(View view){
        buyUpgrade(upgradeManager.getHarvestUpgrade());
    }
    public void clickSales(View view){
        buyUpgrade(upgradeManager.getSalesUpgrade());
    }

    public void clickMove(View view){
        buyUpgrade(upgradeManager.getMoveUpgrade());
    }

    public void buyUpgrade(AbstractUpgrade upgrade){
        output.setText(upgrade.buy(wallet));
        updateButtons();
    }



/*
    Button SpeedButton = (Button) findViewById(R.id.SpeedButton);
    if(Speedcount >= 20 )
    {
        SpeedButton.setText("Speed MAXED");
    }
    else
    {
        SpeedButton.setText("Speed " + Speedi);
        Speedcount++;
        Speedi++;
    }

    Button SizeButton = (Button) findViewById(R.id.SizeButton);

        if(Sizecount >= 20)
        {
            SizeButton.setText("Size MAXED");
        }
        else
        {
            SizeButton.setText("Size " + Sizei);
            Sizecount++;
            Sizei++;
        }

        Button HarvestButton = (Button) findViewById(R.id.HarvestButton);

        if(Harvestcount >= 20)
        {
            HarvestButton.setText("Speed MAXED");
        }
        else
        {
            HarvestButton.setText("Harvest " + Harvesti);
            Harvestcount++;
            Harvesti++;
        }

        Button SalesButton = (Button) findViewById(R.id.SalesButton);

        if(Salescount >= 20)
        {
            SalesButton.setText("Sales MAXED");
        }
        else
        {
            SalesButton.setText("Sales " + Salesi);
            Salescount++;
            Salesi++;
        }

        Button AutomoveButton = (Button) findViewById(R.id.AutomoveButton);

        if(Automovecount == 1)
        {
            AutomoveButton.setText("Automove MAXED");
            Automovecount++;
            Automovei++;
        }
*/


}
