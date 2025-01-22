package com.KoreaItAcdemy.OurNotion.oauth;

import org.springframework.http.RequestEntity;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class CustomOAuth2TokenResponseClient extends DefaultAuthorizationCodeTokenResponseClient {

    @Override
    protected RequestEntity<?> createTokenRequest(OAuth2AuthorizationCodeGrantRequest authorizationGrantRequest) {
        RequestEntity<?> requestEntity = super.createTokenRequest(authorizationGrantRequest);

        // MultiValueMap을 수정하여 client_id, client_secret을 body에 포함 (post 방식 강제)
        MultiValueMap<String, String> formParameters = new LinkedMultiValueMap<>();
        formParameters.add(OAuth2ParameterNames.CLIENT_ID, authorizationGrantRequest.getClientRegistration().getClientId());
        formParameters.add(OAuth2ParameterNames.CLIENT_SECRET, authorizationGrantRequest.getClientRegistration().getClientSecret());
        formParameters.add(OAuth2ParameterNames.CODE, authorizationGrantRequest.getAuthorizationExchange().getAuthorizationResponse().getCode());
        formParameters.add(OAuth2ParameterNames.GRANT_TYPE, authorizationGrantRequest.getClientRegistration().getAuthorizationGrantType().getValue());
        formParameters.add(OAuth2ParameterNames.REDIRECT_URI, authorizationGrantRequest.getAuthorizationExchange().getAuthorizationRequest().getRedirectUri());

        return RequestEntity
                .post(requestEntity.getUrl())
                .headers(requestEntity.getHeaders())
                .body(formParameters);
    }
}
