
package model_design.chess_design;

import model_design.location_design.location;

/**
 * Inspired by the fierce Dragon described in western mystery creature that is powerful while
 * moving quickly. However, because of the short sight of Dragon, it need someone to direct it,
 * like a teammate
 */
public class Dragon extends chess{
    public Dragon(int owner){
        this.owner = owner;
        this.type = chess.Dragon;
    }
    /**
     * return whether pass in model_design.location_design.location start_pos is valid for a model_design.chess_design.Dragon.
     * In the game, model_design.chess_design.Dragon can move without the limitation of distance, but
     * you have to have a teammate in front of you direct the direction
     *
     * @param start_pos
     * @param end_pos
     * @return
     */
    public  boolean checkMove(location start_pos, location end_pos, chess[][] board){
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
     * Make sure there is a intermediate model_design.chess_design.Dragon that help Dragon to move to the
     * right locaiton
     * @param start
     * @param end
     * @param board
     * @return
     */
    public  boolean isChessOnWay(location start, location end, chess[][] board){
        int startX = start.getX();
        int startY = start.getY();
        int endX = end.getX();
        int endY = end.getY();

        if(Math.abs(startX-endX) > 0) {
            int i = startX < endX ? startX + 1 : endX + 1;
            int stopX = startX >= endX ? startX : endX;
            for (; i < stopX; ++i) {
                if (board[i][startY] != null) {
                    return false;
                }
            }
        }else{
            int i = startY < endY ? startY+1 : endY+1;
            int stopY = startY >= endY ? startY : endY;
            for(; i < stopY; ++i){
                if(board[startY][i]!=null){
                    return false;
                }
            }

        }
        return true;
    }

}
