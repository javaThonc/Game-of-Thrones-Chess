package model_design.chess_design;
import model_design.location_design.*;

public class Bishop extends chess {
    public Bishop(int owner){
        this.owner = owner;
        this.type = BISHOP;
    }

    /**
     * return whether pass in model_design.location_design.location start_pos is valid for a model_design.chess_design.Bishop.
     * In the game, model_design.chess_design.Bishop can move in diagonal direction without limitation of
     * distance
     * @param start_pos position where the model_design.chess_design.Bishop from
     * @param end_pos position where the model_design.chess_design.Bishop go
     * @return return true if can move else false
     */
    public boolean checkMove(location start_pos, location end_pos, chess[][] board){
        int startX = start_pos.getX();
        int startY = start_pos.getY();
        int endX = end_pos.getX();
        int endY = end_pos.getY();

        //In diagonal distance
        if(Math.abs(startX-endX)==Math.abs(startY - endY)){
            return (!isStay(start_pos, end_pos)) && (!isChessOnWay(start_pos, end_pos, board));
        }
        return false;
    }

    /**
     * Make sure there is no intermediate model_design.chess_design.chess that prevent model_design.chess_design.Bishop to move to the
     * end position
     * @param start_pos
     * @param end_pos
     * @param board
     * @return true if there is not intermediate model_design.chess_design.chess else false
     */
    public boolean isChessOnWay(location start_pos, location end_pos, chess[][] board){
        int startX = start_pos.getX();
        int startY = start_pos.getY();
        int endX = end_pos.getX();
        int endY = end_pos.getY();
        int distance  = Math.abs(startX-endX);

        if((startX-endX)*(startY-endY)>0){
            int iteX = startX < endX ? startX : endX;
            int iteY = startY < endY ? startY : endY;

            for(int i = 1; i < distance; ++i){

                if(board[iteX+i][iteY+i]!=null){
                    return true;
                }
            }
        }else{
            int iteX = startX > endX ? startX : endX;
            int iteY = startY < endY ? startY : endY;

            for(int i = 1; i < distance; ++i){
                if(board[iteX-i][iteY+i]!=null){
                    return true;
                }
            }
        }

        return false;
    }
}
