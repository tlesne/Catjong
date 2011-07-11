package com.watashiwa.catjong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.Spinner;
import android.widget.Toast;

public class SelectBoard extends Activity {
	
	Button button1;
	Button button2;
	Button button3;
	Button button4;
	Button button5;
	Button button6;
	Button button7;
	Button button8;
	Button button9;
	
	private int numberOfTypes = 6;
	
	 public void onCreate(Bundle icicle)
	    {
	    	super.onCreate(icicle);
	    	setContentView(R.layout.selectboard);
	    	
	    	
	    	button1 = (Button) findViewById(R.id.button1);
	    	button2 = (Button) findViewById(R.id.button2);
	    	button3 = (Button) findViewById(R.id.button3);
	    	button4 = (Button) findViewById(R.id.button4);
	    	button5 = (Button) findViewById(R.id.button5);
	    	button6 = (Button) findViewById(R.id.button6);
	    	button7 = (Button) findViewById(R.id.button7);
	    	button8 = (Button) findViewById(R.id.button8);
	    	button9 = (Button) findViewById(R.id.button9);

	    	
	    	// setup de la dropdown Box
	    	Spinner spinner = (Spinner) findViewById(R.id.spinner);
	        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
	                this, R.array.numbers_array, android.R.layout.simple_spinner_item);
	        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        spinner.setAdapter(adapter);
	        spinner.setSelection(19);
	        
	        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
	        	 public void onItemSelected(AdapterView<?> parent,
	        		        View view, int pos, long id) {
	        		      Toast.makeText(parent.getContext(), "Nombre de doublons : " +
	        		          parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
	        		      numberOfTypes =  Integer.parseInt(parent.getItemAtPosition(pos).toString());
	        	 
	        	 		}
	        	 public void onNothingSelected(AdapterView parent) {
	        	      // Do nothing.
	        	    }
	        
	        });
	    	
	    	 
	    	button1.setOnClickListener(new View.OnClickListener() {
	            	public void onClick(View v) {
	            		launchGameSession(0);
	            	}
	            });
	    	
	    	button2.setOnClickListener(new View.OnClickListener() {
            	public void onClick(View v) {
            		launchGameSession(1);
            	}
            });
	    	button3.setOnClickListener(new View.OnClickListener() {
            	public void onClick(View v) {
            		launchGameSession(2);
            	}
            });
	    	
	    	button4.setOnClickListener(new View.OnClickListener() {
            	public void onClick(View v) {
            		launchGameSession(3);
            	}
            });
    	
    	button5.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		launchGameSession(4);
        	}
        });
    	button6.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		launchGameSession(5);
        	}
        });
    	button7.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		launchGameSession(6);
        	}
        });
    	
    	button8.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		launchGameSession(7);
        	}
        });
    	button9.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		launchGameSession(8);
        	}
        });
	    
	    	
	    }
	 
	 public void launchGameSession(int board)
	 {
		 Intent intent;
		 
		 intent = new Intent(this, Catjong.class);
		 
		 
		 
		 // Ajout d'un parametre supplementaire pour l'id du board a lancer
		 intent = intent.putExtra("initboard", board);
		 // Ajout d'un parametre supplementaire pour le nombre de doublons (debug)
		 intent = intent.putExtra("numberOfTypes", numberOfTypes);
		 
		 
		 this.startActivity(intent);
	    	//this.finish();
	 }
	 
	 

	
}
