package io.frontendlabs.contactselection;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class ContactPageActivity extends ActionBarActivity implements View.OnClickListener {



    final int PHONE = 0;
    final int WEBSITE = 1;

    TextView contactName, contactPhone, contactWebsite;
    ContactObject contactObject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_page);

        contactName = (TextView) findViewById(R.id.contactName);
        contactPhone = (TextView) findViewById(R.id.contactPhone);
        contactWebsite = (TextView) findViewById(R.id.contactWebsite);

        Intent i = getIntent();
        Bundle extras = i.getExtras();

        if (extras == null){
            return;
        }

        contactObject = (ContactObject) i.getSerializableExtra("objetoContacto");
        contactName.setText(contactObject.getName());
        contactPhone.setText(contactObject.getPhone());
        contactWebsite.setText(contactObject.getWebsite());

        contactPhone.setOnClickListener(this);
        contactWebsite.setOnClickListener(this);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_page, menu);



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
    public void onClick(View v) {
        Intent j = new Intent();

        switch(v.getId()){
            case R.id.contactPhone:
                j.putExtra("valor", contactObject.getPhone());
                setResult(PHONE, j);
            break;
            case R.id.contactWebsite:
                j.putExtra("valor", contactObject.getWebsite());
                setResult(WEBSITE, j);
            break;
        }
    finish();

    }
}
