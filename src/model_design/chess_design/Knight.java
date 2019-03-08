package model_design.chess_design;
import model_design.location_design.*;

public class Knight extends chess {
    public Knight(int owner) {
        this.owner = owner;
        this.type = KNIGHT;
    }

    /**
     * return whether pass in model_design.location_design.location start_pos is valid for a model_design.chess_design.Knight.
     * In the game, model_design.chess_design.Knight can move in around gird in one x and two y
     * or two y and one x distance. model_design.chess_design.Knight can move without the model_design.chess_design.chess
     * block between start and end position
     * @param start_pos
     * @param end_pos
     * @return
     */
    public boolean checkMove(location start_pos, location end_pos, chess[][] board){
        int startX = start_pos.getX();
        int startY = start_pos.getY();
        int endX = end_pos.getX();
        int endY = end_pos.getY();
        // horizontal distance is one
        if(Math.abs(startX - endX)==1){
            if(Math.abs(startY - endY)==2){
                return true;
            }
        }
        // vertical distance is one
        else if(Math.abs(startY - endY)==1){
            if(Math.abs(startX - endX)==2){
                return true;
            }
        }
        return false;
    }
}
