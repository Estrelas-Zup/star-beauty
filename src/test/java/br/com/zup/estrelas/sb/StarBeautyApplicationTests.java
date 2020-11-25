package br.com.zup.estrelas.sb;

import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import br.com.zup.estrelas.sb.entity.Cliente;
import br.com.zup.estrelas.sb.repository.ClienteRepository;

@SpringBootTest
class StarBeautyApplicationTests {

    @Autowired
    ClienteRepository clienteRepository;
            
    
	@Test
    void testeCliente() {

        Cliente cliente = new Cliente();

        cliente.setLogin("xpto@mail.com");
        cliente.setSenha("abcd");
        cliente.setCpf("11111111");
        cliente.setDataNascimento(LocalDate.now());

        clienteRepository.save(cliente);
        
        Optional<Cliente> clienteOp = clienteRepository.findById(1L);
        
        
        System.out.println("Stop");
    }

}
