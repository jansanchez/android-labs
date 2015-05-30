package com.example.administrador.pagaporclick;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.atc.utilidad.IabHelper;
import org.atc.utilidad.IabResult;
import org.atc.utilidad.Inventory;
import org.atc.utilidad.Purchase;


public class MainActivity extends ActionBarActivity {
    Button hazClick, compraClick;
    private static final String TAG = "org.atc.utilidad";
    IabHelper mHelper;
    static final String ITEM_SKU = "android.test.purchased";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hazClick = (Button) findViewById(R.id.clickButton);
        compraClick = (Button) findViewById(R.id.buyButton);
        compraClick.setEnabled(false);
        String base64EncodedPublicKey =
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmgZg3jh/MryaFUz44xYYxV+U5h6kb3Gx0VC9oAf7xCGE+l1W3YgPb0TO/hPkIfohtIVrME661ugK4fFVokwqcZoYDD3lLZzknvD1xmzm1yOEVQ3fmBdOaBtJwybl3XGfBDcjZM8BfLGdFvTxHsNJfunakMzl//rTzkLUfcpk3RJl0SAmgaveAkNtqXHJxeSbYfRFWhUbBuODWYV/NC2PeiqtKpkRTgOy+1fOUdTBIn/woMR2nxSGoJJScFE/39l/89Tir3Ncd1wrWS0On7mXmcDmcs5fgeZlWd9XE968fZbcHqBxnIADV/vtYDWSPcwvgYFSmKmY9YsGezd4ePXl/wIDAQAB";

        mHelper = new IabHelper(this, base64EncodedPublicKey);
        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {

            @Override
            public void onIabSetupFinished(IabResult result) {

                if (!result.isSuccess()) {
                    Log.d(TAG, "In-app Billing setup failed: " +
                            result);
                } else {
                    Log.d(TAG, "In-app Billing is set up OK");
                }
            }
        });

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

    public void hacerClick(View v) {
        hazClick.setEnabled(false);
        compraClick.setEnabled(true);
    }

    public void comprarClick(View v) {
        mHelper.launchPurchaseFlow(this, ITEM_SKU, 10001, mPurchaseFinishedListener, "mypurchasetoken");
    }

    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener =
            new IabHelper.OnIabPurchaseFinishedListener() {
                @Override
                public void onIabPurchaseFinished(IabResult result, Purchase info) {
                    if (result.isFailure()) {
                        return;
                    } else if (info.getSku().equals(ITEM_SKU)) {
                        activarClick();
                        compraClick.setEnabled(false);
                    }
                }
            };

    private void activarClick() {
        mHelper.queryInventoryAsync(mReceivedInventoryListener);
    }

    IabHelper.QueryInventoryFinishedListener mReceivedInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
        @Override
        public void onQueryInventoryFinished(IabResult result, Inventory inv) {
            if (result.isFailure()) {
                Toast.makeText(MainActivity.this, "ReceivedInventory ha fallado",
                        Toast.LENGTH_LONG).show();
            } else {
                mHelper.consumeAsync(inv.getPurchase(ITEM_SKU), mConsumeFinishedListener);
            }
        }
    };

    IabHelper.OnConsumeFinishedListener mConsumeFinishedListener = new IabHelper.OnConsumeFinishedListener() {
        @Override
        public void onConsumeFinished(Purchase purchase, IabResult result) {
            if (result.isSuccess()) {
                hazClick.setEnabled(true);
            } else {
                Toast.makeText(MainActivity.this, "No se puede despachar producto",
                        Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHelper != null) {
            mHelper.dispose();
        }
        mHelper = null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(!mHelper.handleActivityResult(requestCode,resultCode,data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
