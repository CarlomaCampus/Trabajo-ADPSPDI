package api;

import java.util.List;

import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.exception.ConstraintViolationException;

import com.google.gson.Gson;

import domain.Alumnos;
import utils.Authorization;
import utils.HibernateSession;

@Path("/Alumnos")
public class AlumnosREST {


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAlumnos(@HeaderParam(HttpHeaders.AUTHORIZATION) String token) {
		
		if (!Authorization.isAuthorized(token)) { return Response.status(401).entity("Token inválido").build(); }
		
		try {
			HibernateSession.getTransaction();
			@SuppressWarnings("unchecked")
			List<Alumnos> listaalumnos = HibernateSession.session.createQuery("from Alumnos").list();
			HibernateSession.commitclose();

			return Response.status(200).entity(new Gson().toJson(listaalumnos)).build();

		} catch (Exception e) {
			HibernateSession.rollbackclose();
			return Response.status(500).entity("El servidor ha encontrado el siguiente error: " + e.toString()).build();
		}
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)

	public Response getAlumno(@HeaderParam(HttpHeaders.AUTHORIZATION) String token, @PathParam("id") String idAlumno) {
		
		if (!Authorization.isAuthorized(token)) { return Response.status(401).entity("Token inválido").build(); }

		Alumnos alumno;
		try {
			HibernateSession.getTransaction();
			try {
				alumno = (Alumnos) HibernateSession.session.createQuery("from Alumnos where idAlumno = " + idAlumno).getSingleResult();
			
				HibernateSession.commitclose();
				return Response.status(200).entity(new Gson().toJson(alumno)).build();
			} catch (NoResultException e) {
				HibernateSession.commitclose();
				return Response.status(404).build();
			}
			

		} catch (Exception e) {
			HibernateSession.commitclose();
			return Response.status(500).entity("El servidor ha encontrado el siguiente error: " + e.toString()).build();
		}

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)

	public Response postAlumno(@HeaderParam(HttpHeaders.AUTHORIZATION) String token, String json) {
		
		if (!Authorization.isAuthorized(token)) { return Response.status(401).entity("Token inválido").build(); }

		try {
			
			Alumnos alumno = new Gson().fromJson(json, Alumnos.class);

			HibernateSession.getTransaction();

			HibernateSession.session.save(alumno);

			HibernateSession.commitclose();
			return Response.status(200).entity(new Gson().toJson(alumno)).build();

		} catch (Exception e) {
			HibernateSession.rollbackclose();
			return Response.status(500).entity("El servidor ha encontrado el siguiente error: " + e.toString()).build();
		}

	}

	@Path("/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response putAlumno(@HeaderParam(HttpHeaders.AUTHORIZATION) String token, @PathParam("id") String idAlumno, String json) {
		
		if (!Authorization.isAuthorized(token)) { return Response.status(401).entity("Token inválido").build(); }

		try {
			Alumnos alumno = new Gson().fromJson(json, Alumnos.class);

			HibernateSession.getTransaction();
			int filasactualizadas = HibernateSession.session
					.createQuery("update Alumnos a set a.nombre = '" + alumno.getNombre() + "', a.apellidos = '"
							+ alumno.getApellidos() + "', a.telefono = '" + alumno.getTelefono() + "', a.dni = '"
							+ alumno.getDni() + "', a.correo = '" + alumno.getCorreo() + "' where a.idAlumno = "
							+ idAlumno)
					// .setParameter("newName", a.getNombre()).setParameter("newSiglas",
					// c.getSiglas())
					// .setParameter("oldName", Integer.parseInt(idAlumno)) EDITAR PARA ALUMNOS
					.executeUpdate();

			HibernateSession.commitclose();
			if (filasactualizadas == 0) {
				return Response.status(404).entity("No se ha encontrado la entidad. No se ha actualizado ninguna fila.")
						.build();
			}

			return Response.status(200).entity(new Gson().toJson(alumno)).build();
		} catch (Exception e) {
			HibernateSession.rollbackclose();
			return Response.status(500).entity("El servidor ha encontrado el siguiente error: " + e.toString()).build();
		}

	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)

	public Response deleteAlumno(@HeaderParam(HttpHeaders.AUTHORIZATION) String token, @PathParam("id") String idAlumno) {
		
		if (!Authorization.isAuthorized(token)) { return Response.status(401).entity("Token inválido").build(); }
		try {
		HibernateSession.getTransaction();

		int filaseliminadas = HibernateSession.session.createQuery("delete Alumnos c where c.idAlumno = " + idAlumno)
				// .setParameter("id", Integer.parseInt(idAlumno))
				.executeUpdate();
		HibernateSession.commitclose();

		return Response.status(200).entity(new Gson().toJson("Filas eliminadas: " + filaseliminadas)).build();
		
	} catch (ConstraintViolationException e) {
		
		HibernateSession.rollbackclose();
		return Response.status(400).entity(new Gson().toJson("Debe eliminar en cascada.")).build();
	} catch (Exception e) {
		HibernateSession.rollbackclose();
		return Response.status(500).entity("El servidor ha encontrado el siguiente error: " + e.toString()).build();
	}

	}

}
