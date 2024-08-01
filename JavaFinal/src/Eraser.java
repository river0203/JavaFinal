import java.awt.Graphics;
import java.time.chrono.Era;

public class Eraser extends DrawMode{

	@Override
	public void DrawFigure(SimplePainterModel data, Graphics page) {
		// TODO Auto-generated method stub
		if(data.nDrawMode == Constants.ERASER)
		{
			page.drawLine(data.ptOne.x, data.ptOne.y, data.ptTwo.x, data.ptTwo.y);
		}
	}

}
