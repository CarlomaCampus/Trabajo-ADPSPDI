package utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

public class Authorization {
	private static final String AUTHENTICATION_SCHEME = "bearer";
	public static final String TOKEN_KEY = "-PR0yEcTO_@dPsPDi-";
	
	public static boolean isAuthorized(String token) {
		try {
			
			if (!isTokenBasedAuthentication(token)) {
				return false;
			} else {
				token = token.substring(AUTHENTICATION_SCHEME.length()).trim();
				validateToken(token);
				return true;
			}
		} catch (Exception exception) {
			return false;
		}
	}

	
	private static boolean isTokenBasedAuthentication(String token) {
		return token != null && token.toLowerCase().
				startsWith(AUTHENTICATION_SCHEME + " ");
	}

	private static void validateToken(String token) throws Exception {
		Algorithm algorithm = Algorithm.HMAC256(TOKEN_KEY);
		JWTVerifier verifier = JWT.require(algorithm).build();
		verifier.verify(token);
		
	}
}
