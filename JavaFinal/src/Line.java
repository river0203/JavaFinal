import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Line extends DrawMode {

	@Override
	public void DrawFigure(SimplePainterModel data, Graphics page) {
		// TODO Auto-generated method stub
		page.setColor(data.selectedColor);
		Graphics2D page2 = (Graphics2D) page;
		page2.setStroke(new BasicStroke(data.nSize));
		page.drawLine(data.ptOne.x, data.ptOne.y, data.ptTwo.x, data.ptTwo.y);
	}

}
