package br.unipar.consultorio.repositories;

import br.unipar.consultorio.model.Endereco;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ApiModel(description = "Classe responsável pela comunicação com o Banco de Dados, referente aos Endereços")
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    @Query
    public List<Endereco> findByCepContainingIgnoreCase(String cep);
}
