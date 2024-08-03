import java.awt.*;

public class FillRect extends DrawMode{
    @Override
    public void DrawFigure(SimplePainterModel data, Graphics nPage) {
        Graphics2D g2 = (Graphics2D) nPage;
        g2.setStroke(new BasicStroke(data.nSize));
        nPage.setColor(data.selectedColor);

        nPage.fillRect(data.ptOne.x, data.ptOne.y, data.ptTwo.x - data.ptOne.x, data.ptTwo.y - data.ptOne.y);
    }
}
