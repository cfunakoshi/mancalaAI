
/**
 * State of a Mancala Game
 * 
 * @author Cheyne Funakoshi
 * @date 1/8/16
 */
public class State
{
    private int[] gameBoard; //Game Board
    public int player1Score;
    public int player2Score;

    /**
     * Constructor for objects of class State
     * 
     */
    public State()
    {
        gameBoard = new int[14]; //initialize Game Board - Player 1 = gameBoard[0] - gameBoard[6] Player 2 = gameBoard[7] - gameBoard[13]
        player1Score = 0;
        player2Score = 0;
        
        for(int i= 0; i < 6 ; i++)
        {
            gameBoard[i] = 4; //Adds 4 tokens to each bin for Player 1's Game Board
            gameBoard[i + 7] = 4; //Adds 4 tokens to each bin for Player 2's Game Board
        }
        
    }
    
    /**
     * Copy of the current state
     * 
     */
    public State(State copy)
    {
        this.gameBoard = copy.gameBoard.clone();
        this.player1Score = copy.player1Score;
        this.player2Score = copy.player2Score;
    }
    

    /**
     * Convert game State to string
     * Mancala Game Board:
     * 
     *    --1--2--3--4--5--6--
     *          Player 2
     *     [4][4][4][4][4][4]
     * P2 X                  X P1
     *     [4][4][4][4][4][4]
     *          Player 1
     *          
     *
     *@return game  state of game as string
     */
    public String toString()
    {
        String game = "";
        
        game += "   --1--2--3--4--5--6--" + "\n";
        game += "         Player 2" + "\n" + "    ";
        for(int b = 12; b > 6; b--)
        {
            game += "[" + gameBoard[b] + "]";
        }
        game += "\n";
        game += "P2 " + player2Score + "                  ";
        game += player1Score + " P1" + "\n" + "    ";
        for(int b = 0; b < 6; b++)
        {
            game += "[" + gameBoard[b] + "]";
        }
        game += "\n" + "         Player 1";
        game += "\n" + "\n";
        game += "--------------------------------------" + "\n";
        
        return game;
    }
    
    /**
     * Player move 
     * 
     * @param move  move that the current player selected
     * @param currPlayer    current player
     * 
     */
    public void move(int move, int currPlayer)
    {
        int tokens; //Number of Tokens in Bin That Player Selected
        int bin;    //Current Bin of Player's Game Board
        if(currPlayer == 1)
        {
            tokens = gameBoard[move - 1];
            gameBoard[move - 1] = 0;
            bin = move;
            
            for(int i = 0; i < tokens; i++)
            {
                if(bin == 13)
                {
                    gameBoard[bin] += 1;
                    int currBin = 0;
                    for(int j = i + 1; j < tokens; j++)
                    {
                            gameBoard[currBin] += 1;
                            currBin += 1;
                    }
                    break;
                }
                else
                {
                    gameBoard[bin] += 1;
                    bin += 1;
                }
            }
        }
        else
        {
            tokens = gameBoard[13 - move];
                gameBoard[13 - move] = 0;
                bin = 14 - move; 

                for(int i = 0; i < tokens; i++)
                {
                    if(bin == 13)
                    {
                        gameBoard[bin] += 1;
                        int currBin = 0;
                        for(int j = i + 1; j < tokens; j++)
                        {
                            gameBoard[currBin] += 1;
                            currBin += 1;
                        }
                        break;
                    }
                    else
                    {
                        gameBoard[bin] += 1;
                        bin += 1;
                    }
                }
        }
        
        player1Score = gameBoard[6];
        player2Score = gameBoard[13];
    }
    
    /**
     * Gets token amount from bin chosen
     * 
     * @param bin   bin to be evaluated
     * 
     */
    public int getTokens(int bin, int currPlayer)
    {
        if(currPlayer == 1)
        {
            int tokens = gameBoard[bin - 1];
            return tokens;
        }
        else
        {
            int tokens = gameBoard[13 - bin];
            return tokens;
        }
    }
    
    /**
     * Gets total number of tokens for the player
     * 
     * @param currPlayer   current player
     * 
     */
    public int getTotalTokens(int currPlayer)
    {
        if(currPlayer == 1)
        {
            int tokens = 0;
            for(int i = 0;i < 6; i++)
            {
                tokens += gameBoard[i];
            }
            return tokens;
        }
        else
        {
            int tokens = 0;
            for(int i = 7;i < 13; i++)
            {
                tokens += gameBoard[i];
            }
            return tokens;
        }
    }
    
    /**
     * Gets the score of the player
     * 
     * @param currPlayer    current player
     * 
     */
    public int getScore(int currPlayer)
    {
        player1Score = gameBoard[6];
        player2Score = gameBoard[13];
        
        if(currPlayer == 1)
        {            
            return player1Score;
        }
        else
        {
            return player2Score;
        }
    }
    
    /**
     * Determines if the game is Over
     * 
     */
    public boolean getTerminalStatus()
    {
        if(getScore(1) > 25 || getScore(2) > 25)
        {
            return true;
        }
                

        if(gameBoard[0] == 0 && gameBoard[1] == 0 && gameBoard[2] == 0 && gameBoard[3] == 0 && gameBoard[4] == 0 && gameBoard[5] == 0)
        {
            return true;
        }
        
        if(gameBoard[7] == 0 && gameBoard[8] == 0 && gameBoard[9] == 0 && gameBoard[10] == 0 && gameBoard[11] == 0 && gameBoard[12] == 0)
        {
            return true;
        }            
                        
        return false;
    }
    
    /**
     * Switches player's turn
     * 
     */
    public int otherPlayer(int currPlayer)
    {
        if(currPlayer == 1)
        {
            return 2;
        }
        else
        {
            return 1;
        }
    }
  }
