package br.com.zup.estrelas.sb.dto;

import java.io.Serializable;

public class TokenDTO implements Serializable {

    private static final long serialVersionUID = -5015060350255392782L;

    private final String jwtToken;

    public TokenDTO(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

}
