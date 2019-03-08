package chess_test;

import model_design.board_design.Board;
import model_design.chess_design.Knight;
import model_design.location_design.location;
import org.junit.Assert;
import org.junit.Test;

public class TestKnight {
    @Test
    public void TestKnightMove(){
        Board game = new Board(false);
        location ori_loc = new location(1,1);
        location valid_l1 = new location(2,3);
        location valid_l2 = new location(0,3);

        location invalid_l1 = new location(-1,-1);
        location invalid_l2 = new location(2,4);
        location invalid_l3 = new location(3,3);
        location invalid_l4 = new location(3 , -1 );

        Knight k1 = new Knight(1);
        game.addChess(ori_loc,k1);

        Assert.assertEquals(game.move(ori_loc,invalid_l1),null);
        Assert.assertEquals(game.move(ori_loc,invalid_l2),null);
        Assert.assertEquals(game.move(ori_loc,invalid_l3),null);
        Assert.assertEquals(game.move(ori_loc,invalid_l4),null);


        Assert.assertTrue(k1.checkMove(ori_loc,valid_l1, game.getBoard()));
        Assert.assertTrue(k1.checkMove(ori_loc,valid_l2, game.getBoard()));
        game.move(ori_loc,valid_l1);
        Assert.assertEquals(game.getBoard()[valid_l1.getX()][valid_l1.getY()],k1);
    }
}
