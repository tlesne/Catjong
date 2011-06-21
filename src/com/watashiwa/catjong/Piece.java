package com.watashiwa.catjong;

import org.anddev.andengine.engine.handler.physics.PhysicsHandler;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

public class Piece extends AnimatedSprite {

	private PhysicsHandler mPhysicsHandler;
	
	public int i = 0;
	public int j = 0;
	public int m = 0;
	
	private float destX = 0;
	private float destY = 0;
	
	private boolean isSelected = false;
	public boolean isAlive = true;
	
	private int type = 0;
	
	public Piece(float pX, float pY, TiledTextureRegion pTiledTextureRegion)
	{
			super(pX, pY, pTiledTextureRegion);
			
			this.mPhysicsHandler = new PhysicsHandler(this);
			this.registerUpdateHandler(this.mPhysicsHandler);
	}
	
	public Piece(TiledTextureRegion pTiledTextureRegion, int pi, int pj, int pm, int ptype)
	{
			super( 0, 0 , pTiledTextureRegion);
			
			this.i = pi;
			this.j = pj;
			this.m = pm;
			this.type = ptype;
					
			destX = j*this.mWidth + Constants.MARGE_LEFT;
			destY = i*(this.mHeight - Constants.PIECE_UP) - m*Constants.PIECE_UP + Constants.MARGE_UP;
			
			this.mX = 0;
			this.mY = 0;
			
			// todo faire une fonction d'affichage du bas vers le haut
			this.mX = destX;
			this.mY = destY;
			
			this.mPhysicsHandler = new PhysicsHandler(this);
			this.registerUpdateHandler(this.mPhysicsHandler);
	}
	
	
	public void deSelectMe()
	{
		if (isSelected)
		{
			isSelected = false;
			this.setScale(1.00f);
			GameScene.selectedPiece = null;
		}
	}
	
	private void selectMe()
	{
		isSelected = true;
	 	GameScene.selectedPiece = this;
    	this.setScale(1.25f);
	}
	
	 public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) 
	 {
		 switch(pSceneTouchEvent.getAction()) 
		 {
	         case TouchEvent.ACTION_DOWN:
	        	 if (GameScene.canSelectPiece(this))
	        	 {
	        		 
		        	 if (!this.isSelected )
		             {
		        		 if(GameScene.selectedPiece != null )
		        		 {
		        			// verif que les piece sont du meme type
		        			 if (GameScene.selectedPiece.type == this.type)
		        			 {
		        				 GameScene.removePiece(GameScene.selectedPiece);
		        				 GameScene.removePiece(this);
		        				 GameScene.selectedPiece = null;
		        				 GameScene.gameScore += 1;
		        			 }
		        			 else
		        			 {
		        				 // pas du bon type
		        				 this.deSelectMe();
		        				 GameScene.selectedPiece.deSelectMe();
		        			 }
		        		 }
		        		 // Pas de piece deja selectionnée
		        		 else
		        		 {
		        			 selectMe();
		        		 }
		        		
		             }
		        	 else
		        	 {
		        		 
		        		 this.deSelectMe();
		        		// theoriquement GameScene.selectedPiece == this mais on sait jamais
		        		 if (GameScene.selectedPiece != null)
		        		 {
		        			 GameScene.selectedPiece.deSelectMe();
		        			 GameScene.selectedPiece = null;
		        		 }
		        	 }
	        	 }
	             break;
	        	 
		 }
		 
		 return true;
		 
	 
	 }
	
	
}
