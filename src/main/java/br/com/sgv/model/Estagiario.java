/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sgv.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Estagiario extends Funcionario{
    @Size(min=1,message = "O CPF precisa ser válido.")
    private String cpf;
    @Override
    public double comissao(double valorVenda) {
        
        return valorVenda * 0.10; 
       
    }

}
