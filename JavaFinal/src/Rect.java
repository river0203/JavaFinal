import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Rect extends DrawMode{

	@Override
	public void DrawFigure(SimplePainterModel data, Graphics page) {
		// TODO Auto-generated method stub
		if(data.nDrawMode == Constants.RECT)
		{
			page.setColor(data.selectedColor);

			if(data.bFill == true)
			{
				page.fillRect(data.ptOne.x, data.ptOne.y, data.ptTwo.x - data.ptOne.x, data.ptTwo.y - data.ptOne.y);
			}
			else
			{
				page.drawRect(data.ptOne.x, data.ptOne.y, data.ptTwo.x - data.ptOne.x, data.ptTwo.y - data.ptOne.y);
			}
		}
	}
}
	
