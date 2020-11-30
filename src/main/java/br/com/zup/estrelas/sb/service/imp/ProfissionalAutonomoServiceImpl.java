package br.com.zup.estrelas.sb.service.imp;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.sb.dto.InativaProfissionalAutonomoDTO;
import br.com.zup.estrelas.sb.dto.MensagemDTO;
import br.com.zup.estrelas.sb.dto.ProfissionalAutonomoDTO;
import br.com.zup.estrelas.sb.entity.ProfissionalAutonomo;
import br.com.zup.estrelas.sb.repository.ProfissionalAutonomoRepository;
import br.com.zup.estrelas.sb.service.ProfissionalAutonomoService;

@Service
public class ProfissionalAutonomoServiceImpl implements ProfissionalAutonomoService {

    @Autowired
    ProfissionalAutonomoRepository profissionalAutonomoRepository;

    @Override
    public ProfissionalAutonomo buscaProfissionalAutonomo(Long idUsuario) {
        return profissionalAutonomoRepository.findById(idUsuario).orElse(null);
    }

    @Override
    public List<ProfissionalAutonomo> listaProfissionaisAutonomos() {
        return (List<ProfissionalAutonomo>) profissionalAutonomoRepository.findAll();
    }

    @Override
    public MensagemDTO adicionaProfissionalAutonomo(
            ProfissionalAutonomoDTO profissionalAutonomoDTO) {
        if (profissionalAutonomoRepository.existsByCpfCnpj(profissionalAutonomoDTO.getCpfCnpj())) {
            return new MensagemDTO("ESTE PROFISSIONAL JÁ ESTÁ CADASTRADO!");
        }

        return this.adicionaAutonomo(profissionalAutonomoDTO);
    }

    @Override
    public MensagemDTO alteraProfissionalAutonomo(Long idUsuario,
            ProfissionalAutonomoDTO profissionalAutonomoDTO) {
        if (!profissionalAutonomoRepository.existsById(idUsuario)) {
            return new MensagemDTO("O PROFISSIONAL EM QUESTÃO NÃO FOI ENCONTRADO!");
        }

        ProfissionalAutonomo profissionalAutonomo =
                profissionalAutonomoRepository.findById(idUsuario).get();

        if (profissionalAutonomo.getCpfCnpj() != profissionalAutonomoDTO.getCpfCnpj()) {
            return new MensagemDTO("CPF JÁ CADASTRADO NO BANCO DE DADOS!");
        }

        return this.alteraInformacoesAutonomo(profissionalAutonomo, profissionalAutonomoDTO);
    }

    @Override
    public MensagemDTO inativaProfissionalAutonomo(Long idUsuario,
            InativaProfissionalAutonomoDTO inativaProfissionalAutonomoDTO) {
        if (!profissionalAutonomoRepository.existsById(idUsuario)) {
            return new MensagemDTO("O PROFISSIONAL EM QUESTÃO NÃO FOI ENCONTRADO!");
        }

        return this.inativaAutonomo(idUsuario, inativaProfissionalAutonomoDTO);
    }

    private MensagemDTO adicionaAutonomo(ProfissionalAutonomoDTO profissionalAutonomoDTO) {

        ProfissionalAutonomo profissionalAutonomo = new ProfissionalAutonomo();

        BeanUtils.copyProperties(profissionalAutonomoDTO, profissionalAutonomo);
        profissionalAutonomo.setAgendamento(Collections.emptyList());
        profissionalAutonomo.setFormasPagamentos(Collections.emptyList());
        profissionalAutonomo.setServico(Collections.emptyList());

        profissionalAutonomoRepository.save(profissionalAutonomo);

        return new MensagemDTO("PROFISSIONAL AUTONOMO ADICIONADO COM SUCESSO!");
    }

    private MensagemDTO alteraInformacoesAutonomo(ProfissionalAutonomo profissionalAutonomo,
            ProfissionalAutonomoDTO profissionalAutonomoDTO) {

        BeanUtils.copyProperties(profissionalAutonomoDTO, profissionalAutonomo);

        profissionalAutonomoRepository.save(profissionalAutonomo);

        return new MensagemDTO("ALTERADO COM SUCESSO!");
    }

    private MensagemDTO inativaAutonomo(Long idUsuario,
            InativaProfissionalAutonomoDTO inativaProfissionalAutonomoDTO) {

        ProfissionalAutonomo profissionalAutonomo =
                profissionalAutonomoRepository.findById(idUsuario).get();

        BeanUtils.copyProperties(inativaProfissionalAutonomoDTO, profissionalAutonomo);

        profissionalAutonomoRepository.save(profissionalAutonomo);

        return new MensagemDTO("INATIVADO COM SUCESSO!");
    }

}
