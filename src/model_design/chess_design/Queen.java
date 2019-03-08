package model_design.chess_design;
import model_design.location_design.*;

public class Queen extends  chess {
    public Queen(int owner){
        this.owner = owner;
        this.type = QUEEN;
    }

    /**
     * return whether pass in model_design.location_design.location start_pos is valid for a model_design.chess_design.Queen.
     * In the game, model_design.chess_design.Queen move both horizontally and vertically without
     * limitation of distance.
     *
     * @param start_pos
     * @param end_pos
     * @return
     */
    public  boolean checkMove(location start_pos, location end_pos, chess[][] board) {
        // Treat model_design.chess_design.Queen as a combination of model_design.chess_design.Rook and model_design.chess_design.Bishop
        Bishop queen_b = new Bishop(-1);
        Rook   queen_r = new Rook(-1);
        return queen_b.checkMove(start_pos, end_pos, board) || queen_r.checkMove(start_pos, end_pos, board);
    }

}
