import java.awt.Graphics;

public class Dot extends DrawMode {

	@Override
	public void DrawFigure(SimplePainterModel data, Graphics page) {
		// TODO Auto-generated method stub
		if(data.nDrawMode == Constants.DOT)
		{
			page.setColor(data.selectedColor);
			page.fillOval(data.ptOne.x-data.nSize/2, data.ptOne.y-data.nSize/2, data.nSize, data.nSize);
		}
	}

}
