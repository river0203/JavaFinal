import java.util.ArrayList;

public class SimplePainterManager {

	private static SimplePainterManager instance;
	private SimplePainterView painterView;
	private SimplePainterModel painterModel;
	private ArrayList<SimplePainterModel> savedModelList;
	private DrawController paintController;
	
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
	
}
