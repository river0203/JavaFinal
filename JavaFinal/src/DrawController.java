import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;

public class DrawController extends JPanel {

	private SimplePainterModel nowData;
	private ArrayList<SimplePainterModel> savedList;
	private DrawListener drawL;
	private SimplePainterView view;
	private boolean bDrag;
	
	public DrawController(SimplePainterView v)
	{
		view = v;
		drawL = new DrawListener();
		
		setBackground(Color.white);
		
		addMouseListener(drawL);
		addMouseMotionListener(drawL);
		
		nowData = new SimplePainterModel();
		savedList = new ArrayList<SimplePainterModel>();
		
		nowData.nDrawMode = Constants.NONE;
		bDrag = false;
	}
	
	public void setDrawMode(int mode)
	{
		nowData.nDrawMode = mode;
		if(nowData.nDrawMode == Constants.LINE)
		{
			view.setTxtsize(1);
		}
		else
		{
			view.setTxtsize(10);
		}
	}
	
	public void setSelectedColor(Color color)
	{
		nowData.selectedColor = color;
	}
	
	public void paintComponent(Graphics page)
	{
		super.paintComponent(page);
		
		if(bDrag == true)
		{
			switch(nowData.nDrawMode) {
				case Constants.LINE:
					page.setColor(nowData.selectedColor);
					Graphics2D page2 = (Graphics2D) page;
					page2.setStroke(new BasicStroke(nowData.nSize));
					page.drawLine(nowData.ptOne.x, nowData.ptOne.y, nowData.ptTwo.x, nowData.ptTwo.y);
					break;
				case Constants.RECT:
					break;
				case Constants.OVAL:
					break;
				
			}
		}
		
		for(SimplePainterModel data:savedList)
		{
			switch(data.nDrawMode) {
				case Constants.DOT:
					page.setColor(data.selectedColor);
					page.fillOval(data.ptOne.x-data.nSize/2, data.ptOne.y-data.nSize/2, data.nSize, data.nSize);
					break;
				case Constants.LINE:
					page.setColor(data.selectedColor);
					Graphics2D page2 = (Graphics2D) page;
					page2.setStroke(new BasicStroke(data.nSize));
					page.drawLine(data.ptOne.x, data.ptOne.y, data.ptTwo.x, data.ptTwo.y);
					break;
				case Constants.RECT:
					if(view.getChkFill())
					{
						page.setColor(data.selectedColor);
						page.fillRect(data.ptOne.x-data.nSize/2, data.ptOne.y-data.nSize/2, data.nSize, data.nSize);
					}
					else
					{
						page.setColor(data.selectedColor);
						page.drawRect(data.ptOne.x-data.nSize/2, data.ptOne.y-data.nSize/2, data.nSize, data.nSize);
					}
					break;
				case Constants.OVAL:
					if(view.getChkFill())
					{
						page.setColor(data.selectedColor);
						page.fillOval(data.ptOne.x-data.nSize/2, data.ptOne.y-data.nSize/2, data.nSize, data.nSize);
					}
					else
					{
						page.setColor(data.selectedColor);
						page.drawOval(data.ptOne.x-data.nSize/2, data.ptOne.y-data.nSize/2, data.nSize, data.nSize);
					}
					break;
				
			}
		}
	}

	public void clearPaintComponent()
	{
		savedList.clear();
		repaint();
	}
	
	private class DrawListener implements MouseListener, MouseMotionListener
	{
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(nowData.nDrawMode == Constants.DOT)
			{
				nowData.ptOne = e.getPoint();
				nowData.nSize = view.getTextSize();
				savedList.add(new SimplePainterModel(nowData));
				repaint();
			}
			else if(nowData.nDrawMode == Constants.RECT)
			{
				nowData.ptOne = e.getPoint();
				nowData.nSize = view.getTextSize();
				savedList.add(new SimplePainterModel(nowData));
				repaint();
			}
			else if(nowData.nDrawMode == Constants.OVAL)
			{
				nowData.ptOne = e.getPoint();
				nowData.nSize = view.getTextSize();
				savedList.add(new SimplePainterModel(nowData));
				repaint();
			}
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			if(nowData.nDrawMode == Constants.LINE)
			{
				bDrag = true;
				
				nowData.ptOne = e.getPoint();
				nowData.nSize = view.getTextSize();		}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			if(nowData.nDrawMode == Constants.LINE)
			{
				bDrag = false;
				
				nowData.ptTwo = e.getPoint();
				savedList.add(new SimplePainterModel(nowData));
				repaint();
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			if(nowData.nDrawMode == Constants.LINE)
			{
				nowData.ptTwo = e.getPoint();
				repaint();
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
