import javax.swing.JFrame;

public class SimplePainter{
	public static void main(String[] args)
	{
		SimplePainterManager manager = SimplePainterManager.getInstance();
		
		JFrame frame = new JFrame("SIMPLE PAINTER");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		SimplePainterView view = manager.getPainterView();
		frame.getContentPane().add(view);
		
		frame.pack();
		frame.setVisible(true);
	}
}