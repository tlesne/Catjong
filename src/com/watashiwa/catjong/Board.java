package com.watashiwa.catjong;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;



// NE PAS UTILISER
// EN COURS POUR CLEANAGE DU CODE
public class Board {

	private static Piece mBoard [][][];
	
    public static int numberOfTiles = 0;
    
    public int numberOfType = 30;
    
    private List<Integer> typeTileList = null;

    // Constructeur
    public Board ()
    {
    	
    }
    
    // init du board a null partout
    // TODO faire un tableau de la bonne taille
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
    
    
    // Est ce que la piece est selectionnable
    public static Boolean canSelectPiece(Piece p)
	{
		// Sur la "premiere couche" ?
		if (p.m + 1 > Constants.NUMBER_PIECE_Z_MAX || mBoard[p.m + 1][p.i][p.j] == null)
		{
			// completement a droite ou case de droite libre ?
			if (p.j + 1 > Constants.NUMBER_PIECE_X_MAX || p.j - 1 < 0)
			{
				return true;
			}
			// completement a gauche ou case de gauche libre ?
			if (mBoard[p.m][p.i][p.j - 1] == null || mBoard[p.m][p.i][p.j + 1] == null)
			{
				return true;
			}		
		}
		return false;
	}
	
    
 // Construit le tableau
	private void buildBoard(int[][] initBoard )
	{
		
		//int initBoard [][] = initBoards[initBoardNumber];
		
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
					
				//	piece1 = new Piece (GameScene.regionPieces[type], i, j, m, type);
				//	mBoard[m][i][j] = piece1;
					
				//	GameScene.displayPiece(piece1);
					// Test si on doit afficher la piece (dessus ou rien devant)
					/*if (m == mMax || mBoard[m][i+1][j]  == null)
					{
						
					}*/
				}
				
			}
		}
		
		
	}
    
    
}
