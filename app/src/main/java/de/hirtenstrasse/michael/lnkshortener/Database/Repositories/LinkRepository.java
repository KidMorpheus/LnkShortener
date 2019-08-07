package de.hirtenstrasse.michael.lnkshortener.Database.Repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import de.hirtenstrasse.michael.lnkshortener.Database.Dao.LinkDao;
import de.hirtenstrasse.michael.lnkshortener.Database.LinkRoomDatabase;
import de.hirtenstrasse.michael.lnkshortener.Models.Link;

public class LinkRepository {

    private LinkDao mLinkDao;
    private LiveData<List<Link>> mAllLinks;

    public LinkRepository(Application application){
        LinkRoomDatabase db = LinkRoomDatabase.getDatabase(application);
        mLinkDao = db.linkDao();
        mAllLinks = mLinkDao.getAllLinks();
    }

    public LiveData<List<Link>> getAllLinks(){
        return mAllLinks;
    }

    public void insert (Link link){
        new insertAsyncTask(mLinkDao).execute(link);
    }

    private static class insertAsyncTask extends AsyncTask<Link, Void, Void> {

        private LinkDao mAsyncTaskDao;

        insertAsyncTask(LinkDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Link... params){
            mAsyncTaskDao.insert(params[0]);
            return null;
        }

    }

}
