package com.example.asteroides;

import java.util.List;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;

public class Juego extends Activity {

	private VistaJuego vistaJuego;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.juego);
		vistaJuego = (VistaJuego) findViewById(R.id.VistaJuego);
	}

	@Override
	protected void onPause() {
		super.onPause();
		vistaJuego.getThread().pausar();
		vistaJuego.pararSensores();
	}

	@Override
	protected void onResume() {
		super.onResume();
		vistaJuego.getThread().reanudar();
		vistaJuego.reanudaSensores();
	
	}

	@Override
	protected void onDestroy() {
		vistaJuego.getThread().detener();
		super.onDestroy();
	}
}
