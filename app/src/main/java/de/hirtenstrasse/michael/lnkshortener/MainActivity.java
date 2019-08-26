package de.hirtenstrasse.michael.lnkshortener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import de.hirtenstrasse.michael.lnkshortener.Activities.SettingsActivity;
import de.hirtenstrasse.michael.lnkshortener.Activities.ShortLinkActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Setting up the view & Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);



    }

    /*
    Inflates Main Menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    /*
    Handling selection of main menu items
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:

                // User chose the "Settings" item, invoking settings intent
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);


        }
    }
}
