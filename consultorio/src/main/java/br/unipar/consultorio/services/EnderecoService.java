package br.unipar.consultorio.services;

import br.unipar.consultorio.model.Endereco;
import br.unipar.consultorio.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco insert(Endereco endereco) throws Exception{
        validaInsert(endereco);
        enderecoRepository.saveAndFlush(endereco);
        return endereco;
    }

    public Endereco update(Endereco endereco) throws Exception {
        validaUpdate(endereco);
        enderecoRepository.saveAndFlush(endereco);
        return endereco;
    }

    public Endereco findById(Long id) throws Exception{
        Optional<Endereco> retorno = enderecoRepository.findById(id);
        if (retorno.isPresent()){
            return retorno.get();
        }
        else {
            throw new Exception("Endereço " + id + " não encontrado");
        }
    }

    public List<Endereco> findByFilters(String cep) throws Exception{
        return enderecoRepository.findByCepContainingIgnoreCase(cep);
    }

    public List<Endereco> findAll() throws Exception{
         return enderecoRepository.findAll();
    }

    private void validaInsert(Endereco endereco) throws Exception{
        if (endereco.getId() != null){
            throw new Exception("Não é necessário informar o ID para inserir um novo Endereço");
        }
    }

    public void validaUpdate(Endereco endereco) throws Exception{
        if (endereco.getId() == null){
            throw new Exception("É necessário informar o ID para atualizar o cadastro do Endereço");
        }
    }

}
