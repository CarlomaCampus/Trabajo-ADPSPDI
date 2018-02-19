package modelos;


@SuppressWarnings("serial")
public class Alumnos implements java.io.Serializable {

	private Integer idAlumno;
	private String nombre;
	private String apellidos;
	private String telefono;
	private String dni;
	private String correo;

	public Alumnos() {
	}
	
	public Alumnos(Integer id, String nombre, String apellidos, String telefono, String correo, String dni) {
		this.idAlumno = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.dni = dni;
		this.correo = correo;
	}


	public Alumnos(String nombre, String apellidos, String telefono, String dni, String correo) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.dni = dni;
		this.correo = correo;
	}

	public Integer getIdAlumno() {
		return this.idAlumno;
	}

	public void setIdAlumno(Integer idAlumno) {
		this.idAlumno = idAlumno;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}


}
