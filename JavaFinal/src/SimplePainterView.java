import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SimplePainterView extends JPanel {
	
	private DrawController drawController;
	
	private JPanel 		menuPanel, optionPanel, messagePanel;
	private JButton[] 	btnMenuArray;
	private JTextField 	txtSize;
	private JButton 	btnColorChooser;
	private JCheckBox 	chkFill;
	
	public SimplePainterView()
	{
		setBackground(Color.white);
		setPreferredSize(new Dimension(820, 830));
		setLayout(null);
		
		drawController = new DrawController(this);
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
		optionPanel.setLayout(new GridLayout(3, 1));
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
			btnMenuArray[i].addMouseListener(new HoveringListener());
			btnMenuArray[i].addActionListener(new MenuListener());
			menuPanel.add(btnMenuArray[i]);
		}
		
		btnColorChooser = new JButton("COLOR CHOOSER");
		btnColorChooser.setBackground(Constants.HOVERING[0]);
		btnColorChooser.setForeground(Constants.HOVERING[1]);
		btnColorChooser.addMouseListener(new HoveringListener());
		btnColorChooser.addActionListener(new MenuListener());
		optionPanel.add(btnColorChooser);
		
		txtSize = new JTextField(10);
		txtSize.setFont(new Font("Verdana", Font.BOLD, 16));
		txtSize.setVisible(false);
		optionPanel.add(txtSize);
		
		chkFill = new JCheckBox("FILL");
		chkFill.setBackground(Color.white);
		chkFill.setFont(new Font("Verdana", Font.BOLD, 16));
		chkFill.setVisible(false);
		optionPanel.add(chkFill);
	}
	
	public void setTxtsize(int size) {txtSize.setText(Integer.toString(size));}
	public int getTextSize() {return Integer.parseInt(txtSize.getText());}
	
	public boolean getChkFill() { return chkFill.isSelected();} 
	private class HoveringListener implements MouseListener
	{
		@Override
		public void mouseEntered(MouseEvent arg0) {
			JButton btnMenu = (JButton)arg0.getSource();
			btnMenu.setBackground(Constants.HOVERING[2]);
			btnMenu.setForeground(Constants.HOVERING[3]);
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			JButton btnMenu = (JButton)arg0.getSource();
			btnMenu.setBackground(Constants.HOVERING[0]);
			btnMenu.setForeground(Constants.HOVERING[1]);
		}

		@Override
		public void mouseClicked(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}
	}
	private class MenuListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object obj = e.getSource();
			
			txtSize.setVisible(true);
			chkFill.setVisible(false);
			
			if(obj == btnColorChooser)
			{
				Color c = JColorChooser.showDialog(btnColorChooser, "COLOR CHOOSER", Color.black);
				drawController.setSelectedColor(c);
			}
			
			for(int i = 0; i < 6; i++)
			{
				if(obj == btnMenuArray[i])
				{
					drawController.setDrawMode(i);
					if(i == Constants.RECT || i == Constants.OVAL)
					{
						chkFill.setVisible(true);
					}
					break;
				}
			}
		}
		
	}
}
