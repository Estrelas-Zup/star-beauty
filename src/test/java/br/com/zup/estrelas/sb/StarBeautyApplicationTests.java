package br.com.zup.estrelas.sb;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import br.com.zup.estrelas.sb.repository.AgendamentoRepository;

@SpringBootTest
class StarBeautyApplicationTests {


    @Autowired
    AgendamentoRepository agRepo;

    @Test
    public void insertAgendamento() {

        boolean testeEntreDataInicial = agRepo.existsByFuncionarioAgenda(1L,
                LocalDateTime.of(2020, 12, 14, 14, 00), LocalDateTime.of(2020, 12, 14, 14, 30));
        boolean testeEntreDataFinal = agRepo.existsByFuncionarioAgenda(1L,
                LocalDateTime.of(2020, 12, 14, 14, 15), LocalDateTime.of(2020, 12, 14, 15, 15));
        boolean testeEntreAsDuasDatas = agRepo.existsByFuncionarioAgenda(1L,
                LocalDateTime.of(2020, 12, 14, 14, 15), LocalDateTime.of(2020, 12, 14, 14, 45));
        boolean testeHorarioCorreto = agRepo.existsByFuncionarioAgenda(1L,
                LocalDateTime.of(2020, 12, 14, 15, 01), LocalDateTime.of(2020, 12, 14, 16, 00));

        System.out.println("Teste 1: " + testeEntreDataInicial);
        System.out.println("Teste 2: " + testeEntreDataFinal);
        System.out.println("Teste 3: " + testeEntreAsDuasDatas);
        System.out.println("Teste 4: " + testeHorarioCorreto);

        boolean testeEntreDataInicialAutonomo = agRepo.existsByAutonomoAgenda(3L,
                LocalDateTime.of(2020, 12, 14, 16, 00), LocalDateTime.of(2020, 12, 14, 17, 30));
        boolean testeEntreDataFinalAutonomo = agRepo.existsByAutonomoAgenda(3L,
                LocalDateTime.of(2020, 12, 14, 17, 15), LocalDateTime.of(2020, 12, 14, 18, 15));
        boolean testeEntreAsDuasDatasAutonomo = agRepo.existsByAutonomoAgenda(3L,
                LocalDateTime.of(2020, 12, 14, 17, 15), LocalDateTime.of(2020, 12, 14, 17, 45));
        boolean testeHorarioCorretoAutonomo = agRepo.existsByFuncionarioAgenda(1L,
                LocalDateTime.of(2020, 12, 14, 18, 00, 01), LocalDateTime.of(2020, 12, 14, 19, 00));

        System.out.println("Teste 1: " + testeEntreDataInicialAutonomo);
        System.out.println("Teste 2: " + testeEntreDataFinalAutonomo);
        System.out.println("Teste 3: " + testeEntreAsDuasDatasAutonomo);
        System.out.println("Teste 4: " + testeHorarioCorretoAutonomo);
    }

}
