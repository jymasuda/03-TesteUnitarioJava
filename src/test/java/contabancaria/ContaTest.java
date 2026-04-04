package contabancaria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Testes unitários para a classe Conta.
 *
 * PARTE 1 — Testes de exemplo (Construtor) já estão prontos.
 *           Observe o padrão AAA e o uso de @Test e @ParameterizedTest.
 *
 * PARTE 2 — Você deve escrever os testes para os demais métodos
 *           seguindo rigorosamente o ciclo TDD: Red → Green → Refactor.
 *
 * Para cada método da classe Conta, crie testes que cubram:
 *   ✅ O cenário de sucesso (caminho feliz)
 *   ❌ Cada regra de validação (cenários de exceção)
 *   🔄 Casos de borda (valores limites)
 */
class ContaTest {

    // =======================================================
    //  PARTE 1 — EXEMPLO GUIADO: Testes do Construtor
    //  Observe o padrão Arrange-Act-Assert (AAA)
    // =======================================================

    @Test
    void construtor_DadosValidos_CriaContaCorretamente() {
        // Arrange & Act
        var conta = new Conta("Maria", 100);

        // Assert
        assertEquals("Maria", conta.getTitular());
        assertEquals(100, conta.getSaldo());
        assertTrue(conta.isAtiva());
    }

    @Test
    void construtor_SemSaldoInicial_CriaContaComSaldoZero() {
        // Arrange & Act
        var conta = new Conta("João");

        // Assert
        assertEquals("João", conta.getTitular());
        assertEquals(0, conta.getSaldo());
        assertTrue(conta.isAtiva());
    }

    @Test
    void construtor_TitularNulo_LancaIllegalArgumentException() {
        // Assert — verifica que a exceção é lançada
        assertThrows(IllegalArgumentException.class, () -> new Conta(null));
    }

    @Test
    void construtor_TitularVazio_LancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Conta(""));
    }

    @Test
    void construtor_SaldoNegativo_LancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Conta("Maria", -50));
    }

    @ParameterizedTest
    @CsvSource({
        "Ana,    0",
        "Carlos, 1000",
        "Beatriz, 0.01"
    })
    void construtor_VariosValoresValidos_CriaContaCorretamente(String titular, double saldo) {
        // Act
        var conta = new Conta(titular, saldo);

        // Assert
        assertEquals(titular, conta.getTitular());
        assertEquals(saldo, conta.getSaldo(), 0.001);
        assertTrue(conta.isAtiva());
    }

    // =======================================================
    //  PARTE 2 — ESCREVA OS TESTES ABAIXO (TDD)
    //  Lembre-se: escreva o teste PRIMEIRO, veja FALHAR (Red),
    //  depois implemente o código para PASSAR (Green),
    //  e por fim faça Refactor se necessário.
    // =======================================================

    // =======================================================
    //  Testes para depositar
    // =======================================================

    @Test
    void depositar_ValorValido_AtualizaSaldo(){
        var conta = new Conta("João");

        conta.depositar(100.00);
        assertEquals(100, conta.getSaldo());
    }

    @Test
    void depositar_ValorZero_LancaIllegalArgumentException(){
        var conta = new Conta("João");

        assertThrows(IllegalArgumentException.class, () -> conta.depositar(0));
    }

    @Test
    void depositar_ValorNegativo_LancaIllegalArgumentException(){
        var conta = new Conta("João");

        assertThrows(IllegalArgumentException.class, () -> conta.depositar(-10));
    }

    @Test
    void depositar_contaInativa_LancaIllegalStateException(){
        var conta = new Conta("João");
        conta.encerrar();

        assertThrows(IllegalStateException.class, () -> conta.depositar(10));
    }

    // =======================================================
    //  Testes para sacar
    //  Sugestão de testes:
    //    - Saque com valor válido atualiza o saldo
    //    - Saque com valor maior que saldo lança IllegalStateException
    //    - Saque com valor zero lança IllegalArgumentException
    //    - Saque com valor negativo lança IllegalArgumentException
    //    - Saque em conta inativa lança IllegalStateException
    // =======================================================

    @Test
    void sacar_valorValido_atualizaSaldo(){
        var conta = new Conta("João", 100.00);

        conta.sacar(10.00);
        assertEquals(90, conta.getSaldo());
    }

    @Test
    void sacar_valorMaiorQueSaldo_lancaIllegalStateException(){
        var conta = new Conta("João", 100.00);

        assertThrows(IllegalStateException.class, () -> conta.sacar(1000));
    }
    // =======================================================
    //  Testes para transferir
    //  Sugestão de testes:
    //    - Transferência válida atualiza saldo de ambas as contas
    //    - Transferência com saldo insuficiente lança exceção
    //    - Transferência com valor zero/negativo lança exceção
    //    - Transferência com conta origem inativa lança exceção
    //    - Transferência com conta destino inativa lança exceção
    // =======================================================


    // =======================================================
    //  Testes para encerrar
    //  Sugestão de testes:
    //    - Encerrar conta com saldo zero funciona
    //    - Encerrar conta com saldo lança IllegalStateException
    //    - Encerrar conta já inativa lança IllegalStateException
    //    - Conta encerrada tem isAtiva() == false
    // =======================================================

}
