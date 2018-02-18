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

import domain.Ciclos;
import utils.Authorization;
import utils.HibernateSession;

@Path("/Ciclos")
public class CiclosREST {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCiclos(@HeaderParam(HttpHeaders.AUTHORIZATION) String token) {

		if (!Authorization.isAuthorized(token)) { return Response.status(401).entity("Token inválido").build(); }

		try {
			HibernateSession.getTransaction();
			@SuppressWarnings("unchecked")
			List<Ciclos> listaciclos = HibernateSession.session.createQuery("from Ciclos").list();
			HibernateSession.commitclose();

			return Response.status(200).entity(new Gson().toJson(listaciclos)).build();

		} catch (Exception e) {
			HibernateSession.commitclose();
			return Response.status(500).entity("El servidor ha encontrado el siguiente error: " + e.toString()).build();
		}

	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)

	public Response getCiclo(@HeaderParam(HttpHeaders.AUTHORIZATION) String token, @PathParam("id") String idCiclo) {

		if (!Authorization.isAuthorized(token)) { return Response.status(401).entity("Token inválido").build(); }
		
		Ciclos ciclo;
		try {
			HibernateSession.getTransaction();
			try {
				ciclo = (Ciclos) HibernateSession.session.createQuery("from Ciclos where idCiclo = " + idCiclo)
						.getSingleResult();

				HibernateSession.commitclose();
				return Response.status(200).entity(new Gson().toJson(ciclo)).build();
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

	public Response postCiclo(@HeaderParam(HttpHeaders.AUTHORIZATION) String token, String json) {
		
		if (!Authorization.isAuthorized(token)) { return Response.status(401).entity("Token inválido").build(); }

		try {
			Ciclos ciclo = new Gson().fromJson(json, Ciclos.class);

			HibernateSession.getTransaction();

			HibernateSession.session.save(ciclo);

			HibernateSession.commitclose();
			return Response.status(200).entity(new Gson().toJson(ciclo)).build();

		} catch (Exception e) {
			HibernateSession.commitclose();
			return Response.status(500).entity("El servidor ha encontrado el siguiente error: " + e.toString()).build();
		}

	}

	@Path("/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response putCiclo(@HeaderParam(HttpHeaders.AUTHORIZATION) String token, @PathParam("id") String idCiclo, String json) {
	
		if (!Authorization.isAuthorized(token)) { return Response.status(401).entity("Token inválido").build(); }
		
		try {
			Ciclos ciclo = new Gson().fromJson(json, Ciclos.class);

			HibernateSession.getTransaction();
			int filasactualizadas = HibernateSession.session
					.createQuery("update Ciclos c set c.nombre = '" + ciclo.getNombre() + "', c.siglas = '"
							+ ciclo.getSiglas() + "' where c.idCiclo = " + idCiclo)
					// .setParameter("newName", c.getNombre()).setParameter("newSiglas",
					// c.getSiglas())
					// .setParameter("oldName", Integer.parseInt(idCiclo))
					.executeUpdate();

			HibernateSession.commitclose();
			if (filasactualizadas == 0) {
				return Response.status(404).entity("No se ha encontrado la entidad. No se ha actualizado ninguna fila.")
						.build();
			}

			return Response.status(200).entity(new Gson().toJson(ciclo)).build();
		} catch (Exception e) {
			HibernateSession.commitclose();
			return Response.status(500).entity("El servidor ha encontrado el siguiente error: " + e.toString()).build();
		}

	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)

	public Response deleteCiclo(@HeaderParam(HttpHeaders.AUTHORIZATION) String token, @PathParam("id") String idCiclo) {
		
		
		if (!Authorization.isAuthorized(token)) { return Response.status(401).entity("Token inválido").build(); }

		HibernateSession.getTransaction();
		try {
			int filaseliminadas = HibernateSession.session.createQuery("delete Ciclos c where c.idCiclo = " + idCiclo)
					// .setParameter("id", Integer.parseInt(idCiclo))
					.executeUpdate();
			HibernateSession.commitclose();

			return Response.status(200).entity(new Gson().toJson("Filas eliminadas: " + filaseliminadas)).build();
		} catch (ConstraintViolationException e) {
			HibernateSession.commitclose();

			return Response.status(400).entity(new Gson().toJson("Debe eliminar en cascada.")).build();
		} catch (Exception e) {
			HibernateSession.commitclose();
			return Response.status(500).entity("El servidor ha encontrado el siguiente error: " + e.toString()).build();
		}

	}

}
