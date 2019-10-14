package one.group.oneapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;


public class InGame extends AppCompatActivity {
    private final int UPGRADE_MENU = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);
    }
    public void clickUpgrades(View view){
        Intent intent = new Intent(InGame.this,UpgradeMenu.class);
        startActivityForResult(intent, UPGRADE_MENU);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == UPGRADE_MENU) {
            if (resultCode == RESULT_OK) {

            }
        }
    }

}
