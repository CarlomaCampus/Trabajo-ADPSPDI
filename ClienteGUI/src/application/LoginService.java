package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import modelos.Usuarios;




public class LoginService extends Service<String> {
	
	TextField username;
	TextField password;
	Button enter;
	Text info;
	String token;
	
	public LoginService(TextField username, TextField password, Button enter, Text info, String token) {
		super();
		this.username = username;
		this.password = password;
		this.enter = enter;
		this.info = info;
		this.token = token;
	}
	

	

    @Override
    protected Task<String> createTask() {
        return new Task<String>() {
            @Override
            protected String call() throws Exception {
            	HttpURLConnection con;
        		try {
        			int responsecode;
        			String infotext;
        			
        			// CREA LA PETICI�N Y LA ENV�A
        			con = (HttpURLConnection) new URL("http://localhost:8080/ADPSPDI/Login").openConnection();
        			con.setDoOutput(true);
        			con.setRequestMethod("POST");
        			con.setRequestProperty("Content-Type", "application/json");
        			OutputStream os = con.getOutputStream();
        			os.write(new Gson().toJson(new Usuarios(username.getText(), password.getText())).getBytes());
        			os.flush();
        			
        			// COMPROBAMOS LA RESPUESTA
        			responsecode = con.getResponseCode();
        			switch (responsecode) {
        			case 200:
        				
        				// recuperamos el json de vuelta, lo convertimos en objeto usuario (gson) y obtenemos el token, guard�ndolo en un fichero			
        				TokenUtils.guardarToken(
        						new Gson()
        						.fromJson((IOUtils.toString(con.getInputStream(), StandardCharsets.UTF_8)), Usuarios.class)
        						.getToken()
        						);
        				return "200";   
        				//break;

        			case 401:
        				
        				
        				return "401"; 
        				//break;
        				
        			default:
        			
        			infotext = ("El servidor ha devuelto el c�digo #" + responsecode);
        			
        			//si no sabemos cu�l es el c�digo guardamos toda la informaci�n posible en una cadena
        			try{con.getErrorStream(); infotext += " con el siguiente error: "+ IOUtils.toString(con.getErrorStream(), StandardCharsets.UTF_8);} catch (Exception ne){ infotext += " sin texto de Error.";}
        			try{con.getInputStream(); infotext += " con la siguiente entrada: "+ IOUtils.toString(con.getInputStream(), StandardCharsets.UTF_8);} catch (Exception ne){ infotext += " sin devolver ninguna entidad.";}
        			
        			info.setText(infotext);
        			info.setVisible(true); 
        			break;
        			}


        			con.disconnect();
        			
        		} catch (Exception e) {
        			info.setText("El programa ha encontrado el siguiente error: " + e.toString());
        		}
				return "500";
            	
        };
    };

	
}
    
    
    
}