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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import domain.Modulos;
import utils.Authorization;
import utils.HibernateProxyTypeAdapter;
import utils.HibernateSession;

@Path("/Modulos")
public class ModulosREST {


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getModulos(@HeaderParam(HttpHeaders.AUTHORIZATION) String token) {
		
		if (!Authorization.isAuthorized(token)) { return Response.status(401).entity("Token inválido").build(); }

		try {
			HibernateSession.getTransaction();
			@SuppressWarnings("unchecked")
			List<Modulos> listamodulos = HibernateSession.session.createQuery("from Modulos").list();
			HibernateSession.commitclose();

			Gson foreigngson = new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create();
			return Response.status(200).entity(foreigngson.toJson(listamodulos)).build();

		} catch (Exception e) {
			HibernateSession.rollbackclose();
			return Response.status(500).entity("El servidor ha encontrado el siguiente error: " + e.toString()).build();
		}
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)

	public Response getModulo(@HeaderParam(HttpHeaders.AUTHORIZATION) String token, @PathParam("id") String idModulo) {
		
		if (!Authorization.isAuthorized(token)) { return Response.status(401).entity("Token inválido").build(); }

		Modulos modulo;
		try {
			HibernateSession.getTransaction();
			try {
				modulo = (Modulos) HibernateSession.session.createQuery("from Modulos where idModulo = " + idModulo).getSingleResult();
			
				HibernateSession.commitclose();

				Gson foreigngson = new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create();
				return Response.status(200).entity(foreigngson.toJson(modulo)).build();
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

	public Response postModulo(@HeaderParam(HttpHeaders.AUTHORIZATION) String token, String json) {

		
		if (!Authorization.isAuthorized(token)) { return Response.status(401).entity("Token inválido").build(); }

		try {
			Modulos modulo = new Gson().fromJson(json, Modulos.class);

			HibernateSession.getTransaction();

			HibernateSession.session.save(modulo);

			HibernateSession.commitclose();
			return Response.status(200).entity(new Gson().toJson(modulo)).build();

		} catch (Exception e) {
			HibernateSession.rollbackclose();
			return Response.status(500).entity("El servidor ha encontrado el siguiente error: " + e.toString()).build();
		}

	}

	@Path("/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response putModulo(@HeaderParam(HttpHeaders.AUTHORIZATION) String token, @PathParam("id") String idModulo, String json) {

		if (!Authorization.isAuthorized(token)) { return Response.status(401).entity("Token inválido").build(); }

		try {
			Modulos modulo = new Gson().fromJson(json, Modulos.class);

			HibernateSession.getTransaction();
			int filasactualizadas = HibernateSession.session
					.createQuery("update Modulos m set m.nombre = '" + modulo.getNombre() + "', m.siglas = '"
							+ modulo.getSiglas() + "' where m.idModulo = " + idModulo)
					// .setParameter("newName", curso.getNombre()).setParameter("newSiglas",
					// curso.getSiglas())
					// .setParameter("oldName", Integer.parseInt(idCurso)) EDITAR PARA MÓDULOS
					.executeUpdate();

			HibernateSession.commitclose();
			if (filasactualizadas == 0) {
				return Response.status(404).entity("No se ha encontrado la entidad. No se ha actualizado ninguna fila.")
						.build();
			}

			return Response.status(200).entity(new Gson().toJson(modulo)).build();
		} catch (Exception e) {
			HibernateSession.rollbackclose();
			return Response.status(500).entity("El servidor ha encontrado el siguiente error: " + e.toString()).build();
		}

	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)

	public Response deleteModulo(@HeaderParam(HttpHeaders.AUTHORIZATION) String token, @PathParam("id") String idModulo) {
		
		if (!Authorization.isAuthorized(token)) { return Response.status(401).entity("Token inválido").build(); }

		try {
			HibernateSession.getTransaction();
			int filaseliminadas = HibernateSession.session.createQuery("delete Modulos m where m.idModulo = " + idModulo)
					// .setParameter("id", Integer.parseInt(idCurso))
					.executeUpdate();
			HibernateSession.commitclose();
			return Response.status(200).entity(new Gson().toJson("Filas eliminadas: " + filaseliminadas)).build();

		} catch (Exception e) {
			HibernateSession.rollbackclose();
			return Response.status(500)
					.entity(new Gson().toJson("El servidor ha encontrado el siguiente error: " + e.toString())).build();
		}

	}

}
