package de.hirtenstrasse.michael.lnkshortener.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import de.hirtenstrasse.michael.lnkshortener.Activities.ShortLinkActivity;
import de.hirtenstrasse.michael.lnkshortener.Adapters.LinkListAdapter;
import de.hirtenstrasse.michael.lnkshortener.Database.ViewModels.LinkViewModel;
import de.hirtenstrasse.michael.lnkshortener.Models.Link;
import de.hirtenstrasse.michael.lnkshortener.R;

public class HomeFragment extends Fragment {

    public static final String EXTRA_MESSAGE = "de.hirtenstrasse.michael.lnkshortener.MESSAGE";
    private LinkViewModel mLinkViewModel;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);


        // Setting up the recycler view
        RecyclerView recyclerView = rootView.findViewById(R.id.link_list);
        final LinkListAdapter adapter = new LinkListAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // wiring up the view with the data
        mLinkViewModel = ViewModelProviders.of(this).get(LinkViewModel.class);
        mLinkViewModel.getAllLinks().observe(this, new Observer<List<Link>>() {
            @Override
            public void onChanged(List<Link> links) {
                adapter.setLinks(links);
            }
        });


        // TODO: Remove me!
        // Test for starting shorten activity
        final Button button3 = rootView.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                final EditText editText;
                editText = rootView.findViewById(R.id.editText);
                String message = editText.getText().toString();
                startShortLinkActivity(view, message);
            }
        });


        return rootView;
    }

    /*
    Starts shortening activity and sends input link as extra information.
     */

    public void startShortLinkActivity(View view, String message){
        Intent intent = new Intent(getActivity(), ShortLinkActivity.class);

        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
