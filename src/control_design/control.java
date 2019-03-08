package control_design;

import model_design.board_design.Board;
import model_design.chess_design.Pawn;
import model_design.chess_design.chess;
import model_design.location_design.location;
import view_design.GUI_design.boardGui;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class control {
    private boardGui boardGui;
    private Board board;
    boolean isStart;
    List<location> paths;
    boolean firstClick;
    location lastPlace;
    boolean checkMate;
    Stack<Board> process;
    int playerOneScore;
    int playerTwoScore;
    boolean forfeit;
    boolean speaker;
    Clip clip;
    AudioInputStream audioInputStream;

    public  control(){
        // See whether the game is initalize to see
        isStart = false;
        paths = new ArrayList<location>();
        firstClick = true;
        checkMate = false;
        playerOneScore = 0;
        playerTwoScore = 0;
        forfeit = false;
        speaker = false;
    }

    /**
     *  Initialize the Board as well as the board view
     */
    public void init() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        audioInputStream =
                AudioSystem.getAudioInputStream(new File("/Users/javathon/Desktop/CS242/sp-19-cs242-assignment1.0/src/view_design/Audios/song.waV").getAbsoluteFile());
        clip = AudioSystem.getClip();
        process = new Stack<Board>();
        boardGui = new boardGui();
        board = new Board(true);
        boardGui.setChess(board.getBoard());

        // Add action to each button
        for(int i =0; i < 8; i++){
            for(int  j=0; j < 8; j++){
                int row = i;
                int col = j;
                boardGui.addButtonListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        buttonAction(row, col);
                    }
                }, i, j);
            }
        }

        // Add action to the Start menu
        boardGui.addMenuListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startAction();
            }
        } , boardGui.Start);

        // Add action to the Start menu
        boardGui.addMenuListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartAction();
            }
        } , boardGui.Restart);

        // Add action to the Forfeit menu
        boardGui.addMenuListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                forfeitAction();
            }
        } , boardGui.Forfeit);

        // Add action to the Undo menu
        boardGui.addMenuListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                undoAction();
            }
        } , boardGui.Undo);

        // Add action to the Undo menu
        boardGui.addMenuListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    speakerAction();
                }  catch (LineUnavailableException e1) {

                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedAudioFileException e1) {
                    e1.printStackTrace();
                }

            }
        } , boardGui.Speaker);


    }
    /**
     *The action of speaker
     */
    public void speakerAction() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException  {
        if(!speaker){
            clip.open(audioInputStream);
            speaker = true;
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }else{
            speaker = false;
            clip.stop();
            clip.close();
        }
    }

    /**
     * The action of forfeit MenuItem
     */
    public void forfeitAction(){
        int result = JOptionPane.showConfirmDialog(null,
                board.getPlayer() +" do you want to forfeit? ", "choose one", JOptionPane.YES_NO_OPTION);
        if(result==JOptionPane.YES_NO_OPTION){
            forfeit = true;
        }
    }
    /**
     * The action of Restart MenuItem
     */
    public void restartAction(){
        int result = JOptionPane.showConfirmDialog(null,
                board.playerName1 +" do you want to restart? ", "choose one", JOptionPane.YES_NO_OPTION);
        int result2 = JOptionPane.showConfirmDialog(null,
                board.playerName2 +" do you want to restart? ", "choose one", JOptionPane.YES_NO_OPTION);

        if(result==JOptionPane.YES_NO_OPTION&&result2==JOptionPane.YES_NO_OPTION){
            board = new Board(true);
            boardGui.setChess(board.getBoard());
            checkMate = false;
            playerOneScore = 0;
            playerTwoScore = 0;
            forfeit = false;
        }
    }
    /**
     * The action of the Start Menuitem
     */
    public void startAction(){
        if(!isStart){
            isStart = true;
            board.playerName1 = JOptionPane.showInputDialog(null,
                    "What is your name! Player One?", null);
            board.playerName2 = JOptionPane.showInputDialog(null,
                    "What is your name! Player Two?", null);
            int result = JOptionPane.showConfirmDialog(null,
                    "BE A LEGEND?", "choose one", JOptionPane.YES_NO_OPTION);
            if(result==JOptionPane.YES_NO_OPTION){
                board = new Board(1);
                boardGui.setChess(board.getBoard());
            }
        }else{
            JOptionPane.showMessageDialog(null, "Game On!!");
        }
    }
    /**
     * Action that Button should perform
     * @param row  are row of the chess board
     * @param col are  cols of the chess board
     */
    public void buttonAction(int row, int col){
        if(forfeit){
            JOptionPane.showMessageDialog(null, board.getPlayer() +" forfeit the game, click restart to restart the game!");
        }
        if(board.isGameOver()){
            JOptionPane.showMessageDialog(null, "The King is dead, Winter Is Coming!!!");
            return;
        }
        //Has not started yet
        if(!isStart){
            JOptionPane.showMessageDialog(null, "Game Not Begin, Click Start");
        }else{
            if(firstClick){
                location currentLoc = new location(row, col);
                if(!board.isPlayer(currentLoc)){
                    JOptionPane.showMessageDialog(null, board.getPlayer() + "! It is not your turn!");
                }else{
                    List<location> locs = board.allPath(currentLoc);
                    if(locs!=null){
                        // Make all the block into highlight??
                        for(location cur : locs){
                            int x = cur.getX();
                            int y = cur.getY();
                            boardGui.setButtonBoarder(x,y);
                            paths.add(cur);
                        }
                        lastPlace = currentLoc;
                        firstClick = false;
                    }
                }
            } else {
                // boolean type to see whether we decide to move a chess
                boolean change = true;
                for(location loc : paths){
                    if(loc.getX()==row && loc.getY() == col){
                        change = false;
                    }
                }
                // Not to change the destination of the chess, we wang to move the chess
                if(!change){
                    // move one the board (model)
                    location end = new location(row, col);
                    Board newBoard = new Board(board);
                    process.add(newBoard);
                    location result  = board.move(lastPlace, end);
                    // not a valid move
                    if(result==null && board.isGameOver()){
                        JOptionPane.showMessageDialog(null, "Game is Over, the Great King is dead");
                        return;
                    }else{
                        boardGui.moveIcon(lastPlace, end);
                    }
                }
                boardGui.buttonBackground();
                firstClick = true;
                paths.clear();
                if(board.getCheckmate()){
                    JOptionPane.showMessageDialog(null, "Checkmate!!!");
                    checkMate = true;
                }
            }
        }
    }

    /**
     * The action of Restart MenuItem
     */
    public void undoAction(){
        JOptionPane.showMessageDialog(null,  "Undo!!!");
        board = process.pop();
        boardGui.setChess(board.getBoard());
        for(int i=0; i < 8; i++){
            if (board.getBoard()[i][1].getType()==chess.PAWN
                 &&board.getBoard()[i][1].getOwner() == 1) {
                ((Pawn)board.getBoard()[i][1]).first_move = true;
            }
            if (board.getBoard()[i][6].getType()==chess.PAWN
                    &&board.getBoard()[i][6].getOwner() == 2) {
                ((Pawn)board.getBoard()[i][6]).first_move = true;
            }
        }
    }

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        control c = new control();
        c.init();
    }
}
