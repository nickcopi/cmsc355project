package one.group.oneapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UpgradeMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_menu);
    }
    public void clickBack(View view) {
        Intent result = new Intent();
        setResult(RESULT_OK,result);
        finish();
    }

}
