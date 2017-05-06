package com.thousandfeeds.studytimer.fragments;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thousandfeeds.studytimer.R;
import com.thousandfeeds.studytimer.database.TasksContract;
import com.thousandfeeds.studytimer.fragments.TodoFragment.OnListFragmentInteractionListener;
import com.thousandfeeds.studytimer.fragments.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyTodoRecyclerViewAdapter extends RecyclerView.Adapter<MyTodoRecyclerViewAdapter.ViewHolder> {

    private final Cursor mCursor;
    private final OnListFragmentInteractionListener mListener;

    public MyTodoRecyclerViewAdapter(Cursor items, OnListFragmentInteractionListener listener) {
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
        mCursor.move(position);
        int titleIndex = mCursor.getColumnIndex(TasksContract.ToDoListTable.COLUMN_TODO_TITLE);
        int timeStampIndex = mCursor.getColumnIndex(TasksContract.ToDoListTable.COLUMN_TODO_TIME_STAMP);
        holder.mIdView.setText(mCursor.getString(titleIndex));
        holder.mContentView.setText(mCursor.getString(timeStampIndex));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public DummyItem mItem;

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
