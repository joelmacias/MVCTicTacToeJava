package Model;


public class ModelTTT{

    public ModelTTT(){
        defaultValues();
    }

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

    public char[][] returnBoard(){
        char [][] copy = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                copy[i][j] = board[i][j];
            }
        }
        return copy;
    }
    public void playerInit(String nameOne, String nameTwo){
        playerX = new Player(nameOne, 'X');
        playerO = new Player(nameTwo, 'O');
    }

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

    private GameStatus status;
    private Player playerX;
    private Player playerO;
    private String winnerName;
    private boolean xTurn;
    private int totalMoves;
    private final int MAX_MOVES = 9;
    private final int MIN_MOVES_TO_WIN = 5;
    private  char[][] board = new char[3][3];
}
