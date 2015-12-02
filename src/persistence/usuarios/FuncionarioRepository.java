package persistence.usuarios;

import model.Funcionario;

public interface FuncionarioRepository extends PessoaRepository<Funcionario> {
	
	public Funcionario getFuncionario(String cpf);


}
