package Model;

/*
 *  Represents a game of tic tac toe with a 3x3 2D char array
 *  Defines data, logic, and computations necessary for a tic tac toe game
 */
public class ModelTTT{

    public ModelTTT(){
        defaultValues();
    }

    //Sets board, and all other data to the values of a new game.
    public void defaultValues(){
        status = GameStatus.VALID;
        xTurn = true;
        winnerName = null;
        totalMoves = 0;
        char ch = '1';
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                board[i][j] = ch;
                ch++;
            }
        }

    }

    //Returns a copy of the current game board.
    public char[][] returnBoard(){
        char [][] copy = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                copy[i][j] = board[i][j];
            }
        }
        return copy;
    }

    //Sets the names, and marks of both players.
    public void playerInit(String nameOne, String nameTwo){
        playerX = new Player(nameOne, 'X');
        playerO = new Player(nameTwo, 'O');
    }

    //Returns true if board was successfully updated with player's move, false otherwise.
    //Sets status to SPACETAKEN, VALID, WINNER, or DRAW according to the result of the player's move.
    public boolean updateBoard(int row, int col){
        if(board[row][col] == 'X' || board[row][col] == 'O'){
            status = GameStatus.SPACETAKEN;
            return false;
        }
        totalMoves++;
        if(xTurn){
            board[row][col] = playerX.getMark();
            //xTurn = false;
        }
        else{
            board[row][col] = playerO.getMark();
            //xTurn = true;
        }
        if(totalMoves >= MIN_MOVES_TO_WIN){
            checkForEndOfGame(row,col);
        }else{
            status = GameStatus.VALID;
        }
        xTurn = !xTurn;
        return true;
    }

    /*
     * If the player has selected a valid move returns true, places move, and sets status to VALIDMOVE
     * If the player selected an invalid move returns false, and sets status to the type of invalid move,
     * either OUTOFBOUNDS, or SPACETAKEN.
     */
    public boolean placeMove(char move){
        switch (move){
            case '1':{
                return updateBoard(0,0);
            }
            case '2':{
                return updateBoard(0,1);
            }
            case '3':{
                return updateBoard(0,2);
            }
            case '4':{
                return updateBoard(1,0);
            }
            case '5':{
                return updateBoard(1,1);
            }
            case '6':{
                return updateBoard(1,2);
            }
            case '7':{
                return updateBoard(2,0);
            }
            case '8':{
                return updateBoard(2,1);
            }
            case '9':{
                return updateBoard(2,2);
            }
            default:{
                status = GameStatus.OUTOFBOUNDS;
                return false;
            }
        }

    }

    public GameStatus getStatus() {
        return status;
    }

    public String getTurn(){
        return xTurn ? playerX.getName() : playerO.getName();
    }

    public String getWinnerName() {
        return winnerName;
    }

    //If the game has ended sets status to WINNER, or DRAW accordingly. Otherwise sets status to VALID
    private void checkForEndOfGame(int row, int col){

        //Get mark of current players turn
        char mark = board[row][col];

        //Check if horizontal win
        if(mark == board[row][0] && mark == board[row][1] && mark == board[row][2]){
           status = GameStatus.WINNER;
        }

        //Check for vertial win
        else if(mark == board[0][col] && mark == board[1][col] && mark == board[2][col]){
            status = GameStatus.WINNER;
        }
        
        //Check for diagonal win
        else if(row == col && mark == board[0][0] &&  mark == board[1][1] && mark == board[2][2]){
            status = GameStatus.WINNER;
        }
       
        //Check for anti-diagnol win
        else if(row + col == 2 && mark == board[0][2] && mark == board[1][1] && mark == board[2][0]){
            status = GameStatus.WINNER;
        }
        
        //Check if game has ended in a draw
        else if(totalMoves == MAX_MOVES){
            status = GameStatus.DRAW;
        }
        
        //Set status to valid move
        else{
           status = GameStatus.VALID;
        }
        if(status.equals(GameStatus.WINNER)){
            winnerName = xTurn ? playerX.getName() : playerO.getName();
        }
    }

    //Current status of the game may be one of the following: WINNER, DRAW, VALID, OUTOFBOUNDS, SPACETAKEN.
    private GameStatus status;
    private Player playerX;
    private Player playerO;
    private String winnerName;
    private boolean xTurn;
    private int totalMoves;
    //Max number of moves allowed in a tic tac toe game.
    private final int MAX_MOVES = 9;
    //Minimum number of moves required for there to be a winner.
    private final int MIN_MOVES_TO_WIN = 5;
    private  char[][] board = new char[3][3];
}
