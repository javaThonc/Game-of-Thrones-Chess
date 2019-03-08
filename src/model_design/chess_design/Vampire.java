package model_design.chess_design;

import model_design.location_design.location;

/**
 * Inspired by Vampire in mystery story that perform like a usual man in daily
 * life but can switch into powerful mode. However, it can only stays
 * in the shadow
 */
public class Vampire extends chess{
    /**
     * Type that specify whether a Vampire is transformed, if not Vampire perform like a
     * usual Pawns
     */
    public boolean transform;

    public Vampire(int owner){
        this.owner = owner;
        this.type = chess.Vampire;
        this.transform = false;
    }

    /**
     * return whether pass in model_design.location_design.location start_pos is valid for a model_design.chess_design.Vampire.
     * In the game, model_design.chess_design.Vampire can move in all direction. However, it has a requirement that
     * it must stay close to a teammate(under its shadow). The absolute distance to its closest closest teammate
     * distance
     * @param start_pos position where the model_design.chess_design.Vampire from
     * @param end_pos position where the model_design.chess_design.Vampire go
     * @return return true if can move else false
     */
    public boolean checkMove(location start_pos, location end_pos, chess[][] board){
        if(transform){
            boolean isFrozen = !transform(start_pos,board);
            if(isFrozen){
                System.out.println("Your Vampire is frozen, cannot move");
                return false;
            }
            // see whether Vampire can survive in one's shadow
            boolean isSurvice = transform(end_pos,board);
            if(isSurvice){
                int x_pos = end_pos.getX();
                int y_pos = end_pos.getY();
                return board[x_pos][y_pos]==null&&!isStay(start_pos,end_pos);
            }
        }
        //Act as a pawn when not transform
        Pawn p1 = new Pawn(this.owner);
        return p1.checkMove(start_pos,end_pos, board );
    }

    /**
     * Transform!!!!Turn a Pawn into powerful Vampire
     * @param loc
     * @param board
     * @return
     */
    public boolean transform(location loc, chess[][] board){
        int x = loc.getX();
        int y = loc.getY();
        for(int x_dim = -1; x_dim<2; x_dim++){
            for(int y_dim = -1; y_dim < 2 ; y_dim++){
                int x_pos = x+x_dim;
                int y_pos = y_dim+y;
                if(x_pos>=0 && x_pos<8 && y_pos>-1 && y_pos <8 && !(x_pos==x&&y_pos==y)){
                    if(board[x_pos][y_pos]!=null){
                        System.out.println("WHAT");
                        if(board[x_pos][y_pos].getOwner()==this.owner&&board[x_pos][y_pos].getType()==chess.PAWN){
                            transform = true;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }



}