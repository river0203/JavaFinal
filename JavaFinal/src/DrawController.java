import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;

// view에서 버튼 값을 넘겨줘서 처리하는 것이 아닌
/// 버튼 누른 곳에서 바로 처리하는 것은 어떨까
/// switch를 없애려면 어떻게 해야하는가

public class DrawController extends JPanel {

	private SimplePainterModel nowData;
	private ArrayList<SimplePainterModel> savedList;
	private DrawListener drawL;
	private SimplePainterView view;
	private DrawMode nDrawMode;
	
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
		//can't change singletone
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
					nDrawMode = painterManager.getLine();
					nDrawMode.DrawFigure(nowData, page);
					break;
				case Constants.ERASER:
					page.setColor(Color.white);
					Graphics2D page3 = (Graphics2D) page;
					page3.setStroke(new BasicStroke(nowData.nSize));
					nDrawMode = painterManager.getEraser();
					nDrawMode.DrawFigure(nowData, page);
					break;
				case Constants.RECT:
					Graphics2D page4 = (Graphics2D) page;
					page4.setStroke(new BasicStroke(nowData.nSize));
					nDrawMode = painterManager.getRect();
					nowData.bFill = view.getChkFill();
					nDrawMode.DrawFigure(nowData, page4);
					break;
				case Constants.OVAL:
					Graphics2D page5 = (Graphics2D) page;
					page5.setStroke(new BasicStroke(nowData.nSize));
					nDrawMode = painterManager.getOval();
					nowData.bFill = view.getChkFill();
					nDrawMode.DrawFigure(nowData, page5);
					break;
			}
		}
		
		for(SimplePainterModel data:savedList)
		{
			switch(data.nDrawMode) {
				case Constants.DOT:
					nDrawMode = painterManager.getDot();
					nDrawMode.DrawFigure(data, page);
					break;
				case Constants.LINE:
					nDrawMode = painterManager.getLine();
					nDrawMode.DrawFigure(data, page);
					break;
				case Constants.ERASER:
					page.setColor(Color.white);
					Graphics2D page3 = (Graphics2D) page;
					page3.setStroke(new BasicStroke(data.nSize));
					nDrawMode = painterManager.getEraser();
					nDrawMode.DrawFigure(data, page);
					break;
				case Constants.RECT:
					Graphics2D page4 = (Graphics2D) page;
					page4.setStroke(new BasicStroke(data.nSize));
					nDrawMode = painterManager.getRect();
					nowData.bFill = view.getChkFill();
					nDrawMode.DrawFigure(data, page);
					break;
				case Constants.OVAL:
					Graphics2D page5 = (Graphics2D) page;
					page5.setStroke(new BasicStroke(data.nSize));
					nDrawMode = painterManager.getOval();
					nowData.bFill = view.getChkFill();
					nDrawMode.DrawFigure(data, page);
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
