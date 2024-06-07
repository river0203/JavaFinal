import java.awt.*;
import javax.swing.*;

public class SimplePainterView extends JPanel {
	
	private DrawController drawController;
	
	private JPanel menuPanel, optionPanel, messagePanel;
	private JButton[] btnMenuArray;
	
	public SimplePainterView()
	{
		setBackground(Color.white);
		setPreferredSize(new Dimension(820, 830));
		setLayout(null);
		
		drawController = new DrawController();
		drawController.setBounds(10, 10, 800, 600);
		drawController.setBorder(BorderFactory.createTitledBorder("DRAWING"));
		add(drawController);
		
		menuPanel = new JPanel();
		menuPanel.setBounds(10, 610, 300, 200);
		menuPanel.setBackground(Color.white);
		menuPanel.setBorder(BorderFactory.createTitledBorder("MENU"));
		menuPanel.setLayout(new GridLayout(2, 3));
		add(menuPanel);
		
		
		optionPanel = new JPanel();
		optionPanel.setBounds(310, 610, 200, 200);
		optionPanel.setBackground(Color.white);
		optionPanel.setBorder(BorderFactory.createTitledBorder("OPTION"));
		add(optionPanel);
		
		messagePanel = new JPanel();
		messagePanel.setBounds(510, 610, 300, 200);
		messagePanel.setBackground(Color.white);
		messagePanel.setBorder(BorderFactory.createTitledBorder("MESSAGE"));
		add(messagePanel);
		
		btnMenuArray = new JButton[6];
		for(int i = 0; i<6; i++)
		{
			btnMenuArray[i] = new JButton(Constants.MENU[i]);
			btnMenuArray[i].setBackground(Constants.HOVERING[0]);
			btnMenuArray[i].setForeground(Constants.HOVERING[1]);
			menuPanel.add(btnMenuArray[i]);
		}
	}
	
}
