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

    public Button SpeedButton;
    int Speedcount = 1;
    int Speedi = 1;

    private Button HarvestButton;
    int Harvestcount = 1;
    int Harvesti = 1;

    private Button SizeButton;
    int Sizecount = 1;
    int Sizei = 1;

    private Button SalesButton;
    int Salescount = 1;
    int Salesi = 1;

    private Button AutomoveButton;
    int Automovecount = 1;
    int Automovei = 1;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_upgrade_menu);

        SpeedButton = (Button) findViewById(R.id.SpeedButton);
        SpeedButton.setOnClickListener(this);

        HarvestButton = (Button) findViewById(R.id.HarvestButton);
        HarvestButton.setOnClickListener(this);

        SizeButton = (Button) findViewById(R.id.SizeButton);
        SizeButton.setOnClickListener(this);

        SalesButton = (Button) findViewById(R.id.SalesButton);
        SalesButton.setOnClickListener(this);

        AutomoveButton = (Button) findViewById(R.id.AutomoveButton);
        AutomoveButton.setOnClickListener(this);

    }
    public void clickBack(View view) {
        Intent result = new Intent();
        setResult(RESULT_OK,result);
        finish();
    }


    @Override
    public void onClick(View view) {

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



    }
}
