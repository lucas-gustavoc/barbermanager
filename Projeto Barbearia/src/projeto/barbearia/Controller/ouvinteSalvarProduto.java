
package projeto.barbearia.Controller;

import projeto.barbearia.Model.Produto;
import projeto.barbearia.Model.Tipos.TipoCadastro;

/**
 *
 * @author lucas
 */
public class ouvinteSalvarProduto {
    
    public static boolean salvar(String nome, String marca, int qtd, float custo, 
            float valor, int estoqueMinimo, int fornecedorid, 
            TipoCadastro tipo, int produtoid) {
        
        boolean retorno;
        
        if (tipo == TipoCadastro.NOVO_CADASTRO) {
            Produto p = new Produto();
            p.setNome_bd(nome.toUpperCase());
            p.setMarca_bd(marca.toUpperCase());
            p.setQtd_bd(qtd);
            p.setCusto_bd(custo);
            p.setValor_bd(valor);
            p.setEstoqueMinimo_bd(estoqueMinimo);
            p.setFornecedorid_bd(fornecedorid);
            retorno = p.cadastrar();
        } else {
            retorno = Produto.editar(produtoid, nome, marca, custo, valor, qtd, 
                    estoqueMinimo, fornecedorid);
        }
        
        return retorno;
        
    }
    
}
