package br.com.sgv.controller;

import br.com.sgv.model.Item;
import br.com.sgv.model.Venda;
import br.com.sgv.repository.ProdutoRepository;
import br.com.sgv.repository.VendaRepository;
import jakarta.validation.Valid;
import java.util.Iterator;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Pablo Rangel <pablo.rangel@gmail.com>
 * @date 22/04/2021
 * @brief class VendaController
 */
@Controller
public class VendaController {

    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    private Venda venda;

    @GetMapping("/vendas")
    public String listarVendas(Model model) {
        model.addAttribute("listaVendas", vendaRepository.findAll());
        return "gerenciar_vendas";
    }

    @GetMapping("/vendas/novo")
    public String novo(Model model) {
        venda = new Venda();
        vendaRepository.save(venda);
        model.addAttribute("listaProdutos", produtoRepository.findAll());
        model.addAttribute("venda", venda);
        model.addAttribute("item", new Item());
        return "editar_venda";
    }

    @GetMapping("/vendas/{id}")
    public String editar(@PathVariable("id") long idVenda, Model model) {
        Optional<Venda> busca = vendaRepository.findById(idVenda);
        venda = busca.get();
        model.addAttribute("venda", venda);
        model.addAttribute("item", new Item());
        model.addAttribute("listaProdutos", produtoRepository.findAll());
        return "editar_venda";
    }

    @PostMapping("/vendas")
    public String salvar(@Valid Venda venda, BindingResult result) {
        if (result.hasErrors()) {
            return "editar_venda";
        }
        this.venda.setDataVenda(venda.getDataVenda());
        vendaRepository.save(this.venda);
        return "redirect:/vendas";
    }
    
   @PostMapping("/vendas/itens")
public String adicionarItem(@Valid Item item, Model model, BindingResult result) {
    if (result.hasErrors()) {
        return "editar_venda";
    }
    if (item.getProduto() != null && venda != null) {
        venda.adicionarItem(item);
        item.setVenda(venda);  // Associando o item à venda
        vendaRepository.save(venda);  // Salvando a venda com o item adicionado
    } else {
        // Caso venda seja null, você pode criar ou buscar a venda, por exemplo
        // Exemplo: Se for necessário criar uma nova venda
        venda = new Venda();
        venda.adicionarItem(item);
        item.setVenda(venda);
        vendaRepository.save(venda);  // Salvando a nova venda
    }
    return "redirect:/vendas/" + venda.getId();  // Redirecionando para a página da venda
}

    @GetMapping("/vendas/itens/{id}")
public String removerItem(@PathVariable("id") long id) {
    Optional<Venda> vendaOptional = vendaRepository.findById(id);
    
    // Verificar se a venda foi encontrada no banco de dados
    if (vendaOptional.isPresent()) {
        Venda venda = vendaOptional.get();

        // Iterar pela lista de itens da venda e buscar o item com o id correspondente
        Item itemToRemove = null;
        for (Item item : venda.getListaItens()) {
            if (item.getId() == id) {
                itemToRemove = item;
                break;
            }
        }

        // Se o item foi encontrado, removê-lo da venda
        if (itemToRemove != null) {
            venda.removerItem(itemToRemove);  // Remove o item da venda
            itemToRemove.setVenda(null);  // Desassocia o item da venda
            vendaRepository.save(venda);  // Atualiza a venda no banco de dados
        }
    }

    // Redireciona para a página de edição da venda
    return "redirect:/vendas/" + id;
}

    
    @DeleteMapping("/vendas/{id}")
    public String excluir(@PathVariable("id") long id) {
        vendaRepository.deleteById(id);
        return "redirect:/vendas";
    }
}
