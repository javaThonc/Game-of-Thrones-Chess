package chess_test;

import model_design.board_design.Board;
import model_design.chess_design.King;
import model_design.chess_design.chess;
import model_design.location_design.location;
import org.junit.Assert;
import org.junit.Test;
public class TestKing {
    @Test
    public void KingPlaceTest(){
        Board game = new Board(false);
        location l1 = new location(1,1);
        King k1 = new King(1);
        game.addChess(l1,k1);
        chess[][] chessboard = game.getBoard();

        for(int i = 0; i < 8; i++){
            for(int j=0; j < 8; j++){
                if(chessboard[i][j]!=null){
                    Assert.assertEquals(i, 1);
                    Assert.assertEquals(j, 1);
                }
            }
        }

        Assert.assertNotEquals(chessboard[l1.getX()][l1.getY()],null);
        Assert.assertEquals(chessboard[l1.getX()][l1.getY()].getType(), chess.KING);
    }
    @Test
    public void KingMoveTest(){
        Board game = new Board(false);
        location valid_l1 = new location(1,1);
        location valid_l2 = new location(0,1);
        location invalid_l3 = new location(3,1);
        King k1 = new King(1);
        game.addChess(valid_l1,k1);

        // test checkMove function in model_design.chess_design.King
        chess fir = new chess();
        Assert.assertFalse(fir.checkMove(valid_l1,invalid_l3, game.getBoard()));
        Assert.assertEquals(k1.checkMove(valid_l1,valid_l2, game.getBoard()), true);
        Assert.assertNotEquals(k1.checkMove(valid_l1,invalid_l3, game.getBoard()), true);

        // Move the model_design.chess_design.chess
        game.move(valid_l1,valid_l2);
        Assert.assertEquals(game.getBoard()[valid_l2.getX()][valid_l2.getY()], k1 );
        Assert.assertEquals(game.getBoard()[valid_l1.getX()][valid_l1.getY()], null );
    }

}
