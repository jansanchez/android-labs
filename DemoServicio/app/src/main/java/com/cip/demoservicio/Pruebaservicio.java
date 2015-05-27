package com.cip.demoservicio;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;

public class Pruebaservicio extends Service {
	
	public static Activity act1;
	private Timer timer = null;
	
	public Pruebaservicio() {
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		return null;
	}

	

	public static void establecerActividadPrincipal(Activity actividad) {
		Pruebaservicio.act1 = actividad;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		 this.iniciarServicio();
		 
	        Log.i(getClass().getSimpleName(), "Servicio iniciado");
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		 this.finalizarServicio();
		 
	        Log.i(getClass().getSimpleName(), "Servicio detenido");
	}
	
	
	 public void iniciarServicio()
	    {
	        try
	        {
	            Log.i(getClass().getSimpleName(), "Iniciando servicio...");
	 
	            // Creamos el timer
	            this.timer=new Timer();
	 
	            // Configuramos lo que tiene que hacer
	            this.timer.scheduleAtFixedRate ( new TimerTask() {
	            	public void run() { ejecutarTarea(); } }, 0, 1000 // Cada segundo 
	            		);
	 
	            Log.i(getClass().getSimpleName(), "Temporizador iniciado");
	        }
	        catch(Exception e)
	        {
	            Log.i(getClass().getSimpleName(), e.getMessage());
	        }
	    }
	 
	    public void finalizarServicio()
	    {
	        try
	        {
	            Log.i(getClass().getSimpleName(), "Finalizando servicio...");
	 
	            // Detenemos el timer
	            this.timer.cancel();
	 
	            Log.i(getClass().getSimpleName(), "Temporizador detenido");
	        }
	        catch(Exception e)
	        {
	            Log.i(getClass().getSimpleName(), e.getMessage());
	        }
	    }
	    
	    private void ejecutarTarea()
	    {
	        Log.i(getClass().getSimpleName(), "Ejecutando tarea...");
	 
	        // Reflejamos la tarea en la actividad principal
	        Pruebaservicio.act1.runOnUiThread ( new Runnable()
	        {
	            public void run()
	            {
	                TextView ejecuciones=(TextView)Pruebaservicio.act1.findViewById(R.id.cuenta);
	                ejecuciones.append(".");
	            }
	        } );
	    }
}
