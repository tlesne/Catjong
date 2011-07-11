package com.watashiwa.catjong;

import org.anddev.andengine.engine.handler.physics.PhysicsHandler;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.scene.Scene;
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
	
	private boolean isInFinalXPos = false;
	private boolean isInFinalYPos = false;
	
	private boolean isSelected = false;
	public boolean isAlive = true;
	
	public Rectangle shadow = null;
	
	public int type = 0;
	
	public Piece(float pX, float pY, TiledTextureRegion pTiledTextureRegion)
	{
			super(pX, pY, pTiledTextureRegion);
			
			this.mPhysicsHandler = new PhysicsHandler(this);
			this.registerUpdateHandler(this.mPhysicsHandler);
	}
	
	public Piece(TiledTextureRegion pTiledTextureRegion, int pi, int pj, int pm, int ptype, GameScene gameScene)
	{
			super( 0, 0 , pTiledTextureRegion);
			
			this.i = pi;
			this.j = pj;
			this.m = pm;
			this.type = ptype;
					
			destX = j*this.mWidth + Constants.MARGE_LEFT;
			destY = i*(this.mHeight - Constants.PIECE_UP) - m*Constants.PIECE_UP + Constants.MARGE_UP;
			
			this.mX = Constants.CAMERA_WIDTH/2;
			this.mY = Constants.CAMERA_HEIGHT + 50;
			
			// todo faire une fonction d'affichage du bas vers le haut
			this.mX = destX;
			this.mY = destY;
			
			//if (gameScene.mustDisplayShadow(this))
			if (this.m > 0)
			{
				shadow = new Rectangle(destX, destY + this.mHeight, this.mWidth, this.mHeight/8);
				shadow.setColor(0.0f,0.0f, 0.0f);
				shadow.setAlpha(0.40f);
				gameScene.getChild(GameScene.LAYER_PIECES + pm).attachChild(shadow);
			}
			
			
			this.mPhysicsHandler = new PhysicsHandler(this);
			this.registerUpdateHandler(this.mPhysicsHandler);
			//this.mPhysicsHandler.setVelocity(Constants.DEMO_VELOCITY, Constants.DEMO_VELOCITY);
			
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
	
	protected void onManagedUpdate(final float pSecondsElapsed)
	{
		/*if(isAlive)
		{
			if (this.mX != destX)
			{
				if(this.mX < destX)
				{
					this.mPhysicsHandler.setVelocityX(Constants.DEMO_VELOCITY);
				}
				else if(this.mX > destX)
				{
					this.mPhysicsHandler.setVelocityX(-Constants.DEMO_VELOCITY);
				}
				else
				{
					isInFinalXPos = true;
				}
			}
	
			if (this.mY != destY)
			{
				if(this.mY < destY)
				{
					this.mPhysicsHandler.setVelocityY(Constants.DEMO_VELOCITY);
				}
				else if(this.mY > destY)
				{
					this.mPhysicsHandler.setVelocityY(-Constants.DEMO_VELOCITY);
				}
				else
				{
					isInFinalYPos = true;
				}
			}
		}*/
		super.onManagedUpdate(pSecondsElapsed);
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
		        				 GameScene.removePieces(this, GameScene.selectedPiece);
		        				 
		        				 
		        			 }
		        			 else
		        			 {
		        				 // pas du bon type
		        				// this.deSelectMe();
		        				 // GameScene.selectedPiece.deSelectMe();
		        				 
		        				 // deselectionne l'autre piece
		        				 GameScene.selectedPiece.deSelectMe();
				        		GameScene.selectedPiece = null;
				        		// et selectionne moi
				        		 selectMe();
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
