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

    private Game game;


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

        sizeButton = (Button) findViewById(R.id.SizeButton);
        sizeTextView = (TextView) findViewById(R.id.SizeTextView);

        salesButton = (Button) findViewById(R.id.SalesButton);

        autoMoveButton = (Button) findViewById(R.id.AutomoveButton);

        game = (Game) getIntent().getSerializableExtra("game");
        updateButtons();
       // SalesButton.setText("Test" + game.getPlayer().getHeight());

    }

    public void updateButtons(){
        SpeedUpgrade speedUpgrade = game.getPlayer().getUpgradeManager().getSpeedUpgrade();
        speedButton.setText(getName(speedUpgrade));
        speedTextView.setText(speedUpgrade.getDescription());

        SizeUpgrade sizeUpgrade = game.getPlayer().getUpgradeManager().getSizeUpgrade();
        sizeButton.setText(getName(sizeUpgrade));
        sizeTextView.setText(sizeUpgrade.getDescription());

        money.setText("$" + game.getPlayer().getMoney());
    }
    public void clickBack(View view) {
        Intent result = new Intent();
        result.putExtra("game",game);
        setResult(RESULT_OK,result);
        finish();
    }


    public String getName(AbstractUpgrade upgrade){
        return upgrade.getName() + " " + upgrade.getLevel();
    }

    public void clickSize(View view){
        buyUpgrade(game.getPlayer().getUpgradeManager().getSizeUpgrade());
    }

    public void buyUpgrade(AbstractUpgrade upgrade){
        output.setText(upgrade.buy(game.getPlayer().getWallet()));
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
