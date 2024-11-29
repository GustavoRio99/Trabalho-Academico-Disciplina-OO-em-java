package br.com.sgv.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
public class Venda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> listaItens = new ArrayList<>();

    private Date dataVenda = new Date();

    // Retorna a data formatada como dd/MM/yyyy
    public String getDataVendaFormatada() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dataVenda);
    }

    // Retorna a data no formato padrão yyyy-MM-dd
    public String getDataVenda() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        return formato.format(dataVenda);
    }

    // Define a data da venda a partir de uma string no formato yyyy-MM-dd
    public void setDataVenda(String dataVenda) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.dataVenda = formato.parse(dataVenda);
        } catch (ParseException ex) {
            Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, "Erro ao formatar a data", ex);
            this.dataVenda = new Date(); // Define a data atual como padrão em caso de erro
        }
    }

    // Adiciona um item à lista de itens da venda
    public void adicionarItem(Item item) {
        listaItens.add(item);
    }

    // Remove um item da lista de itens da venda
    public void removerItem(Item item) {
        listaItens.remove(item);
    }

    // Calcula o total da venda somando o preço de cada item
    public Double calcularTotal() {
        double total = 0;
        if (listaItens != null) {
            for (Item item : listaItens) {
                if (item != null && item.getProduto() != null) {
                    total += item.getProduto().getPreco() * item.getQuantidade();
                }
            }
        }
        return total;
    }

    // Retorna o id da venda
    public Long getId() {
        return this.id;
    }

    public Iterable<Item> getListaItens() {
        return listaItens;
    }
}

