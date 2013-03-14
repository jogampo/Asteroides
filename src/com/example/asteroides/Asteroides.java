package com.example.asteroides;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Asteroides extends Activity {

	private Button bAcercaDe;
	private Button bSalir;
	// almacen de puntuaciones
	public static AlmacenPuntuaciones almacen = new AlmacenPuntuacionesArray();
	private MediaPlayer mp;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bAcercaDe = (Button) findViewById(R.id.buttonAcercaDe);

		bAcercaDe.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {

				lanzarAcercaDe(null);

			}

		});

		mp = MediaPlayer.create(this, R.raw.audio);
		mp.start();
		/*
		 * bSalir = (Button) findViewById(R.id.buttonExit);
		 * bSalir.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View view) { lanzarSalir(null);
		 * 
		 * } });
		 */
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.config:
			lanzarPreferencias(null);
			break;

		case R.id.acercaDe:
			lanzarAcercaDe(null);
			break;
		}
		return true;
	}

	public void lanzarAcercaDe(View view) {

		Intent i = new Intent(this, AcercaDe.class);

		startActivity(i);

	}

	public void lanzarPreferencias(View view) {

		Intent i = new Intent(this, Preferencias.class);

		startActivity(i);

	}

	public void lanzarJuego(View view) {

		Intent i = new Intent(this, Juego.class);

		startActivity(i);

	}

	public void lanzarSalir(View view) {
		finish();
	}

	public void lanzarPuntuaciones(View view) {

		Intent i = new Intent(this, Puntuaciones.class);

		startActivity(i);

	}

	@Override
	protected void onStop() {
		super.onStop();
		mp.pause();		
	}

	@Override
	protected void onResume() {
		super.onResume();
		mp.start();
		Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onPause() {
		super.onPause();
		Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
		
	}
	
	//Metodos que mantienen donde esta la música.
	//Para que cuando se gire la pantalla siga funcionando
	@Override
	   protected void onSaveInstanceState(Bundle estadoGuardado){
	          super.onSaveInstanceState(estadoGuardado);
	          if (mp != null) {
	                 int pos = mp.getCurrentPosition();
	                 estadoGuardado.putInt("posicion", pos);
	          }
	   }
	 
	   @Override
	   protected void onRestoreInstanceState(Bundle estadoGuardado){
	          super.onRestoreInstanceState(estadoGuardado);
	          if (estadoGuardado != null && mp != null) {
	                 int pos = estadoGuardado.getInt("posicion");
	                 mp.seekTo(pos);
	          }
	   }
}
