package br.com.zup.estrelas.sb.exceptions;

public class NotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    public NotFoundException(String msg) {
        
        super(msg);
    }
}
