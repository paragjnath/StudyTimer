package com.thousandfeeds.studytimer.adapters;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thousandfeeds.studytimer.R;
import com.thousandfeeds.studytimer.database.TopicsContract;
import com.thousandfeeds.studytimer.fragments.DoubtsFragment.*;

/**
 * {@link RecyclerView.Adapter} that can display a todoitem and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyDoubtsRecyclerViewAdapter extends RecyclerView.Adapter<MyDoubtsRecyclerViewAdapter.ViewHolder> {

    private Cursor mCursor;
    private Uri uri;
    private OnListFragmentInteractionListener mListener;

    public MyDoubtsRecyclerViewAdapter(Cursor items, OnListFragmentInteractionListener listener) {
        mCursor = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_todo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        /*holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);*/
        int titleIndex = mCursor.getColumnIndex(TopicsContract.DoubtsTable.COLUMN_DOUBT_TITLE);
        int timeStampIndex = mCursor.getColumnIndex(TopicsContract.DoubtsTable.COLUMN_DOUBT_TIME_STAMP);

        mCursor.moveToPosition(position);

        holder.mIdView.setText(mCursor.getString(titleIndex));
        holder.mContentView.setText(mCursor.getString(timeStampIndex));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(uri);
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
        TextView mIdView;
        TextView mContentView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.todoTitleTextView);
            mContentView = (TextView) view.findViewById(R.id.todoTimeStampTextView);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}

