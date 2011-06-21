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
					
			this.mX = j*this.mWidth + Constants.MARGE_LEFT;
			this.mY = i*(this.mHeight - Constants.PIECE_UP) - m*Constants.PIECE_UP + Constants.MARGE_UP;
			
			this.mPhysicsHandler = new PhysicsHandler(this);
			this.registerUpdateHandler(this.mPhysicsHandler);
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
		        		// verif que les piece sont du meme type
	        			 if(GameScene.selectedPiece != null && GameScene.selectedPiece.type == this.type)
	        			 {
	        				 GameScene.removePiece(GameScene.selectedPiece);
	        				 GameScene.removePiece(this);
	        				 GameScene.selectedPiece = null;
	        			 }
	        			 else
		        		 // Pas de piece deja selectionnée ou pas du bon type
		        		 {
		        		 	// todo si piece deja pas selectionné sinon check que le type est idendique.
		        		 	isSelected = true;
		        		 	GameScene.selectedPiece = this;
		                	this.setScale(1.25f);
		        		 }		 
		             }
		        	 else
		        	 {
		        		 isSelected = false;
		        		 this.setScale(1.00f);
		        		 GameScene.selectedPiece = null;
		        	 }
	        	 }
	             break;
	        	 
		 }
		 
		 return true;
		 
	 
	 }
	
	
}
