package persistence.contas;

import java.util.List;

import model.Cliente;
import model.Conta;

public interface ContaRepository<T extends Conta> {
	
	public void salvar(T conta,Cliente cliente);

	public void atualizar(T conta);

	public void deletar(T conta);

	public List<T> lista();

	public T getConta(Cliente cliente);
	
	public T getConta(String numero);
	
	

}
