package domain;

import java.util.Date;

@SuppressWarnings("serial")
public class Ciclos implements java.io.Serializable {

	private Integer idCiclo;
	private String nombre;
	private String siglas;
	private Date fechaAlta;

	public Ciclos() {
	}

	public Ciclos(String nombre, String siglas, Date fechaAlta) {
		this.nombre = nombre;
		this.siglas = siglas;
		this.fechaAlta = fechaAlta;
	}

	public Integer getIdCiclo() {
		return this.idCiclo;
	}

	public void setIdCiclo(Integer idCiclo) {
		this.idCiclo = idCiclo;
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
