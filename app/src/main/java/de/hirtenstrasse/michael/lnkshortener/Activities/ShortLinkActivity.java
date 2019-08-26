package de.hirtenstrasse.michael.lnkshortener.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;


import java.util.Date;

import de.hirtenstrasse.michael.lnkshortener.Database.ViewModels.LinkViewModel;
import de.hirtenstrasse.michael.lnkshortener.Fragments.HomeFragment;
import de.hirtenstrasse.michael.lnkshortener.Models.Link;
import de.hirtenstrasse.michael.lnkshortener.R;

public class ShortLinkActivity extends AppCompatActivity {
    private LinkViewModel mLinkViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Setting up the view & Toolbar
        setContentView(R.layout.activity_short_link);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Getting long url from intent extra
        Intent intent = getIntent();
        String long_url = intent.getStringExtra(HomeFragment.EXTRA_MESSAGE);

        // wiring up the view with the data
        mLinkViewModel = ViewModelProviders.of(this).get(LinkViewModel.class);

        // Creating new link
        // TODO: Move to own method, wire to API
        Link link = new Link(long_url, long_url, new Date());
        mLinkViewModel.insert(link);
    }


}
