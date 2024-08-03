import java.awt.*;

public class Dot extends DrawMode {

	@Override
	public void DrawFigure(SimplePainterModel data, Graphics page) {
		// TODO Auto-generated method stub
		page.setColor(data.selectedColor);
		Graphics2D g2 = (Graphics2D) page;
		g2.setStroke(new BasicStroke(data.nSize));
		page.drawLine(data.ptOne.x, data.ptOne.y, data.ptOne.x, data.ptOne.y);
	}

}
