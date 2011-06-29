package com.watashiwa.catjong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectBoard extends Activity {

	Button button1;
	Button button2;
	Button button3;
	
	
	 public void onCreate(Bundle icicle)
	    {
	    	super.onCreate(icicle);
	    	setContentView(R.layout.selectboard);
	    	
	    	
	    	button1 = (Button) findViewById(R.id.button1);
	    	button2 = (Button) findViewById(R.id.button2);
	    	button3 = (Button) findViewById(R.id.button3);
	    	
	    	 
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
	    }
	 
	 public void launchGameSession(int board)
	 {
		 Intent intent;
		 
		 intent = new Intent(this, Catjong.class);
		 
		 
		 
		 // Ajout d'un parametre supplementaire pour l'id du board a lancer
		 intent = intent.putExtra("initboard", board);
		 this.startActivity(intent);
	    	//this.finish();
	 }
	 
	 

	
}
