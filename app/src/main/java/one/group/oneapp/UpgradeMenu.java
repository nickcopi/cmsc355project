package one.group.oneapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class UpgradeMenu extends Activity implements View.OnClickListener {

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





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_upgrade_menu);

        speedButton = (Button) findViewById(R.id.SpeedButton);
        speedButton.setOnClickListener(this);

        harvestButton = (Button) findViewById(R.id.HarvestButton);
        harvestButton.setOnClickListener(this);

        sizeButton = (Button) findViewById(R.id.SizeButton);
        sizeButton.setOnClickListener(this);

        salesButton = (Button) findViewById(R.id.SalesButton);
        salesButton.setOnClickListener(this);

        autoMoveButton = (Button) findViewById(R.id.AutomoveButton);
        autoMoveButton.setOnClickListener(this);
        game = (Game) getIntent().getSerializableExtra("game");
        initButtons();
       // SalesButton.setText("Test" + game.getPlayer().getHeight());

    }

    public void initButtons(){
        SpeedUpgrade speedUpgrade = game.getPlayer().getUpgradeManager().getSpeedUpgrade();
        speedButton.setText(speedUpgrade.getName() +" " +speedUpgrade.getLevel());
        SizeUpgrade sizeUpgrade = game.getPlayer().getUpgradeManager().getSizeUpgrade();
        sizeButton.setText(sizeUpgrade.getName() +" " +sizeUpgrade.getLevel());
    }
    public void clickBack(View view) {
        Intent result = new Intent();
        setResult(RESULT_OK,result);
        finish();
    }


    @Override
    public void onClick(View view) {
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
}
