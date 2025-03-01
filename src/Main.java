import asse.GuiClass;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * This main class will set the scene and launch the Application to the user
 * It uses the GUI Class for the components
 */
public class Main extends Application {
   
	// declaring the GuiClass
	private GuiClass guiClass;
	
	
	//launching the application
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage StageName) throws Exception { 
		
		
		// Create a GuiClass instance 
		guiClass = new GuiClass(); 
		
		//Setting the scene
		Scene scene = new Scene(guiClass);
		
		
		//setting the Stage
		StageName.setTitle("HealthCare Records Management System");
		StageName.setWidth(1300);
		StageName.setHeight(610);
		StageName.setScene(scene); 
		StageName.show();
		
		
	}

	
	
	
}
