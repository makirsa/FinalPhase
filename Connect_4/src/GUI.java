import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.net.URL;
import javax.swing.*;

/** Test setting Swing's JComponents properties and appearances */
@SuppressWarnings("serial")
public class GUI extends JFrame {
    private final Container cp;
    private JLabel label;
    private JPanel panel;
    private static JTextField name1, name2; //textield, enables user to enter in text
    private static BufferedReader br, bt;
    private JComboBox colorList1, colorList2; //Dropdown options
    String st;
	String rt;

    private final Connect4Game game;

    // TODO: Get it from the board
    int rows;
    int columns;

    // TODO: Calculate them instead
    int windowWidth = 750;
    int windowHeight = 650;

    // Prepare ImageIcons to be used with JComponents
    private ImageIcon iconEmpty = null;
    private ImageIcon iconRed = null;
    private ImageIcon iconYellow = null;
    private ImageIcon iconPurple = null;
    private ImageIcon iconPink = null;
    private ImageIcon iconOrange = null;
    private ImageIcon iconGreen = null;
    private ImageIcon iconBrown = null;


    
    private final String title = "Connect 4 - ";
    private void boardupdater(JButton button) {
        int row10plusCol = Integer.parseInt(button.getName());
        int col = row10plusCol % 10;

        boolean player1turn = game.isIs1playing();
        if(player1turn) setTitle(title + st);
        else setTitle(title + rt);

        int addedRow = game.round(col);
       

        if(addedRow != -1) {
            JButton buttonToUpdate = ((JButton)(cp.getComponent(columns * addedRow + col)));

            if(game.isIs1playing()) buttonToUpdate.setIcon(iconYellow);
            else buttonToUpdate.setIcon(iconRed);

            // check for winner
            if(game.checkForWinnerInGUI(col)) {
                JOptionPane.showMessageDialog(null, "You have won!");
                int reply = JOptionPane.showConfirmDialog(null, "Would you like to play again?", null, JOptionPane.YES_NO_OPTION);
                if(reply == JOptionPane.YES_OPTION) {
                    System.out.println("Trying to play again...");
                    game.reset(6, 7);
                    resetBoard();
         
                } else {
                	new MenuPage();
        			this.dispose(); //get rid of current frame
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a valid position.");
        }
    }
    

    public void resetBoard() {
        for(int row = 0; row < rows; row++)
            for (int col = 0; col < columns; col++)
                ((JButton)(cp.getComponent(columns * row + col))).setIcon(iconEmpty);
    }
    /** Constructor to setup the GUI */
    public GUI(boolean player1turn, Connect4Game game, int rows, int columns) {
        this.game = game;
        this.rows = rows;
        this.columns = columns;
        

        // Prepare Icons
        // Image path relative to the project root (i.e., bin)
        String imgEmptyFilename = "Images/empty.png";
        URL imgURL = getClass().getClassLoader().getResource(imgEmptyFilename);
        if (imgURL != null) iconEmpty = new ImageIcon(imgURL);
        else System.err.println("Couldn't find file: " + imgEmptyFilename);

        String imgRedFilename = "Images/red.png";
        imgURL = getClass().getClassLoader().getResource(imgRedFilename);
        if (imgURL != null) iconRed = new ImageIcon(imgURL);
        else System.err.println("Couldn't find file: " + imgRedFilename);


        String imgYellowFilename = "Images/yellow.png";
        imgURL = getClass().getClassLoader().getResource(imgYellowFilename);
        if (imgURL != null) iconYellow = new ImageIcon(imgURL);
        else System.err.println("Couldn't find file: " + imgYellowFilename);
        
        String imgBrownFilename = "Images/brown.png";
        imgURL = getClass().getClassLoader().getResource(imgBrownFilename);
        if (imgURL != null) iconBrown = new ImageIcon(imgURL);
        else System.err.println("Couldn't find file: " + imgBrownFilename);
        
        String imgGreenFilename = "Images/green.png";
        imgURL = getClass().getClassLoader().getResource(imgGreenFilename);
        if (imgURL != null) iconGreen = new ImageIcon(imgURL);
        else System.err.println("Couldn't find file: " + imgGreenFilename);
        
        String imgOrangeFilename = "Images/orange.png";
        imgURL = getClass().getClassLoader().getResource(imgOrangeFilename);
        if (imgURL != null) iconOrange = new ImageIcon(imgURL);
        else System.err.println("Couldn't find file: " + imgOrangeFilename);
        
        String imgPinkFilename = "Images/pink.png";
        imgURL = getClass().getClassLoader().getResource(imgPinkFilename);
        if (imgURL != null) iconPink = new ImageIcon(imgURL);
        else System.err.println("Couldn't find file: " + imgPinkFilename);
        
        String imgPurpleFilename = "Images/purple.png";
        imgURL = getClass().getClassLoader().getResource(imgPurpleFilename);
        if (imgURL != null) iconPurple = new ImageIcon(imgURL);
        else System.err.println("Couldn't find file: " + imgPurpleFilename);

        cp = getContentPane();
        cp.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        for(int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                JButton button = new JButton(); // use setter to set text and icon
                button.setIcon(iconEmpty);
                button.setPreferredSize(new Dimension(100, 100));
                // row * 10 + col
                button.setName(Integer.toString((row * 10 + col)));

                button.addActionListener(actionEvent -> boardupdater(((JButton) (actionEvent.getSource()))));
                cp.add(button);
            }
        }
		try {
			br = new BufferedReader(new FileReader("Player1.txt"));
			bt = new BufferedReader(new FileReader("Player2.txt"));
	        // Condition holds true till there is character in a string
	        while ((st = br.readLine()) == null) {
	        	 if(player1turn) setTitle(title + st);
	             //else setTitle(title + "Player 1");
	        }
	       while ((rt = bt.readLine()) == null) {
	        	 if(!player1turn) setTitle(title + rt);
	        }
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
        //cp.add(label);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(windowWidth, windowHeight);
        setLocationRelativeTo(null); // center window on the screen
        setResizable(false); //user cannot resize frame
        setVisible(true);
    }
}