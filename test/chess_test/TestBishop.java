package chess_test;

import model_design.board_design.Board;
import model_design.chess_design.Bishop;
import model_design.chess_design.King;
import model_design.location_design.location;
import org.junit.Assert;
import org.junit.Test;
public class TestBishop {
    @Test
    public void testBishopMove(){
        Board game = new Board(false);
        location ori_loc = new location(1,1);
        Bishop b1 = new Bishop(1);

        location valid_l1 = new location(0,0);
        location valid_l2 = new location(0,2);
        location valid_l3 = new location(7,7);
        location valid_l4 = new location(2,0);

        location invalid_l1 = new location(1,2);
        location invalid_l2 = new location(1,0);
        location invalid_l3 = new location(0,1);
        location invalid_l4 = new location(2,1);


        Assert.assertTrue(b1.checkMove(ori_loc,valid_l1, game.getBoard()));
        Assert.assertTrue(b1.checkMove(ori_loc,valid_l2, game.getBoard()));
        Assert.assertTrue(b1.checkMove(ori_loc,valid_l3, game.getBoard()));
        Assert.assertTrue(b1.checkMove(ori_loc,valid_l4, game.getBoard()));

        Assert.assertFalse(b1.checkMove(ori_loc,invalid_l1, game.getBoard()));
        Assert.assertFalse(b1.checkMove(ori_loc,invalid_l2, game.getBoard()));
        Assert.assertFalse(b1.checkMove(ori_loc,invalid_l3, game.getBoard()));
        Assert.assertFalse(b1.checkMove(ori_loc,invalid_l4, game.getBoard()));

    }
    @Test
    public void testBishopBlock(){
        Board game = new Board(false);
        location ori_loc = new location(1,1);
        Bishop b1 = new Bishop(1);
        King k1 = new King(1);

        location valid_l1 = new location(0,0);
        location valid_l2 = new location(0,2);
        location valid_l3 = new location(7,7);
        location valid_l4 = new location(2,0);

        location block_1 = new location(2,2);
        location block_2 = new location(3,3);
        location block_3 = new location(4,4);
        location block_4 = new location(5,5);
        location block_5 = new location(6,6);

        location free_1 = new location(6,7);
        game.addChess(ori_loc,b1);
        game.addChess(block_1,k1);
        Assert.assertFalse(b1.checkMove(ori_loc,valid_l3, game.getBoard()));
        Assert.assertNotEquals(game.move(block_1,block_2),null);
        Assert.assertFalse(b1.checkMove(ori_loc,valid_l3, game.getBoard()));
        game.setPlayer(1);
        Assert.assertNotEquals(game.move(block_2,block_3),null);
        game.setPlayer(1);
        Assert.assertFalse(b1.checkMove(ori_loc,valid_l3, game.getBoard()));
        game.setPlayer(1);
        Assert.assertNotEquals(game.move(block_3,block_4),null);
        Assert.assertFalse(b1.checkMove(ori_loc,valid_l3, game.getBoard()));
        game.setPlayer(1);
        Assert.assertNotEquals(game.move(block_4,block_5),null);
        Assert.assertFalse(b1.checkMove(ori_loc,valid_l3, game.getBoard()));
        game.setPlayer(1);
        Assert.assertNotEquals(game.move(block_5,free_1),null);
        Assert.assertTrue(b1.checkMove(ori_loc,valid_l3, game.getBoard()));

    }
    @Test
    public void testBishopBlock2(){
        Board game = new Board(false);
        location ori_loc = new location(3,3);
        Bishop b1 = new Bishop(1);
        King k1 = new King(1);

        location block_1 = new location(2,4);
        location valid_l1 = new location(1,5);
        game.addChess(ori_loc,b1);
        game.addChess(block_1,k1);

        Assert.assertFalse(b1.checkMove(ori_loc,valid_l1, game.getBoard()));

    }
}
