package com.cseunited.alumni.cseunited;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Debanik on 19-10-2017.
 * An adapter for the recycler view to load posts on the forum dynamically
 */

class DiscussAdapter extends RecyclerView.Adapter<DiscussAdapter.MyHolder> {

    private LayoutInflater inflater;
    private List<DiscussItem> itemList = Collections.emptyList();
    private ItemClickListener clickListener;

    /**
     * Constructor to initialize an object of the adapter.
     *
     * @param context  Context object.
     * @param itemList The list of DiscussionItem objects representing the posts in the forum.
     */

    DiscussAdapter(Context context, List<DiscussItem> itemList) {
        this.itemList = itemList;
        inflater = LayoutInflater.from(context);
    }

    /**
     * Registers the click listener on the item in the list.
     *
     * @param clickListener An object of the class implementing the ItemClickListener interface.
     */
    void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        DiscussItem currentItem = itemList.get(position);
        holder.top.setText(currentItem.topText);
        holder.bottom.setText(currentItem.bottomText);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView top, bottom;

        MyHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null)
                        clickListener.onItemClick(view, getAdapterPosition());
                    else {
                        Log.d("CLICK", "Click Listener is null, probably it was not set");
                    }
                }
            });
            top = (TextView) itemView.findViewById(R.id.textTitle);
            bottom = (TextView) itemView.findViewById(R.id.textSubtitle);
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            top = null;
            bottom = null;
        }
    }

    /**
     * Interface to implement to register a callback for an item click.
     */
    interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
