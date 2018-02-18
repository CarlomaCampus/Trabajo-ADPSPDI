package modelos;

import java.util.Date;


@SuppressWarnings("serial")
public class Modulos implements java.io.Serializable {

	private Integer idModulo;
	private Cursos cursos;
	private Integer idCurso;
	private String nombre;
	private String siglas;
	private Date fechaAlta;

	public Modulos() {
	}


	public Modulos(Cursos cursos, String nombre, String siglas) {
		this.cursos = cursos;
		this.nombre = nombre;
		this.siglas = siglas;
	}
	
	public Modulos(String nombre, String siglas) {
		this.nombre = nombre;
		this.siglas = siglas;
	}
	
	public Modulos(Cursos cursos, String nombre, String siglas, Date fechaAlta) {
		this.cursos = cursos;
		this.nombre = nombre;
		this.siglas = siglas;
		this.fechaAlta = fechaAlta;
	}
	
	public Modulos(Integer idModulo, String nombre, String siglas, Date fechaAlta, Integer idCurso) {
		this.idModulo = idModulo;
		this.idCurso = idCurso;
		this.nombre = nombre;
		this.siglas = siglas;
	}

	public Integer getIdModulo() {
		return this.idModulo;
	}

	public void setIdModulo(Integer idModulo) {
		this.idModulo = idModulo;
	}

	public Cursos getCursos() {
		return this.cursos;
	}

	public void setCursos(Cursos cursos) {
		this.cursos = cursos;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSiglas() {
		return this.siglas;
	}

	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}

	public Date getfechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}


	public Integer getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Integer idCurso) {
		this.idCurso = idCurso;
	}


}
