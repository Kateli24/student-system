
import dao.StudentCollectionsDAO;
import dao.StudentDAO;
import gui.MainMenuFrame;
import java.awt.EventQueue;

public class Main {

	public static void main(String[] args) {

		final StudentDAO dao = new StudentCollectionsDAO();

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				new MainMenuFrame(dao).setVisible(true);
			}

		});
	}
}
