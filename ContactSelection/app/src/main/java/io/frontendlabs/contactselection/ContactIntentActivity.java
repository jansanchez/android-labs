package io.frontendlabs.contactselection;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class ContactIntentActivity extends ActionBarActivity {

    private final int PHONE = 0;
    private final int WEBSITE = 1;

    ListView intentListView;
    ArrayAdapter<String> adaptador;

    List<ContactObject> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_intent);

        intentListView = (ListView) findViewById(R.id.listView1);

        cargar_contactos();

        cargar_nombres();

        intentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ContactIntentActivity.this,ContactPageActivity.class);

                i.putExtra("objetoContacto", contactsList.get(position));
                startActivityForResult(i, 12345); // 12345 es solo un identificador, puede ser cualquier nombre

            }
        });

    }



    private void cargar_nombres() {

        ArrayList<String> nombres = new ArrayList();
        for(ContactObject contador : contactsList){
            nombres.add(contador.getName());
        }
        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombres);

        intentListView.setAdapter(adaptador);



    }

    private void cargar_contactos() {
        contactsList = new ArrayList<ContactObject>();
        String linea;

        try {
            InputStream entrada = getAssets().open("contacts.txt");
            InputStreamReader lectura = new InputStreamReader(entrada);
            BufferedReader contenido = new BufferedReader(lectura);
            linea = contenido.readLine();
            while (linea != null && linea.length() > 0){
                StringTokenizer corte = new StringTokenizer(linea, "*");
                ContactObject contacto = new ContactObject();
                contacto.setName(corte.nextToken());
                contacto.setPhone(corte.nextToken());
                contacto.setWebsite(corte.nextToken());

                contactsList.add(contacto);

                linea = contenido.readLine();
            }

            entrada.close();
            lectura.close();
            contenido.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_intent, menu);
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);

        if (data == null){
            return;
        }

        Bundle resultData = data.getExtras();
        String valor = resultData.getString("valor");
        //String cadena = "tel";

        switch(resultCode) {
            case PHONE:
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + valor)));
            break;
            case WEBSITE:
                //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(valor)));

                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(valor));
                startActivity(i);

                break;
        }



        /*
        if (resultCode == WEBSITE){
            cadena = "web";
        }
        */


        /*

        String cadena = "tel";

        if (resultCode == WEBSITE){
            cadena = "web";
        }

        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(cadena+ ": " + valor)));

        * */





    }
}
