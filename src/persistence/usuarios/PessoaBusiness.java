package persistence.usuarios;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import controller.AlertBox;
import model.Pessoa;
import model.exceptions.PessoaNaoEncontradaException;
import util.ConnectionFactory;

public class PessoaBusiness<T extends Pessoa> implements PessoaRepository<T> {
	
	protected final Class<T> clazz;
	protected AlertBox ab = new AlertBox();
	
	public PessoaBusiness(){
		@SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.clazz = clazz;
	}
	
	public boolean isDisponivel(String cpf) {
		Connection c = ConnectionFactory.getConnection();
		String sql = "SELECT * FROM pessoa where cpf=?";
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			statement = c.prepareStatement(sql);

			statement.setString(1, cpf + "");

			result = statement.executeQuery();

		if (!result.next() ) {

				return true;

			} else {

				return false;
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível fazer a Consulta!");
			e.printStackTrace();
		} finally {
			try {

				result.close();
				statement.close();
				c.close();

			} catch (SQLException e) {
				System.out.println("Não foi possível fechar a conexão!");

			}
		}
		return true;
	}
	
	public Pessoa getPessoa(String cpf) {
		Connection c = ConnectionFactory.getConnection();
		String sql = "SELECT * FROM pessoa where cpf=?";

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

			Pessoa pessoa = null;

			while (result.next()) {
				pessoa = new Pessoa();
				pessoa.setId(result.getInt("id"));
				pessoa.setNome(result.getString("nome"));
				pessoa.setCpf(result.getString("cpf"));
				pessoa.setEmail(result.getString("email"));
				pessoa.setDataNascimento(new java.util.Date(result.getDate("data_nascimento").getTime()));

			}

			return pessoa;

		} catch (SQLException e) {
			System.out.println("Não foi possível fazer a Consulta!");
			e.printStackTrace();

		} catch (PessoaNaoEncontradaException e2) {
			System.out.println("Pessoa não encontrada");

		} finally {
			try {

				result.close();

				statement.close();

				c.close();

			} catch (SQLException e) {
				System.out.println("Não foi possível fechar a conexão!");

			}
		}
		return null;
	}
	
	public int getIdPessoa(int id) {
		Connection c = ConnectionFactory.getConnection();
		
		String sql = "select id_pessoa from " + clazz.getName().replace("model.", "")+"  where id=?";
		PreparedStatement statement = null;
		ResultSet result = null;
		try {

			statement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.setInt(1, id);
			result = statement.executeQuery();

			if (!result.next()) {
				throw new PessoaNaoEncontradaException();
			}

			result.absolute(0);

			int id_pessoa = 0;

			while (result.next()) {
			
			id_pessoa = result.getInt("id_pessoa");
				

			}

			return id_pessoa;

		} catch (SQLException e) {
			System.out.println("Não foi possível fazer a Consulta!");
			e.printStackTrace();

		} catch (PessoaNaoEncontradaException e2) {
			System.out.println("Pessoa não encontrada");

		} finally {
			try {

				result.close();

				statement.close();

				c.close();

			} catch (SQLException e) {
				System.out.println("Não foi possível fechar a conexão!");

			}
		}
		return 0;
	}
	
	
	

	@Override
	public void salvar(T pessoa) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletar(T pessoa) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atualizar(T Pessoa) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<T> lista() {
		
		return null;
	}
}
