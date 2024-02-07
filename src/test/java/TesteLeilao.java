import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.victor.leilao.dominio.Lance;
import br.com.victor.leilao.dominio.Leilao;
import br.com.victor.leilao.dominio.Usuario;
import org.junit.jupiter.api.Test;



public class TesteLeilao {
    
    @Test
    public void deveReceberUmLance(){
        Leilao leilao = new Leilao("macbook");
        assertEquals(0, leilao.getLances().size());
        
        leilao.propoe(new Lance(new Usuario("joao"), 2000));
        
        assertEquals(1, leilao.getLances().size());
        assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
    }
    
    @Test
    public void deveReceberVariosLances(){
        Leilao leilao = new Leilao("macbook");

        leilao.propoe(new Lance(new Usuario("joao"), 2000));
        leilao.propoe(new Lance(new Usuario("jose"), 3000));

        assertEquals(2, leilao.getLances().size());
        assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
        assertEquals(3000, leilao.getLances().get(1).getValor(), 0.00001);
    }

    @Test
    public void naoDeveAceitarDoisLancesSeguidosNoMesmoUsuario(){
        Leilao leilao = new Leilao("macbook");

        Usuario joao = new Usuario("joao");

        leilao.propoe(new Lance(joao, 2000));
        leilao.propoe(new Lance(joao, 3000));

        assertEquals(1, leilao.getLances().size());
        assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
    }

    @Test
    public void naoDeveAceitarMaisDoQue5LancesDeUmUsuario(){
        Leilao leilao = new Leilao("macbook");
        Usuario joao = new Usuario("joao");
        Usuario jose = new Usuario("jose");

        leilao.propoe(new Lance(joao, 2000));
        leilao.propoe(new Lance(jose, 3000));
        
        leilao.propoe(new Lance(joao, 4000));
        leilao.propoe(new Lance(jose, 5000));

        leilao.propoe(new Lance(joao, 6000));
        leilao.propoe(new Lance(jose, 7000));

        leilao.propoe(new Lance(joao, 8000));
        leilao.propoe(new Lance(jose, 9000));

        leilao.propoe(new Lance(joao, 10000));
        leilao.propoe(new Lance(jose, 11000));
        
        //deve ser ignorado
        leilao.propoe(new Lance(jose, 12000));

        assertEquals(10, leilao.getLances().size());
        assertEquals(11000.0, leilao.getLances().get(leilao.getLances().size()-1).getValor(), 0.00001);
    }
}
