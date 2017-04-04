package com.dev4u.ntc.actionmodecallback.listview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.dev4u.ntc.actionmodecallback.R;
import com.dev4u.ntc.actionmodecallback.models.Item;
import com.dev4u.ntc.actionmodecallback.recyclerview.RecyclerViewItemActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener,
        AdapterView.OnItemClickListener, ActionMode.Callback {

    private ListView lvActionMode;
    private ListViewAdapter mAdapter;
    private List<Item> itemList;
    private ActionMode mActionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lvActionMode = (ListView) findViewById(R.id.lvActionMode);
        itemList = new ArrayList<>();

        for (int i = 1; i < 20; i++) {
            itemList.add(new Item(i, "Item " + i, "SubItem " + 1));
        }

        mAdapter = new ListViewAdapter(this, itemList);
        lvActionMode.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        lvActionMode.setOnItemClickListener(this);
        lvActionMode.setOnItemLongClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (mActionMode != null) {
            enableActionMode(i);
        } else {
            startActivity(new Intent(this, RecyclerViewItemActivity.class));
            Toast.makeText(this, "Click item " + i, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        enableActionMode(i);
        return true;
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
            mActionMode.setTitle(String.valueOf(count) + " selected");
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
        mAdapter.removeSelection();  // remove selection
        mActionMode = null;
        lvActionMode.post(new Runnable() {
            @Override
            public void run() {
                mAdapter.notifyDataSetChanged();
            }
        });
    }

}
