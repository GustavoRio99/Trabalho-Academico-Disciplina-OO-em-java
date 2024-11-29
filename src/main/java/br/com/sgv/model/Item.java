package br.com.sgv.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    public Produto produto;
    @OneToOne
    private Venda venda;
    private int quantidade;

    public Item(){
        produto = new Produto();
        quantidade = 1;
    }
    
    @Override
    public String toString(){
        return produto.getNome() + " " + quantidade;
    }
    public int getQuantidade(){
    
        return quantidade;
    }
    
    public int getPreco(){
    
        return 0;
    }

    public Produto getProduto() {
        return produto;
    }


    public void setVenda(Venda venda) {
    if (venda == null) {
        throw new IllegalArgumentException("Venda não pode ser nula");
    }
    this.venda = venda;
   }

    public long getId() {
        return id;
    }
}
