package de.hirtenstrasse.michael.lnkshortener.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hirtenstrasse.michael.lnkshortener.Models.Link;
import de.hirtenstrasse.michael.lnkshortener.R;

public class LinkListAdapter extends RecyclerView.Adapter<LinkListAdapter.LinkViewHolder>{

    private final LayoutInflater mInflater;
    private List<Link> mLinks;

    public LinkListAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public LinkViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new LinkViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LinkViewHolder holder, int position){
        if (mLinks != null ){
            Link current = mLinks.get(position);
            holder.linkItemView.setText(current.getShortUrl());
        } else {
            holder.linkItemView.setText("Nada");
        }
    }

    public void setLinks(List<Link> links){
        mLinks = links;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount(){
        if(mLinks != null)
            return mLinks.size();
        else return 0;
    }

    class LinkViewHolder extends RecyclerView.ViewHolder {
        private final TextView linkItemView;

        private LinkViewHolder(View itemView){
            super(itemView);
            linkItemView = itemView.findViewById(R.id.textView);
        }
    }

}
