package com.example.assetdb;

import java.io.IOException;

import com.example.assetdb.data.DataBaseHelper;

import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Prueba mateo
 * @author emaneff
 *
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		loadData();
	}
	
	private void loadData() {
		DataBaseHelper myDbHelper = new DataBaseHelper(getApplicationContext());
 
        try { 
        	myDbHelper.createDataBase(); 
        } 
        catch (IOException ioe) { 
        	throw new Error("Unable to create database"); 
        }
 
        try { 
        	myDbHelper.openDataBase(); 
        	SQLiteDatabase db = myDbHelper.getDataBase();
        	
        	Cursor cursor = db.rawQuery("SELECT _id, nombre FROM Categorias", null);
			
			cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    	Log.i("Categorias", "Nombre: " + cursor.getString(1));
		    	cursor.moveToNext();
		    }
		    cursor.close();
		    db.close();
		    myDbHelper.close();
        }
        catch(SQLException sqle){
        	throw sqle; 
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
