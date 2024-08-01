import java.time.chrono.Era;
import java.util.ArrayList;

public class SimplePainterManager {

	private static SimplePainterManager instance;
	private SimplePainterView painterView;
	private SimplePainterModel painterModel;
	private ArrayList<SimplePainterModel> savedModelList;
	private DrawController paintController;

	private DrawMode Dot;
	private DrawMode Line;
	private DrawMode Oval;
	private DrawMode Rect;
	private DrawMode Eraser;

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

	public DrawMode getDot()
	{
		Dot = new Dot();
		return Dot;
	}

	public DrawMode getLine()
	{
		Line = new Line();
		return Line;
	}

	public DrawMode getOval()
	{
		Oval = new Oval();
		return Oval;
	}

	public DrawMode getRect()
	{
		Rect = new Rect();
		return Rect;
	}

	public DrawMode getEraser()
	{
		Eraser = new Eraser();
		return Eraser;
	}
}
