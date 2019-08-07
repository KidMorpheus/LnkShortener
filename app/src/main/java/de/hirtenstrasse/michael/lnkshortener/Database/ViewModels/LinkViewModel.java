package de.hirtenstrasse.michael.lnkshortener.Database.ViewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import de.hirtenstrasse.michael.lnkshortener.Database.Repositories.LinkRepository;
import de.hirtenstrasse.michael.lnkshortener.Models.Link;

public class LinkViewModel extends AndroidViewModel {

    private LinkRepository mRepository;
    private LiveData<List<Link>> mAllLinks;

    public LinkViewModel(Application application){
        super(application);
        mRepository = new LinkRepository(application);
        mAllLinks = mRepository.getAllLinks();
    }

    LiveData<List<Link>> getAllLinks() { return mAllLinks; }

    public void insert(Link link) { mRepository.insert(link); }

}
