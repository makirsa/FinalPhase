/**
 * This is where the player can setup the game, by inputting username snad chip color choices
 */
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.JTextField;

import javax.swing.*;

@SuppressWarnings("serial")
public class SetupPage extends FormatFrame implements ActionListener{
	private ImageIcon bg; //bg will be used once an appropriate image is found
	private JButton jbtPlay, jbtMenu, jbtOk;
	private JComboBox colorList1, colorList2; //chip color dropdown
	private static JTextField name1, name2; //textfield that allows user to enter usernames
	private PaintPanel canvas = new PaintPanel(); //all components are put on a PaintPanel which holds the background image (bg)
	private JLabel label; //text label
	private final Color DIFFICULTY_COLOR_FG = new Color(25,25,112), DIFFICULTY_COLOR_BG = Color.cyan; //constant colors of difficulty button foreground and background respectively
	private final Font DIFFICULTY_FONT = new Font("Sanserif", Font.PLAIN, 25); //constant font of difficulty buttons
	
	//Constructor for DifficultyScreen:
	SetupPage(){
		super(); //calls ScreenFrame constructor creating a new ScreenFrame
		
		
		//set a background image
		bg = new ImageIcon("images/bgMenu.png");
		//instantiate canvas and set null layout manager of canvas so we can manually place components
		canvas = new PaintPanel();
		canvas.setLayout(null);
		this.add(canvas); //add canvas to Setup Page
		
		//Label prompting the user to press a button
		label = addCustomLabel("Setup", DIFFICULTY_COLOR_FG, new Font("MV Boli", Font.BOLD, 70));
		//Sets bounds of the label
		label.setBounds(380, -315, this.getWidth() - 1, this.getHeight() - 1 );
		canvas.add(label); //add label to canvas
		
		//Add custom button for medium mode
		jbtPlay = addCustomButton("Play", 750, 650, 200, 80, DIFFICULTY_COLOR_FG, DIFFICULTY_COLOR_BG, DIFFICULTY_FONT);
		jbtPlay.addActionListener(this); //register action listener
		canvas.add(jbtPlay); //add button to canvas
		
		
		//Add custom button to return to menu
		jbtMenu = addCustomButton("Menu", 50, 650, 200, 80, DIFFICULTY_COLOR_FG, DIFFICULTY_COLOR_BG, DIFFICULTY_FONT);
		jbtMenu.addActionListener(this); //register action listener
		canvas.add(jbtMenu); //add button to canvas
		
		//Add custom button to confirm setup
		jbtOk = addCustomButton("OK", 420, 400, 200, 80, DIFFICULTY_COLOR_FG, DIFFICULTY_COLOR_BG, DIFFICULTY_FONT);
		jbtOk.addActionListener(this); //register action listener
		canvas.add(jbtOk); //add button to canvas
		
		//Input prompt for first player
		JLabel userName_1 = new JLabel("Player 1: ");
		userName_1.setFont(new Font("Ariel", Font.BOLD, 40)); // font type and size
		userName_1.setBounds(100, 150, 200, 40);
		canvas.add(userName_1);
		
		
		name1 = new JTextField(10); // gets users input
		name1.setBounds(280, 150, 175, 40); // set bounds
		canvas.add(name1); // adds the text box
		
		
		//Input prompt for second player
		JLabel userName_2 = new JLabel("Player 2: ");
		userName_2.setFont(new Font("Ariel", Font.BOLD, 40)); // font type and size
		userName_2.setBounds(100, 230, 200, 40);
		canvas.add(userName_2);
				
		name2 = new JTextField(10); // gets users input
		name2.setBounds(280, 232, 175, 40); // set bounds
		canvas.add(name2); // adds the text box
		
		//Color input for first player
		JLabel color1 = new JLabel("Chip Color:");
		color1.setFont(new Font("Ariel", Font.BOLD, 40)); // font type and size
		color1.setBounds(500, 150, 250, 40);
		canvas.add(color1);
		
		String[] colorStrings1 = { "Red", "Yellow", "Purple", "Pink", "Orange", "Green", "Brown" };
        //Create the combo box, select the item at index 6.
        //Indexes start at 0, so 6 specifies Brown.
        colorList1 = new JComboBox(colorStrings1);
        colorList1.setSelectedIndex(0);
        colorList1.addActionListener(this);
        colorList1.setPreferredSize(new Dimension(500,25));
        colorList1.setBounds(725, 160, 150, 30);
        canvas.add(colorList1);
		
		//Color input for Second player
		JLabel color2 = new JLabel("Chip Color:");
		color2.setFont(new Font("Ariel", Font.BOLD, 40)); // font type and size
		color2.setBounds(500, 230, 250, 40);
		canvas.add(color2);
		
		String[] colorStrings2 = { "Red", "Yellow", "Purple", "Pink", "Orange", "Green", "Brown" };
        //Create the combo box, select the item at index 6.
        //Indexes start at 0, so 6 specifies Brown.
        colorList2 = new JComboBox(colorStrings2);
        colorList2.setSelectedIndex(1);
        colorList2.addActionListener(this);
        colorList2.setPreferredSize(new Dimension(500,25));
        colorList2.setBounds(725, 240, 150, 30);
        canvas.add(colorList2);
        		
	}//end of Setup Screen constructor

	/**
	 * This method is implemented from the ActionListener interface. Each action performed leads to a different scenario.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jbtOk) {
			try {
				BufferedWriter writer1 = new BufferedWriter(new FileWriter("Player1.txt"));
				BufferedWriter writer2 = new BufferedWriter(new FileWriter("Player2.txt"));
				
				name1.write(writer1);
				name2.write(writer2);
			    writer1.close();
			    writer2.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		if(e.getSource() == jbtPlay) {
			new GameLoop();
			this.dispose(); //get rid of current frame
			
		}
		else if(e.getSource() == jbtMenu) {
			//create new instance of MenuScreen class
			new MenuPage();
			this.dispose(); //get rid of current frame
		}
		

	}//end of actionPerformed method
	
	/*
	 * This class is a canvas that draws the background image. All components are added to this Panel, then the
	 * PaintPanel is added to the JFrame.
	 */
	class PaintPanel extends JPanel{
		protected void paintComponent(Graphics g){
			//Prints the canvas that holds the buttons and label
			super.paintComponent(g); //override the paintComponent method
			//draw the background image to fill the entire panel
			g.drawImage(bg.getImage(),0,0,this.getWidth(),this.getHeight(),null);
		}//end of paintComponent method
			
	}


}

