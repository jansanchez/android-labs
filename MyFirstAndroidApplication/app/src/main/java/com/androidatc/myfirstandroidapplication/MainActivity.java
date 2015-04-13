package com.androidatc.myfirstandroidapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    EditText caja1, caja2;
    Button bcalculo;
    TextView lresultado;
    Float altura, peso, BMI;
    String txtBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        caja1 = (EditText) findViewById(R.id.caltura);
        caja2 = (EditText) findViewById(R.id.cpeso);
        bcalculo = (Button) findViewById(R.id.bcalcular);
        lresultado = (TextView) findViewById(R.id.lresultado);

        txtBMI = (String) "Tu BMI: ";


        bcalculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (caja1.getText().length() > 0 && caja2.getText().length() > 0) {
                    altura = Float.parseFloat(caja1.getText().toString());
                    peso = Float.parseFloat(caja2.getText().toString());

                    BMI = calcularBMI(peso, altura);

                    if (BMI < 16) {
                        lresultado.setText(txtBMI + BMI + "(Severamente bajo de peso)");
                    } else if (BMI < 18.5) {
                        lresultado.setText(txtBMI + BMI + "(Bajo peso)");
                    } else if (BMI < 25) {
                        lresultado.setText(txtBMI + BMI + "(Normal)");
                    } else if (BMI < 30) {
                        lresultado.setText(txtBMI + BMI + "(Sobrepeso)");
                    } else {
                        lresultado.setText(txtBMI + BMI + "(Obeso)");
                    }
                } else {
                    lresultado.setText("Uno de los valores es inválido, intentar de nuevo");
                    clearFields();
                }
            }
        });

    }

    private void clearFields() {
        caja1.setText("");
        caja2.setText("");
        caja1.requestFocus();
    }

    private Float calcularBMI(float peso, float altura) {
        altura = (altura / 100);
        return (float) (peso / (altura * altura));
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
}
