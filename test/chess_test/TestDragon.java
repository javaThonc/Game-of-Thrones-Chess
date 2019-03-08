package chess_test;

import model_design.board_design.Board;
import model_design.chess_design.Pawn;
import model_design.chess_design.Dragon;
import model_design.location_design.location;
import org.junit.Assert;
import org.junit.Test;

public class TestDragon {
    @Test
    public void TestDragonMove(){
        Board game = new Board(false);
        location valid_l1 = new location(1,1);
        location valid_l2 = new location(1,2);
        location valid_l4 =  new location(1,7);
        location valid_l5 = new location(0,1);

        location valid_l6 = new location(7,7);
        location block_k1 = new location(3,7);
        location invalid_l3 = new location(2,2);
        Dragon g1 = new Dragon(1);
        Dragon g2 = new Dragon(2);
        Pawn p1 = new Pawn(1);
        game.addChess(valid_l1,g1);

        //Test model_design.chess_design.dragon can walk
        Assert.assertFalse(g1.checkMove(valid_l1,valid_l4, game.getBoard()));
        Assert.assertFalse(g1.checkMove(valid_l1,invalid_l3, game.getBoard()));

        //Fake Block
        game.addChess(valid_l5,p1);
        Assert.assertFalse(g1.checkMove(valid_l1,valid_l4, game.getBoard()));

        // Block the model_design.chess_design.Dragon
        game.addChess(valid_l2,p1);
        Assert.assertNotEquals(game.move(valid_l1, valid_l4),null);

        game.addChess(block_k1,p1);
        Assert.assertTrue(g2.checkMove(valid_l4, valid_l6,game.getBoard()));


    }
}
