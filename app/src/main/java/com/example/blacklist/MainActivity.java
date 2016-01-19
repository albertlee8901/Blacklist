package com.example.blacklist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends ActionBarActivity {
    private ListView lv_main;
    private List<BlacklistNumber> data;
    private BlacklistAdapter adapter;
    private BlacklistNumberDAO dao;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_main = (ListView) findViewById(R.id.lv_main);
        dao = new BlacklistNumberDAO(this);
        data = dao.getAll();
        adapter = new BlacklistAdapter();

        lv_main.setAdapter(adapter);

        lv_main.setOnCreateContextMenuListener(this);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0, 1, 0, "Update");
        menu.add(0, 2, 0, "Delete");

        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
        position = adapterContextMenuInfo.position;

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        BlacklistNumber number = data.get(position);

        switch (item.getItemId()) {
            case 1:
                updateList(number);
                break;
            case 2:
                dao.delete(number.get_id());
                data.remove(position);
                adapter.notifyDataSetChanged();
                break;
            default:
                break;
        }

        return super.onContextItemSelected(item);
    }

    public void updateList(final BlacklistNumber blacklistNumber){
        final EditText editText = new EditText(this);
        editText.setHint(blacklistNumber.getNumber());
        new AlertDialog.Builder(this)
                .setTitle("Update Number")
                .setView(editText)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String number = editText.getText().toString();
                        //update data list
                        blacklistNumber.setNumber(number);
                        //update db
                        dao.update(blacklistNumber);
                        adapter.notifyDataSetChanged();
                    }
                })
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class BlacklistAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = View.inflate(MainActivity.this, R.layout.item, null);
            }
            String number = data.get(position).getNumber();
            TextView textView = (TextView)convertView.findViewById(R.id.tv_item_number);
            textView.setText(number);
            return convertView;
        }
    }

    public void add_number(View view) {
        final EditText editText = new EditText(this);
        editText.setHint("Enter new number");
        new AlertDialog.Builder(this)
                .setTitle("Add Number")
                .setView(editText)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String number = editText.getText().toString();
                        BlacklistNumber newNumber = new BlacklistNumber(-1, number);
                        dao.add(newNumber);
                        data.add(0,newNumber);
                        adapter.notifyDataSetChanged();
                    }
                })
                .show();

    }
}
