package com.androidatc.codigo.inappbilling;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.androidatc.codigo.inappbilling.util.IabHelper;
import com.androidatc.codigo.inappbilling.util.IabResult;
import com.androidatc.codigo.inappbilling.util.Inventory;
import com.androidatc.codigo.inappbilling.util.Purchase;


public class InAppBillingActivity extends ActionBarActivity {
    private Button clickButton;
    private Button buyButton;

    private static final String TAG = "com.androidatc.billing";
    IabHelper mHelper;

    static final String ITEM_SKU = "android.test.purchased";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_app_billing);
        buyButton = (Button) findViewById(R.id.buyButton);
        clickButton = (Button) findViewById(R.id.clickButton);
        clickButton.setEnabled(false);

        String base64EncodedPublicKey =
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlVKkuhCnC1BOQbKLEMn/0AEBaITBRf31FTwX2lJmDQvD2GOWDyIxxHydsodhuY3bYqhQLkpPAqgAJWVD+9sfsdk/eQU3dJCNULr5sR/HaMwZcj+ClOaUxAicDa/m6mCQIQw6urJKEbT5WXHN82LzUIkD5tJ21FMksXZLSbsOAfXLA6u2jBAY7fYXfSXjR6gj1dB7S6K0fWOZ6N1bkq2kIKrOSLTWOaB//BWJyFw5ihXBCSB9taGuBD0pHPN1CHLWLg9ZSel0aHwbEvspqvi4LrT7aOVC/fmNVsZVsbCC3b8slt4iu9lPYHN6scYp2sbjNFx8UvqUEXIETvoNG2MVrwIDAQAB";

        mHelper = new IabHelper(this, base64EncodedPublicKey);

        mHelper.startSetup(new
                                   IabHelper.OnIabSetupFinishedListener() {
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
        getMenuInflater().inflate(R.menu.menu_in_app_billing, menu);
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

    public void buttonClicked(View view) {
        clickButton.setEnabled(false);
        buyButton.setEnabled(true);

    }
    public void buyClick(View view) {
        mHelper.launchPurchaseFlow(this, ITEM_SKU, 10001,
                mPurchaseFinishedListener, "mypurchasetoken");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!mHelper.handleActivityResult(requestCode,
                resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener =
            new IabHelper.OnIabPurchaseFinishedListener() {
                @Override
                public void onIabPurchaseFinished(IabResult result, Purchase info) {
                    if (result.isFailure()) {
                        // Handle error
                        return;
                    }
                    else if (info.getSku().equals(ITEM_SKU)) {
                        consumeItem();
                        buyButton.setEnabled(false);
                    }

                }
            };

    private void consumeItem() {
        mHelper.queryInventoryAsync(mReceivedInventoryListener);
    }

    IabHelper.QueryInventoryFinishedListener mReceivedInventoryListener
            = new IabHelper.QueryInventoryFinishedListener() {
        @Override
        public void onQueryInventoryFinished(IabResult result, Inventory inv) {
            if (result.isFailure()) {
                // Handle failure
            } else {
                mHelper.consumeAsync(inv.getPurchase(ITEM_SKU),
                        mConsumeFinishedListener);
            }

        }
    };
    IabHelper.OnConsumeFinishedListener mConsumeFinishedListener
            = new IabHelper.OnConsumeFinishedListener() {
        @Override
        public void onConsumeFinished(Purchase purchase, IabResult result) {
            if (result.isSuccess()) {
                clickButton.setEnabled(true);
            } else {
                // handle error
            }

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHelper != null) mHelper.dispose();
        mHelper = null;

    }


}
