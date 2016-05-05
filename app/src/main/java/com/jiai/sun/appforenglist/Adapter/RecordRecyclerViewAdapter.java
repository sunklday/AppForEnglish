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
    private OnItemClickLitener mOnItemClickLitener;

    public RecordRecyclerViewAdapter(List<Record> recordList){
        this.recordList = recordList;
    }


    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_record,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.txvRecordDate.setText(recordList.get(position).getLearnDate().toString());
        holder.txvRecordWordsCount.setText(recordList.get(position).getWordsCount().toString());
        holder.txvRecordStarWordsCount.setText(recordList.get(position).getStarWordsCount().toString());
        holder.txvRecordDifficultWordsCount.setText(recordList.get(position).getDifficultWordsCount().toString());
        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }
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

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }
}
