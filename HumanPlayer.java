
/**
 * Human player
 * 
 * @author Cheyne Funakoshi 
 * @date 1/8/16
 */
public class HumanPlayer extends Player
{

    private final java.util.Scanner input;

    /**
     * Constructor for objects of class HumanPlayer
     */
    public HumanPlayer()
    {
        super();
        input = new java.util.Scanner(System.in);
    }

    /**
     * Gets the move of the human player
     * 
     * @param  currentState   the current state of the game
     * @return     returns the bin choosen
     */
    @Override
    protected int getMove(State currentState)
    {
        System.out.println("Choose a bin: ");
        
        int bin = 0;
        try
        {
            bin = input.nextInt();
        }
        catch(java.util.InputMismatchException e)
        {
            input.nextLine();
            responseToInvalid();
            getMove(currentState);
        }
        
        return bin;
    }
    
    /**
     * Responds to an Invalid Move
     * 
     */
    @Override
    protected void responseToInvalid()
    {
        System.out.println("You can't move from there!");
    }
}
