package model_design.board_design;

import model_design.chess_design.Pawn;
import model_design.chess_design.chess;
import model_design.location_design.location;
import model_design.logic_design.game_rule;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private int width;
    private int height;
    private chess board[][];
    private boolean checkmate;
    private boolean isOver;
    private int current_player;
    public static String playerName1;
    public static  String playerName2;
    /**
     * Create a model_design.board_design.Board with allocation of all model_design.chess_design.chess
     */
    public Board(boolean isAllocate){
        width = 8;
        height = 8;
        board = new chess[width][height];
        checkmate = false;
        isOver = false;
        current_player = 1;
        if(isAllocate){
            game_rule.setBoard(board);
        }
    }
    /**
     * Create a copy constructor
     */
    public Board(Board board){
        width = 8;
        height = 8;
        this.board = new chess[width][height];
        this.checkmate = board.getCheckmate();
        this.current_player = board.getPlayNum();
        this.isOver = board.isOver;
        this.playerName1 = board.playerName1;
        this.playerName2 = board.playerName2;

        chess[][] b = board.getBoard();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++ ){
                this.board[i][j] = b[i][j];
            }
        }
    }
    /**
     *Get the Legendary Board
     */
    public Board(int i){
        width = 8;
        height = 8;
        board = new chess[width][height];
        checkmate = false;
        isOver = false;
        current_player = 1;
        if(i==1){
            game_rule.setLegendBoard(board);
        }
    }

    /**
     * Move the model_design.chess_design.chess on start_pos to end_pos. If start_pos does not has a model_design.chess_design.chess
     * or the movement is illegal by the rule of the game return null
     */
    public location move(location start_pos, location end_pos){
        if(isOver){
            System.out.println("Game has finished");
            return null;
        }

        int start_x = start_pos.getX();
        int start_y = start_pos.getY();
        int end_x = end_pos.getX();
        int end_y = end_pos.getY();

        if(board[start_x][start_y].getOwner()!=current_player){
            System.out.println("It is not your model_design.chess_design.chess! Can't move");
            return null;
        }

        if(isInside(start_pos) && isInside(end_pos)
                && board[start_x][start_y] != null){
            chess start_chess = board[start_x][start_y];
            chess end_chess = board[end_x][end_y];

            if(start_chess.checkMove(start_pos, end_pos, board) && !chess.isTeammate(start_chess, end_chess)){
                if(board[start_x][start_y].getType()==chess.PAWN) {

                    ((Pawn)board[start_x][start_y]).first_move = false;
                }
                // The king has already dead and the game is over
                if(end_chess!=null && end_chess.getType() == chess.KING){
                    end_game(start_chess.getOwner());
                }
                //Capture the opposite model_design.chess_design.chess or move the model_design.chess_design.chess
                board[end_x][end_y] = board[start_x][start_y];
                board[start_x][start_y] = null;
                //Switch the player
                current_player = current_player == 1 ? 2 : 1;
                System.out.print(current_player+"!!!!");
                isCheckmate(current_player);
                return end_pos;
            }
        }
        System.out.println("Not a valid move, try another model_design.location_design.location");
        return null;
    }

    /**
     *  The game is finished
     */
    private void end_game(int player) {
        System.out.println("Game finish! Player:" + player + " has already win the game");
        isOver = true;
    }

    /**
     * get the current board for the current game
     */
    public chess[][] getBoard(){
        return board;
    }

    /**
     * Set the current board for the current game
     */
    public void setBoard(chess[][] newBoard){
        board = newBoard;
    }
    /**
     *determine whether game is ended by rule
     */
    public void isCheckmate(int player){
        for(int row=0; row < board.length; row++ ){
            for(int column=0; column < board[row].length; column++){
                chess piece  = board[row][column];
                //Find the player's model_design.chess_design.King and detect whether checkmate
                if(piece!=null&&piece.getType()==chess.KING&&piece.getOwner()==player){
                    if(game_rule.detect_checkmate(board, row, column,player)){
                        System.out.print("CheckMate, model_design.chess_design.King of Player:" + player + " is in danger");
                        checkmate = true;
                    }else {
                        checkmate = false;
                    }
                    return;//Return to save some time in case board is huge
                }
            }
        }
    }

    /**
     * put a model_design.chess_design.chess on the board. If there there is already one check or other illegal
     * movement, return false.
     * @param chess_pos
     * @param new_chess
     */
    public boolean addChess(location chess_pos, chess new_chess){
        int new_x = chess_pos.getX();
        int new_y = chess_pos.getY();
        if(board[new_x][new_y]==null){
            board[new_x][new_y] = new_chess;
            return true;
        }
        return false;
    }
    /**
     *Return a array of location containing all the possible location
     */
    public List<location> allPath(location currentLoc){
        if(board[currentLoc.getX()][currentLoc.getY()]!=null){
            List<location> locList = new ArrayList<location>();
            for(int i =0; i<8; i++){
                for(int j = 0; j < 8; j++ ){
                    location loc = new location(i,j);
                    chess chessCur = board[currentLoc.getX()][currentLoc.getY()];
                    chess target = board[i][j];
                    if(chessCur.checkMove(currentLoc, loc, board) && !chess.isTeammate(chessCur,target)){
                        locList.add(loc);
                    }
                }
            }
            // there is actually one valid path
            return locList.size()==0 ? null : locList;
        }
        return null;
    }

    /**
     * Remove a model_design.chess_design.chess from the board that is specified in the model_design.location_design.location
     * If the remove fail, return false else return true
     * @param chess_pos
     */
    public boolean removeChess(location chess_pos){
        int new_x = chess_pos.getX();
        int new_y = chess_pos.getY();
        if(board[new_x][new_y]==null){
            return false;
        }
        board[new_x][new_y] = null;
        return true;
    }

    /**
     * Test the model_design.location_design.location inside the boundary(width, height)
     * @param loc test position
     * @return
     */
    public boolean isInside(location loc){
        return loc.getX()<width && loc.getY() < height && loc.getX()>=0 && loc.getY()>=0;
    }

    /**
     * Test whether the game is over
     * @return
     */
    public boolean isGameOver(){
        return isOver;
    }

    public void setPlayer(int new_player){current_player = new_player;}

    /**
     * return the checkmate, if has already check mate, then return true else return false;
     * @return
     */
    public boolean getCheckmate(){
        return checkmate;
    }

    /**
     * Return the current player who should play
     */
    public String getPlayer(){
        return current_player==1 ? playerName1 : playerName2 ;
    }

    /**
     * Judge whether it is the right player who is playing
     */
    public boolean isPlayer(location loc){
       return board[loc.getX()][loc.getY()].getOwner() == current_player;
    }

    /**
     * Return the current player
     */
    public int getPlayNum(){
        return current_player;
    }

}
