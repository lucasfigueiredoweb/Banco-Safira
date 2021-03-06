package persistence.usuarios;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Funcionario;
import model.Pessoa;
import model.exceptions.CpfJaCadastradoException;
import model.exceptions.NenhumaContaCadastradaException;
import model.exceptions.PessoaNaoEncontradaException;
import util.ConnectionFactory;

public class FuncionarioBusiness extends PessoaBusiness<Funcionario> implements FuncionarioRepository {
	
	
	public void salvar(Funcionario func) {
		Connection c = ConnectionFactory.getConnection();
		PreparedStatement statement = null;

		try {

			if (this.isDisponivel(func.getCpf())) {
				String sql = "INSERT INTO pessoa (nome,cpf,data_nascimento,email) VALUES (?, ?, ?, ?)";
				statement = c.prepareStatement(sql);
				statement.setString(1, func.getNome());
				statement.setString(2, func.getCpf());
				statement.setDate(3, new Date(func.getDataNascimento().getTime()));
				statement.setString(4, func.getEmail());
				statement.execute();
				
				funcionarioPessoa(c, func);
				
				System.out.println("Funcionario salvo com sucesso!");

			} else {
				throw new CpfJaCadastradoException();
			}
		} catch (SQLException e) {
			System.out.println("Error ao conectar com o banco de dados!");
			e.printStackTrace();
		} catch (CpfJaCadastradoException e2) {
			System.out.println("Cpf ja cadastrado");
		} finally {

			try {

				statement.close();

				c.close();
			} catch (SQLException e) {
				System.out.println("N�o foi poss�vel fechar a conex�o!");
			}
		}

	}

	public Funcionario getFuncionario(String cpf) {
		Connection c = ConnectionFactory.getConnection();
		String sql = "select funcionario.id as id,pessoa.nome as nome,pessoa.cpf as cpf,pessoa.email as email,pessoa.data_nascimento as data_nascimento,funcionario.senha as senha,funcionario.salario as salario from funcionario INNER JOIN pessoa ON id_pessoa=pessoa.id where cpf=?";

		PreparedStatement statement = null;
		ResultSet result = null;
		try {

			statement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.setString(1, cpf);
			result = statement.executeQuery();

			if (!result.next()) {
				throw new PessoaNaoEncontradaException();
			}

			result.absolute(0);

			Funcionario func = null;

			while (result.next()) {
				func = new Funcionario();
				func.setId(result.getInt("id"));
				func.setSenha(result.getString("senha"));
				func.setSalario(result.getDouble("salario"));
				func.setNome(result.getString("nome"));
				func.setCpf(result.getString("cpf"));
				func.setEmail(result.getString("email"));
				func.setDataNascimento(new java.util.Date(result.getDate("data_nascimento").getTime()));

			}

			return func;

		} catch (SQLException e) {
			System.out.println("N�o foi poss�vel fazer a Consulta!");
			e.printStackTrace();

		} catch (PessoaNaoEncontradaException e2) {
			try{
				ab.display("CPF ou senha Incorretos");
				}catch(Exception e){
					System.out.println("Ocorreu um erro");
				}

		} finally {
			try {

				result.close();

				statement.close();

				c.close();

			} catch (SQLException e) {
				System.out.println("N�o foi poss�vel fechar a conex�o!");

			}
		}
		return null;
	}
	
	
	
	
	private void funcionarioPessoa(Connection c, Funcionario func) {
		Pessoa atual = this.getPessoa(func.getCpf());
		String sql = "INSERT INTO funcionario (id_pessoa,senha,salario) VALUES (" + atual.getId() + ",?,?)";
		PreparedStatement statement = null;
		try {

			statement = c.prepareStatement(sql);
			statement.setString(1,func.getSenha());
			statement.setDouble(2,func.getSalario());
			
			
			statement.execute();

		} catch (SQLException e) {
			System.out.println("Ocorreu um erro na hora de salvar o funcionario");
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {

			}
		}

	}

	public void deletar(Funcionario funcionario) {

		Connection c = ConnectionFactory.getConnection();
		Pessoa p = this.getPessoa(funcionario.getCpf());
		String sql = "DELETE FROM funcionario WHERE id=?";
		String sql2 = "DELETE FROM pessoa WHERE id=?";

		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		
		try {
			statement = c.prepareStatement(sql);
			statement2 = c.prepareStatement(sql2);

			statement.setInt(1, funcionario.getId());
			statement2.setInt(1, p.getId());

			if (statement.executeUpdate() > 0 && statement2.executeUpdate() > 0) {
				System.out.println("Usuario foi deletado com sucesso!");
			} else {
				throw new PessoaNaoEncontradaException();
			}
		} catch (SQLException e) {
			System.out.println("N�o foi poss�vel deletar a Conta!");
		} catch (PessoaNaoEncontradaException e2) {
			e2.printStackTrace();

		} finally {
			try {
				statement.close();
				statement2.close();
				c.close();
			} catch (SQLException e) {
				System.out.println("N�o foi poss�vel fechar a conex�o!");
			}
		}

	}

	public void atualizar(Funcionario func) {
		Connection c = ConnectionFactory.getConnection();
	
		String sql = "UPDATE pessoa SET nome=?,cpf=?,email=? WHERE id=?";
		String sql2 = "UPDATE funcionario SET salario=?,senha=? WHERE id=?";

		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		try {
			
			statement = c.prepareStatement(sql);
			statement2 = c.prepareStatement(sql2);

			statement.setString(1,func.getNome());
			statement.setString(2, func.getCpf());
			statement.setString(3, func.getEmail());
			statement.setInt(4, this.getIdPessoa(func.getId()));
			
			statement2.setDouble(1, func.getSalario());
			statement2.setString(2, func.getSenha());
			statement2.setInt(3, func.getId());
			

			if (statement.executeUpdate() > 0 && statement2.executeUpdate() > 0) {
				System.out.println("Usuario Atualizada com sucesso!");
			} else {
				throw new PessoaNaoEncontradaException();
			}
			statement.close();
			statement2.close();
			c.close();
		} catch (SQLException e) {
			System.out.println("N�o foi poss�vel atualizar a Conta!");

		} catch (PessoaNaoEncontradaException e2) {
			e2.printStackTrace();
		} finally {

			try {
				statement.close();
				statement2.close();
				c.close();
			} catch (SQLException e) {
				System.out.println("N�o foi poss�vel fechar a conex�o!");
			}
		}
	}

	public List<Funcionario> lista() {
		Connection c = ConnectionFactory.getConnection();
		String sql = "select funcionario.id as id,pessoa.nome as nome,pessoa.cpf as cpf,pessoa.email as email,pessoa.data_nascimento as data_nascimento,funcionario.salario as salario,funcionario.senha as senha from funcionario INNER JOIN pessoa ON id_pessoa=pessoa.id;";
		Statement statement = null;
		ResultSet result = null;

		try {
			statement = c.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.CONCUR_UPDATABLE);

			result = statement.executeQuery(sql);

			if (!result.next()) {

				throw new NenhumaContaCadastradaException();
			}

			result.absolute(0);

			Funcionario func = null;
			List<Funcionario> list = new ArrayList<Funcionario>();

			while (result.next()) {
				func = new Funcionario();
				func.setId(result.getInt("id"));
				func.setNome(result.getString("nome"));
				func.setCpf(result.getString("cpf"));
				func.setSenha(result.getString("senha"));
				func.setEmail(result.getString("email"));
				func.setSalario(result.getDouble("salario"));
				func.setDataNascimento(new java.util.Date(result.getDate("data_nascimento").getTime()));

				list.add(func);
			}
			c.close();
			return list;

		} catch (SQLException e) {
			System.out.println("N�o foi poss�vel fazer a Consulta!");

		} catch (NenhumaContaCadastradaException e2) {
			e2.printStackTrace();

		} finally {
			try {
				result.close();
				statement.close();
				c.close();

			} catch (SQLException e) {
				System.out.println("N�o foi poss�vel fechar a conex�o!");

			}
		}
		return null;
	}


}
