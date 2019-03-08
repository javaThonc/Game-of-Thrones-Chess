package model_design.chess_design;
import model_design.location_design.*;

public class Pawn extends chess {
    /**
     * @param first_move this is a boolean type to specify whether
     *                   the pawn is start from the start place
     *                   where it can move two steps
     */
    public boolean first_move;

    public Pawn(int owner){
        this.owner = owner;
        this.type = PAWN;
        first_move = true;
    }

    /**
     * return whether pass in model_design.location_design.location start_pos is valid for a model_design.chess_design.Pawn.
     * In the game, model_design.chess_design.Pawn vertically to opposite direction and kill when
     * move horizon totally. In the first move, the pawn can move two
     * steps
     *
     * @param start_pos
     * @param end_pos
     * @return
     */
    public boolean checkMove(location start_pos, location end_pos, chess[][] board) {
        int direction = this.owner == 1 ? 1 : -1;
        int startX = start_pos.getX();
        int startY = start_pos.getY();
        int endX = end_pos.getX();
        int endY = end_pos.getY();

        // In the right direction
        if((endY-startY)*direction > 0){
            // In the first movement, pawn can move two steps
            if(first_move){
                // stay in the vertical line
                if(Math.abs(endY-startY)<3 && endX == startX && !isStay(start_pos, end_pos)){
                    // no stay at the same place and no blocking model_design.chess_design.chess
                    if((board[startX][startY + direction] == null) && (board[startX][startY + 2*direction] == null)){
                        return true;
                    }
                }
            }else {

                // move one step forward
                if (Math.abs(endY - startY) == 1) {
                    if (endX == startX && !isStay(start_pos, end_pos) && board[endX][endY] == null) {
                        // no staying at the same place and no blocking
                        return true;
                    } else if (Math.abs(endY - startY) == 1 && Math.abs(endX - startX) == 1 && board[endX][endY] != null) {
                        // kill the opposite model_design.chess_design.chess and in the model_design.chess_design.chess rule  has already check whether end position is teammate
                        return true;
                    }
                }
            }
        }

        return false;

    }
}

