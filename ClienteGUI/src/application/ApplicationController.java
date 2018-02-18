package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import modelos.Alumnos;
import modelos.Ciclos;
import modelos.Cursos;
import modelos.Matriculas;
import modelos.MatriculasId;
import modelos.Modulos;

@SuppressWarnings("rawtypes")
public class ApplicationController {

	String json = ""; // multiusos
	String token = "";
	String infotext;

	ObservableList<Alumnos> filasAlumnos = FXCollections.observableArrayList();
	ObservableList<Ciclos> filasCiclos = FXCollections.observableArrayList();
	ObservableList<Cursos> filasCursos = FXCollections.observableArrayList();
	ObservableList<Matriculas> filasMatriculas = FXCollections.observableArrayList();
	ObservableList<Modulos> filasModulos = FXCollections.observableArrayList();
	Type listTypeAlumnos = new TypeToken<ArrayList<Alumnos>>() {}.getType();
	Type listTypeCiclos = new TypeToken<ArrayList<Ciclos>>() {}.getType();
	Type listTypeCursos = new TypeToken<ArrayList<Cursos>>() {}.getType();
	Type listTypeMatriculas = new TypeToken<ArrayList<Matriculas>>() {}.getType();
	Type listTypeModulos = new TypeToken<ArrayList<Modulos>>() {}.getType();

	Image imagetick = new Image(new File("src/tick.png").toURI().toString());
	Image imagecross = new Image(new File("src/cross.png").toURI().toString());

	@FXML private TextField nombrealumnoTab1; @FXML private Text nombrealumnotextTab1; @FXML private TextField apellidosalumnoTab1;
	@FXML private Text apellidosalumnotextTab1;
	@FXML private TextField telefonoalumnoTab1;
	@FXML private Text telefonoalumnotextTab1;
	@FXML private TextField correoalumnoTab1;
	@FXML private Text correoalumnotextTab1;
	@FXML private TextField dnialumnoTab1;
	@FXML private Text dnialumnotextTab1;
	@FXML private TextField nombrecicloTab1;
	@FXML private Text nombreciclotextTab1;
	@FXML private TextField siglascicloTab1;
	@FXML private Text siglasciclotextTab1;
	@FXML private TextField nombrecursoTab1;
	@FXML private Text nombrecursotextTab1;
	@FXML private TextField idciclocursoTab1;
	@FXML private Text idciclocursotextTab1;
	@FXML private TextField idalumnomatriculaTab1;
	@FXML private Text idalumnomatriculatextTab1;
	@FXML private TextField idmodulomatriculaTab1;
	@FXML private Text idmodulomatriculatextTab1;
	@FXML private TextField nombremoduloTab1;
	@FXML private Text nombremodulotextTab1;
	@FXML private TextField siglasmoduloTab1;
	@FXML private Text siglasmodulotextTab1;
	@FXML private TextField idcursomoduloTab1;
	@FXML private Text idcursomodulotextTab1;
	@FXML private Button añadiralumno;
	@FXML private Button añadirciclo;
	@FXML private Button añadircurso;
	@FXML private Button añadirmatricula;
	@FXML private Button añadirmodulo;
	@FXML private TextField nombrealumnoTab2;
	@FXML private Text nombrealumnotextTab2;
	@FXML private TextField apellidosalumnoTab2;
	@FXML private Text apellidosalumnotextTab2;
	@FXML private TextField telefonoalumnoTab2;
	@FXML private Text telefonoalumnotextTab2;
	@FXML private TextField correoalumnoTab2;
	@FXML private Text correoalumnotextTab2;
	@FXML private TextField dnialumnoTab2;
	@FXML private Text dnialumnotextTab2;
	@FXML private TextField nombrecicloTab2;
	@FXML private Text nombreciclotextTab2;
	@FXML private TextField siglascicloTab2;
	@FXML private Text siglasciclotextTab2;
	@FXML private TextField nombrecursoTab2;
	@FXML private Text nombrecursotextTab2;
	@FXML private TextField idciclocursoTab2;
	@FXML private Text idciclocursotextTab2;
	@FXML private TextField idalumnomatriculaTab2;
	@FXML private Text idalumnomatriculatextTab2;
	@FXML private TextField idmodulomatriculaTab2;
	@FXML private Text idmodulomatriculatextTab2;
	@FXML private TextField nombremoduloTab2;
	@FXML private Text nombremodulotextTab2;
	@FXML private TextField siglasmoduloTab2;
	@FXML private Text siglasmodulotextTab2;
	@FXML private TextField idcursomoduloTab2;
	@FXML private Text idcursomodulotextTab2;
	@FXML private Button editaralumno;
	@FXML private Button editarciclo;
	@FXML private Button editarcurso;
	@FXML private Button editarmatricula;
	@FXML private Button editarmodulo;
	@FXML private TableView<Alumnos> tablealumnosTab2;
	@FXML private TableColumn colidalumnoTab2;
	@FXML private TableColumn colnombrealumnoTab2;
	@FXML private TableColumn colapellidosalumnoTab2;
	@FXML private TableColumn colcorreoalumnoTab2;
	@FXML private TableColumn coltelefonoalumnoTab2;
	@FXML private TableColumn coldnialumnoTab2;
	@FXML private TableView<Ciclos> tableciclosTab2;
	@FXML private TableColumn colidcicloTab2;
	@FXML private TableColumn colnombrecicloTab2;
	@FXML private TableColumn colsiglascicloTab2;
	@FXML private TableColumn colfechacicloTab2;
	@FXML private TableView<Cursos> tablecursosTab2;
	@FXML private TableColumn colidcursoTab2;
	@FXML private TableColumn colnombrecursoTab2;
	@FXML private TableColumn colidciclocursoTab2;
	@FXML private TableColumn colfechacursoTab2;
	@FXML private TableView<Matriculas> tablematriculasTab2;
	@FXML private TableColumn colidalumnomatriculaTab2;
	@FXML private TableColumn colidmodulomatriculaTab2;
	@FXML private TableColumn colfechaaltamatriculaTab2;
	@FXML private TableColumn colfechabajamatriculaTab2;
	@FXML private TableView<Modulos> tablemodulosTab2;
	@FXML private TableColumn colidmoduloTab2;
	@FXML private TableColumn colnombremoduloTab2;
	@FXML private TableColumn colsiglasmoduloTab2;
	@FXML private TableColumn colidcursomoduloTab2;
	@FXML private TableColumn colfechamoduloTab2;
	@FXML private TableView<Alumnos> tablealumnosTab3;
	@FXML private TableColumn colidalumnoTab3;
	@FXML private TableColumn colnombrealumnoTab3;
	@FXML private TableColumn colapellidosalumnoTab3;
	@FXML private TableColumn colcorreoalumnoTab3;
	@FXML private TableColumn coltelefonoalumnoTab3;
	@FXML private TableColumn coldnialumnoTab3;
	@FXML private TableView<Ciclos> tableciclosTab3;
	@FXML private TableColumn colidcicloTab3;
	@FXML private TableColumn colnombrecicloTab3;
	@FXML private TableColumn colsiglascicloTab3;
	@FXML private TableColumn colfechacicloTab3;
	@FXML private TableView<Cursos> tablecursosTab3;
	@FXML private TableColumn colidcursoTab3;
	@FXML private TableColumn colnombrecursoTab3;
	@FXML private TableColumn colidciclocursoTab3;
	@FXML private TableColumn colfechacursoTab3;
	@FXML private TableView<Matriculas> tablematriculasTab3;
	@FXML private TableColumn colidalumnomatriculaTab3;
	@FXML private TableColumn colidmodulomatriculaTab3;
	@FXML private TableColumn colfechaaltamatriculaTab3;
	@FXML private TableColumn colfechabajamatriculaTab3;
	@FXML private TableView<Modulos> tablemodulosTab3;
	@FXML private TableColumn colidmoduloTab3;
	@FXML private TableColumn colnombremoduloTab3;
	@FXML private TableColumn colsiglasmoduloTab3;
	@FXML private TableColumn colidcursomoduloTab3;
	@FXML private TableColumn colfechamoduloTab3;
	@FXML private Button eliminaralumno;
	@FXML private Button eliminarciclo;
	@FXML private Button eliminarcurso;
	@FXML private Button eliminarmatricula;
	@FXML private Button eliminarmodulo;
	@FXML private ImageView icon;
	@FXML private Label info;
	
	
 
	@FXML
	private void añadiralumnolistener() {

		post(new Alumnos(nombrealumnoTab1.getText(), apellidosalumnoTab1.getText(), telefonoalumnoTab1.getText(),
				correoalumnoTab1.getText(), dnialumnoTab1.getText()), "Alumnos");

	}

	@FXML
	private void añadirciclolistener() {

		post(new Ciclos(nombrecicloTab1.getText(), siglascicloTab1.getText()), "Ciclos");

	}

	@FXML
	private void añadircursolistener() {

		Ciclos ciclo = new Ciclos();
		try {
			ciclo.setIdCiclo(Integer.parseInt(idciclocursoTab1.getText()));
			post(new Cursos(ciclo, nombrecicloTab1.getText()), "Cursos");
		} catch (NumberFormatException e) {
			info.setText("Introduce un id correcto");
			info.setTextFill(Color.web("#8c2a00"));
			icon.setImage(imagecross);
		}

	}

	@FXML
	private void añadirmatriculalistener() {

		try {
			MatriculasId matriculaid = new MatriculasId(Integer.parseInt(idalumnomatriculaTab1.getText()),
					Integer.parseInt(idmodulomatriculaTab1.getText()));
			post(new Matriculas(matriculaid), "Matriculas");
		} catch (NumberFormatException e) {
			info.setText("Introduce un id correcto");
			info.setTextFill(Color.web("#8c2a00"));
			icon.setImage(imagecross);
		}

	}

	@FXML
	private void añadirmodulolistener() {

		Cursos curso = new Cursos();
		try {
			curso.setIdCurso(Integer.parseInt(idcursomoduloTab1.getText()));
			post(new Modulos(curso, nombremoduloTab1.getText(), siglasmoduloTab1.getText()), "Modulos");
		} catch (NumberFormatException e) {
			info.setText("Introduce un id correcto");
			info.setTextFill(Color.web("#8c2a00"));
			icon.setImage(imagecross);
		}

	}

	@FXML
	private void editaralumnolistener() {

	}

	@FXML
	private void editarciclolistener() {
	}

	@FXML
	private void editarcursolistener() {

	}

	@FXML
	private void editarmatriculalistener() {

	}

	@FXML
	private void editarmodulolistener() {

	}

	@FXML
	private void eliminaralumnolistener() {

	}

	@FXML
	private void eliminarciclolistener() {
	}

	@FXML
	private void eliminarcursolistener() {

	}

	@FXML
	private void eliminarmatriculalistener() {

	}

	@FXML
	private void eliminarmodulolistener() {

	}

	private void post(Object objeto, String entidad) {

		HttpURLConnection con;

		try {

			if (TokenUtils.elTokenExiste() && !TokenUtils.elTokenGuardadoHaExpirado()) {

				token = TokenUtils.obtenerTokenGuardado();

				// CREA LA PETICIÓN Y LA ENVÍA
				con = (HttpURLConnection) new URL("http://localhost:8080/ADPSPDI/" + entidad).openConnection();
				con.setDoOutput(true);
				con.setRequestMethod("POST");
				con.setRequestProperty("Content-Type", "application/json");
				con.setRequestProperty("Authorization", "bearer " + token);
				OutputStream os = con.getOutputStream();

				os.write(new Gson().toJson(objeto).getBytes(StandardCharsets.UTF_8));
				
				// os.write(("{\"username\":\"" + username.getText() + "\",\"password\":\"" +
				// password.getText() + "\"}").getBytes()); //Creo que es peor práctica
				os.flush();

				// COMPROBAMOS LA RESPUESTA

				informarResultado(con.getResponseCode(), con);

				con.disconnect();

			} else {
				info.setText("Tu token ha expirado. Por favor, cierra sesión y vuelve a iniciar.");
			}
		} catch (Exception e) {
			info.setText("El programa ha encontrado el siguiente error: " + e.toString());
		}

		resetearCamposTab1();
	}

	private String get(String entidad) {

		String lineaoutput;
		try {

			if (TokenUtils.elTokenExiste() && !TokenUtils.elTokenGuardadoHaExpirado()) {

				token = TokenUtils.obtenerTokenGuardado();

				HttpURLConnection con = (HttpURLConnection) new URL(Constants.url + entidad).openConnection();
				con.setRequestMethod("GET");
				con.setRequestProperty("Content-Type", "application/json");
				con.setRequestProperty("Authorization", "bearer " + token);

				BufferedReader br = new BufferedReader(
						new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
				json = "";
				while ((lineaoutput = br.readLine()) != null) {
					json += lineaoutput;
				}

				informarResultado(con.getResponseCode(), con);

				con.disconnect();

			} else {
				info.setText("Tu token ha expirado. Por favor, cierra sesión y vuelve a iniciar.");
			}
		} catch (Exception e) {
			info.setText("El programa ha encontrado el siguiente error: " + e.toString());
		}

		return json;
	}

	private void informarResultado(int responsecode, HttpURLConnection con) {
		switch (responsecode) {
		case 200:
			info.setText("Operación exitosa: " + con.getRequestMethod());
			info.setTextFill(Color.web("#00bc80"));
			icon.setImage(imagetick);
			break;

		case 401:

			info.setText("El usuario, la contraseña o el token no son correctos."); // esto nunca debería ocurrir ya que
																					// si el token no es correcto se va
																					// al else de abajo
			info.setTextFill(Color.web("#8c2a00"));
			icon.setImage(imagecross);
			break;

		case 404:
			info.setText("No se ha encontrado la entidad especificada. Vuelve a comprobar el id."); // esto nunca
																									// debería ocurrir
																									// ya que si el
																									// token no es
																									// correcto se va al
																									// else de abajo
			info.setTextFill(Color.web("#8c2a00"));
			icon.setImage(imagecross);
			break;

		default:

			infotext = ("El servidor ha devuelto el código #" + responsecode);
			// si no sabemos cuál es el código guardamos toda la información posible en una
			// cadena
			try {
				con.getErrorStream();
				infotext += " con el siguiente error: "
						+ IOUtils.toString(con.getErrorStream(), StandardCharsets.UTF_8);
			} catch (Exception ne) {
				infotext += " sin texto de Error.";
			}
			try {
				con.getInputStream();
				infotext += " con la siguiente entrada: "
						+ IOUtils.toString(con.getInputStream(), StandardCharsets.UTF_8);
			} catch (Exception ne) {
				infotext += " sin devolver ninguna entidad.";
			}

			;
			info.setText(infotext);
			info.setVisible(true);
			icon.setImage(imagecross);
			break;
		}

	}

	@FXML
	private void rbalumnolistenerTab1() {

		prepararInterfazTab1();
		nombrealumnoTab1.setVisible(true);
		nombrealumnotextTab1.setVisible(true);
		apellidosalumnoTab1.setVisible(true);
		apellidosalumnotextTab1.setVisible(true);
		telefonoalumnoTab1.setVisible(true);
		telefonoalumnotextTab1.setVisible(true);
		correoalumnoTab1.setVisible(true);
		correoalumnotextTab1.setVisible(true);
		dnialumnoTab1.setVisible(true);
		dnialumnotextTab1.setVisible(true);
		añadiralumno.setVisible(true);

	}

	@FXML
	private void rbciclolistenerTab1() {

		prepararInterfazTab1();
		nombrecicloTab1.setVisible(true);
		nombreciclotextTab1.setVisible(true);
		siglascicloTab1.setVisible(true);
		siglasciclotextTab1.setVisible(true);
		añadirciclo.setVisible(true);

	}

	@FXML
	private void rbcursolistenerTab1() {

		prepararInterfazTab1();
		nombrecursoTab1.setVisible(true);
		nombrecursotextTab1.setVisible(true);
		idciclocursoTab1.setVisible(true);
		idciclocursotextTab1.setVisible(true);
		añadircurso.setVisible(true);
	}

	@FXML
	private void rbmatriculalistenerTab1() {

		prepararInterfazTab1();
		idalumnomatriculaTab1.setVisible(true);
		idalumnomatriculatextTab1.setVisible(true);
		idmodulomatriculaTab1.setVisible(true);
		idmodulomatriculatextTab1.setVisible(true);
		añadirmatricula.setVisible(true);

	}

	@FXML
	private void rbmodulolistenerTab1() {

		prepararInterfazTab1();
		nombremoduloTab1.setVisible(true);
		nombremodulotextTab1.setVisible(true);
		siglasmoduloTab1.setVisible(true);
		siglasmodulotextTab1.setVisible(true);
		idcursomoduloTab1.setVisible(true);
		idcursomodulotextTab1.setVisible(true);
		añadirmodulo.setVisible(true);

	}

	private void prepararInterfazTab1() {

		nombrealumnoTab1.setVisible(false);
		nombrealumnotextTab1.setVisible(false);
		apellidosalumnoTab1.setVisible(false);
		apellidosalumnotextTab1.setVisible(false);
		telefonoalumnoTab1.setVisible(false);
		telefonoalumnotextTab1.setVisible(false);
		correoalumnoTab1.setVisible(false);
		correoalumnotextTab1.setVisible(false);
		dnialumnoTab1.setVisible(false);
		dnialumnotextTab1.setVisible(false);
		nombrecicloTab1.setVisible(false);
		nombreciclotextTab1.setVisible(false);
		siglascicloTab1.setVisible(false);
		siglasciclotextTab1.setVisible(false);
		nombrecursoTab1.setVisible(false);
		nombrecursotextTab1.setVisible(false);
		idciclocursoTab1.setVisible(false);
		idciclocursotextTab1.setVisible(false);
		idalumnomatriculaTab1.setVisible(false);
		idalumnomatriculatextTab1.setVisible(false);
		idmodulomatriculaTab1.setVisible(false);
		idmodulomatriculatextTab1.setVisible(false);
		nombremoduloTab1.setVisible(false);
		nombremodulotextTab1.setVisible(false);
		siglasmoduloTab1.setVisible(false);
		siglasmodulotextTab1.setVisible(false);
		idcursomoduloTab1.setVisible(false);
		idcursomodulotextTab1.setVisible(false);
		añadiralumno.setVisible(false);
		añadirciclo.setVisible(false);
		añadircurso.setVisible(false);
		añadirmatricula.setVisible(false);
		añadirmodulo.setVisible(false);
		info.setText("Conectado");
		icon.setImage(imagetick);

	}

	private void resetearCamposTab1() {

		nombrealumnoTab1.setText("");
		apellidosalumnoTab1.setText("");
		telefonoalumnoTab1.setText("");
		correoalumnoTab1.setText("");
		dnialumnoTab1.setText("");
		nombrecicloTab1.setText("");
		siglascicloTab1.setText("");
		nombrecursoTab1.setText("");
		idciclocursoTab1.setText("");
		idalumnomatriculaTab1.setText("");
		idmodulomatriculaTab1.setText("");
		nombremoduloTab1.setText("");
		siglasmoduloTab1.setText("");
		idcursomoduloTab1.setText("");

	}

	@SuppressWarnings("unchecked")
	@FXML
	private void rbalumnolistenerTab2() {

		prepararInterfazTab2();
		nombrealumnoTab2.setVisible(true);
		nombrealumnotextTab2.setVisible(true);
		apellidosalumnoTab2.setVisible(true);
		apellidosalumnotextTab2.setVisible(true);
		telefonoalumnoTab2.setVisible(true);
		telefonoalumnotextTab2.setVisible(true);
		correoalumnoTab2.setVisible(true);
		correoalumnotextTab2.setVisible(true);
		dnialumnoTab2.setVisible(true);
		dnialumnotextTab2.setVisible(true);
		editaralumno.setVisible(true);
		tablealumnosTab2.setVisible(true);

		colidalumnoTab2.setCellValueFactory(new PropertyValueFactory<Alumnos, Integer>("idAlumno"));
		colnombrealumnoTab2.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("nombre"));
		colapellidosalumnoTab2.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("apellidos"));
		coltelefonoalumnoTab2.setCellValueFactory(new PropertyValueFactory<Alumnos, Integer>("telefono"));
		colcorreoalumnoTab2.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("dni"));
		coldnialumnoTab2.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("correo"));

		json = get("Alumnos");
		List<Alumnos> listaalumnos = new Gson().fromJson(json, listTypeAlumnos);
		filasAlumnos.clear();
		for (int i = 0; i < listaalumnos.size(); i++) {
			filasAlumnos.add(new Alumnos(listaalumnos.get(i).getIdAlumno(), listaalumnos.get(i).getNombre(),
					listaalumnos.get(i).getApellidos(), listaalumnos.get(i).getTelefono(), listaalumnos.get(i).getDni(),
					listaalumnos.get(i).getCorreo()));
		}

		tablealumnosTab2.setItems(filasAlumnos);

	}

	@SuppressWarnings("unchecked")
	@FXML
	private void rbciclolistenerTab2() {

		prepararInterfazTab2();
		nombrecicloTab2.setVisible(true);
		nombreciclotextTab2.setVisible(true);
		siglascicloTab2.setVisible(true);
		siglasciclotextTab2.setVisible(true);
		editarciclo.setVisible(true);
		tableciclosTab2.setVisible(true);

		colidcicloTab2.setCellValueFactory(new PropertyValueFactory<Ciclos, Integer>("idCiclo"));
		colnombrecicloTab2.setCellValueFactory(new PropertyValueFactory<Ciclos, String>("nombre"));
		colsiglascicloTab2.setCellValueFactory(new PropertyValueFactory<Ciclos, String>("siglas"));
		colfechacicloTab2.setCellValueFactory(new PropertyValueFactory<Ciclos, String>("fechaAlta"));

		json = get("Ciclos");
		List<Ciclos> listaciclos = new Gson().fromJson(json, listTypeCiclos);
		filasCiclos.clear();
		for (int i = 0; i < listaciclos.size(); i++) {
			filasCiclos.add(new Ciclos(listaciclos.get(i).getIdCiclo(), listaciclos.get(i).getNombre(),
					listaciclos.get(i).getSiglas(), listaciclos.get(i).getFechaAlta()));
		}

		tableciclosTab2.setItems(filasCiclos);

	}

	@SuppressWarnings("unchecked")
	@FXML
	private void rbcursolistenerTab2() {

		prepararInterfazTab2();
		nombrecursoTab2.setVisible(true);
		nombrecursotextTab2.setVisible(true);
		idciclocursoTab2.setVisible(true);
		idciclocursotextTab2.setVisible(true);
		editarcurso.setVisible(true);
		tablecursosTab2.setVisible(true);

		colidcursoTab2.setCellValueFactory(new PropertyValueFactory<Cursos, Integer>("idCurso"));
		colnombrecursoTab2.setCellValueFactory(new PropertyValueFactory<Cursos, String>("nombre"));
		colfechacursoTab2.setCellValueFactory(new PropertyValueFactory<Cursos, String>("fechaAlta"));
		colidciclocursoTab2.setCellValueFactory(new PropertyValueFactory<Cursos, Integer>("idCiclo"));

		json = get("Cursos");
		List<Cursos> listacursos = new Gson().fromJson(json, listTypeCursos);
		filasCursos.clear();
		for (int i = 0; i < listacursos.size(); i++) {
			filasCursos.add(new Cursos(listacursos.get(i).getIdCurso(), listacursos.get(i).getNombre(),
					listacursos.get(i).getFechaAlta(), listacursos.get(i).getCiclos().getIdCiclo()));
		}

		tablecursosTab2.setItems(filasCursos);
	}

	@SuppressWarnings("unchecked")
	@FXML
	private void rbmatriculalistenerTab2() {

		prepararInterfazTab2();
		idalumnomatriculaTab2.setVisible(true);
		idalumnomatriculatextTab2.setVisible(true);
		idmodulomatriculaTab2.setVisible(true);
		idmodulomatriculatextTab2.setVisible(true);
		editarmatricula.setVisible(true);
		tablematriculasTab2.setVisible(true);

		colidalumnomatriculaTab2.setCellValueFactory(new PropertyValueFactory<Matriculas, Integer>("idAlumno"));
		colidmodulomatriculaTab2.setCellValueFactory(new PropertyValueFactory<Matriculas, Integer>("idModulo"));
		colfechaaltamatriculaTab2.setCellValueFactory(new PropertyValueFactory<Matriculas, String>("fechaAlta"));
		colfechabajamatriculaTab2.setCellValueFactory(new PropertyValueFactory<Matriculas, String>("fechaBaja"));

		json = get("Matriculas");

		List<Matriculas> listamatriculas = new Gson().fromJson(json, listTypeMatriculas);
		
		
		filasMatriculas.clear();
		for (int i = 0; i < listamatriculas.size(); i++) {
			filasMatriculas.add(new Matriculas(listamatriculas.get(i).getAlumnos().getIdAlumno(),
					listamatriculas.get(i).getModulos().getIdModulo(), listamatriculas.get(i).getFechaAlta(),
					listamatriculas.get(i).getFechaBaja()));
		}

		tablematriculasTab2.setItems(filasMatriculas);

	}

	@SuppressWarnings("unchecked")
	@FXML
	private void rbmodulolistenerTab2() {

		prepararInterfazTab2();
		nombremoduloTab2.setVisible(true);
		nombremodulotextTab2.setVisible(true);
		siglasmoduloTab2.setVisible(true);
		siglasmodulotextTab2.setVisible(true);
		idcursomoduloTab2.setVisible(true);
		idcursomodulotextTab2.setVisible(true);
		editarmodulo.setVisible(true);
		tablemodulosTab2.setVisible(true);
		
		colidmoduloTab2.setCellValueFactory(new PropertyValueFactory<Modulos, Integer>("idModulo"));
		colnombremoduloTab2.setCellValueFactory(new PropertyValueFactory<Modulos, Integer>("nombre"));
		colsiglasmoduloTab2.setCellValueFactory(new PropertyValueFactory<Modulos, String>("siglas"));
		colfechamoduloTab2.setCellValueFactory(new PropertyValueFactory<Modulos, String>("fechaAlta"));
		colidcursomoduloTab2.setCellValueFactory(new PropertyValueFactory<Modulos, String>("idCurso"));
		
		json = get("Modulos");

		List<Modulos> listamodulos = new Gson().fromJson(json, listTypeModulos);
		filasModulos.clear();
		for (int i = 0; i < listamodulos.size(); i++) {
			filasModulos.add(new Modulos(listamodulos.get(i).getIdModulo(),
					listamodulos.get(i).getNombre(), listamodulos.get(i).getSiglas(), listamodulos.get(i).getFechaAlta(), listamodulos.get(i).getCursos().getIdCurso()));
		}

		tablemodulosTab2.setItems(filasModulos);

	}

	private void prepararInterfazTab2() {

		nombrealumnoTab2.setVisible(false);
		nombrealumnotextTab2.setVisible(false);
		apellidosalumnoTab2.setVisible(false);
		apellidosalumnotextTab2.setVisible(false);
		telefonoalumnoTab2.setVisible(false);
		telefonoalumnotextTab2.setVisible(false);
		correoalumnoTab2.setVisible(false);
		correoalumnotextTab2.setVisible(false);
		dnialumnoTab2.setVisible(false);
		dnialumnotextTab2.setVisible(false);
		nombrecicloTab2.setVisible(false);
		nombreciclotextTab2.setVisible(false);
		siglascicloTab2.setVisible(false);
		siglasciclotextTab2.setVisible(false);
		nombrecursoTab2.setVisible(false);
		nombrecursotextTab2.setVisible(false);
		idciclocursoTab2.setVisible(false);
		idciclocursotextTab2.setVisible(false);
		idalumnomatriculaTab2.setVisible(false);
		idalumnomatriculatextTab2.setVisible(false);
		idmodulomatriculaTab2.setVisible(false);
		idmodulomatriculatextTab2.setVisible(false);
		nombremoduloTab2.setVisible(false);
		nombremodulotextTab2.setVisible(false);
		siglasmoduloTab2.setVisible(false);
		siglasmodulotextTab2.setVisible(false);
		idcursomoduloTab2.setVisible(false);
		idcursomodulotextTab2.setVisible(false);
		editaralumno.setVisible(false);
		editarciclo.setVisible(false);
		editarcurso.setVisible(false);
		editarmatricula.setVisible(false);
		editarmodulo.setVisible(false);
		tablealumnosTab2.setVisible(false);
		tableciclosTab2.setVisible(false);
		tablecursosTab2.setVisible(false);
		tablematriculasTab2.setVisible(false);
		tablemodulosTab2.setVisible(false);
		info.setText("Conectado");
		icon.setImage(imagetick);

	}

	private void resetearCamposTab2() {

		nombrealumnoTab2.setText("");
		apellidosalumnoTab2.setText("");
		telefonoalumnoTab2.setText("");
		correoalumnoTab2.setText("");
		dnialumnoTab2.setText("");
		nombrecicloTab2.setText("");
		siglascicloTab2.setText("");
		nombrecursoTab2.setText("");
		idciclocursoTab2.setText("");
		idalumnomatriculaTab2.setText("");
		idmodulomatriculaTab2.setText("");
		nombremoduloTab2.setText("");
		siglasmoduloTab2.setText("");
		idcursomoduloTab2.setText("");

	}

	@SuppressWarnings("unchecked")
	@FXML
	private void rbalumnolistenerTab3() {

		prepararInterfazTab3();
		eliminaralumno.setVisible(true);
		tablealumnosTab3.setVisible(true);
		
		
		colidalumnoTab3.setCellValueFactory(new PropertyValueFactory<Alumnos, Integer>("idAlumno"));
		colnombrealumnoTab3.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("nombre"));
		colapellidosalumnoTab3.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("apellidos"));
		coltelefonoalumnoTab3.setCellValueFactory(new PropertyValueFactory<Alumnos, Integer>("telefono"));
		colcorreoalumnoTab3.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("dni"));
		coldnialumnoTab3.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("correo"));

		json = get("Alumnos");
		List<Alumnos> listaalumnos = new Gson().fromJson(json, listTypeAlumnos);
		filasAlumnos.clear();
		for (int i = 0; i < listaalumnos.size(); i++) {
			filasAlumnos.add(new Alumnos(listaalumnos.get(i).getIdAlumno(), listaalumnos.get(i).getNombre(),
					listaalumnos.get(i).getApellidos(), listaalumnos.get(i).getTelefono(), listaalumnos.get(i).getDni(),
					listaalumnos.get(i).getCorreo()));
		}

		tablealumnosTab3.setItems(filasAlumnos);

	}

	@SuppressWarnings("unchecked")
	@FXML
	private void rbciclolistenerTab3() {

		prepararInterfazTab3();
		eliminarciclo.setVisible(true);
		tableciclosTab3.setVisible(true);

		colidcicloTab3.setCellValueFactory(new PropertyValueFactory<Ciclos, Integer>("idCiclo"));
		colnombrecicloTab3.setCellValueFactory(new PropertyValueFactory<Ciclos, String>("nombre"));
		colsiglascicloTab3.setCellValueFactory(new PropertyValueFactory<Ciclos, String>("siglas"));
		colfechacicloTab3.setCellValueFactory(new PropertyValueFactory<Ciclos, String>("fechaAlta"));
		
		json = get("Ciclos");
		List<Ciclos> listaciclos = new Gson().fromJson(json, listTypeCiclos);
		filasCiclos.clear();
		for (int i = 0; i < listaciclos.size(); i++) {
			filasCiclos.add(new Ciclos(listaciclos.get(i).getIdCiclo(), listaciclos.get(i).getNombre(),
					listaciclos.get(i).getSiglas(), listaciclos.get(i).getFechaAlta()));
		}

		tableciclosTab3.setItems(filasCiclos);
	}

	@SuppressWarnings("unchecked")
	@FXML
	private void rbcursolistenerTab3() {

		prepararInterfazTab3();
		eliminarcurso.setVisible(true);
		tablecursosTab3.setVisible(true);
		
		colidcursoTab3.setCellValueFactory(new PropertyValueFactory<Cursos, Integer>("idCurso"));
		colnombrecursoTab3.setCellValueFactory(new PropertyValueFactory<Cursos, String>("nombre"));
		colfechacursoTab3.setCellValueFactory(new PropertyValueFactory<Cursos, String>("fechaAlta"));
		colidciclocursoTab3.setCellValueFactory(new PropertyValueFactory<Cursos, Integer>("idCiclo"));


		json = get("Cursos");
		List<Cursos> listacursos = new Gson().fromJson(json, listTypeCursos);
		filasCursos.clear();
		for (int i = 0; i < listacursos.size(); i++) {
			filasCursos.add(new Cursos(listacursos.get(i).getIdCurso(), listacursos.get(i).getNombre(),
					listacursos.get(i).getFechaAlta(), listacursos.get(i).getCiclos().getIdCiclo()));
		}

		tablecursosTab3.setItems(filasCursos);
	}

	@SuppressWarnings("unchecked")
	@FXML
	private void rbmatriculalistenerTab3() {

		prepararInterfazTab3();
		eliminarmatricula.setVisible(true);
		tablematriculasTab3.setVisible(true);
		
		colidalumnomatriculaTab3.setCellValueFactory(new PropertyValueFactory<Matriculas, Integer>("idAlumno"));
		colidmodulomatriculaTab3.setCellValueFactory(new PropertyValueFactory<Matriculas, Integer>("idModulo"));
		colfechaaltamatriculaTab3.setCellValueFactory(new PropertyValueFactory<Matriculas, String>("fechaAlta"));
		colfechabajamatriculaTab3.setCellValueFactory(new PropertyValueFactory<Matriculas, String>("fechaBaja"));
		
		json = get("Matriculas");

		List<Matriculas> listamatriculas = new Gson().fromJson(json, listTypeMatriculas);
		
		
		filasMatriculas.clear();
		for (int i = 0; i < listamatriculas.size(); i++) {
			filasMatriculas.add(new Matriculas(listamatriculas.get(i).getAlumnos().getIdAlumno(),
					listamatriculas.get(i).getModulos().getIdModulo(), listamatriculas.get(i).getFechaAlta(),
					listamatriculas.get(i).getFechaBaja()));
		}

		tablematriculasTab3.setItems(filasMatriculas);

	}

	@SuppressWarnings("unchecked")
	@FXML
	private void rbmodulolistenerTab3() {

		prepararInterfazTab3();
		eliminarmodulo.setVisible(true);
		tablemodulosTab3.setVisible(true);
		
		colidmoduloTab3.setCellValueFactory(new PropertyValueFactory<Modulos, Integer>("idModulo"));
		colnombremoduloTab3.setCellValueFactory(new PropertyValueFactory<Modulos, Integer>("nombre"));
		colsiglasmoduloTab3.setCellValueFactory(new PropertyValueFactory<Modulos, String>("siglas"));
		colfechamoduloTab3.setCellValueFactory(new PropertyValueFactory<Modulos, String>("fechaAlta"));
		colidcursomoduloTab3.setCellValueFactory(new PropertyValueFactory<Modulos, String>("idCurso"));
		
		json = get("Modulos");

		List<Modulos> listamodulos = new Gson().fromJson(json, listTypeModulos);
		filasModulos.clear();
		for (int i = 0; i < listamodulos.size(); i++) {
			filasModulos.add(new Modulos(listamodulos.get(i).getIdModulo(),
					listamodulos.get(i).getNombre(), listamodulos.get(i).getSiglas(), listamodulos.get(i).getFechaAlta(), listamodulos.get(i).getCursos().getIdCurso()));
		}

		tablemodulosTab3.setItems(filasModulos);

	}

	private void prepararInterfazTab3() {

		
		eliminaralumno.setVisible(false);
		eliminarciclo.setVisible(false);
		eliminarcurso.setVisible(false);
		eliminarmatricula.setVisible(false);
		eliminarmodulo.setVisible(false);
		tablealumnosTab3.setVisible(false);
		tableciclosTab3.setVisible(false);
		tablecursosTab3.setVisible(false);
		tablematriculasTab3.setVisible(false);
		tablemodulosTab3.setVisible(false);
		info.setText("Conectado");
		icon.setImage(imagetick);

	}

	

}
