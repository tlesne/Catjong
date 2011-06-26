package com.watashiwa.catjong;


import com.watashiwa.catjong.R;
import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends Activity {

	Button newGame;
	Button options;
	
	 public void onCreate(Bundle icicle)
	    {
	    	super.onCreate(icicle);
	    	setContentView(R.layout.main);
	    	
	    	newGame = (Button) findViewById(R.id.new_game);
	    	options = (Button) findViewById(R.id.options);
	    	
	    	 
	    	newGame.setOnClickListener(new View.OnClickListener() {
	            	public void onClick(View v) {
	            		launchGameSession();
	            	}
	            });
	    	
	    	options.setOnClickListener(new View.OnClickListener() {
            	public void onClick(View v) {
            		launchOptions();
            	}
            });
	    }
	
	 public void launchGameSession()
	 {
		 this.startActivity(new Intent(this, Catjong.class));
		
	    	//this.finish();
	 }
	 
	 public void launchOptions()
	 {
		 this.startActivity(new Intent(this, Options.class));
		
	    	//this.finish();
	 }
	
}
