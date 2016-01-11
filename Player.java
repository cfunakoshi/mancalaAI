
/**
 * Abstract class Player
 * 
 * @author Cheyne Funakoshi
 * @date 1/8/16
 */
public abstract class Player
{
    protected abstract int getMove(State currState);

    protected abstract void responseToInvalid();
}
