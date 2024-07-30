import java.awt.Graphics;

public class Eraser extends DrawMode{

	@Override
	public void DrawFigure(SimplePainterModel data, Graphics page) {
		// TODO Auto-generated method stub
		page.drawLine(data.ptOne.x, data.ptOne.y, data.ptTwo.x, data.ptTwo.y);
	}

}
