package com.MyApp.FaceBookData;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.FacebookApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

public class Oauthtest {
	
	
	public static void main(String[] args) {
		OAuthService service = new ServiceBuilder()
	    .provider(FacebookApi.class)
	    .apiKey("1389244341382506")
	    .apiSecret("bebc9b0953ef853322469bec36352b20")
	    .build();
		Token requestToken = service.getRequestToken();
		String authUrl = service.getAuthorizationUrl(requestToken);
		Verifier v = new Verifier("verifier you got from the user");
		Token accessToken = service.getAccessToken(requestToken, v); // the requestToken you had from step 2
		OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.twitter.com/1/account/verify_credentials.xml");
		service.signRequest(accessToken, request); // the access token from step 4
		Response response = request.send();
		System.out.println(response.getBody());
		System.out.println();
		System.out.println("11111");
	}

}
