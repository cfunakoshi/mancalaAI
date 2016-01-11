
/**
 * Mancala Game
 * 
 * @author Cheyne Funakoshi 
 * @date 1/8/16
 */
public class Game
{
    private State currentState; //state of the game
    private boolean terminalStatus;   //determines if state of game is terminal
    private Player player1; //Player 1
    private Player player2; //Player 2
    private int playerMove; //player's move
    private int currPlayer; //current player
    
    /**
     * Constructor for objects of class Game
     * 
     */
    public Game(Player player1, Player player2)
    {
        this.player1 = player1;
        this.player2 = player2;
        
        reset();
    }
    
    /**
     * Starts a game between human and AI
     *  
     */
    public static void HumanvsAI()
    {
        Game game = new Game(new HumanPlayer(), new AI());
        game.play();
    }
    
    /**
     * Starts a game between human and human
     * 
     */
    public static void HumanvsHuman()
    {
        Game game = new Game(new HumanPlayer(), new HumanPlayer());
        game.play();
    }
    
    /**
     * Resets the game
     * 
     */
    protected void reset()
    { 
        this.currentState = new State();
    }
    
    /**
     * Begins the Mancala Game
     * 
     */
    public void play()
    {
        reset();    //creates a new game
        
        boolean gameOver = false;   //determines if game is over
        currPlayer = 1;
        
        System.out.println("  Lets play Mancala: ");
        while(gameOver != true)
        {
            System.out.println("============================");
            System.out.println(currentState.toString());
            System.out.println("Player " + currPlayer + ", your move: ");;
            playerMove = getMove(currPlayer);
            currentState.move(playerMove, currPlayer);
            if(currentState.getTerminalStatus() == true)
            {
                gameOver = true;    //game is over
            }
            else
            {
                otherPlayer();
            }
        }
        
        int player1Score = currentState.player1Score + currentState.getTotalTokens(1);
        int player2Score = currentState.player2Score + currentState.getTotalTokens(2);
        
        //declare winner!!!
        if(player1Score > player2Score)
        {
            System.out.println("Player 1 Wins!");
        }
        else
        {
            System.out.println("Player 2 Wins");
        }
    }
    
    /**
     * returns the player's move
     * 
     * @param currPlayer    current player
     * 
     */
    public int getMove(int currPlayer)
    {
        int move;
        if(currPlayer == 1)
        {
            move = player1.getMove(currentState);
            
            if(currentState.getTokens(move, 1) == 0)
            {
                System.out.println("You can't choose from an empty bin.");
                move = getMove(currPlayer);
            }
        }
        else
        {
            move = player2.getMove(currentState);
            
            if(currentState.getTokens(move, 2) == 0)
            {
                System.out.println("You can't choose from an empty bin.");
                move = getMove(currPlayer);
            }
        }
        return move;
    }
        
    /**
     * Switches player's turn
     * 
     */
    public void otherPlayer()
    {
        if(currPlayer == 1)
        {
            currPlayer = 2;
        }
        else
        {
            currPlayer = 1;
        }
    }
}