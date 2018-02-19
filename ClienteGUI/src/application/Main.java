package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{
	
	public static void main(String[] args) {
	
		launch(args);
	}
	
	
	public static void startLogin() {
		if(isCheckboxPressed() && TokenUtils.elTokenExiste()
    			&& !TokenUtils.elTokenHaExpirado(TokenUtils.obtenerTokenGuardado())
    			) {

    		startApplication();
    		
    	} else {
        Stage primaryStage = new Stage();
        LoginController logincontroller = new LoginController();
        Parent root;
		try {
			
		root = FXMLLoader.load(logincontroller.getClass().getResource("Login.fxml")); 
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("icono.png"));
        primaryStage.show();
        
		} catch (IOException e) { e.printStackTrace(); }
    	}
    }
	
	public static void startApplication() {
        Stage primaryStage = new Stage();
        ApplicationController applicationcontroller = new ApplicationController();
        Parent root;
		try {
			
		root = FXMLLoader.load(applicationcontroller.getClass().getResource("Application.fxml")); 
        primaryStage.setTitle("Campus DB");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("icono.png"));
        primaryStage.show();
        
		} catch (IOException e) { e.printStackTrace(); }
    }


	@Override
	public void start(Stage primaryStage) throws Exception {

		startLogin();
		
	}
	

	private static boolean isCheckboxPressed(){

		File file = new File("src/application/checkbox.txt");
		BufferedReader bufferedreader;

		try {
			bufferedreader = new BufferedReader(new FileReader(file));

			String line = bufferedreader.readLine();
			bufferedreader.close();
			if (line.equals("true")) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	};
	
	
	
	
}
