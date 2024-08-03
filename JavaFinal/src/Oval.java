import java.awt.*;

public class Oval extends DrawMode{

	@Override
	public void DrawFigure(SimplePainterModel data, Graphics page) {
		// TODO Auto-generated method stub
		Graphics2D g2 = (Graphics2D) page;
		g2.setStroke(new BasicStroke(data.nSize));
		page.setColor(data.selectedColor);

		page.drawOval(data.ptOne.x, data.ptOne.y, data.ptTwo.x - data.ptOne.x, data.ptTwo.y - data.ptOne.y);
	}

}
