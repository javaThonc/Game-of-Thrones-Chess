package chess_test;

import model_design.board_design.Board;
import model_design.chess_design.Pawn;
import model_design.chess_design.Rook;
import model_design.location_design.location;
import org.junit.Assert;
import org.junit.Test;
public class TestRook {
    @Test
    public void TestRookMove(){
        Board game = new Board(false);
        location valid_l1 = new location(1,1);
        location valid_l2 = new location(1,2);
        location valid_l4 =  new location(1,7);
        location valid_l5 = new location(0,1);

        location valid_l6 = new location(7,7);
        location block_k1 = new location(3,7);
        location invalid_l3 = new location(2,2);
        Rook r1 = new Rook(1);
        Pawn p1 = new Pawn(1);
        game.addChess(valid_l1,r1);

        //Test model_design.chess_design.Rook can walk
        Assert.assertTrue(r1.checkMove(valid_l1,valid_l4, game.getBoard()));
        Assert.assertFalse(r1.checkMove(valid_l1,invalid_l3, game.getBoard()));

        //Fake Block
        game.addChess(valid_l5,p1);
        Assert.assertTrue(r1.checkMove(valid_l1,valid_l4, game.getBoard()));

        // Block the model_design.chess_design.Rook
        game.addChess(valid_l2,p1);
        Assert.assertEquals(game.move(valid_l1, valid_l4),null);

        //Remove and try again.
        game.removeChess(valid_l2);
        Assert.assertNotEquals(game.move(valid_l1, valid_l4),null);
        Assert.assertEquals(game.getBoard()[valid_l4.getX()][valid_l4.getY()], r1);

        // move left and block;
        game.addChess(block_k1, p1);
        Assert.assertEquals(game.move(valid_l4, valid_l6),null);

    }

    @Test
    public void TestBlock(){
        Board game = new Board(false);
        location valid_l1 = new location(2,2);
        location valid_l2 = new location(7,2);
        location block_l1 = new location(6,2);

        Pawn p1 = new Pawn(1);
        Rook r1 = new Rook(1);

        game.addChess(block_l1,p1);
        game.addChess(valid_l1,r1);

        Assert.assertFalse(r1.checkMove(valid_l1,valid_l2,game.getBoard()));

    }

}
