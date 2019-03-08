package chess_test;

import model_design.board_design.Board;
import model_design.chess_design.*;
import model_design.location_design.location;
import org.junit.Assert;
import org.junit.Test;

public class Test_Vampire {
    @Test
    public void testVampireMove(){
        Board game = new Board(false);
        location ori_loc = new location(1,0);
        location ori_loc2 = new location(1,1);

        Pawn p1 = new Pawn(1);
        Pawn p2  = new Pawn(1);
        Pawn p3  = new Pawn(1);
        Pawn p4  = new Pawn(1);
        Vampire v1 = new Vampire(1);

        location l1 = new location(0,0);

        location l11 =new location(6,7);
        location l2 = new location(7,7);

        location l3 = new location(0,7);
        location l4 = new location(7,0);
        game.addChess(l2,p2);
        game.addChess(l3,p3);
        game.addChess(l4,p4);
        game.addChess(ori_loc,v1);
        Assert.assertTrue(v1.checkMove(ori_loc,ori_loc2,game.getBoard()));
        Assert.assertFalse(v1.transform(ori_loc,game.getBoard()));
        game.addChess(l1,p1);
        Assert.assertTrue(v1.transform(ori_loc,game.getBoard()));

        Assert.assertTrue(v1.checkMove(ori_loc,l11,game.getBoard() ));

    }
}
