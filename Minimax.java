
import java.util.ArrayList;


public class Minimax
{
	/**
	 *
	 * This will recursively call Minimax depending on the current player, if the
	 * current player is O, the algorithm will find the MAX available board and if
	 * the current player is X, the algorithm will find the MIN avaiLable board.
	 * 
	 * We assume that the human player is X and that the AI is O
	 * 
	 * The terminal state check is done at the start before recursively calling
	 * Minimax, the terminal checks are checkWinner for player X(Human) and O(AI) and
	 * if the board state is full, if either of the conditions gets satisfied then
	 * it will return the value as decided if winner is AI(O), assign +1, if
	 * winner is User(X) assign -1 and if the state is draw assign 0 and return
	 *
	 * @param state
	 *            board for which the Minimax will be called recursively
	 * @param player
	 *            player for whom the game state should be generated
	 * @return boolean true/false
	 **/
	public static int miniMax(GameState state, String player)
	{
		GameAI.setTotalCount(GameAI.getTotalCount() + 1);
		
		if(state.checkWinner(state.getBoardState(), "O"))
			return 1;
		else if(state.checkWinner(state.getBoardState(), "X"))
			return -1;
		else if(state.boardFullCheck(state.getBoardState()))
			return 0;
		
		int max, min;
		max = (int) Double.NEGATIVE_INFINITY;
		min = (int) Double.POSITIVE_INFINITY;
		
		ArrayList<GameState> children = state.generateSuccessors(state, player);
		GameState childBoardState;
		int best = 0;
		int size = children.size();
		
		for(int i=0; i<size; i++)
		{
			childBoardState = children.get(i);
			childBoardState.printBoardStateMax(children.get(i).getBoardState());
			if(player == "X")
			{
				best = miniMax(childBoardState, "O");
				min = Integer.min(min, best);
				Log.debug("Inside min value " + min);
			}
			else if(player == "O")
			{
				best = miniMax(childBoardState, "X");
				max = Integer.max(max, best);
				Log.debug("Inside max value " + max);
			}
		}
		
		if(player == "X")
			return min;
		else
			return max;
		
		//return returnNum;


	}
}