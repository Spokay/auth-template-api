package com.spokay.authtemplate.configuration.oauth2Server;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.server.authorization.InMemoryOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.InMemoryOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeAuthenticationProvider;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.token.DelegatingOAuth2TokenGenerator;
import org.springframework.security.oauth2.server.authorization.token.OAuth2AccessTokenGenerator;
import org.springframework.security.oauth2.server.authorization.token.OAuth2RefreshTokenGenerator;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;


@Profile("oauth2-auth-server")
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class OAuth2ServerConfiguration {

    @Value("${oauth2.client.app.client-id}")
    String clientId;

    @Value("${oauth2.client.app.client-secret}")
    String clientSecret;

    public final PasswordEncoder passwordEncoder;

    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer =
				new OAuth2AuthorizationServerConfigurer();
		RequestMatcher endpointsMatcher = authorizationServerConfigurer
				.getEndpointsMatcher();

        http
            .authorizeHttpRequests(requestMatcher -> requestMatcher
                    .requestMatchers(HttpMethod.OPTIONS).permitAll()
                    .requestMatchers("/oauth2/authorize/**").permitAll()
                    .requestMatchers("/oauth2/token/**").permitAll()
                    .anyRequest().authenticated()
            )
            .csrf(
                    csrf -> csrf.ignoringRequestMatchers(endpointsMatcher)
            );

        return http.build();
    }

	/*@Bean
    public static JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
		Set<JWSAlgorithm> jwsAlgs = new HashSet<>();
		jwsAlgs.addAll(JWSAlgorithm.Family.RSA);
		jwsAlgs.addAll(JWSAlgorithm.Family.EC);
		jwsAlgs.addAll(JWSAlgorithm.Family.HMAC_SHA);
		ConfigurableJWTProcessor<SecurityContext> jwtProcessor = new DefaultJWTProcessor<>();
		JWSKeySelector<SecurityContext> jwsKeySelector =
				new JWSVerificationKeySelector<>(jwsAlgs, jwkSource);
		jwtProcessor.setJWSKeySelector(jwsKeySelector);
		// Override the default Nimbus claims set verifier as NimbusJwtDecoder handles it instead
		jwtProcessor.setJWTClaimsSetVerifier((claims, context) -> {
		});
		return new NimbusJwtDecoder(jwtProcessor);
	}*/

    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        RSAKey rsaKey = generateRsa();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }

    public static RSAKey generateRsa() {
        KeyPair keyPair = generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        return new RSAKey.Builder(publicKey).privateKey(privateKey).keyID(UUID.randomUUID().toString()).build();
    }

    static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new OAuth2AuthorizationCodeAuthenticationProvider(
                authorizationService(),
                oauth2TokenGenerator()
        );
    }

    @Bean
    public OAuth2AuthorizationService authorizationService() {
        return new InMemoryOAuth2AuthorizationService();
    }

    @Bean
    public OAuth2TokenGenerator<OAuth2Token> oauth2TokenGenerator() {
        return new DelegatingOAuth2TokenGenerator(
                new OAuth2AccessTokenGenerator(),
                new OAuth2RefreshTokenGenerator()
        );
    }

    @Bean
    public OAuth2AuthorizationConsentService authorizationConsentService() {
        return new InMemoryOAuth2AuthorizationConsentService();
    }

}