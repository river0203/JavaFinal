import java.time.chrono.Era;
import java.util.ArrayList;

public class SimplePainterManager {

	private static SimplePainterManager instance;
	private SimplePainterView painterView;
	private SimplePainterModel painterModel;
	private ArrayList<SimplePainterModel> savedModelList;
	private DrawController paintController;

	private DrawMode Dot = new Dot();
	private DrawMode Line = new Line();;
	private DrawMode Oval = new Oval();
	private DrawMode Rect = new Rect();
	private DrawMode Eraser = new Eraser();
	private DrawMode FillOval = new FillOval();
	private DrawMode FillRect = new FillRect();

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
		painterView = new SimplePainterView();
		return painterView;
	}
	
	public SimplePainterModel getPainterModel()
	{
		painterModel = new SimplePainterModel();
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

	public DrawMode getDrawMode(int mode, boolean checkFill)
	{
		switch (mode) {
			case Constants.LINE:
				return Line;
			case Constants.ERASER:
				return Eraser;
			case Constants.RECT:
				if(checkFill)
				{
					return FillRect;
				}
				return Rect;
			case Constants.OVAL:
				if(checkFill)
				{
					return FillOval;
				}
				return Oval;
			case Constants.DOT:
				return Dot;
			default:
				System.out.print("no Draw Mode");
		}
        return null;
    }
}
