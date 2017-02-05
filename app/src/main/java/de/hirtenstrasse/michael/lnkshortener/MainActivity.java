package de.hirtenstrasse.michael.lnkshortener;

// Copyright (C) 2017 Michael Achmann

//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU General Public License for more details.
//
//  You should have received a copy of the GNU General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.

import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "de.hirtenstrasse.michael.lnkshortener.MESSAGE";
    public final static String ACTIVITY_MESSAGE = "de.hirtenstrasse.michael.lnkshortener.ACTIVITY";
    String originalUrl;
    String errorMessage;
    private UrlManager urlmanager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        urlmanager = new UrlManager(this);

        // Needed at every entrypoint to the App
        PreferenceManager.setDefaultValues(this, R.xml.main_settings, false);

        // Setting up the Layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setting up the Toolbar
        Toolbar mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        // Preparing Fragment
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // MainFragment is the start screen
        MainFragment mainFragment = new MainFragment();

        // The Bundle is needed for passing on error messages to MainFragment
        Bundle mainFragmentBundle = new Bundle();

        // Retrieving possible error messages from the Intent
        Intent intent = getIntent();
        originalUrl = intent.getStringExtra(DisplayShortenedUrlActivity.EXTRA_MESSAGE);
        errorMessage = intent.getStringExtra(DisplayShortenedUrlActivity.ERROR_MESSAGE);

        // If an error occurred in in DisplayShortenedUrlActivity they are passed on to MainFragment
        if (intent.getBooleanExtra(DisplayShortenedUrlActivity.ERROR_BOOL, false)) {
            mainFragmentBundle.putBoolean("error", true);
            mainFragmentBundle.putString("errorMessage", errorMessage);
            mainFragmentBundle.putString("originalUrl", originalUrl);
        }

        mainFragment.setArguments(mainFragmentBundle);

        // Finally MainFragment is added to the main container
        transaction.replace(R.id.fragment_container, mainFragment);
        transaction.commit();

    }

    public void shortenLink(View view) {

        // This function is called by the "SHORTEN" button on the home screen. It validates the entered
        // URL and finally sends it via Intent to DisplayShortenedUrlActivity

        // Setting up the Intent
        Intent intent = new Intent(this, DisplayShortenedUrlActivity.class);

        // Setting up layout variables
        EditText urlInput = (EditText) findViewById(R.id.urlInput);
        TextView textViewError = (TextView) findViewById(R.id.textViewError);
        ImageView imageViewError = (ImageView) findViewById(R.id.imageViewError);

        // URL as typed into the TextEdit
        originalUrl = urlInput.getText().toString();

        // Remove trailing and leading whitespaces
        originalUrl = originalUrl.trim();

        // If originalUrl is a valide URL it is passed on
        if(urlmanager.validateURL(originalUrl)){
            originalUrl = UrlManager.guessUrl(originalUrl);
            intent.putExtra(EXTRA_MESSAGE, originalUrl);
            intent.putExtra(ACTIVITY_MESSAGE, true);

            urlInput.setText("");
            textViewError.setText("");
            textViewError.setVisibility(View.INVISIBLE);
            imageViewError.setVisibility(View.INVISIBLE);

            startActivity(intent);

        } else {
            // If not we display an error message

            textViewError.setText(getString(R.string.error_valid_url));
            textViewError.setVisibility(View.VISIBLE);
            imageViewError.setVisibility(View.VISIBLE);

        }


    }

    // Adds the main menu to the Activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainactivity_menu, menu);
        return true;
    }

    // Adds functionality to the main menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // Starts the SettingsActivity
                Intent intent = new Intent(this,SettingsActivity.class);
                startActivity(intent);

                return true;

            case R.id.action_bug:

                String url = "https://github.com/michaelachmann/LnkShortener/issues/";
                Intent intentBug = new Intent(Intent.ACTION_VIEW);
                intentBug.setData(Uri.parse(url));
                startActivity(intentBug);

                return true;

            case R.id.action_history:
                // Perparing Fragment
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // LinkHistoryFragment is the start screen
                LinkHistoryFragment linkHistoryFragment = new LinkHistoryFragment();

                // Finally LinkHistoryFragment is added to the main container
                transaction.replace(R.id.fragment_container, linkHistoryFragment);
                transaction.addToBackStack("");
                transaction.commit();






            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}
