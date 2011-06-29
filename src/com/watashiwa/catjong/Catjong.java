package com.watashiwa.catjong;



import java.io.IOException;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.font.FontFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.content.Intent;
import android.os.Bundle;

public class Catjong extends BaseGameActivity {

	private Camera camera;
	
    private GameScene scene;
    
   
    
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
    	// recupere les parametre de l'activity précedentes
    	Intent i = getIntent();
    	int initBoardNumber = i.getIntExtra("initboard", 0);
    	
    	if (scene != null)
    	{
    		scene.initBoardNumber = initBoardNumber;
    	}
	}
    
    
    public Scene onLoadScene() {
		
		this.mEngine.registerUpdateHandler(new FPSLogger());

		
        scene.setTouchAreaBindingEnabled(true);
		return scene;

	}
  
	//init
	public Engine onLoadEngine() {

		camera = new Camera(0,0,Constants.CAMERA_WIDTH, Constants.CAMERA_HEIGHT);
		
		scene = new GameScene(Constants.LAYER_COUNT);
		
		return new Engine(new EngineOptions(true, ScreenOrientation.PORTRAIT, new RatioResolutionPolicy(Constants.CAMERA_WIDTH, Constants.CAMERA_HEIGHT),camera));

	}
    
    
	// Initialisation des répertoires d'assets
	public void onLoadResources() {
		 /* La Font. */
        FontFactory.setAssetBasePath("font/");
       
        // Les sprites
		TextureRegionFactory.setAssetBasePath("spr/");
        
		scene.loadResources(getEngine(), this);

	    
        /*Utils test = new Utils();
		test.setInputFile("c:/temp/lars.xls");
		try 
		{ 
			test.read();
		}
		catch (IOException e)
		{
			System.out.println("Erreur de lectue de fichier EXCEL");
		}*/
		
	}

	public boolean onSceneTouchEvent(final Scene pScene, final TouchEvent pSceneTouchEvent) {
		/* Removing entities can only be done safely on the UpdateThread.
		 * Doing it while updating/drawing can
		 * cause an exception with a suddenly missing entity.
		 * Alternatively, there is a possibility to run the TouchEvents on the UpdateThread by default, by doing:
		 * engineOptions.getTouchOptions().setRunOnUpdateThread(true);
		 * when creating the Engine in onLoadEngine(); */
		this.runOnUpdateThread(new Runnable() {
			@Override
			public void run() {
				/* Now it is save to remove the entity! */
				if (GameScene.selectedPiece != null)
				{
					scene.detachChild(GameScene.selectedPiece);
				}
				
			}
		});
		
		if (scene.gameWin)
		{
			reload();
		}
		
		return false;
	}
	
	
	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub
		
	}

	  public void reload()
	  {

		    Intent intent = getIntent();
		    overridePendingTransition(0, 0);
		    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		    finish();

		    overridePendingTransition(0, 0);
		    startActivity(intent);
		}
	
} 