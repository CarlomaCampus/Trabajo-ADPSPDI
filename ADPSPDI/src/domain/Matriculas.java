package domain;
import java.util.Date;


@SuppressWarnings("serial")
public class Matriculas implements java.io.Serializable {

	private MatriculasId id;
	private Alumnos alumnos;
	private Modulos modulos;
	private Date fechaAlta;
	private Date fechaBaja;

	public Matriculas() {
	}

	public Matriculas(MatriculasId id, Alumnos alumnos, Modulos modulos, Date fechaAlta) {
		this.id = id;
		this.alumnos = alumnos;
		this.modulos = modulos;
		this.fechaAlta = fechaAlta;
	}

	public Matriculas(MatriculasId id, Alumnos alumnos, Modulos modulos, Date fechaAlta, Date fechaBaja) {
		this.id = id;
		this.alumnos = alumnos;
		this.modulos = modulos;
		this.fechaAlta = fechaAlta;
		this.fechaBaja = fechaBaja;
	}

	public MatriculasId getId() {
		return this.id;
	}

	public void setId(MatriculasId id) {
		this.id = id;
	}

	public Alumnos getAlumnos() {
		return this.alumnos;
	}

	public void setAlumnos(Alumnos alumnos) {
		this.alumnos = alumnos;
	}

	public Modulos getModulos() {
		return this.modulos;
	}

	public void setModulos(Modulos modulos) {
		this.modulos = modulos;
	}

	public Date getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaBaja() {
		return this.fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

}
