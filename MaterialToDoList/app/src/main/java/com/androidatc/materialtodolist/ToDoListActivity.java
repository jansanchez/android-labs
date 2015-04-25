package com.androidatc.materialtodolist;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;


public class ToDoListActivity extends ActionBarActivity {

    private EditText item;
    private ImageButton add;
    private ListView dynamicListView;

    private ArrayList<String> lista;
    private ArrayAdapter<String> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        add= (ImageButton) findViewById(R.id.add_item_button);
        dynamicListView = (ListView) findViewById(R.id.itemsListView);
        item = (EditText) findViewById(R.id.itemEditText);

        lista = new ArrayList<String>();
        lista.add("Primer Item AndroidATC");
        adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,lista);
        dynamicListView.setAdapter(adaptador);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todoItem = item.getText().toString();
                if(todoItem.length() > 0){
                    lista.add(item.getText().toString());
                    adaptador.notifyDataSetChanged();
                    item.setText("");
                }
            }
        });

        dynamicListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                lista.remove(position);
                adaptador.notifyDataSetChanged();
                return true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_to_do_list, menu);
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
}
