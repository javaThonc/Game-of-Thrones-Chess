package model_design.location_design;

public class location{
    /**
     * Private variable stands for horizontal and vertiacal model_design.location_design.location in the model_design.board_design.Board in range of 0 to size-1 of board
     * where 0 is the leftmost and bottommost corner and size-1 is the diagonal corner
     */
    private int locX;
    private int locY;
    public location(int new_x, int new_y) {
        locX = new_x;
        locY = new_y;
    }
    public int getX(){ return locX; }
    public int getY() { return locY; }
}