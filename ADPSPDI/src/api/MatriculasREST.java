package api;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import domain.Matriculas;
import utils.Authorization;
import utils.HibernateProxyTypeAdapter;
import utils.HibernateSession;

@Path("/Matriculas")
public class MatriculasREST {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMatriculas(@HeaderParam(HttpHeaders.AUTHORIZATION) String token) {

		if (!Authorization.isAuthorized(token)) {
			return Response.status(401).entity("Token inválido").build();
		}

		try {
			HibernateSession.getTransaction();
			@SuppressWarnings("unchecked")
			List<Matriculas> listamatriculas = HibernateSession.session.createQuery("from Matriculas").list();
			HibernateSession.commitclose();

			Gson foreigngson = new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create();
			return Response.status(200).entity(foreigngson.toJson(listamatriculas)).build();

		} catch (Exception e) {
			HibernateSession.rollbackclose();
			return Response.status(500).entity("El servidor ha encontrado el siguiente error: " + e.toString()).build();
		}
	}

	@GET
	@Path("/Alumnos/{idAlumno}/Modulos/{idModulo}")
	@Produces("application/json")
	public Response getMatriculas(@HeaderParam(HttpHeaders.AUTHORIZATION) String token,
			@PathParam("idAlumno") String idAlumno, @PathParam("idModulo") String idModulo) {

		if (!Authorization.isAuthorized(token)) {
			return Response.status(401).entity("Token inválido").build();
		}

		Matriculas matricula;
		try {
			HibernateSession.getTransaction();
			try {
				matricula = (Matriculas) HibernateSession.session
						.createQuery(
								"from Matriculas where id.idAlumno = " + idAlumno + "AND id.idModulo = " + idModulo)
						.getSingleResult();

				HibernateSession.commitclose();
				Gson foreigngson = new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
						.create();
				return Response.status(200).entity(foreigngson.toJson(matricula)).build();
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

	public Response postMartricula(@HeaderParam(HttpHeaders.AUTHORIZATION) String token, String json) {

		if (!Authorization.isAuthorized(token)) {return Response.status(401).entity("Token inválido").build();}

		try {

			Matriculas matricula = new Gson().fromJson(json, Matriculas.class);

			
			HibernateSession.getTransaction();

			HibernateSession.session.save(matricula);
			HibernateSession.commitclose();
			return Response.status(200).entity(new Gson().toJson(matricula)).build();

		} catch (PersistenceException e) {

			HibernateSession.rollbackclose();
			return Response.status(404).build();
		
		
		} catch (Exception e) {
			HibernateSession.rollbackclose();
			return Response.status(500).entity("El servidor ha encontrado el siguiente error: " + e.toString()).build();

		}

	}

	@DELETE
	@Path("/Alumnos/{idAlumno}/Modulos/{idModulo}")
	@Produces(MediaType.APPLICATION_JSON)

	public Response deleteMatricula(@HeaderParam(HttpHeaders.AUTHORIZATION) String token,
			@PathParam("idAlumno") String idAlumno, @PathParam("idModulo") String idModulo) {

		if (!Authorization.isAuthorized(token)) {
			return Response.status(401).entity("Token inválido").build();
		}

		try {
			HibernateSession.getTransaction();
			int filaseliminadas = HibernateSession.session
					.createQuery("update Matriculas set fecha_baja = now() where id.idAlumno = " + idAlumno
							+ " AND id.idModulo =" + idModulo)
					// .setParameter("id", Integer.parseInt(idAlumno))
					.executeUpdate();
			HibernateSession.commitclose();

			return Response.status(200).entity(new Gson().toJson("Matrículas dadas de baja: " + filaseliminadas))
					.build();
		} catch (Exception e) {
			HibernateSession.rollbackclose();
			return Response.status(500)
					.entity(new Gson().toJson("El servidor ha encontrado el siguiente error: " + e.toString())).build();
		}

	}

}
