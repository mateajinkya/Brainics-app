package com.example.brainics;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class schedulerAdapter extends RecyclerView.Adapter<schedulerAdapter.schedulerViewHolder> {
    private Context mContext;
    private Cursor mCursor;

    public schedulerAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }

    public class schedulerViewHolder extends RecyclerView.ViewHolder {
        public TextView nameText;
        public TextView countText;

        public schedulerViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.textview_task_item);
            countText = itemView.findViewById(R.id.textview_hours_item);
        }
    }

    @NonNull
    @Override
    public schedulerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.scheduler_item, parent, false);
        return new schedulerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull schedulerViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }
        String name = mCursor.getString(mCursor.getColumnIndex(schedulerContract.schedulerEntry.COLUMN_NAME));
        int amount = mCursor.getInt(mCursor.getColumnIndex(schedulerContract.schedulerEntry.COLUMN_AMOUNT));
        long id = mCursor.getLong(mCursor.getColumnIndex(schedulerContract.schedulerEntry._ID));

        holder.nameText.setText(name);
        holder.countText.setText(String.valueOf(amount));
        holder.itemView.setTag(id);

    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) {
            mCursor.close();
        }
        mCursor = newCursor;

        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }
}
