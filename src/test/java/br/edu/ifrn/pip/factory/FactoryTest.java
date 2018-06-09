/**
 *
 */
package br.edu.ifrn.pip.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

import br.edu.ifrn.pip.connectors.Connector;

/**
 * @author Lucas Mariano
 *
 */
public class FactoryTest {

	/**
	 * Teste responsável por verificar se o método fábrica
	 * {@link br.edu.ifrn.pip.factory.Factory#getInstance()} não está retornando um
	 * objeto nulo.
	 */
	@Test
	public void testGetInstanceIsNotNull() {
		Factory factory = Factory.getInstance();
		assertNotNull("A instância retornada não deveria ser nula.", factory);
	}

	/**
	 * Teste responsável por verificar se o método fábrica
	 * {@link br.edu.ifrn.pip.factory.Factory#getInstance()} realmente entrega o
	 * mesmo objeto (definido pela localização na memória).
	 */
	@Test
	public void testGetInstance() {
		Factory factoryA = Factory.getInstance();
		assertNotNull(factoryA);
		Factory factoryB = Factory.getInstance();
		assertNotNull(factoryB);
		assertEquals("Os objetos deveriam ser iguais, pois trata-se de um singleton.", factoryA, factoryB);
	}

	/**
	 * Teste responsável por verificar se o método
	 * {@link br.edu.ifrn.pip.factory.Factory#getInstance()} é thread-safe. Para
	 * isso, são criadas 1000 threads e cada uma delas tenta obter uma instância de
	 * {@link Factory}. Os hashcode das instâncias são comparadas para verificar se
	 * são todos iguais. Caso algum seja diferente, significa que existe mais de uma
	 * instância.
	 */
	@Test
	public void testGetInstanceThreadSafe() {
		ExecutorService pool = Executors.newFixedThreadPool(1000);

		Integer hashAnterior = 0;
		for (int i = 0; i < 1000; i++) {
			Future<Integer> future = pool.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					return Factory.getInstance().hashCode();
				}
			});
			try {
				Integer hashAtual = future.get();
				if (hashAnterior == 0) {
					hashAnterior = hashAtual;
				} else {
					assertEquals(
							"Ops... parece que foi criada uma instancia diferente por uma outra thread. Isso não deveria ocorrer, pois a implementação deveria ser thread-safe.",
							hashAnterior, hashAtual);
				}
			} catch (InterruptedException | ExecutionException exception) {
				exception.printStackTrace();
			}
		}
	}

	/**
	 * Teste responsável por verificar se o método
	 * {@link br.edu.ifrn.pip.factory.Factory#criarConnector(java.lang.String)} cria
	 * corretamente um {@link Connector} para um determinado tipo válido.
	 */
	@Test
	public void testCriarConnector() {
		Connector connector = Factory.getInstance().criarConnector("centralservicos/donoticket");
		assertNotNull("Deveria ter criado corretamente o conector, pois o tipo existe.", connector);
	}

	/**
	 * Teste responsável por verificar se o método
	 * {@link br.edu.ifrn.pip.factory.Factory#criarConnector(java.lang.String)}
	 * lança {@link IllegalArgumentException} quando o parâmetro informado
	 * representa um tipo de conector não nulo desconhecido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCriarConnectorTipoInexistente() {
		final String tipoConectorInexistente = UUID.randomUUID().toString();
		Factory.getInstance().criarConnector(tipoConectorInexistente);
		fail("Deveria ter lançado uma exceção por ter tentado criar um conector desconhecido/inexistente.");
	}

	/**
	 * Teste responsável por verificar se o método
	 * {@link br.edu.ifrn.pip.factory.Factory#criarConnector(java.lang.String)}
	 * lança {@link IllegalArgumentException} quando o parâmetro informado
	 * representa um tipo de conector <code>null</code>.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCriarConnectorTipoNulo() {
		final String tipoConectorNulo = null;
		Factory.getInstance().criarConnector(tipoConectorNulo);
		fail("Deveria ter lançado uma exceção por ter tentado criar um conector de tipo null.");
	}

	/**
	 * Teste responsável por verificar se o método
	 * {@link br.edu.ifrn.pip.factory.Factory#criarConnector(java.lang.String)}
	 * lança {@link IllegalArgumentException} quando o parâmetro informado
	 * representa um identificador de uma configuração que não possui valor.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCriarConnectorTipoSemValor() {
		final String tipoSemValor = "usuario/alunos";
		Factory.getInstance().criarConnector(tipoSemValor);
		fail("Deveria ter lançado uma exceção por ter tentado criar um conector que está vazio.");
	}
}
