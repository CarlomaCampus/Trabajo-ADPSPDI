package api;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.gson.Gson;

import domain.Usuarios;
import utils.Authorization;
import utils.HibernateSession;

@Path("/Login")
public class LoginRest {



	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(String json) {
		
		
		try {
			HibernateSession.getTransaction();
			Gson gson = new Gson();
			Usuarios usuario = gson.fromJson(json, Usuarios.class);
			//no solo comprobamos si hay un usuario con esa contraseña sino que le ponemos un id al objeto usuario
			usuario = (Usuarios) HibernateSession.session.createQuery("from Usuarios where username = '" + usuario.getUsername() + "' AND password = '"+ usuario.getPassword()+"'").getSingleResult();
			usuario.setToken(generateToken(usuario.getIdUsuario()));
			HibernateSession.commitclose();
			usuario.setPassword("OCULTA");
			return Response.status(200).entity(new Gson().toJson(usuario)).build();
		} catch (NoResultException e) {
			HibernateSession.commitclose();
			return Response.status(401).build();

		} catch (Exception e) {
			HibernateSession.rollbackclose();
			return Response.status(500).entity(new Gson().toJson("Error al iniciar sesión: " + e.toString())).build();
		}
	}

	private String generateToken(int id) throws Exception {
		
		// Se preparan y formatean las fechas
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date issuedDate = format.parse(LocalDate.now().toString());
		Date expirationDate = format.parse(LocalDate.now().plusMonths(1).toString());

		// Se define el algoritmo con la clave elegida
		Algorithm algorithm = Algorithm.HMAC256(Authorization.TOKEN_KEY);

		// Se crea el token
		String token = JWT.create().withSubject(String.valueOf(id)).withIssuedAt(issuedDate)
				.withExpiresAt(expirationDate).sign(algorithm);
		return token;
	}
}
