package application;

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

	private String token;

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

		final LoginService servicelogin = new LoginService(username, password, enter, info, token);
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
		});

		servicelogin.restart(); // here you start your service

	}

	@Override
	public void start(Stage primaryStage) {

		Main.startLogin();

	}

}
