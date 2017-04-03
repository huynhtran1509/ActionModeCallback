package com.dev4u.ntc.actionmodecallback.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.dev4u.ntc.actionmodecallback.R;
import com.dev4u.ntc.actionmodecallback.models.Item;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewItemActivity extends AppCompatActivity implements ActionMode.Callback, RecyclerViewAdapter.ItemAdapterListener {
    private List<Item> itemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerViewAdapter mAdapter;
//    private ActionMode mActionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_item);
        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        for (int i = 1; i < 20; i++) {
            itemList.add(new Item(i, "RecyclerView Item " + i, "SubItem " + 1));
        }

        mAdapter = new RecyclerViewAdapter(this, itemList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onItemClick(int position) {
        // verify whether action mode is enabled or not
        // if enabled, change the row state to activated
        if (mActionMode != null) {
            enableActionMode(position);
        } else {
            Toast.makeText(this, "Click item " + position, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemLongClick(int position) {
        enableActionMode(position);
    }

    private void enableActionMode(int position) {
        if (mActionMode == null) {
            mActionMode = startSupportActionMode(this);
        }
        toggleSelection(position);
    }

    private void toggleSelection(int position) {
        mAdapter.toggleSelection(position);
        int count = mAdapter.getSelectedCount();
        if (count == 0) {
            mActionMode.finish();
        } else {
            mActionMode.setTitle(String.valueOf(count));
            mActionMode.invalidate();
        }
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        mode.getMenuInflater().inflate(R.menu.menu_action_mode, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                // delete all the selected messages
                mAdapter.deleteItems();
                mode.finish();
                return true;

            default:
                return false;
        }
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        mAdapter.removeSelection();
        mActionMode = null;
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                mAdapter.notifyDataSetChanged();
            }
        });
    }
}
