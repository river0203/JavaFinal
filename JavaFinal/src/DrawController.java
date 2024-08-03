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
		// 나중에 생성한 걸로 그림 그릴 수 있음
		// 드로우 모드를 판별하여 생성?
		if(bDrag) {
			nDrawMode.DrawFigure(nowData, page);
		}

		for(SimplePainterModel data:savedList)
		{
			DrawMode drawMode = painterManager.getDrawMode(data.nDrawMode);
			drawMode.DrawFigure(data, page);
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
			bDrag = true;
			if(nowData.nDrawMode == Constants.LINE)
			{
				nowData.ptOne = e.getPoint();
				nowData.nSize = view.getTextSize();		
			}
			else if(nowData.nDrawMode == Constants.RECT)
			{
				nowData.ptOne = e.getPoint();
				nowData.nSize = view.getTextSize();
			}
			else if(nowData.nDrawMode == Constants.OVAL)
			{
				nowData.ptOne = e.getPoint();
				nowData.nSize = view.getTextSize();
			}
			else if(nowData.nDrawMode == Constants.ERASER)
			{
				nowData.ptOne = e.getPoint();
				nowData.nSize = view.getTextSize();
			}
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			bDrag = false;
			if(nowData.nDrawMode == Constants.LINE)
			{
				nowData.ptTwo = e.getPoint();
				savedList.add(new SimplePainterModel(nowData));
				repaint();
			}
			else if(nowData.nDrawMode == Constants.RECT)
			{
				nowData.ptTwo = e.getPoint();
				savedList.add(new SimplePainterModel(nowData));
				repaint();
			}
			else if(nowData.nDrawMode == Constants.OVAL)
			{
				nowData.ptTwo = e.getPoint();
				savedList.add(new SimplePainterModel(nowData));
				repaint();
			}
			else if(nowData.nDrawMode == Constants.ERASER)
			{
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
