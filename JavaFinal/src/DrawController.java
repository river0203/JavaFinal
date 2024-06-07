import java.awt.Color;
import java.util.ArrayList;
import java.awt.Graphics;
import javax.swing.JPanel;

public class DrawController extends JPanel {

	private SimplePainterModel nowData;
	private ArrayList<SimplePainterModel> savedList;
	
	public DrawController()
	{
		setBackground(Color.white);
		
		nowData = new SimplePainterModel();
		savedList = new ArrayList<SimplePainterModel>();
	}
	
	public void paintComponent(Graphics page)
	{
		super.paintComponent(page);
	}
	
}
