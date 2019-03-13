package com.example.gogetter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gogetter.database.TodoTask;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    private List<TodoTask> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    TodoAdapter(Context context, ItemClickListener itemClickListener){
        this.mInflater = LayoutInflater.from(context);
        mClickListener = itemClickListener;
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        LinearLayout myLinearLayout;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.tvTaskTitle);
            myLinearLayout = itemView.findViewById(R.id.layoutTaskRow);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }


    /*
     * RecyclerView.Adapter overrides
     */

    @NonNull
    @Override
    public TodoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.ViewHolder holder, int position) {
        TodoTask task = mData.get(position);
        String title = task.getTitle();
        int color = task.getColor();
        holder.myTextView.setText(title);

        //TODO: Get rid of hardcoded background color and repeated
        switch (color) {
            case 0: holder.myLinearLayout.setBackgroundColor(Color.parseColor("#4285F4"));
            break;
            case 1: holder.myLinearLayout.setBackgroundColor(Color.parseColor("#DB4437"));
            break;
            case 2: holder.myLinearLayout.setBackgroundColor(Color.parseColor("#F4B400"));
            break;
            case 3: holder.myLinearLayout.setBackgroundColor(Color.parseColor("#0F9D58"));
            break;
            default: holder.myLinearLayout.setBackgroundColor(Color.parseColor("#4285F4"));
        }
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        if (mData == null){
            return 0;
        }
        return mData.size();
    }

    /*
     * My functions
     */

    /**
     * Set the contents of the adapter to an List of elements.
     *
     * @param listFromLiveData List of content being loaded from ViewModel/Repository.
     */
    public void setContents(List<TodoTask> listFromLiveData) {
        mData = listFromLiveData;
        notifyDataSetChanged();
    }

    /**
     * Sets the itemClickListener for a given RecyclerView.
     *
     * @param itemClickListener The Recyclerview's ItemClickListener.
     */
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }


    /**
     * Interface used by RecyclerViews to handle OnClick events,
     * governing the behavior when an item at a given position is clicked.
     */
    interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
