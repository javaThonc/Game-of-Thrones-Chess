package view_design.GUI_design;

import model_design.board_design.Board;
import model_design.chess_design.*;
import model_design.location_design.location;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.*;




public class boardGui extends JFrame {
    /**
     * @param is the grid that specify the each grid on the chess board
     */
    private JButton[][] grid;
    private JMenuItem[] menuItems;
    public static final int Start = 0;
    public static final int Restart = 1;
    public static final int Forfeit = 2;
    public static final int Undo = 3;
    public static final int Speaker = 4;


    int row;
    int column;

    Color dark;
    Color light;

    /**
     * Build a boardGui without the chess on board
     * Since we are building a standard chess board
     * assume the size is 8*8
     */
    public boardGui(){
        row = 8;
        column = 8;

        dark = new Color(		0, 26, 51);
        light = new Color(225, 212, 192);
        grid = new JButton[8][8];

        for(int x_dim = 0; x_dim<row; x_dim++){
            for(int y_dim = 0; y_dim<column; y_dim++){
                // assign a JButton
                grid[x_dim][y_dim] = new JButton();
                this.add(grid[x_dim][y_dim]);
                // Set color with respect to the real chess board
                if(x_dim%2==0){
                    if(y_dim%2==0){
                        setButton(dark, x_dim, y_dim);
                    }else{
                        setButton(light,x_dim, y_dim);
                    }
                }else{
                    if(y_dim%2==1){
                        setButton(dark, x_dim, y_dim);
                    }else{
                        setButton(light, x_dim, y_dim);
                    }
                }
            }
        }
        this.setLayout(new GridLayout(8,8));

        // Set a Boarder
        getRootPane().setBorder(BorderFactory.createLineBorder(new Color(	31, 18, 19), 35, false));
        Border border = getRootPane().getBorder();
        Border margin = BorderFactory.createLineBorder(new Color(197,179,88), 6, false);
        getRootPane().setBorder(new CompoundBorder(border, margin));

        setSize(800,800);
        setVisible(true);

        setMenuBar();
    }



    /**
     * Set Chess Image on board with Jbutton
     * with clickable reaction that print the current location!
     * by the board that is assigned
     */
    public void setChess( chess[][] chess_board){
        // Allocate a true board
//        Board board = new Board(true);
//        chess[][] chess_board = board.getBoard();
        for(int i=0; i < row; i++){
            for(int j=0; j < column;j++) {
                if (chess_board[i][j] != null) {
                    // Get Image by type and owner
                    ImageIcon icon = new ImageIcon("/Users/javathon/Desktop/CS242/sp-19-cs242-assignment1.0/src/view_design/Images/" +
                            chess_board[i][j].getType() + '-' + chess_board[i][j].getOwner() + ".png");
                    icon = resizeIcon(icon,90);
                    grid[i][j].setIcon(icon);
                    int x_dim = i;
                    int y_dim = j;

                } else {
                    grid[i][j].setIcon(null);
                }
            }
        }
    }

    /**
     * Set up MenuBar to implement the functionality that is required
     */
    public void setMenuBar(){
        menuItems = new JMenuItem[5];

        Color dYello = new Color(197,179,88);
        Font dFont =  new Font("Arial", Font.BOLD, 16);

        JMenuBar menuBar = new JMenuBar();

        menuBar.setBorderPainted(true);
        menuItems[0] = new JMenuItem("Start");
        setupMenu(menuItems[0], dYello, dFont);

        menuItems[1] = new JMenuItem("Restart");
        setupMenu(menuItems[1], dYello, dFont);

        menuItems[2] = new JMenuItem("Forfeit");
        setupMenu(menuItems[2], dYello, dFont);

        menuItems[3] = new JMenuItem("Undo");
        setupMenu(menuItems[3], dYello, dFont);

        menuItems[4] = new JMenuItem("");
        ImageIcon icon = new ImageIcon("/Users/javathon/Desktop/CS242/sp-19-cs242-assignment1.0/src/view_design/Images/speaker.png");
        icon = resizeIcon(icon, 20);
        menuItems[4].setIcon(icon);

        setupMenu(menuItems[4], dYello, dFont);

        menuBar.add(menuItems[0]);
        menuBar.add(menuItems[1]);
        menuBar.add(menuItems[2]);
        menuBar.add(menuItems[3]);
        menuBar.add(menuItems[4]);


        this.getRootPane().setMenuBar(menuBar);
    }

    /**
     * Set up background color as well as the font of a menu
     * @param Item
     * @param color
     * @param font
     */
    public void setupMenu(JMenuItem Item, Color color, Font font){
        Item.setOpaque(true);
        Item.setBackground(color);
        Item.setFont(font);
    }

    /**
     * Resize Icon to make it look fit into the one grid
     * @param icon the icon to change the size
     * @return  the after-resized button
     */
    public ImageIcon resizeIcon(ImageIcon icon, int size){
        Image image = icon.getImage();
        Image newimg = image.getScaledInstance(size, size,  java.awt.Image.SCALE_AREA_AVERAGING);
        icon = new ImageIcon(newimg);
        return icon;
    }

    /**
     * Set button to a color
     * @param color color that is set to the button
     * @param x and y are x_axis and y_axis
     */
    public void setButton(Color color, int x, int y){
        JButton button = grid[x][y];
        button.setOpaque(true);
        button.setBackground(color);
        button.setBorderPainted(false);
    }

    /**
     * Help function to define the action of menuitems
     * @param action
     * @param item
     */
    public void addMenuListener(ActionListener action, int item){
        System.out.println("??fds");
        menuItems[item].addActionListener(action);
    }

    /**
     * Help function to define the action of buttons
     * @param action
     * @param itemX, itemY
     */
    public void addButtonListener(ActionListener action, int itemX, int itemY){
        grid[itemX][itemY].addActionListener(action);
    }
    /**
     * return the target button
     */
    public void setButtonBoarder(int x, int y){
        JButton button = grid[x][y];
//        button.setBorder(BorderFactory.createLineBorder(new Color(	31, 18, 19), 2, false));
//        button.setBorder(new LineBorder(Color.BLACK));
        button.setBackground( new Color(197,179,88));
        button.setBorderPainted(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(197,179,88), 15, false));
        Border b = button.getBorder();
        Border margin = BorderFactory.createLineBorder(new Color(0, 26, 51), 5, false);
        button.setBorder(new CompoundBorder(b, margin));
    }
    /**
     * Remove the Icon on the button to another button
     */
    public void moveIcon(location start, location end){
        Icon icon = grid[start.getX()][start.getY()].getIcon();
        grid[end.getX()][end.getY()].setIcon(icon);
        grid[start.getX()][start.getY()].setIcon(null);
    }

    /**
     * Set the background color of buttons of origin color
     */
    public void buttonBackground(){
        for(int x_dim = 0; x_dim<row; x_dim++){
            for(int y_dim = 0; y_dim<column; y_dim++){
                // Set color with respect to the real chess board
                if(x_dim%2==0){
                    if(y_dim%2==0){
                        setButton(dark, x_dim, y_dim);
                    }else{
                        setButton(light,x_dim, y_dim);
                    }
                }else{
                    if(y_dim%2==1){
                        setButton(dark, x_dim, y_dim);
                    }else{
                        setButton(light, x_dim, y_dim);
                    }
                }
            }
        }
    }
}
