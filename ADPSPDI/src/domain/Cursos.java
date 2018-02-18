package domain;

import java.util.Date;

@SuppressWarnings("serial")
public class Cursos implements java.io.Serializable {

	private Integer idCurso;
	private Ciclos ciclos;
	private String nombre;
	private Date fechaAlta;

	public Cursos() {
	}

	public Cursos(Ciclos ciclos) {
		this.ciclos = ciclos;
	}

	public Cursos(Ciclos ciclos, String nombre, Date fechaAlta) {
		this.ciclos = ciclos;
		this.nombre = nombre;
		this.fechaAlta = fechaAlta;
	}

	public Integer getIdCurso() {
		return this.idCurso;
	}

	public void setIdCurso(Integer idCurso) {
		this.idCurso = idCurso;
	}

	public Ciclos getCiclos() {
		return this.ciclos;
	}

	public void setCiclos(Ciclos ciclos) {
		this.ciclos = ciclos;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}



}
