package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.application.Application;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginController extends Application {

	String result = "";


	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	private CheckBox rememberme;

	@FXML
	private Button enter;

	@FXML
	private Text info;

	@FXML
	private ProgressIndicator progress;

	@FXML
	private void enter() {

		final LoginService servicelogin = new LoginService(username, password, enter, info);
		info.setText("");

		// mientras el servicelogin esté ejecutando, la propiedad visible del
		// progressindicator será true
		progress.visibleProperty().bind(servicelogin.runningProperty());
		servicelogin.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent workerStateEvent) {
				// El login devuelve un código para usarlo desde este thread
				result = servicelogin.getValue();
				switch (result) {

				case "200":
					System.out.println("sw200");
					saveCheckboxState();
					((Stage) username.getScene().getWindow()).close();
					Main.startApplication();
					break;
				case "401":
					
					info.setText("El usuario o la contraseña no son correctos.");
    				info.setVisible(true);
    				break;	
    				
				default: 
					info.setText("El servidor no responde.");
    				info.setVisible(true);
					break;
				
				}
				

			}

			private void saveCheckboxState (){
				File file = new File("src/application/checkbox.txt");
				FileWriter filewriter;
				try { filewriter = new FileWriter(file); 
				PrintWriter printwriter = new PrintWriter(filewriter);
				if(rememberme.isSelected()) {printwriter.println(true);}
				else {printwriter.println(false);}
				printwriter.close();
				} catch (IOException e) { e.printStackTrace();}
			}
		});

		servicelogin.restart(); // here you start your service

	}

	@Override
	public void start(Stage primaryStage) {

		Main.startLogin();

	}

}
