import java.awt.*;

public class Oval extends DrawMode{

	@Override
	public void DrawFigure(SimplePainterModel data, Graphics page) {
		// TODO Auto-generated method stub
		if(data.nDrawMode == Constants.OVAL)
		{
			page.setColor(data.selectedColor);

			if(data.bFill == true)
			{
				page.fillOval(data.ptOne.x, data.ptOne.y, data.ptTwo.x - data.ptOne.x, data.ptTwo.y - data.ptOne.y);
			}
			else
			{
				page.drawOval(data.ptOne.x, data.ptOne.y, data.ptTwo.x - data.ptOne.x, data.ptTwo.y - data.ptOne.y);
			}
		}
	}

}
