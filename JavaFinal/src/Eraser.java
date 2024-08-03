import java.awt.*;
import java.time.chrono.Era;

public class Eraser extends DrawMode{

	@Override
	public void DrawFigure(SimplePainterModel data, Graphics page) {
		// TODO Auto-generated method stub
		page.setColor(Color.white);
		Graphics2D g2 = (Graphics2D) page;
		g2.setStroke(new BasicStroke(data.nSize));
		page.drawLine(data.ptOne.x, data.ptOne.y, data.ptTwo.x, data.ptTwo.y);
	}

}
