/**
 * @author Makirsa Selvaranjan & Sadaf Hairat
 */
//Importing java packages
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MenuPage extends FormatFrame implements ActionListener{
	//Instance variables
	private ImageIcon bg, bgGif; //background image of menu screen and the background gif that will be put on labelGif
	private JButton jbtSetup ,jbtRules, jbtLeaderBoard, jbtExit; //action buttons on main menu
	private PaintPanel canvas; //all components are put on a PaintPanel which holds the background image
	private JLabel heading, labelGif; //the heading label of the Main Menu and the label that will hold bgGif
	private final Color MENU_COLOR_FG = new Color(25,25,112), MENU_COLOR_BG = new Color(51,153,153); //constant colors of menu button foreground and background respectively
	private final Font MENU_FONT = new Font("Sanserif", Font.PLAIN, 25); //constant font of menu buttons
	//Constructor for MenuScreen
	MenuPage(){
		super(); //calls ScreenFrame constructor creating a new ScreenFrame
		
	//set a background image
	bg = new ImageIcon("images/bgMenu.png");
	//instantiate canvas and set null layout manager of canvas so we can manually place components
	canvas = new PaintPanel();
	canvas.setLayout(null);
	this.add(canvas); //add canvas to frame
		
	bgGif = new ImageIcon("images/connect4Logo.gif");
	labelGif = new JLabel(bgGif);
	canvas.add(labelGif);
	labelGif.setBounds(280, 150, 500, 400);
	
		
	//add a custom label for the heading
	canvas.add(heading = addCustomLabel("Connect-4!", MENU_COLOR_FG, new Font("Sanserif", Font.BOLD, 80)));
	heading.setBounds(275, 25, 900, 150); //set bounds for the heading on the canvas
		
	//add the Play button
	jbtSetup = addCustomButton("Play", 50, 650, 200, 80, MENU_COLOR_FG, MENU_COLOR_BG, MENU_FONT);
	jbtSetup.addActionListener(this); //register action listener
	canvas.add(jbtSetup); //add to canvas
		
	//add the Instructions button
	jbtRules = addCustomButton("Rules", 280, 650, 200, 80, MENU_COLOR_FG, MENU_COLOR_BG, MENU_FONT);
	jbtRules.addActionListener(this); //register action listener
	canvas.add(jbtRules); //add to canvas

	//add the Scores button
	jbtLeaderBoard = addCustomButton("Leader Board ", 510, 650, 200, 80, MENU_COLOR_FG, MENU_COLOR_BG, MENU_FONT);
	jbtLeaderBoard.addActionListener(this); //register action listener
	canvas.add(jbtLeaderBoard); //add to canvas

		//add the Exit button
		jbtExit = addCustomButton("Exit", 740, 650, 200, 80, MENU_COLOR_FG, MENU_COLOR_BG, MENU_FONT);
		jbtExit.addActionListener(this); //register action listener
		canvas.add(jbtExit); //add to canvas
		
	}//end of MenuScreen constructor

	/**
	 * This method is implemented from the ActionListener interface. Each action performed leads to a different scenario.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//different scenarios for each button
		if(e.getSource() == jbtSetup) {
			//create new instance of DifficultyScreen
			new SetupPage();
			this.dispose(); //get rid of current frame
		}
		else if(e.getSource() == jbtRules) {
			//create new instance of InstructionsScreen
			new RulesPage();
			this.dispose(); //get rid of current frame
		}
		else if(e.getSource() == jbtLeaderBoard) {
			//create new instance of ScoreScreen
			new LeaderBoardPage();
			//this.dispose(); //get rid of current frame
		}
		//exit button exits the program
		else if(e.getSource() == jbtExit) {
			//exit the program
			System.exit(0);
		}
	}//end of actionPerformed method
	
	/*
	 * This class is a canvas that draws the background image. All components are added to this Panel, then the
	 * PaintPanel is added to the JFrame.
	 */
	class PaintPanel extends JPanel{
		protected void paintComponent(Graphics g){
			super.paintComponent(g); //override the paintComponent method
			//draw the background image to fill the entire panel
			g.drawImage(bg.getImage(),0,0,this.getWidth(),this.getHeight(),null);
		}//end of paintComponent method
	}

}
