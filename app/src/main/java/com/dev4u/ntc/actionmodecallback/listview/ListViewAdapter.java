package com.dev4u.ntc.actionmodecallback.listview;

import android.content.Context;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.dev4u.ntc.actionmodecallback.R;
import com.dev4u.ntc.actionmodecallback.models.Item;

import java.util.List;

/**
 * IDE: Android Studio
 * Created by Nguyen Trong Cong  - 2DEV4U.COM
 * Name packge: com.dev4u.ntc.actionmodecallback.listview
 * Name project: ActionModeCallback
 * Date: 3/21/2017
 * Time: 21:18
 */

public class ListViewAdapter extends BaseAdapter {
    private Context context;
    private List<Item> itemList;
    private LayoutInflater layoutInflater;
    private SparseBooleanArray mSelectedItemsIds;

    public ListViewAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
        layoutInflater = LayoutInflater.from(context);
        mSelectedItemsIds = new SparseBooleanArray();
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int i) {
        return itemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_row, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.title.setText(itemList.get(i).getTitle());
        holder.subTitle.setText(itemList.get(i).getSubTitle());

        /** Change background color of the selected items in list view  **/
        view.setBackgroundColor(mSelectedItemsIds.get(i) ? Color.GREEN : Color.TRANSPARENT);
        return view;
    }

    //Toggle selection methods
    public void toggleSelection(int position) {
        selectView(position, !mSelectedItemsIds.get(position));
    }

    //Remove selected selections
    public void removeSelection() {
        mSelectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    //Put or delete selected position into SparseBooleanArray
    public void selectView(int position, boolean value) {
        if (value)
            mSelectedItemsIds.put(position, value);
        else
            mSelectedItemsIds.delete(position);

        notifyDataSetChanged();
    }

    //Get total selected count
    public int getSelectedCount() {
        return mSelectedItemsIds.size();
    }

    //Return all selected ids
    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }

    public void deleteItems() {
        SparseBooleanArray selected = getSelectedIds();//Get selected ids
        //Loop all selected ids
        for (int i = (selected.size() - 1); i >= 0; i--) {
            if (selected.valueAt(i)) {
                //If current id is selected remove the item via key
                itemList.remove(selected.keyAt(i));
                notifyDataSetChanged();//notify adapter
            }
        }
        Toast.makeText(context, selected.size() + " item deleted.", Toast.LENGTH_SHORT).show();//Show Toast
    }

    public static class ViewHolder {
        public View rootView;
        public TextView title;
        public TextView subTitle;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.title = (TextView) rootView.findViewById(R.id.title);
            this.subTitle = (TextView) rootView.findViewById(R.id.sub_title);
        }

    }
}
