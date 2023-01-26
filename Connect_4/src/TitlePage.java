/**
 * @author Makirsa Selvaranjan & Sadaf Hairat
 * This client class is the Title page. This will be the first thing that is run when the project is opened.
 * The player will be led to the title page. This is where the project name, authors name, teacher name, course code and 
 * the date it was completed, will be shown. When the button “ Main Menu” is clicked, it will be led to the menu page.
 * This screen will only be shown once and you can not return to it, once you enter the main menu.
 */

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class TitlePage extends FormatFrame implements ActionListener{
	//instance variables
	private JButton jbtNxt; //Next button
	//Constants, so we can access them easily if a change is required
	private final Font TITLE_FONT = new Font("Sanserif", Font.BOLD, 40); //Setting title font
	private final Color TITLE_COLOR = new Color(00,149,237); //Setting Title Color
	private final Color BACKGROUND_COLOR = new Color(176,224,230); //Setting Background Color
	private final Color DETAIL_COLOR = new Color(25,25,112); // Setting Detail Color for color of buttons and divider color 
		
	//Constructor for TitleScreen
	public TitlePage() {
	super(); //calls ScreenFrame constructor creating a new ScreenFrame
			
	//set layout manager and background color of TitleScreen
	this.setLayout(new FlowLayout(FlowLayout.CENTER, 400, 30));
	this.getContentPane().setBackground(BACKGROUND_COLOR);
			
	//This line creates a header line before all of the details
	this.add(addDividerLabel());
			
	//Add Project name as a custom label (addCustomLabel was declared in FormatFrame class so we inherit it)
	this.add(addCustomLabel("Game: Connect 4!", TITLE_COLOR, TITLE_FONT));
	//Add Authors' names as a custom label
	this.add(addCustomLabel("Name: Makirsa Selvaranjan & Sadaf Hairat", TITLE_COLOR, TITLE_FONT));
	//Add Teacher name as a custom label
	this.add(addCustomLabel("Teacher: Ms. Xie", TITLE_COLOR, TITLE_FONT));
	//Add Date of Completion as a custom label
	this.add(addCustomLabel("Jan 25, 2022", TITLE_COLOR, TITLE_FONT));
	//Add Course Code as a custom label
	this.add(addCustomLabel("ICS3U1-01", TITLE_COLOR, TITLE_FONT));
			
	// "Next" Button to go to MenuScreen
	jbtNxt = addCustomButton("Next", 200, 75, DETAIL_COLOR, DETAIL_COLOR, new Font("Sanserif", Font.BOLD, 20));
	jbtNxt.addActionListener(this); //register action listener so actions can be performed when pressed
	this.add(jbtNxt); //add "Next" button to the frame
			
			//This line creates a footer line (below all the details
			this.add(addDividerLabel());	
			
			//Set Visible again because FlowLayout needs it to be set last
			this.setVisible(true);
		}//end of TitleScreen constructor

		/**
		 * This method returns a JLabel as a divider
		 * @return A midnight blue -colored divider label with the same font as the title font
		 */
		private JLabel addDividerLabel() {
			//create the custom divider using addCustomLabel method
			JLabel label = addCustomLabel("___________________________________", DETAIL_COLOR, TITLE_FONT);
			return label; //return divider label
		}//end of addDividerLabel method
		
		/**
		 * This method is implemented from the ActionListener interface. Each action performed leads to a different scenario.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			//clicking on jbtNxt will take user to MenuScreen
			if(e.getSource() == jbtNxt) {
				this.dispose(); //get rid of the current frame (TitlePage)
				new MenuPage(); //create new instance of MenuScreen with title
			}
		}//end of actionPerformed method
		
}

