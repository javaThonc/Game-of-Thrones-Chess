package board_test;

import model_design.board_design.Board;
import model_design.chess_design.King;
import model_design.chess_design.chess;
import model_design.location_design.location;
import model_design.logic_design.game_rule;
import org.junit.Assert;
import org.junit.Test;

public class Board_test {
    @Test
    /**
     * Test whether model_design.board_design.Board make the game before we start playing
     */
    public void BoardBuildTest(){
        Board game = new Board(false);
        chess [][] game_board = game.getBoard();
        game_rule.setBoard(game_board);
        for(int i = 0; i < 8; i++){
            Assert.assertEquals(game_board[i][1].getType(), chess.PAWN );
            Assert.assertEquals(game_board[i][6].getType(), chess.PAWN );
        }
        Assert.assertEquals(game_board[0][0].getType(), chess.ROOK );
        Assert.assertEquals(game_board[1][0].getType(), chess.KNIGHT );
        Assert.assertEquals(game_board[2][0].getType(), chess.BISHOP );
        Assert.assertEquals(game_board[3][0].getType(), chess.KING );
        Assert.assertEquals(game_board[4][0].getType(), chess.QUEEN );
        Assert.assertEquals(game_board[5][0].getType(), chess.BISHOP );
        Assert.assertEquals(game_board[6][0].getType(), chess.KNIGHT );
        Assert.assertEquals(game_board[7][0].getType(), chess.ROOK );

        Assert.assertEquals(game_board[0][7].getType(), chess.ROOK );
        Assert.assertEquals(game_board[1][7].getType(), chess.KNIGHT );
        Assert.assertEquals(game_board[2][7].getType(), chess.BISHOP );
        Assert.assertEquals(game_board[3][7].getType(), chess.KING );
        Assert.assertEquals(game_board[4][7].getType(), chess.QUEEN );
        Assert.assertEquals(game_board[5][7].getType(), chess.BISHOP );
        Assert.assertEquals(game_board[6][7].getType(), chess.KNIGHT );
        Assert.assertEquals(game_board[7][7].getType(), chess.ROOK );

        for(int i =0; i < 8; i++){
            for(int j=0; j<8; j++){
                location l1= new location(i,j);
                if(j==0||j==1|j==6||j==7){
                    Assert.assertTrue(game.removeChess(l1));
                }
            }
        }

        game = new Board(game);
        game_board = game.getBoard();
        game_rule.setBoard(game_board);
        for(int i = 0; i < 8; i++){
            Assert.assertEquals(game_board[i][1].getType(), chess.PAWN );
            Assert.assertEquals(game_board[i][6].getType(), chess.PAWN );
        }
        Assert.assertEquals(game_board[0][0].getType(), chess.ROOK );
        Assert.assertEquals(game_board[1][0].getType(), chess.KNIGHT );
        Assert.assertEquals(game_board[2][0].getType(), chess.BISHOP );
        Assert.assertEquals(game_board[3][0].getType(), chess.KING );
        Assert.assertEquals(game_board[4][0].getType(), chess.QUEEN );
        Assert.assertEquals(game_board[5][0].getType(), chess.BISHOP );
        Assert.assertEquals(game_board[6][0].getType(), chess.KNIGHT );
        Assert.assertEquals(game_board[7][0].getType(), chess.ROOK );

        Assert.assertEquals(game_board[0][7].getType(), chess.ROOK );
        Assert.assertEquals(game_board[1][7].getType(), chess.KNIGHT );
        Assert.assertEquals(game_board[2][7].getType(), chess.BISHOP );
        Assert.assertEquals(game_board[3][7].getType(), chess.KING );
        Assert.assertEquals(game_board[4][7].getType(), chess.QUEEN );
        Assert.assertEquals(game_board[5][7].getType(), chess.BISHOP );
        Assert.assertEquals(game_board[6][7].getType(), chess.KNIGHT );
        Assert.assertEquals(game_board[7][7].getType(), chess.ROOK );

        for(int i =0; i < 8; i++){
            for(int j=0; j<8; j++){
                location l1= new location(i,j);
                if(j==0||j==1|j==6||j==7){
                    Assert.assertTrue(game.removeChess(l1));
                }
            }
        }

        game = new Board(1);
        Assert.assertNotEquals(game, null);
        game.setBoard(new Board(true).getBoard());
        Assert.assertNotEquals(game.allPath(new location(1,1)),null);
        game.getPlayer();
        game.getPlayNum();
        Assert.assertNotEquals(new chess(new chess()), null);

    }
    @Test
    /**
     * Test whether model_design.board_design.Board make the game before we start playing
     */
    public void Board_functionality_test(){
        Board game = new Board(true);
        King k1 = new King(1);
        for(int i =0; i < 8; i++){
            for(int j=0; j<8; j++){
                location l1= new location(i,j);
                if(j==0||j==1|j==6||j==7){
                    Assert.assertFalse(game.addChess(l1,k1));
                }else{
                    Assert.assertFalse(game.removeChess(l1));
                    Assert.assertTrue(game.addChess(l1,k1));
                }
                Assert.assertTrue(game.removeChess(l1));
                Assert.assertTrue(game.isInside(l1));
            }
        }

    }
}
