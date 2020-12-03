package br.com.zup.estrelas.sb.exceptions;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

public class SbErrorResponse {

    String descricaoErro;
    int statusHttp;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    LocalDateTime timeStamp;

    public SbErrorResponse() {

    }

    public SbErrorResponse(String descricaoErro, int statusHttp, LocalDateTime timeStamp) {

        this.descricaoErro = descricaoErro;
        this.statusHttp = statusHttp;
        this.timeStamp = timeStamp;
    }

    public String getCodigoErro() {
        return descricaoErro;
    }

    public void setCodigoErro(String descricaoErro) {
        this.descricaoErro = descricaoErro;
    }


    public int getStatusHttp() {
        return statusHttp;
    }

    public void setStatusHttp(int statusHttp) {
        this.statusHttp = statusHttp;
    }

    public LocalDateTime getTimestamp() {
        return timeStamp;
    }

    public void setTimestamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }


}
