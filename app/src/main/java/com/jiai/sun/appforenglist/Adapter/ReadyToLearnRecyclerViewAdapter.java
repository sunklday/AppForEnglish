package com.jiai.sun.appforenglist.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiai.sun.appforenglist.R;
import com.jiai.sun.appforenglist.domain.Words;

import java.util.List;

/**
 * Created by Sun on 2016/4/16.
 */
public class ReadyToLearnRecyclerViewAdapter extends RecyclerView.Adapter<ReadyToLearnRecyclerViewAdapter.ViewHolder> {

    private List<Words> wordList;
    public ReadyToLearnRecyclerViewAdapter(List<Words> wordList){
        this.wordList = wordList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ready_to_learn_cardview,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        System.out.println("onCreate");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        System.out.println("onBind");
        holder.readyToLearnWord.setText(wordList.get(position).getWord());
        holder.readyToLearnMean.setText(wordList.get(position).getMean());
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView readyToLearnWord;
        public TextView readyToLearnMean;

        public ViewHolder(View itemView) {
            super(itemView);
            readyToLearnWord = (TextView) itemView.findViewById(R.id.txv_readyToLearnWord);
            readyToLearnMean = (TextView) itemView.findViewById(R.id.txv_readyToLearnMean);
        }
    }
}
