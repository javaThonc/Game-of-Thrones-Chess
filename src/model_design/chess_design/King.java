package model_design.chess_design;
import model_design.location_design.*;

public class King extends chess {
   public King(int new_owner){
       this.owner = new_owner;
       this.type = KING;
   }

    /**
     * return whether pass in model_design.location_design.location start_pos is valid for a model_design.chess_design.King.
     * In the game, model_design.chess_design.King can move in around gird in one distance
     *
     * @param start_pos
     * @param end_pos
     * @return
     */
    public  boolean checkMove(location start_pos, location end_pos, chess[][] board){
        //absolute distance is limited inside one
        if(Math.abs(start_pos.getX() - end_pos.getX())<2){
            if(Math.abs(start_pos.getY() - end_pos.getY())<2){
                // Do not stay where it is
                return !isStay(start_pos,end_pos);
            }
        }
        return false;
    }
}
