package br.com.zup.estrelas.sb.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import br.com.zup.estrelas.sb.entity.Agendamento;
import br.com.zup.estrelas.sb.repository.AgendamentoRepository;

public class AgendamentoServiceTests {
    
    @Mock
    AgendamentoRepository agendamentoRepository;
    
    @InjectMocks
    AgendamentoService agegendamentoService;
    
    @Test
    public void naoDeveRemoverUmAgendamento() {
        
        Agendamento agendamento = new Agendamento();
        
        //Mockito.when(agendamentoRepository.findById(1L)).thenReturn(false);
        
    }

}
