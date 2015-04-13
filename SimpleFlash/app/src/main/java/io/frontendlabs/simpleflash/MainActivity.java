package io.frontendlabs.simpleflash;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.ToggleButton;


public class MainActivity extends ActionBarActivity {

    private Camera camera;
    private boolean isFlashOn;
    private boolean hasFlash;
    Camera.Parameters param;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hasFlash = getPackageManager().
                hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if (!hasFlash){
            AlertDialog alert = new AlertDialog.Builder(this).create();
            alert.setTitle("Error");
            alert.setMessage("Su dispositivo no tiene luz de flash");
            alert.setButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();
        }
        getCamera();

        ToggleButton flashSwitch = (ToggleButton) findViewById(R.id.flash_switch);

        flashSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    turnOnFlash();
                }else{
                    turnOffFlash();
                }
            }
        });

    }


    private void turnOnFlash() {

        if (!isFlashOn){

            if (camera == null || param == null){
                return;
            }

            param = camera.getParameters();
            param.setFlashMode(android.hardware.Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(param);
            camera.startPreview();
            isFlashOn = true;

            Log.v("Frontend Labs", "Flash On");

        }

    }


    private void turnOffFlash() {

        if (isFlashOn){

            if (camera == null || param == null){
                return;
            }

            param = camera.getParameters();
            param.setFlashMode(android.hardware.Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(param);
            camera.stopPreview();
            isFlashOn = false;

            Log.v("Frontend Labs", "Flash Off");

        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        turnOffFlash();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getCamera();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (camera!= null){
            camera.release();
            camera = null;
        }
    }

    private void getCamera() {

        try {
            if (camera == null){
                camera = Camera.open();
                param = camera.getParameters();
            }
        } catch (RuntimeException e){
            Log.e("Camera Error: ", e.getMessage());
        }

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
