package persistence.usuarios;

import java.util.List;

import model.Pessoa;

public interface PessoaRepository<T extends Pessoa> {
	
	public boolean isDisponivel(String cpf);
	public void salvar(T pessoa);
	public void deletar(T pessoa);
	public void atualizar(T Pessoa);
	public List<T> lista();
	public Pessoa getPessoa(String cpf);

}
