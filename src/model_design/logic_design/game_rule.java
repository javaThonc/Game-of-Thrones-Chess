package model_design.logic_design;

import model_design.chess_design.*;
import model_design.location_design.location;

public class game_rule {
    public static void setLegendBoard(chess[][] board){
        // set up model_design.chess_design.chess
        for(int i=0; i < 8; i++) {
            if(i==3||i==4||i==0||i==7){
               continue;
            }
            Pawn pawn_1 = new Pawn(1);
            board[i][1] = pawn_1;
            Pawn pawn_2 = new Pawn(2);
            board[i][6] = pawn_2;
        }

        board[3][1] = new Dragon(1);
        board[4][1] = new Dragon(1);
        board[3][6] = new Dragon(2);
        board[4][6] = new Dragon(2);

        board[0][1] = new Vampire(1);
        board[7][1] = new Vampire(1);
        board[0][6] = new Vampire(2);
        board[7][6] = new Vampire(2);

        board[0][0] = new Rook(1);
        board[1][0] = new Knight(1);
        board[2][0] = new Bishop(1);
        board[3][0] = new King(1);
        board[4][0] = new Queen(1);
        board[5][0] = new Bishop(1);
        board[6][0] = new Knight(1);
        board[7][0] = new Rook(1);

        board[0][7] = new Rook(2);
        board[1][7] = new Knight(2);
        board[2][7] = new Bishop(2);
        board[3][7] = new King(2);
        board[4][7] = new Queen(2);
        board[5][7] = new Bishop(2);
        board[6][7] = new Knight(2);
        board[7][7] = new Rook(2);
    }
    /**
     * Set the board according to the rule in the
     * model_design.chess_design.chess game
     */
    public static void setBoard(chess[][] board){
        // set up model_design.chess_design.chess
        for(int i=0; i < 8; i++){
            Pawn pawn_1 = new Pawn(1);
            board[i][1] = pawn_1;
            Pawn pawn_2 = new Pawn(2);
            board[i][6] = pawn_2;
        }
        board[0][0] = new Rook(1);
        board[1][0] = new Knight(1);
        board[2][0] = new Bishop(1);
        board[3][0] = new King(1);
        board[4][0] = new Queen(1);
        board[5][0] = new Bishop(1);
        board[6][0] = new Knight(1);
        board[7][0] = new Rook(1);

        board[0][7] = new Rook(2);
        board[1][7] = new Knight(2);
        board[2][7] = new Bishop(2);
        board[3][7] = new King(2);
        board[4][7] = new Queen(2);
        board[5][7] = new Bishop(2);
        board[6][7] = new Knight(2);
        board[7][7] = new Rook(2);
    }

    /**
     * Test whether the model_design.chess_design.King is in checkmate by another player
     * @param Board The game board where model_design.chess_design.chess are placed
     * @param kingX x dimension of the model_design.chess_design.chess model_design.chess_design.King
     * @param kingY y dimension of the model_design.chess_design.chess model_design.chess_design.King
     * @param player the player who we check the security of the Kings
     * @return
     */
    public static boolean detect_checkmate(chess[][] Board, int kingX, int kingY, int player){

        return checkPawn(Board, kingX, kingY, player)|| checkKing(Board, kingX, kingY, player)||
                checkStraight(Board, kingX, kingY, player)|| checkDiagonal(Board, kingX, kingY, player)
                || checkKnight(Board, kingX, kingY, player) ;
    }

    /**
     * Whether the model_design.chess_design.King is checked by a pawn from another player
     * @param Board The game board where model_design.chess_design.chess are placed
     * @param kingX x dimension of the model_design.chess_design.chess model_design.chess_design.King
     * @param kingY y dimension of the model_design.chess_design.chess model_design.chess_design.King
     * @param player the player who we check the security of the Kings
     * @return
     */
    public static  boolean checkPawn(chess[][] Board, int kingX, int kingY, int player){
        int direction = player==1 ? 1 : -1;
        // pawn checkmate if in danger_y position;
        int danger_y = kingY + direction;
        //Out of bound, no way to be checkmated
        if(danger_y<0||danger_y>Board.length){
            return false;
        }
        // Dangerous position for opposite pawn
        chess left_up = Board[kingX-1][danger_y];
        chess right_up = Board[kingX+1][danger_y];

        if(left_up!=null && left_up.getOwner()!=player
                && left_up.getType()==chess.PAWN){
            return true;
        } else if(right_up!=null && right_up.getOwner()!=player
                && right_up.getType()==chess.PAWN){
            return true;
        }
        return false;
    }

    /**
     * Whether the model_design.chess_design.King is checked by a model_design.chess_design.King from another player
     * @param Board The game board where model_design.chess_design.chess are placed
     * @param kingX x dimension of the model_design.chess_design.chess model_design.chess_design.King
     * @param kingY y dimension of the model_design.chess_design.chess model_design.chess_design.King
     * @param player the player who we check the security of the Kings
     * @return
     */
    public static boolean checkKing(chess[][] Board, int kingX, int kingY, int player){
        for(int x_dim = -1; x_dim < 2; x_dim++){
            for(int y_dim = -1; y_dim < 2; y_dim++){
                int danger_x = kingX+x_dim;
                int danger_y = kingY+y_dim;
                // If it is out of bound, no threat
                if(danger_x < Board.length && danger_x >0
                        && danger_y < Board[0].length && danger_y>0){
                    chess killChess = Board[danger_x][danger_y];
                    if(killChess!=null && killChess.getType()==chess.KING && killChess.getOwner()!=player){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    /**
     * Whether the model_design.chess_design.King is checked horizontally and vertically  from another player
     * @param Board The game board where model_design.chess_design.chess are placed
     * @param kingX x dimension of the model_design.chess_design.chess model_design.chess_design.King
     * @param kingY y dimension of the model_design.chess_design.chess model_design.chess_design.King
     * @param player the player who we check the security of the Kings
     * @return
     */
    public static boolean checkStraight(chess[][] Board, int kingX, int kingY, int player) {
        int width = Board.length;
        int height = Board[0].length;
        // Go up
        for(int step = 1; step + kingY < height; step++){
            if(Board[kingX][step + kingY]!=null){
                chess killChess = Board[kingX][step + kingY];
                if(killChess.getOwner()!=player && (killChess.getType()==chess.QUEEN || killChess.getType()==chess.ROOK)){
                    return true;
                }else{
                    // No need to keep moving since rest of block has already benn blocked
                    break;
                }
            }
        }
        //Go right
        for(int step = 1; step + kingX < width; step++){
            if(Board[step + kingX][kingY]!=null){
                chess killChess = Board[step + kingX][kingY];
                if(killChess.getOwner()!=player && (killChess.getType()==chess.QUEEN || killChess.getType()==chess.ROOK)){
                    return true;
                }else{
                    // No need to keep moving since rest of block has already benn blocked
                    break;
                }
            }
        }
        //Go down
        for(int step = 1; kingY - step >= 0; step++){
            if(Board[kingX][kingY - step]!=null){
                chess killChess = Board[kingX][kingY - step];
                if(killChess.getOwner()!=player && (killChess.getType()==chess.QUEEN || killChess.getType()==chess.ROOK)){
                    return true;
                }else{
                    // No need to keep moving since rest of block has already benn blocked
                    break;
                }
            }
        }
        //Go left
        for(int step = 1; kingX - step >= 0; step++){
            if(Board[kingX-step][kingY]!=null){
                chess killChess = Board[kingX-step][kingY];
                if(killChess.getOwner()!=player && (killChess.getType()==chess.QUEEN || killChess.getType()==chess.ROOK)){
                    return true;
                }else{
                    // No need to keep moving since rest of block has already benn blocked
                    break;
                }
            }
        }
        return false;
    }
    /**
     * Whether the model_design.chess_design.King is checked horizontally and vertically  from another player
     * @param Board The game board where model_design.chess_design.chess are placed
     * @param kingX x dimension of the model_design.chess_design.chess model_design.chess_design.King
     * @param kingY y dimension of the model_design.chess_design.chess model_design.chess_design.King
     * @param player the player who we check the security of the Kings
     * @return
     */
    public static boolean checkDiagonal(chess[][] Board, int kingX, int kingY, int player){
        int width = Board.length;
        int height = Board[0].length;
        // Go upright
        for(int step = 0; (kingX+step)<width && (kingY+step)<height; step++){
            int dangerX = kingX+step;
            int dangerY = kingY+step;
            chess killChesss = Board[dangerX][dangerY];
            if(killChesss!=null){
                if(killChesss.getOwner()!=player&&(killChesss.getType()==chess.QUEEN
                        ||killChesss.getType()==chess.BISHOP)){
                    return true;
                }else{
                    break;
                }
            }
        }
        // Go  right down
        for(int step = 0; (kingX-step)>0 && (kingY+step)<height; step++){
            int dangerX = kingX-step;
            int dangerY = kingY+step;
            chess killChesss = Board[dangerX][dangerY];
            if(killChesss!=null){
                if(killChesss.getOwner()!=player&&(killChesss.getType()==chess.QUEEN
                        ||killChesss.getType()==chess.BISHOP)){
                    return true;
                }else{
                    break;
                }
            }
        }
        // Go left down
        for(int step = 0; (kingX-step)>0 && (kingY-step)>0; step++){
            int dangerX = kingX-step;
            int dangerY = kingY-step;
            chess killChesss = Board[dangerX][dangerY];
            if(killChesss!=null){
                if(killChesss.getOwner()!=player&&(killChesss.getType()==chess.QUEEN
                        ||killChesss.getType()==chess.BISHOP)){
                    return true;
                }else{
                    break;
                }
            }
        }
        // Go right down
        for(int step = 0; (kingX+step)<width && (kingY-step)>0; step++){
            int dangerX = kingX+step;
            int dangerY = kingY-step;
            chess killChesss = Board[dangerX][dangerY];

            if(killChesss!=null){
                if(killChesss.getOwner()!=player&&(killChesss.getType()==chess.QUEEN
                        ||killChesss.getType()==chess.BISHOP)){
                    return true;
                }else{
                    break;
                }
            }
        }
        return false;
    }
    /**
     * Whether the model_design.chess_design.King is checked by a knight  from another player
     * @param Board The game board where model_design.chess_design.chess are placed
     * @param kingX x dimension of the model_design.chess_design.chess model_design.chess_design.King
     * @param kingY y dimension of the model_design.chess_design.chess model_design.chess_design.King
     * @param player the player who we check the security of the Kings
     * @return
     */
    public static boolean checkKnight(chess[][] Board, int kingX, int kingY, int player){
        System.out.print(kingX);
        System.out.print(kingY);

        location King_pos = new location(kingX,kingY);
        for(int x_dim =0; x_dim < 3; x_dim++){
            for(int y_dim=0; y_dim <3 ;y_dim++){
                location kni_loc = new location(x_dim,y_dim);
                int opponent = player==1? 2 : 1;
                Knight k1 = new Knight(opponent);
                boolean result = k1.checkMove(kni_loc,King_pos,Board);
                if(result&&Board[x_dim][y_dim]!=null
                        &&Board[x_dim][y_dim].getOwner()!=player
                        &&Board[x_dim][y_dim].getType()==chess.KNIGHT){
                    return true;
                }
            }

        }
        return false;
    }
}
