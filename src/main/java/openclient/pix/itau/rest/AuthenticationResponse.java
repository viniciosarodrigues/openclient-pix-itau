package openclient.pix.itau.rest;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 892520697471199461L;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private Integer expiresIn;

    private String scope;

    private String jti;

    public AuthenticationResponse() {
        super();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AuthenticationResponse [accessToken=");
        builder.append(accessToken);
        builder.append(", refreshToken=");
        builder.append(refreshToken);
        builder.append(", tokenType=");
        builder.append(tokenType);
        builder.append(", expiresIn=");
        builder.append(expiresIn);
        builder.append(", scope=");
        builder.append(scope);
        builder.append(", jti=");
        builder.append(jti);
        builder.append("]");
        return builder.toString();
    }

}
