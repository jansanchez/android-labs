package com.androidatc.cmac.papeltapiz;

import android.app.WallpaperManager;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView g= (GridView) findViewById(R.id.Gridview1);
        g.setAdapter(new ImageAdapter(this));
        registerForContextMenu(g);
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
       switch (item.getOrder()){
           case 100:  startActivity(new Intent(this,AboutActivity.class));
               break;

           case 200: finish();
               break;

       }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("Menu Contextual");
        AdapterView.AdapterContextMenuInfo cmi =
                (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.add(1,cmi.position,0,"Convertir a Wallpaper");
        menu.add(2,cmi.position,0,"Ver imagen");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        GridView g= (GridView) findViewById(R.id.Gridview1);
        Integer resourceId = (Integer)g.getItemAtPosition(item.getItemId());
        switch (item.getGroupId()){
            case 1:
                final WallpaperManager papelt= WallpaperManager.getInstance(this);
                try {
                    papelt.setResource(resourceId);
                    Toast.makeText(this,"Papel tapiz cambiado",
                            Toast.LENGTH_SHORT).show();

                }catch (IOException e){
                    Log.e("Error en tapiz", e.getMessage());
                }
                break;
            case 2:
                Intent i= new Intent(this,ImagePreview.class);
                i.putExtra("id",resourceId);
                startActivity(i);
        }

        return super.onContextItemSelected(item);
    }
}
