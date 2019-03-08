package model_design.chess_design;
import model_design.location_design.*;

public class Rook extends chess{

    public Rook(int owner){
        this.owner = owner;
        this.type = ROOK;
    }

    /**
     * return whether pass in model_design.location_design.location start_pos is valid for a model_design.chess_design.Rook.
     * In the game, model_design.chess_design.Rook can move either vertically or horizontally until
     * meet the boundary or a model_design.chess_design.chess
     *
     * @param start_pos
     * @param end_pos
     * @return
     */
    public boolean checkMove(location start_pos, location end_pos, chess[][] board){
        // move in vertical direction or horizontal direction
        if(start_pos.getY() == end_pos.getY() || start_pos.getX() == end_pos.getX() ){
            // Distance is greater than zero
            if(!isStay(start_pos, end_pos)){
                return !isChessOnWay(start_pos, end_pos, board);
            }
        }
        return false;
    }

    /**
     * Make sure there is no intermediate model_design.chess_design.chess that prevent rook to move to the
     * right locaiton
     * @param start
     * @param end
     * @param board
     * @return
     */
    public  boolean isChessOnWay(location start, location end, chess[][] board) {
        int startX = start.getX();
        int startY = start.getY();
        int endX = end.getX();
        int endY = end.getY();

        if(Math.abs(startX-endX) > 0){
            int step = startX < endX ? 1 : -1;
            for(int i = startX+step; i != endX ; i+=step){
                if(board[i][startY]!=null){
                    return true;
                }
            }
        }
        else if(Math.abs(startY-endY) > 0){
            int step = startY < endY ? 1 : -1;
            for(int  i = startY+step; i != endY; i+=step){
                if(board[startX][i]!=null){
                    return true;
                }
            }

        }
        return false;

    }

}

