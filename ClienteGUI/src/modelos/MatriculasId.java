package modelos;

@SuppressWarnings("serial")
public class MatriculasId implements java.io.Serializable {

	private int idAlumno;
	private int idModulo;

	public MatriculasId() {
	}

	public MatriculasId(int idAlumno, int idModulo) {
		this.idAlumno = idAlumno;
		this.idModulo = idModulo;
	}

	public int getIdAlumno() {
		return this.idAlumno;
	}

	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}

	public int getIdModulo() {
		return this.idModulo;
	}

	public void setIdModulo(int idModulo) {
		this.idModulo = idModulo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MatriculasId))
			return false;
		MatriculasId castOther = (MatriculasId) other;

		return (this.getIdAlumno() == castOther.getIdAlumno()) && (this.getIdModulo() == castOther.getIdModulo());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdAlumno();
		result = 37 * result + this.getIdModulo();
		return result;
	}

}
