package com.thousandfeeds.studytimer.adapters;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thousandfeeds.studytimer.R;
import com.thousandfeeds.studytimer.database.TasksContract;
import com.thousandfeeds.studytimer.fragments.TopicsFragment.OnListFragmentInteractionListener;

/**
 * {@link RecyclerView.Adapter} that can display a todoitem and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyTopicsRecyclerViewAdapter extends RecyclerView.Adapter<MyTopicsRecyclerViewAdapter.ViewHolder> {

    private Cursor mCursor;
    private final OnListFragmentInteractionListener mListener;

    public MyTopicsRecyclerViewAdapter(Cursor items, OnListFragmentInteractionListener listener) {
        mCursor = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.topic_list_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        /*holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);*/
        int titleIndex = mCursor.getColumnIndex(TasksContract.TasksTable.COLUMN_TASK_TITLE);
        int timeStampIndex = mCursor.getColumnIndex(TasksContract.TasksTable.COLUMN_TASK_TIME_STAMP);


        mCursor.moveToPosition(position);

        holder.mTitleView.setText(mCursor.getString(titleIndex));
        holder.mContentView.setText(mCursor.getString(timeStampIndex));


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mCursor.moveToPosition(position);
                    int idIndex = mCursor.getColumnIndex(TasksContract.TasksTable._ID);
                    int id = mCursor.getInt(idIndex);
                    Uri currentTopicUri = ContentUris.withAppendedId(TasksContract.TasksTable.CONTENT_URI, id);
                    mListener.onListFragmentInteraction(currentTopicUri);

                }
            }
        });


    }

    @Override
    public int getItemCount() {
        if (mCursor == null) {
            return 0;
        }
        return mCursor.getCount();
    }

    /**
     * When data changes and a re-query occurs, this function swaps the old Cursor
     * with a newly updated Cursor (Cursor c) that is passed in.
     */
    public Cursor swapCursor(Cursor c) {
        // check if this cursor is the same as the previous cursor (mCursor)
        if (mCursor == c) {
            return null; // bc nothing has changed
        }
        Cursor temp = mCursor;
        this.mCursor = c; // new cursor value assigned

        //check if this is a valid cursor, then update the cursor
        if (c != null) {
            this.notifyDataSetChanged();
        }
        return temp;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView mTitleView;
        TextView mContentView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitleView = (TextView) view.findViewById(R.id.cardTitle);
            mContentView = (TextView) view.findViewById(R.id.cardDate);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

}