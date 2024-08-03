import java.time.chrono.Era;
import java.util.ArrayList;

public class SimplePainterManager {

	private static SimplePainterManager instance;
	private SimplePainterView painterView = new SimplePainterView();
	private SimplePainterModel painterModel = new SimplePainterModel();
	private ArrayList<SimplePainterModel> savedModelList;
	private DrawController paintController;

	private DrawMode Dot = new Dot();
	private DrawMode Line = new Line();;
	private DrawMode Oval = new Oval();
	private DrawMode Rect = new Rect();
	private DrawMode Eraser = new Eraser();

	private SimplePainterManager()
	{
		
	}
	
	public static SimplePainterManager getInstance()
	{
		if (instance == null) {
            instance = new SimplePainterManager();
        }
		
		return instance;
	}
	
	public SimplePainterView getPainterView()
	{
		return painterView;
	}
	
	public SimplePainterModel getPainterModel()
	{
		return painterModel;
	}
	
	public DrawController getPainterController(SimplePainterView v)
	{
		paintController = new DrawController(v);
		return paintController;
	}
	
	public ArrayList<SimplePainterModel> getSavedList()
	{
		return savedModelList;
	}

	public DrawMode getDrawMode(int mode)
	{
		switch (mode) {
			case Constants.LINE:
				return Line;
			case Constants.ERASER:
				return Eraser;
			case Constants.RECT:
				return Rect;
			case Constants.OVAL:
				return Oval;
			case Constants.DOT:
				return Dot;
			default:
				throw new IllegalArgumentException("Invalid draw mode");
		}
	}
}
