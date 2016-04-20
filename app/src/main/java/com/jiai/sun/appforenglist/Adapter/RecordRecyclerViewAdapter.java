package com.jiai.sun.appforenglist.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiai.sun.appforenglist.R;
import com.jiai.sun.appforenglist.domain.Record;

import java.util.List;

/**
 * Created by admin on 2016/4/19.
 */
public class RecordRecyclerViewAdapter extends RecyclerView.Adapter<RecordRecyclerViewAdapter.ViewHolder> {

    private List<Record> recordList;

    public RecordRecyclerViewAdapter(List<Record> recordList){
        this.recordList = recordList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_learning_record,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txvRecordDate.setText( recordList.get(position).getLearnDate().toString());
        holder.txvRecordWordsCount.setText( recordList.get(position).getLearnDate().toString());
        holder.txvRecordStarWordsCount.setText( recordList.get(position).getLearnDate().toString());
        holder.txvRecordDifficultWordsCount.setText( recordList.get(position).getLearnDate().toString());

    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txvRecordDate;
        public TextView txvRecordWordsCount;
        public TextView txvRecordDifficultWordsCount;
        public TextView txvRecordStarWordsCount;
        public ViewHolder(View itemView) {
            super(itemView);
            txvRecordDate = (TextView) itemView.findViewById(R.id.txv_recordDate);
            txvRecordDifficultWordsCount = (TextView) itemView.findViewById(R.id.txv_recordDifficultWordsCount);
            txvRecordStarWordsCount = (TextView) itemView.findViewById(R.id.txv_recordStarWordsCount);
            txvRecordWordsCount = (TextView) itemView.findViewById(R.id.txv_recordWordsCount);
        }
    }
}
