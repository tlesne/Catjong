package com.watashiwa.catjong;

import java.io.IOException;

import javax.microedition.khronos.opengles.GL10;



import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.font.FontFactory;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import android.content.Context;
import android.graphics.Color;


public class GameScene extends Scene {


	
	private TiledTextureRegion regionPieces[] = {null, null, null, null};
	
	private Texture mFontTexture;
    private Font mFont;
	
  /*  private int initBoard [][] = 
    	{
    		{ 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0 }, 
    		{ 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0 },
    		{ 0, 0, 1, 2, 2, 2, 2, 2, 1, 0, 0 },
    		{ 0, 1, 2, 0, 2, 3, 2, 0, 2, 1, 0 },
    		{ 1, 2, 2, 3, 3, 3, 3, 3, 2, 2, 1 }, 
    		{ 1, 0, 1, 2, 2, 2, 2, 2, 1, 0, 1 }, 
    		{ 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1 }, 
    		{ 0, 0, 0, 2, 3, 0, 3, 2, 0, 0, 0 }
    	};
    
    */
    
    private int initBoard [][] = 
	{
		{ 2, 2, 2, 2, 2, 2},
		{ 2, 0, 0, 0, 0, 2}, 
		{ 2, 0, 2, 2, 0, 2}, 
		{ 2, 0, 2, 2, 0, 2}, 
		{ 2, 0, 2, 2, 0, 2}, 
		{ 2, 0, 0, 0, 0, 2}, 
		{ 2, 2, 2, 2, 2, 2}, 
		
	};
    
    private static Piece mBoard [][][];
    
    // Layer
	private static final int LAYER_BACKGROUND = 0;
    private static final int LAYER_PIECES = LAYER_BACKGROUND + 1;
    public static final int LAYER_SCORE = LAYER_PIECES + 10;
	
    public static Piece selectedPiece = null;
    public static Piece pieceToRemove = null;
    
    
    // Score
    public static int gameScore = 0;
	private ChangeableText mScoreText;

	
	protected boolean mGameRunning = true;
	


	public static void main(String[] args) throws IOException {
		
	}

	
	public GameScene(final int pLayerCount) {
		super(pLayerCount);
	}
	
	// Chargement des ressources (Font, Sprites, etc.)
	public void loadResources(final Engine engine, Context context) {
		
		// La font
		this.mFontTexture = new Texture(256, 256, TextureOptions.BILINEAR);
        this.mFont = FontFactory.createFromAsset(mFontTexture, context, "ARIAL.TTF", 32, true, Color.WHITE);

        // Ajout de la font
        engine.getTextureManager().loadTexture(mFontTexture);
        engine.getFontManager().loadFont(mFont);
		
        final Texture texturePiece0 = new Texture(64, 128, TextureOptions.BILINEAR);
        regionPieces[0] = TextureRegionFactory.createTiledFromAsset(texturePiece0, context, "piece1.png", 0, 0, 1, 1);
        
        engine.getTextureManager().loadTextures(texturePiece0);
		
		// BG
		/*final Texture mBackgroundTexture = new Texture(512, 1024, TextureOptions.BILINEAR);
		bgImage = TextureRegionFactory.createFromAsset(mBackgroundTexture, context, "bg_test1.png", 0, 0);
	 */
		// on ajoute toutes les textures
	//	engine.getTextureManager().loadTextures(mBackgroundTexture /*, textureDroid0, textureDroid1, textureDroid2, textureDroid3 */);
		
      
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

	private void buildBoard()
	{
		//int m = 0;
		Piece piece1 = null;
		
	//	myFinalBoard = new ArrayList<Piece[][]>();
		 
		mBoard = new Piece [Constants.NUMBER_PIECE_Z_MAX][Constants.NUMBER_PIECE_Y_MAX][Constants.NUMBER_PIECE_X_MAX];
		initBoard();
		 
		for(int i = 0; i <  initBoard.length ; i++)
		{
			for(int j = initBoard[i].length - 1; j >= 0  ; j--)
			{
				//mBoard[i] =  new Piece [initBoard[i].length];
				for(int m = initBoard[i][j]; m > 0 ; m--)
				{
					piece1 = new Piece (regionPieces[0], i, j, m, 0);
					mBoard[m][i][j] = piece1;
					
				    this.getChild(LAYER_PIECES + m).attachChild(piece1);
				    this.registerTouchArea(piece1);
				}
				
			}
		}
		
	}
		
	
	// Init des données de Jeux
	private void init ()
	{	
		// Creation du BG
		/*final Sprite myBackground = new Sprite(0, 0, 480, 800, bgImage);
		 this.getChild(LAYER_BACKGROUND).attachChild(myBackground);
		*/
       
        /* Le score */
        this.mScoreText = new ChangeableText(Constants.CAMERA_WIDTH/2, 5, mFont, "Score: 0", "Score: XXXX".length());
        this.mScoreText.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        //this.mScoreText.setAlpha(0.5f);
        this.mScoreText.setColor(0.1f, 0.1f, 0.9f);
        this.getChild(LAYER_SCORE).attachChild(mScoreText);
  
        
        buildBoard();
    
       
        
	}
	

	

	 public boolean onSceneTouchEvent(final TouchEvent pSceneTouchEvent)
	 {
		boolean ret = super.onSceneTouchEvent(pSceneTouchEvent);
		
		
		
		 
		return ret;
     }

	 //TODO faire une vraie suppression de sprite
	 public static void removePiece(Piece piece)
	 {
		 if ( piece != null)
	     {	
			 piece.setPosition(-100, -100);
			 mBoard[piece.m][piece.i][piece.j] = null;
			 
		 }
	  }
	

	
}
