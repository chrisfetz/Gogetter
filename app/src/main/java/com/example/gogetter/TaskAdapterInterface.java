package com.example.gogetter;

import android.view.View;

import java.util.ArrayList;

/**
 * Interface for Adapters of Recyclerviews that show a list of TodoTasks.
 */
public interface TaskAdapterInterface {

    /**
     * Fetches the item at a given position.
     * @param position The element's position in the content list.
     * @return The title at a given position.
     */
    String getItem(int position);

    /**
     * Set the contents of the adapter to an ArrayList of elements.
     * @param listFromLiveData List of content being loaded from ViewModel/Repository.
     * @param <E> A generic element type, such as a String or a TodoTask.
     */
    <E> void setContents(ArrayList<E> listFromLiveData);

    /**
     * Sets the itemClickListener for a given RecyclerView.
     * @param itemClickListener The Recyclerview's ItemClickListener.
     */
    void setClickListener(ItemClickListener itemClickListener);

    /**
     * Interface used by RecyclerViews to handle OnClick events,
     * governing the behavior when an item at a given position is clicked.
     */
    interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
