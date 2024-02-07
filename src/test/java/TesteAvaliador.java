
import br.com.victor.leilao.dominio.Lance;
import br.com.victor.leilao.dominio.Leilao;
import br.com.victor.leilao.dominio.Usuario;
import br.com.victor.leilao.servico.Avaliador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



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

        Assertions.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
        Assertions.assertEquals(menorEsperado, leiloeiro.getMenorDeTodos(),0.00001);
    }
}
