package chess_test;

import model_design.board_design.Board;
import model_design.chess_design.Pawn;
import model_design.chess_design.Queen;
import model_design.chess_design.Rook;
import model_design.location_design.location;
import org.junit.Assert;
import org.junit.Test;
public class TestQueen {
    @Test
    public void TestQueenMove(){
        // Test diagonal part
        Board game = new Board(false);
        location ori_loc = new location(1,1);
        Queen q1 = new Queen(1);

        location valid_l1 = new location(0,0);
        location valid_l2 = new location(0,2);
        location valid_l3 = new location(7,7);
        location valid_l4 = new location(2,0);

        location invalid_l1 = new location(1,2);
        location invalid_l2 = new location(1,0);
        location invalid_l3 = new location(0,1);
        location invalid_l4 = new location(2,1);


        Assert.assertTrue(q1.checkMove(ori_loc,valid_l2, game.getBoard()));
        Assert.assertTrue(q1.checkMove(ori_loc,valid_l3, game.getBoard()));
        Assert.assertTrue(q1.checkMove(ori_loc,valid_l4, game.getBoard()));

        // Test vertical and horizontal part
        valid_l1 = new location(1,1);
        valid_l2 = new location(1,2);
        valid_l4 =  new location(1,7);
        invalid_l3 = new location(2,2);
        Rook r1 = new Rook(1);
        Pawn p1 = new Pawn(1);
        game.addChess(valid_l1,r1);

        //Test model_design.chess_design.Queen can walk
        Assert.assertTrue(r1.checkMove(valid_l1,valid_l4, game.getBoard()));
        Assert.assertFalse(r1.checkMove(valid_l1,invalid_l3, game.getBoard()));

        // Block the model_design.chess_design.Queen
        game.addChess(valid_l2,p1);
        Assert.assertEquals(game.move(valid_l1, valid_l4),null);

        //Remove and try again.
        game.removeChess(valid_l2);
        Assert.assertNotEquals(game.move(valid_l1, valid_l4),null);
        Assert.assertEquals(game.getBoard()[valid_l4.getX()][valid_l4.getY()], r1);
    }
}
