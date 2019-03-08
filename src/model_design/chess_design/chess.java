package model_design.chess_design;
import model_design.location_design.location;

/**
 * This is a template class that is waiting for overloading from specfic
 * implementation of some model_design.chess_design.chess
 */
public class chess {

    public final static int KING = 1;
    public final static int QUEEN = 2;
    public final static int BISHOP = 3;
    public final static int ROOK = 4;
    public final static int KNIGHT = 5;
    public final static int PAWN = 6;
    public final static int Vampire = 7;
    public final static int Dragon = 8;


    int type;
    int owner;

    public int getOwner(){return owner;}
    public int getType() {return this.type;}

    /**
     * Default constructor that set the type to undefined
     */
    public chess(){
        type = 0;
    }

    /**
     * Create a copy constructor
     */
    public chess(chess c){
        this.type = c.type;
        this.owner = c.owner;
    }

    /**
     * Test two model_design.location_design.location model_design.chess_design.chess whether is  a team mate
     */
    public  static boolean isTeammate( chess chess_1, chess chess_2){
        if(chess_2==null){
            return false;
        }
        return chess_1.getOwner() == chess_2.getOwner();
    }
    /**
     *Server as a virtual function that test
     *  the validity of a move waiting
     * for further implementation
     * @return return If one can step from start to end
     */
    public boolean checkMove(location start_pos, location end_pos, chess[][] board){ return false; }

    /**
     * Judge  whether a model_design.chess_design.chess stay in the original position
     */
    public  boolean isStay(location start_pos, location end_pos){
        return (start_pos.getX() == end_pos.getX())  && (start_pos.getY() == end_pos.getY());
    }

}
