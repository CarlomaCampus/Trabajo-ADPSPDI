package api;


import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
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
import com.google.gson.GsonBuilder;

import domain.Cursos;
import utils.Authorization;
import utils.HibernateProxyTypeAdapter;
import utils.HibernateSession;
@Path("/Cursos")
public class CursosREST {


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCursos(@HeaderParam(HttpHeaders.AUTHORIZATION) String token) {
		
		if (!Authorization.isAuthorized(token)) { return Response.status(401).entity("Token inválido").build(); }

		try {
			HibernateSession.getTransaction();
			@SuppressWarnings("unchecked")
			List<Cursos> listacursos = HibernateSession.session.createQuery("from Cursos").list();
			HibernateSession.commitclose();
			
			Gson foreigngson = new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create();
			return Response.status(200).entity(foreigngson.toJson(listacursos)).build();

		} catch (Exception e) {
			HibernateSession.rollbackclose();
			return Response.status(500).entity("El servidor ha encontrado el siguiente error: " + e.toString()).build();
		}
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)

	public Response getCurso(@HeaderParam(HttpHeaders.AUTHORIZATION) String token, @PathParam("id") String idCurso) {
		
		if (!Authorization.isAuthorized(token)) { return Response.status(401).entity("Token inválido").build(); }

		Cursos curso;
		try {
			HibernateSession.getTransaction();
			try {
				curso = (Cursos) HibernateSession.session.createQuery("from Cursos where idCurso = " + idCurso).getSingleResult();
				
				
				HibernateSession.commitclose();
				Gson foreigngson = new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create();
				return Response.status(200).entity(foreigngson.toJson(curso)).build();

			} catch (NoResultException e) {
				HibernateSession.commitclose();
				return Response.status(404).build();
			}
			
		} catch (Exception e) {
			HibernateSession.rollbackclose();
			return Response.status(500).entity("El servidor ha encontrado el siguiente error: " + e.toString()).build();
		}

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)

	public Response postCurso(@HeaderParam(HttpHeaders.AUTHORIZATION) String token, String json) {
		
		if (!Authorization.isAuthorized(token)) { return Response.status(401).entity("Token inválido").build(); }

		try {
			Cursos curso = new Gson().fromJson(json, Cursos.class);

			HibernateSession.getTransaction();

			HibernateSession.session.save(curso);

			HibernateSession.commitclose();
			return Response.status(200).entity(new Gson().toJson(curso)).build();

		} catch (PersistenceException e) {
			HibernateSession.rollbackclose();
			return Response.status(404).build();
		} catch (Exception e) {
			HibernateSession.rollbackclose();
			return Response.status(500).entity("El servidor ha encontrado el siguiente error: " + e.toString()).build();
		}

	}

	@Path("/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response putCurso(@HeaderParam(HttpHeaders.AUTHORIZATION) String token, @PathParam("id") String idCurso, String json) {
		
		if (!Authorization.isAuthorized(token)) { return Response.status(401).entity("Token inválido").build(); }

		try {
			Cursos curso = new Gson().fromJson(json, Cursos.class);

			HibernateSession.getTransaction();
			int filasactualizadas = HibernateSession.session
					.createQuery(
							"update Cursos c set c.nombre = '"+curso.getNombre()+"' where c.idCurso = "+idCurso)
					//.setParameter("newName", curso.getNombre()).setParameter("newSiglas", curso.getSiglas())
					//.setParameter("oldName", Integer.parseInt(idCurso))
					.executeUpdate();

			HibernateSession.commitclose();
			if (filasactualizadas == 0) {
				return Response.status(404).entity("No se ha encontrado la entidad. No se ha actualizado ninguna fila.")
						.build();
			}

			return Response.status(200).entity(new Gson().toJson(curso)).build();
		} catch (Exception e) {
			HibernateSession.rollbackclose();
			return Response.status(500).entity("El servidor ha encontrado el siguiente error: " + e.toString()).build();
		}

	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)

	public Response deleteCurso(@HeaderParam(HttpHeaders.AUTHORIZATION) String token, @PathParam("id") String idCurso) {
		
		if (!Authorization.isAuthorized(token)) { return Response.status(401).entity("Token inválido").build(); }

		HibernateSession.getTransaction();
		try {
			int filaseliminadas = HibernateSession.session.createQuery("delete Cursos c where c.idCurso = "+idCurso)
					//.setParameter("id", Integer.parseInt(idCurso))
					.executeUpdate();
			HibernateSession.commitclose();
			return Response.status(200).entity(new Gson().toJson("Filas eliminadas: " + filaseliminadas)).build();
		} catch (ConstraintViolationException e) {
			
			HibernateSession.rollbackclose();			
			return Response.status(400).entity(new Gson().toJson("Debe eliminar en cascada.")).build();
		} catch (Exception e) {
			HibernateSession.rollbackclose();
			return Response.status(500).entity(new Gson().toJson("El servidor ha encontrado el siguiente error: " + e.toString())).build();
		}

	}


}
