package com.dev4u.ntc.actionmodecallback.recyclerview;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dev4u.ntc.actionmodecallback.R;
import com.dev4u.ntc.actionmodecallback.models.Item;

import java.util.List;

/**
 * IDE: Android Studio
 * Created by Nguyen Trong Cong  - 2DEV4U.COM
 * Name packge: com.dev4u.ntc.actionmodecallback.recyclerview
 * Name project: ActionModeCallback
 * Date: 3/21/2017
 * Time: 23:45
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Context context;
    private List<Item> itemList;
    private ItemAdapterListener listener;
    private SparseBooleanArray selectedItems;

    public RecyclerViewAdapter(Context context, List<Item> itemList, ItemAdapterListener listener) {
        this.context = context;
        this.itemList = itemList;
        this.listener = listener;
        selectedItems = new SparseBooleanArray();
    }

    @Override
    public long getItemId(int position) {
        return itemList.get(position).getId();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = itemList.get(position);
        // displaying text view data
        holder.title.setText(item.getTitle());
        holder.subTitle.setText(item.getSubTitle());
        // change the row state to activated
        //holder.itemView.setActivated(selectedItems.get(position, false));
        holder.itemView.setBackgroundColor(selectedItems.get(position) ? Color.LTGRAY : Color.TRANSPARENT);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    //Toggle selection methods
    public void toggleSelection(int position) {
        selectView(position, !selectedItems.get(position));
    }

    //Remove selected selections
    public void removeSelection() {
        selectedItems = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    //Put or delete selected position into SparseBooleanArray
    public void selectView(int position, boolean value) {
        if (value)
            selectedItems.put(position, value);
        else
            selectedItems.delete(position);

        notifyDataSetChanged();
    }

    //Return all selected ids
    public SparseBooleanArray getSelectedIds() {
        return selectedItems;
    }

    public int getSelectedCount() {
        return selectedItems.size();
    }

    public void deleteItems() {
        //Get selected ids
        SparseBooleanArray selected = getSelectedIds();
        //Loop all selected ids
        for (int i = (selected.size() - 1); i >= 0; i--) {
            if (selected.valueAt(i)) {
                //If current id is selected remove the item via key
                itemList.remove(selected.keyAt(i));
                //notify adapter
                notifyDataSetChanged();
            }
        }
        Toast.makeText(context, selected.size() + " item deleted.", Toast.LENGTH_SHORT).show();
    }

    public interface ItemAdapterListener {

        void onItemClick(int position);

        void onItemLongClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {
        public TextView title;
        public TextView subTitle;
        public LinearLayout mRlItem;

        public ViewHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.title);
            this.subTitle = (TextView) view.findViewById(R.id.sub_title);
            this.mRlItem = (LinearLayout) view.findViewById(R.id.mRlItem);
            view.setOnLongClickListener(this);
            view.setOnClickListener(this);
        }

        @Override
        public boolean onLongClick(View view) {
            listener.onItemLongClick(getAdapterPosition());
            view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
            return true;
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(getAdapterPosition());
        }
    }
}
