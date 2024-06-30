
public class SimplePainterManager {

	private static SimplePainterManager instance;
	
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
	
}
