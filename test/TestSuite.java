import board_test.Board_test;
import chess_test.*;
import logic_test.TestCheckMate;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        Board_test.class,
        TestBishop.class,
        TestKing.class,
        TestKnight.class,
        TestPawn.class,
        TestQueen.class,
        TestRook.class,
        TestCheckMate.class,
        Test_Vampire.class,
        TestDragon.class
})

public class TestSuite {
}  	