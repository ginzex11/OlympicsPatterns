package mvc_Olympics;



import javafx.application.Application;
import javafx.stage.Stage;
import mvc_Olympice_view.OlympiceJavaFx;
import mvc_Olympics_model.ManagementSystem;
import mvs_Olympice_controller.OlympiceController;

public class OlympiceMain extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ManagementSystem theModel = new ManagementSystem();
		OlympiceJavaFx theView = new OlympiceJavaFx(primaryStage);
		new OlympiceController(theView, theModel);
	}
}
