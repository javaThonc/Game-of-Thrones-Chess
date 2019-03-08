package chess_test;

import model_design.board_design.Board;
import model_design.chess_design.King;
import model_design.chess_design.Pawn;
import model_design.location_design.location;
import org.junit.Assert;
import org.junit.Test;

public class TestPawn {
    @Test
    public void testPawnMove(){
        Board game = new Board(false);
        location ori_loc = new location(1,1);
        location invalid_l1 = new location(2,2);
        location invalid_l2 = new location(0,2);
        location invalid_l3 = new location(1,0);
        location invalid_l4 = new location(1,4);


        location valid_l1 = new location(1,2);
        location valid_l2 = new location(1,3);

        Pawn p1 = new Pawn(1);
        Pawn p2 = new Pawn(1);
        Pawn p3 = new Pawn(1);
        Pawn p4 = new Pawn(1);

        game.addChess(ori_loc,p1);
        Assert.assertTrue(p3.checkMove(ori_loc,valid_l2, game.getBoard()));
        Assert.assertTrue(p1.checkMove(ori_loc, valid_l1, game.getBoard()));
        Assert.assertTrue(p1.checkMove(valid_l1, valid_l2, game.getBoard()));


        // Try whether first move is apply normally
        Assert.assertTrue(p1.checkMove(ori_loc, valid_l2, game.getBoard()));
        // Test step further in the first step
        Assert.assertTrue(p2.checkMove(ori_loc, valid_l2, game.getBoard()));

        Assert.assertFalse(p1.checkMove(ori_loc, invalid_l1, game.getBoard()));
        Assert.assertFalse(p1.checkMove(ori_loc, invalid_l2, game.getBoard()));
        Assert.assertFalse(p1.checkMove(ori_loc, invalid_l3, game.getBoard()));
        Assert.assertFalse(p1.checkMove(ori_loc, invalid_l4, game.getBoard()));
    }
    @Test
    public void testPawnKill(){
        Board game = new Board(false);
        location ori_loc = new location(1,1);
        location kill_pos = new location(2,2);

        Pawn p1 = new Pawn(1);
        King k1 = new King(2);
        game.addChess(ori_loc,p1);
        Assert.assertEquals(game.move(ori_loc, kill_pos),null);
        game.addChess(kill_pos,k1);
        Assert.assertEquals(game.move(ori_loc, kill_pos),null);
    }
}
