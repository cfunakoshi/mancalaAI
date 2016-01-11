import java.util.ArrayList;
/**
 * AI player
 * 
 * @author Cheyne Funakoshi 
 * @date 1/8/16
 */
public class AI extends Player
{
    private int move;   //AI's move

    /**
     * Constructor for objects of class AI
     */
    public AI()
    {
        super();
    }

    /**
     * Gets the move of the AI
     * 
     * @param  currentState   Current State that AI will evaluate
     * @return     move of the AI
     */
    @Override
    protected int getMove(State currentState)
    {
        System.out.println("Choose a bin: ");
        int depth = 6;  //depth of the tree
        ArrayList<State> expanded = expand(currentState, 2);    //list of states for each move
        int[] moves = new int[expanded.size()]; //array of moves
        int[] value = new int[expanded.size()]; //array of values
        
        for(int i = 0;i < expanded.size(); i++)
        {
            moves[i] = i + 1;
            if(expanded.get(i) == null)
            {
                value[i] = 0;   //adds 0 if state is null
            }
            else
            {
                value[i] = miniMax(expanded.get(i), depth, 2);  //runs minimax on all possible states
            }
        }
        
        int choices = value.length - 1; //choices of states in expanded
        int choice = 5;
        
        for(int i = 0;i < value.length; i++)
        {
            System.out.println(value[i]);
            System.out.println(moves[i]);
        }
        while(choices != 0) //sets move to the best value given from minimax 
        {
            if(value[choices] == 0)
            {                
            }
            else if(value[choice] > value[choices - 1])
            {
                move = moves[choice]; 
            }
            else
            {
                move = moves[choices - 1];
                choice = choices - 1;
            }
            
            choices -= 1;
        }
        
        System.out.println(move);
        return move;
    }

    /**
     * Expands the current game state
     * 
     * @param currentState  current state of the game
     * @currPlayer currPlayer   current player
     * 
     * @return  returns expanded list
     * 
     */ 
    protected ArrayList<State> expand(State currentState, int currPlayer)
    {
        ArrayList<State> expanded = new ArrayList<State>();    //tree of game states
        State copy = new State(currentState);  //copy of current state

        for(int i = 1; i < 7; i++)
        {
            if(copy.getTokens(i, currPlayer) != 0)
            {
                copy.move(i, currPlayer);  //makes move i to current game state
                expanded.add(copy); 
                copy = new State(currentState);                
            }
            else
            {
                expanded.add(null);
            }
        }

        return expanded;
    }

    protected int eval(State currentState)
    {
        int score =  0;
        
        if(currentState.player2Score > currentState.player1Score)
        {
            score += 5;  //returns a positive score of if Player 1 wins
        }
        else if(currentState.player1Score > currentState.player2Score)
        {
            score -= 5;  //returns a negative score if Player 1 loses
        }
        else
        {
            score += 0;
        }

        return score;
    }

    protected int miniMax(State currentState, int depth, int currPlayer)
    {        
        if(depth == 0 || currentState.getTerminalStatus() == true)  //returns utility if is terminal
        {
            return eval(currentState);
        }  

        ArrayList<State> expanded = expand(currentState, currPlayer);
        int[] value = new int[expanded.size()];

        depth -= 1;         
        int nodeValue = 0;  //heuristic value of the node

        for(int i = 0;i < expanded.size(); i++)
        {
            if(expanded.get(i) == null)
            {
                value[i] = 0;
            }
            else
            {
                value[i] = miniMax(expanded.get(i), depth, expanded.get(i).otherPlayer(currPlayer));
            }
        }

        for(int j = 1;j < value.length;j++)
        {
            nodeValue += value[j];  //adds each node's value
        }
        return nodeValue;

    }       

    /**
     * Response to invalid move
     * 
     */
    @Override
    protected void responseToInvalid()
    {
        System.out.println("OOPSIE");
    }
}
