package domain;

import java.util.Date;


@SuppressWarnings("serial")
public class Modulos implements java.io.Serializable {

	private Integer idModulo;
	private Cursos cursos;
	private String nombre;
	private String siglas;
	private Date fechaAlta;

	public Modulos() {
	}

	public Modulos(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Modulos(Cursos cursos, String nombre, String siglas, Date fechaAlta) {
		this.cursos = cursos;
		this.nombre = nombre;
		this.siglas = siglas;
		this.fechaAlta = fechaAlta;
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

	public Date getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}


}
