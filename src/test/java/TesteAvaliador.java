
import br.com.victor.leilao.dominio.Lance;
import br.com.victor.leilao.dominio.Leilao;
import br.com.victor.leilao.dominio.Usuario;
import br.com.victor.leilao.servico.Avaliador;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;





public class TesteAvaliador {

    @Test
    public void testDeveEntenderLancesEmOrdemCrescente() {
        
        //cenario
        Usuario joao = new Usuario("joao");
        Usuario jose = new Usuario("jose");
        Usuario maria = new Usuario("maria");
        
        Leilao leilao = new Leilao("ps3");
        
        leilao.propoe(new Lance(joao, 300.0));
        leilao.propoe(new Lance(jose, 400.0));
        leilao.propoe(new Lance(maria, 250.0));
        
        //ação
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);
        
        //validacao
        Double maiorEsperado = 400.0;
        Double menorEsperado = 250.0;
        
        assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
        assertEquals(menorEsperado, leiloeiro.getMenorDeTodos(),0.00001);
    }
    
    @Test
    public void deveEntenderLeilaoComApenasUmLance() {
        //cenario
        Usuario joao = new Usuario("joao");
        
        Leilao leilao = new Leilao("ps3");
        
        leilao.propoe(new Lance(joao, 1000.0));
        
        //ação
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);
        
        //validacao
        assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.00001);
        assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.00001);
    }
    
    @Test
    public void deveEncontrarOsTresMaioresLances(){
        Usuario joao = new Usuario("joao");
        Usuario maria = new Usuario("maria");
        
        Leilao leilao = new Leilao("ps3");
        
        leilao.propoe(new Lance(joao, 100.0));
        leilao.propoe(new Lance(maria, 200.0));
        leilao.propoe(new Lance(joao, 300.0));
        leilao.propoe(new Lance(maria, 400.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);
        
        List<Lance> maiores = leiloeiro.getTresMaiores();
        assertEquals(3, maiores.size());
        assertEquals(400.0, maiores.get(0).getValor(), 0.00001);
        assertEquals(300.0, maiores.get(1).getValor(), 0.00001);
        assertEquals(200.0, maiores.get(2).getValor(), 0.00001);
    }
}
