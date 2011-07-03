package com.watashiwa.catjong;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;


import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.font.FontFactory;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;


public class GameScene extends Scene  {


	
	private TiledTextureRegion regionPieces[] = {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null};
	
	private Texture mFontTexture;
	
	private TextureRegion bgImage;
	
	private TextureRegion winMessage;
    private Font mFont;
	
    public int initBoardNumber; 
    
    private int initBoard1 [][] = 
	{
    		{ 1, 2, 2, 1},
    		{ 1, 2, 2, 1},
    		{ 1, 2, 2, 1},
    		{ 1, 2, 2, 1},
	};
    
    private int initBoard2 [][] =
    {
    	{ 0, 0, 0, 2, 0, 0, 2},
		{ 0, 0, 2, 2, 0, 2, 2},
		{ 0, 2, 3, 3, 3, 3, 2},
		{ 2, 2, 0, 2, 0, 2, 2},
		{ 3, 3, 3, 3, 3, 3, 2},
		{ 0, 1, 0, 1, 0, 0, 2},
		{ 0, 0, 0, 0, 0, 0, 2},
		{ 0, 1, 0, 1, 0, 2, 2},
		{ 3, 3, 3, 3, 2, 2, 2},
		{ 2, 2, 2, 2, 2, 2, 2},
	};
    
    private int initBoard3 [][] =
    {
    	{ 3, 3, 3, 3, 3, 3, 3},
    	{ 3, 0, 0, 0, 0, 0, 3},
    	{ 3, 0, 3, 2, 1, 0, 3},
    	{ 3, 0, 3, 2, 1, 0, 3},
    	{ 3, 0, 3, 2, 1, 0, 3},
    	{ 3, 0, 3, 2, 1, 0, 3},
    	{ 3, 0, 3, 2, 1, 0, 3},
    	{ 3, 0, 0, 0, 0, 0, 3},
    	{ 3, 3, 3, 3, 3, 3, 3},

	};
    
    private int initBoard4 [][] =
    {
    	{ 3, 0, 3, 3, 3, 0, 3},
    	{ 3, 0, 3, 0, 3, 0, 3},
    	{ 3, 0, 3, 0, 3, 0, 3},
    	{ 3, 0, 3, 0, 3, 0, 3},
    	{ 3, 0, 3, 0, 3, 0, 3},
    	{ 3, 0, 3, 0, 3, 0, 3},
    	{ 3, 0, 3, 0, 3, 0, 3},
    	{ 3, 0, 3, 0, 3, 0, 3},
    	{ 3, 3, 3, 3, 3, 3, 3},

	};
    
    private int initBoard5 [][] =
    {
    	{ 0, 2, 0, 0, 0, 2, 0},
    	{ 0, 0, 2, 0, 2, 0, 0},
    	{ 0, 2, 2, 2, 2, 2, 0},
    	{ 2, 2, 2, 2, 2, 2, 2},
    	{ 2, 2, 0, 2, 0, 2, 2},
    	{ 2, 2, 2, 2, 2, 2, 2},
    	{ 2, 0, 2, 2, 2, 0, 2},
    	{ 2, 0, 2, 0, 2, 0, 2},
    	{ 0, 0, 0, 2, 0, 0, 0},
	};
    
    private int initBoard6 [][] =
    {
    	{ 0, 0, 2, 2, 2, 0, 0},
    	{ 0, 2, 2, 2, 2, 2, 0},
    	{ 2, 2, 2, 2, 2, 2, 2},
    	{ 2, 2, 2, 2, 2, 0, 0},
    	{ 2, 2, 2, 0, 0, 0, 0},
    	{ 2, 2, 2, 2, 2, 0, 0},
    	{ 2, 2, 2, 2, 2, 2, 2},
    	{ 0, 2, 2, 2, 2, 2, 0},
    	{ 0, 0, 2, 2, 2, 0, 0},
	};
    
    
    private int initBoard7 [][] =
    {
    	{ 0, 0, 1, 1, 1, 0, 0},
    	{ 0, 1, 2, 2, 2, 1, 0},
    	{ 1, 2, 2, 2, 2, 2, 1},
    	{ 1, 2, 2, 2, 2, 2, 1},
    	{ 1, 2, 2, 2, 2, 2, 1},
    	{ 1, 2, 0, 2, 0, 2, 1},
    	{ 0, 1, 2, 2, 2, 1, 0},
    	{ 0, 0, 2, 1, 2, 0, 0},
    	{ 0, 0, 1, 0, 1, 0, 0},
	};
    
    private int initBoard8 [][] =
    {
    	{ 1, 2, 3, 2, 1},
    	{ 0, 0, 3, 0, 0},
    	{ 3, 4, 4, 4, 3},
    	{ 3, 4, 6, 4, 3},
    	{ 3, 4, 4, 4, 3},
    	{ 0, 0, 3, 0, 0},
    	{ 1, 2, 3, 2, 1},
    
	};
    
    private int initBoard9 [][] =
    {
    		{ 1, 0, 1, 0, 1, 0, 1},
    		{ 0, 1, 0, 1, 0, 1, 0},
    		{ 1, 0, 1, 0, 1, 0, 1},
    		{ 0, 1, 0, 1, 0, 1, 0},
    		{ 1, 0, 1, 0, 1, 0, 1},
    		{ 0, 1, 0, 1, 0, 1, 0},
    		{ 1, 0, 1, 0, 1, 0, 1},
    		{ 0, 1, 0, 1, 0, 1, 0},
    		{ 1, 0, 1, 0, 1, 0, 1},
        	
	};
    
    
    
    int initBoards [][][] = {initBoard1, initBoard2, initBoard3, initBoard4, initBoard5, initBoard6, initBoard7, initBoard8, initBoard9};
    
    private static Piece mBoard [][][];
    
    // Layer
	private static final int LAYER_BACKGROUND = 0;
    private static final int LAYER_PIECES = LAYER_BACKGROUND + 1;
    public static final int LAYER_SCORE = LAYER_PIECES + 10;
	
    public static Piece selectedPiece = null;
    public static Piece pieceToRemove = null;
    
    public static int numberOfTiles = 0;
    
    public int numberOfType = 30;
    
    private List<Integer> typeTileList = null;
    
    // Score
    public static int gameScore = 0;
	public ChangeableText mScoreText;
	public static boolean gameWin = false;

	private Sprite winSprite = null; 
	
	//Bar de temps
	private static int timeBar = Constants.INIT_TIME_BAR;
	Rectangle rectTimeBar = null;
	
	protected boolean mGameRunning = true;
	


	public static void main(String[] args) throws IOException {
		
	}


	
	
	public GameScene(final int pLayerCount) {
		super(pLayerCount);
		//initBoardNumber = p_initBoardNumber;
		
	}
	
	
	
	// Chargement des ressources (Font, Sprites, etc.)
	public void loadResources(final Engine engine, Context context) {
		
		// La font
		this.mFontTexture = new Texture(256, 256, TextureOptions.BILINEAR);
        this.mFont = FontFactory.createFromAsset(mFontTexture, context, "ARIAL.TTF", 32, true, Color.WHITE);

        // Ajout de la font
        engine.getTextureManager().loadTexture(mFontTexture);
        engine.getFontManager().loadFont(mFont);
		
        String namePiece = "";
        
        // Load toute les textures.
        for (int i = 0; i < this.numberOfType && i < regionPieces.length; i++)
        {
        	if (i < 10)
        	{
        		namePiece = "0" + i + ".png";
        	}
        	else
        	{
        		namePiece = i + ".png";
        	}
        		
        	final Texture texturePiece0 = new Texture(64, 128, TextureOptions.BILINEAR);
            regionPieces[i] = TextureRegionFactory.createTiledFromAsset(texturePiece0, context, namePiece, 0, 0, 1, 1);
            engine.getTextureManager().loadTexture(texturePiece0);
        }
        
		// BG
		final Texture mBackgroundTexture = new Texture(512, 1024, TextureOptions.BILINEAR);
		bgImage = TextureRegionFactory.createFromAsset(mBackgroundTexture, context, "decors.png", 0, 0);
	 
		final Texture mWinTexture = new Texture(256, 128, TextureOptions.BILINEAR);
		winMessage = TextureRegionFactory.createFromAsset(mWinTexture, context, "you_win.png", 0, 0);
		 
		// on ajoute toutes les textures
		engine.getTextureManager().loadTextures(mBackgroundTexture, mWinTexture);
		
		init();
	}

	

	public static Boolean canSelectPiece(Piece p)
	{
		
		
		if (p.m + 1 > Constants.NUMBER_PIECE_Z_MAX || mBoard[p.m + 1][p.i][p.j] == null)
		{
			
			if (p.j + 1 > Constants.NUMBER_PIECE_X_MAX || p.j - 1 < 0)
			{
				return true;
			}
			
			if (mBoard[p.m][p.i][p.j - 1] == null || mBoard[p.m][p.i][p.j + 1] == null)
			{
				return true;
			}
				
		}
		
		return false;
	}
	
	
	private void initBoard()
	{
		for(int m = 0; m < Constants.NUMBER_PIECE_Z_MAX; m++)
		{
			for(int i = 0; i < Constants.NUMBER_PIECE_Y_MAX; i++)
			{
				for(int j = 0; j < Constants.NUMBER_PIECE_X_MAX; j++)
				{
					mBoard[m][i][j] = null;
				}
			}
		}
	}

	// Construit le tableau
	private void buildBoard()
	{
		
		int initBoard [][] = initBoards[initBoardNumber];
		
		
		//int m = 0;
		Piece piece1 = null;
		
		int type,  mMax = 0;
		numberOfTiles = 0;
		 
		// init du board
		mBoard = new Piece [Constants.NUMBER_PIECE_Z_MAX][Constants.NUMBER_PIECE_Y_MAX][Constants.NUMBER_PIECE_X_MAX];
		initBoard();
		 
		//get le nombre de tile (piece), oui c'est pas super optimisé tout ca
		for(int i = 0; i <  initBoard.length ; i++)
		{
			for(int j = initBoard[i].length - 1; j >= 0  ; j--)
			{
				if (initBoard[i][j] > mMax )
				{
					mMax = initBoard[i][j];
				}
				
				for(int m = initBoard[i][j]; m > 0 ; m--)
				{
					numberOfTiles ++;
				}
				
			}
		}
		
		
		typeTileList = new ArrayList<Integer>();
		
		// init des types en fonction du nombre de tiles (divisé par deux car que des doubles)
		for (int i = 0; i <  numberOfTiles/2 ; i++)
		{
			// todo prendre en compte le nombre de doublons max
			//type = randomGenerator.nextInt(6);
			type = i % numberOfType;
			typeTileList.add(type);
			typeTileList.add(type);
		}
		// on melange les pieces
		Collections.shuffle(typeTileList);
		
		Iterator<Integer> itr = typeTileList.iterator();
		
		// on fait la "vraie" init du board
		for(int i = 0; i <  initBoard.length ; i++)
		{
			for(int j = initBoard[i].length - 1; j >= 0  ; j--)
			{
				for(int m = initBoard[i][j]; m > 0 ; m--)
				{
					type = itr.next();
					
					piece1 = new Piece (regionPieces[type], i, j, m, type);
					mBoard[m][i][j] = piece1;
					
					displayPiece(piece1);
					// Test si on doit afficher la piece (dessus ou rien devant)
					/*if (m == mMax || mBoard[m][i+1][j]  == null)
					{
						
					}*/
				}
				
			}
		}
		
		
	}
	
	
	public void displayPiece(Piece p_piece)
	{
		this.getChild(LAYER_PIECES + p_piece.m).attachChild(p_piece);
		this.registerTouchArea(p_piece);
	}
	
	private void init()
	{
		// Creation du BG
		final Sprite myBackground = new Sprite(0, 0, 480, 800, bgImage);
		this.getChild(LAYER_BACKGROUND).attachChild(myBackground);
		
       
        /* Le score */
        this.mScoreText = new ChangeableText(Constants.CAMERA_WIDTH/2 + 50, 15, mFont, "Score : 0", "Score: XXXX".length());
        this.mScoreText.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        //this.mScoreText.setAlpha(0.5f);
        this.mScoreText.setColor(1.0f, 1.0f, 1.0f);
        this.getChild(LAYER_SCORE).attachChild(mScoreText);
		
        // La bar de temps
        rectTimeBar = new Rectangle(20, 30, 2*timeBar, 20);
        rectTimeBar.setColor(1.0f, 0.5f, 0.0f);
        this.getChild(LAYER_SCORE).attachChild(rectTimeBar);
		
        // le message de win
	     winSprite = new Sprite(-100, -100, winMessage) {
				@Override
				public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) 
				{
					 switch(pSceneTouchEvent.getAction()) 
					 {
				         case TouchEvent.ACTION_DOWN:
				        	 //initGameSession ();
				        	 // Relaunch the Catjong activity
				        	 reload(); 
				        	 
				             break;
					 }
					 return true;
				}
			};
			
			  this.registerTouchArea(winSprite);
			  this.getChild(LAYER_SCORE).attachChild(winSprite);
        
			  // fait baisser la jauge de temps toute les 0.1 seconde
		        this.registerUpdateHandler(new TimerHandler(0.1f, true, new ITimerCallback()
		        {
					@Override
					public void onTimePassed(final TimerHandler pTimerHandler)
					{
						if(timeBar > 0 && !gameWin)
						{
							timeBar --;
							 // maj de la taille de la bar
							 rectTimeBar.setWidth(Constants.INIT_TIME_BAR_SIZE*timeBar);
						}
					}
				}));
			  
		initGameSession ();
	
	}
	
	// Init des données de Jeux
	private void initGameSession ()
	{	
        // initialisation du board
        buildBoard();
        timeBar = Constants.INIT_TIME_BAR;
        gameWin = false;
        gameScore = 0;
        
	}
	

	

	 public boolean onSceneTouchEvent(final TouchEvent pSceneTouchEvent, final ITouchArea pTouchArea, final float pTouchAreaLocalX, final float pTouchAreaLocalY)
	 {
		boolean ret = super.onSceneTouchEvent(pSceneTouchEvent);
		
		//this.mScoreText.setText("Score: " + gameScore);
		
		
		
		
		return ret;
     }

	 //TODO faire une vraie suppression de sprite
	 public static void removePiece(Piece piece)
	 {
		 
		 if ( piece != null)
	     {	
			 piece.isAlive = false;
			 piece.setPosition(-100, -100);
			 mBoard[piece.m][piece.i][piece.j] = null;
			 
		 }
	  }
	
	 public static void removePieces (Piece pPiece1, Piece pPiece2)
	 {
		 removePiece(pPiece1);
	 	 removePiece(pPiece2);
		 selectedPiece = null;
		 
		 //calcul du score
		 if (timeBar >= 50)
		 {
			 gameScore += 10;
		 }
		 else if (timeBar > 0)
		 {
			 gameScore += 5;
		 }
		 else
		 {
			 gameScore += 1;
		 }
		 
		// gameScore += 1 * timeBar;
		 timeBar = Constants.INIT_TIME_BAR;
		 
		 numberOfTiles -= 2;
		 if (numberOfTiles == 0)
		 {
			 gameWin = true;
			 
		 }
	 }
	 
	 protected void onManagedUpdate(final float pSecondsElapsed)
	 { 
		if (gameWin)
		{
			winPhase();
		}
		this.mScoreText.setText("Score: " + gameScore);
		super.onManagedUpdate(pSecondsElapsed);
	}
	 
	 private void winPhase ()
	 {	
	     winSprite.setPosition(Constants.CAMERA_WIDTH/2 - winSprite.getWidth()/2, Constants.CAMERA_HEIGHT/2 - winSprite.getWidth()/2);
	 }
	 
	 
	   public void reload() 
	   {
		   initGameSession ();
		   winSprite.setPosition(-100, -100);
		}
}
