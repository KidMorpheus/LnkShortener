package de.hirtenstrasse.michael.lnkshortener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Date;
import java.util.List;

import de.hirtenstrasse.michael.lnkshortener.Adapters.LinkListAdapter;
import de.hirtenstrasse.michael.lnkshortener.Database.ViewModels.LinkViewModel;
import de.hirtenstrasse.michael.lnkshortener.Models.Link;

public class MainActivity extends AppCompatActivity {

    private LinkViewModel mLinkViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setting up the recycler view
        RecyclerView recyclerView = findViewById(R.id.link_list);
        final LinkListAdapter adapter = new LinkListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // wiring up the view with the data
        mLinkViewModel = ViewModelProviders.of(this).get(LinkViewModel.class);
        mLinkViewModel.getAllLinks().observe(this, new Observer<List<Link>>() {
            @Override
            public void onChanged(List<Link> links) {
                adapter.setLinks(links);
            }
        });


        // TODO: Remove me!
        // Test for adding information to the database.
        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                final EditText editText;
                editText = findViewById(R.id.editText);
                Link link = new Link(editText.getText().toString(), editText.getText().toString(), new Date());
                mLinkViewModel.insert(link);
                Toast.makeText(getApplicationContext(),
                        "Saved link",
                        Toast.LENGTH_LONG).show();
            }
        });


    }
}
