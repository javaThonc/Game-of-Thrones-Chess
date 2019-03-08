package logic_test;

import model_design.board_design.Board;
import model_design.chess_design.*;
import model_design.location_design.location;
import model_design.logic_design.game_rule;
import org.junit.Assert;
import org.junit.Test;
public class TestCheckMate {
    @Test
    public void Test_end_game(){
        Board game = new Board(false);
        location l1 = new location(1,1);
        location l2 = new location(0,1);
        location l3 = new location(0,2);

        King k1 = new King(1);
        King k2 = new King (1);
        King k3 = new King(2);

        game.addChess(l1,k1);
        game.addChess(l2,k2);
        game.addChess(l3,k3);

        chess [][] chessboard = game.getBoard();

        // Fake capture of the same team
        Assert.assertEquals(game.isGameOver(), false );
        Assert.assertEquals(game.move(l1,l2),null);
        Assert.assertEquals(game.isGameOver(), false );
        // Kill the opponent king
        game.move(l1,l3);
        Assert.assertEquals(game.isGameOver(), true );
        Assert.assertEquals(game.move(l1,l2),null);
    }

    @Test
    public void Test_checkMate_straight(){
        Board game = new Board(false);
        chess[][] board = game.getBoard();

        Queen q1 = new Queen(2);
        location ori_loc = new location (2,2);
        game.addChess(ori_loc, q1);

        Assert.assertTrue(game_rule.checkStraight(board, 7,2, 1));
        Assert.assertTrue(game_rule.checkStraight(board, 2,7, 1));
        Assert.assertTrue(game_rule.checkStraight(board, 2,0, 1));
        Assert.assertTrue(game_rule.checkStraight(board, 0,2, 1));

        Assert.assertFalse(game_rule.checkStraight(board, 1,1, 1));
        Assert.assertFalse(game_rule.checkStraight(board, 3,3, 1));
        Assert.assertFalse(game_rule.checkStraight(board, 0,0, 1));

    }
    @Test
    public void Test_checkMate_diagonal(){
        Board game = new Board(false);
        chess[][] board = game.getBoard();

        Queen q1 = new Queen(2);
        location ori_loc = new location (2,2);
        game.addChess(ori_loc, q1);

        Assert.assertFalse(game_rule.checkDiagonal(board, 7,2, 1));
        Assert.assertFalse(game_rule.checkDiagonal(board, 2,7, 1));
        Assert.assertFalse(game_rule.checkDiagonal(board, 2,0, 1));
        Assert.assertFalse(game_rule.checkDiagonal(board, 0,2, 1));

        Assert.assertTrue(game_rule.checkDiagonal(board, 1,1, 1));
        Assert.assertTrue(game_rule.checkDiagonal(board, 3,1, 1));
        Assert.assertTrue(game_rule.checkDiagonal(board, 1,3, 1));
        Assert.assertTrue(game_rule.checkDiagonal(board, 3,3, 1));
    }
    @Test
    public void Test_checkMate_Pawn(){
        Board game = new Board(false);
        chess[][] board = game.getBoard();

        Pawn p1 = new Pawn(2);
        location ori_loc = new location (2,2);
        game.addChess(ori_loc, p1);

        Assert.assertTrue(game_rule.checkPawn(board, 3,1, 1));
        Assert.assertTrue(game_rule.checkPawn(board, 1,1, 1));

        Assert.assertFalse(game_rule.checkPawn(board, 1,3, 1));
        Assert.assertFalse(game_rule.checkPawn(board, 3,3, 1));
    }
    @Test
    public void Test_checkMate_King(){
        Board game = new Board(false);
        chess[][] board = game.getBoard();

        King k1 = new King(2);
        location ori_loc = new location (2,2);
        game.addChess(ori_loc, k1);

        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(Math.abs(i-2)<2&&Math.abs(j-2)<2){
                    Assert.assertTrue(game_rule.checkKing(board, i,j, 1));
                }else{
                    Assert.assertFalse(game_rule.checkKing(board, i,j, 1));
                }
            }
        }
    }
    @Test
    public void Test_checkMate_by_knight(){
        Board game = new Board(false);
        chess[][] board = game.getBoard();

        Knight kn1 = new Knight(2);
        location ori_loc = new location (2,2);
        game.addChess(ori_loc, kn1);

        Assert.assertTrue(game_rule.checkKnight(board, 1,0, 1));
        Assert.assertTrue(game_rule.checkKnight(board, 1,4, 1));
        Assert.assertTrue(game_rule.checkKnight(board, 4,3, 1));
        Assert.assertTrue(game_rule.checkKnight(board, 4,1, 1));

        Assert.assertFalse(game_rule.checkKnight(board, 1,1, 1));
        Assert.assertFalse(game_rule.checkKnight(board, 3,1, 1));
        Assert.assertFalse(game_rule.checkKnight(board, 1,3, 1));
        Assert.assertFalse(game_rule.checkKnight(board, 3,3, 1));
    }
    @Test
    public void real_check_mate_test(){
        Board game = new Board(false);
        chess[][] board = game.getBoard();
        Rook r1 = new Rook(1);
        King k1 = new King(2);


        location rook_l1 = new location(0,0);
        location rook_l2  = new location(0,1);
        location king_l1 = new location(3,1);
        Assert.assertFalse(game.getCheckmate());
        game.addChess(rook_l1, r1);
        game.addChess(king_l1, k1);
        game.move(rook_l1,rook_l2);
        Assert.assertTrue(game.getCheckmate());
    }
}
