package br.com.sgv.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Supervisor extends Funcionario{
    @Size(min=1,message = "O CFG precisa ser válido.")
    private String cnpj;

     @Override
    public double comissao(double valorVenda) {
        return valorVenda * 0.50; 
        
        
    }
    
}
