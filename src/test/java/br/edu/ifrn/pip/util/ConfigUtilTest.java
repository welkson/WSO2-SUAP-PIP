/**
 *
 */
package br.edu.ifrn.pip.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

/**
 * @author Lucas Mariano
 *
 */
public class ConfigUtilTest {

	/**
	 * Teste responsável por verificar se o método fábrica Test method for
	 * {@link br.edu.ifrn.pip.util.ConfigUtil#getInstance()} não está retornando um
	 * objeto nulo.
	 */
	@Test
	public void testGetInstanceNotNull() {
		ConfigUtil configUtil = ConfigUtil.getInstance();
		assertNotNull("A instância retornada não deveria ser nula.", configUtil);
	}

	/**
	 * Teste responsável por verificar se o método fábrica
	 * {@link br.edu.ifrn.pip.util.ConfigUtil#getInstance()} realmente entrega o
	 * mesmo objeto (definido pela localização na memória).
	 */
	@Test
	public void testGetInstance() {
		ConfigUtil configUtilA = ConfigUtil.getInstance();
		assertNotNull(configUtilA);
		ConfigUtil configUtilB = ConfigUtil.getInstance();
		assertNotNull(configUtilB);
		assertEquals("Os objetos deveriam ser iguais, pois trata-se de um singleton.", configUtilA, configUtilB);
	}

	/**
	 * Teste responsável por verificar se o método fábrica
	 * {@link br.edu.ifrn.pip.util.ConfigUtil#getInstance()} é thread-safe. Para
	 * isso, são criadas 1000 threads e cada uma delas tenta obter uma instância de
	 * {@link ConfigUtil}. Os hashcode das instâncias são comparadas para verificar
	 * se são todos iguais. Caso algum seja diferente, significa que existe mais de
	 * uma instância.
	 */
	@Test
	public void testGetInstanceThreadSafe() {
		ExecutorService pool = Executors.newFixedThreadPool(1000);

		Integer hashAnterior = 0;
		for (int i = 0; i < 1000; i++) {
			Future<Integer> future = pool.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					return ConfigUtil.getInstance().hashCode();
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
	 * {@link br.edu.ifrn.pip.util.ConfigUtil#recuperarValorDeConfiguracao(java.lang.String)}
	 * retorna vazio quando a configuração solicitada não possui valor definido.
	 */
	@Test
	public void testRecuperarValorDeConfiguracaoSemValor() {
		assertEquals("Deveria ter retornado um valor vazio.", "",
				ConfigUtil.getInstance().recuperarValorDeConfiguracao("configSemValor"));
	}

	/**
	 * Teste responsável por verificar se o método
	 * {@link br.edu.ifrn.pip.util.ConfigUtil#recuperarValorDeConfiguracao(java.lang.String)}
	 * lança {@link IllegalArgumentException} quando a configuração solicitada é
	 * <code>null</code>.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRecuperarValorDeConfiguracaoNull() {
		ConfigUtil.getInstance().recuperarValorDeConfiguracao(null);
	}

	/**
	 * Teste responsável por verificar se o método
	 * {@link br.edu.ifrn.pip.util.ConfigUtil#recuperarValorDeConfiguracao(java.lang.String)}
	 * lança {@link IllegalArgumentException} quando o parâmetro informado
	 * representa um identificador de configuração desconhecido.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRecuperarValorDeConfiguracaoInxistente() {
		ConfigUtil.getInstance().recuperarValorDeConfiguracao(UUID.randomUUID().toString());
	}

}
