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
	
	private SimplePainterManager painterManager = SimplePainterManager.getInstance();
	
	public DrawController(SimplePainterView v)
	{
		view = v;
		drawL = new DrawListener();
		
		setBackground(Color.white);
		
		addMouseListener(drawL);
		addMouseMotionListener(drawL);
		
		nowData = painterManager.getPainterModel();
		//
		savedList = new ArrayList<SimplePainterModel>();
		
		nowData.nDrawMode = Constants.NONE;
		bDrag = false;
	}
	
	public int getDrawMode() 
	{
		return nowData.nDrawMode;
	}
	public void setDrawMode(int mode)
	{
		nowData.nDrawMode = mode;
		if(nowData.nDrawMode == Constants.DOT || nowData.nDrawMode == Constants.ERASER)
		{
			view.setTxtsize(10);
		}
		else
		{
			view.setTxtsize(1);
		}
	}
	public Point getPresentPosition() 
	{
		return nowData.ptOne;
	}
	public Color getSelectedColor() 
	{
		return nowData.selectedColor;
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
				case Constants.ERASER:
					page.setColor(Color.white);
					Graphics2D page3 = (Graphics2D) page;
					page3.setStroke(new BasicStroke(nowData.nSize));
					page.drawLine(nowData.ptOne.x, nowData.ptOne.y, nowData.ptTwo.x, nowData.ptTwo.y);
					break;
				case Constants.RECT:
					Graphics2D page4 = (Graphics2D) page;
					page4.setStroke(new BasicStroke(nowData.nSize));
					if(view.getChkFill())
					{
						nowData.bFill = view.getChkFill();
						page.setColor(nowData.selectedColor);
						page.fillRect(nowData.ptOne.x, nowData.ptOne.y, nowData.ptTwo.x - nowData.ptOne.x, nowData.ptTwo.y - nowData.ptOne.y);
					}
					else
					{
						page.setColor(nowData.selectedColor);
						page.drawRect(nowData.ptOne.x, nowData.ptOne.y, nowData.ptTwo.x - nowData.ptOne.x, nowData.ptTwo.y - nowData.ptOne.y);
					}
					break;
				case Constants.OVAL:
					Graphics2D page5 = (Graphics2D) page;
					page5.setStroke(new BasicStroke(nowData.nSize));
					if(view.getChkFill())
					{
						nowData.bFill = view.getChkFill();
						page.setColor(nowData.selectedColor);
						page.fillOval(nowData.ptOne.x, nowData.ptOne.y, nowData.ptTwo.x - nowData.ptOne.x, nowData.ptTwo.y - nowData.ptOne.y);;
					}
					else
					{
						page.setColor(nowData.selectedColor);
						page.drawOval(nowData.ptOne.x, nowData.ptOne.y, nowData.ptTwo.x - nowData.ptOne.x, nowData.ptTwo.y - nowData.ptOne.y);
					}
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
				case Constants.ERASER:
					page.setColor(Color.white);
					Graphics2D page3 = (Graphics2D) page;
					page3.setStroke(new BasicStroke(data.nSize));
					page.drawLine(data.ptOne.x, data.ptOne.y, data.ptTwo.x, data.ptTwo.y);
					break;
				case Constants.RECT:
					Graphics2D page4 = (Graphics2D) page;
					page4.setStroke(new BasicStroke(data.nSize));
					if(data.bFill == true)
					{
						nowData.bFill = view.getChkFill();
						page.setColor(data.selectedColor);
						page.fillRect(data.ptOne.x, data.ptOne.y, data.ptTwo.x - data.ptOne.x, data.ptTwo.y - data.ptOne.y);
					}
					else
					{
						page.setColor(data.selectedColor);
						page.drawRect(data.ptOne.x, data.ptOne.y, data.ptTwo.x - data.ptOne.x, data.ptTwo.y - data.ptOne.y);
					}
					break;
				case Constants.OVAL:
					Graphics2D page5 = (Graphics2D) page;
					page5.setStroke(new BasicStroke(data.nSize));
					if(data.bFill == true)
					{
						nowData.bFill = view.getChkFill();
						page.setColor(data.selectedColor);
						page.fillOval(data.ptOne.x, data.ptOne.y, data.ptTwo.x - data.ptOne.x, data.ptTwo.y - data.ptOne.y);
					}
					else
					{
						page.setColor(data.selectedColor);
						page.drawOval(data.ptOne.x, data.ptOne.y, data.ptTwo.x - data.ptOne.x, data.ptTwo.y - data.ptOne.y);
					}
					break;
			}
		}
	}
	
	public void undoPaintComponent() 
	{
		int listLen = savedList.size();
		if(listLen == 0)
		{
			System.out.print("Emepty");
			repaint();
		}
		else
		{
			savedList.remove(listLen-1);
			repaint();
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
			view.printMessage();
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			if(nowData.nDrawMode == Constants.LINE)
			{
				bDrag = true;
				
				nowData.ptOne = e.getPoint();
				nowData.nSize = view.getTextSize();		
			}
			else if(nowData.nDrawMode == Constants.RECT)
			{
				bDrag = true;
				
				nowData.ptOne = e.getPoint();
				nowData.nSize = view.getTextSize();
			}
			else if(nowData.nDrawMode == Constants.OVAL)
			{
				bDrag = true;
				
				nowData.ptOne = e.getPoint();
				nowData.nSize = view.getTextSize();
			}
			else if(nowData.nDrawMode == Constants.ERASER)
			{
				bDrag = true;
				
				nowData.ptOne = e.getPoint();
				nowData.nSize = view.getTextSize();
			}
			
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
			else if(nowData.nDrawMode == Constants.RECT)
			{
				bDrag = false;
				
				nowData.ptTwo = e.getPoint();
				savedList.add(new SimplePainterModel(nowData));
				repaint();
			}
			else if(nowData.nDrawMode == Constants.OVAL)
			{
				bDrag = false;
				
				nowData.ptTwo = e.getPoint();
				savedList.add(new SimplePainterModel(nowData));
				repaint();
			}
			else if(nowData.nDrawMode == Constants.ERASER)
			{
				bDrag = false;
				
				nowData.ptTwo = e.getPoint();
				savedList.add(new SimplePainterModel(nowData));
				repaint();
			}
			
			view.printMessage();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			if(nowData.nDrawMode == Constants.LINE)
			{
				nowData.ptTwo = e.getPoint();
				repaint();
			}
			else if(nowData.nDrawMode == Constants.RECT)
			{
				nowData.ptTwo = e.getPoint();
				repaint();
			}
			else if(nowData.nDrawMode == Constants.OVAL)
			{
				nowData.ptTwo = e.getPoint();
				repaint();
			}
			else if(nowData.nDrawMode == Constants.ERASER)
			{
				nowData.ptTwo = e.getPoint();
				repaint();
			}
			view.printMessage();
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
