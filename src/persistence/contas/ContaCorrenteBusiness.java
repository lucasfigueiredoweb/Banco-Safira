package persistence.contas;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.ContaCorrente;
import model.exceptions.ContaJaCadastradaException;
import model.exceptions.ContaNaoEncontradaException;
import model.exceptions.NenhumaContaCadastradaException;
import util.ConnectionFactory;

public class ContaCorrenteBusiness extends ContaBusiness<ContaCorrente> implements ContaCorrenteRepository {

	public void salvar(ContaCorrente conta,Cliente cliente) {
		Connection c = ConnectionFactory.getConnection();
		String sql = "INSERT INTO conta_corrente (numero,saldo,data_cadastro) VALUES (?, ?, ?)";
		PreparedStatement statement = null;

		try {

			if (this.isDisponivel(conta.getNumero())) {

				statement = c.prepareStatement(sql);
				statement.setString(1, conta.getNumero());
				statement.setDouble(2, conta.getSaldo());
				statement.setDate(3, new Date(conta.getData().getTime()));

				statement.execute();
				this.contaCliente(c, conta, cliente);
				System.out.println("Uma nova Conta foi criada com sucesso!");

			} else {
				throw new ContaJaCadastradaException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ContaJaCadastradaException e2) {
			e2.printStackTrace();
		} finally {

			try {
				statement.close();
				c.close();
			} catch (SQLException e) {
				System.out.println("N�o foi poss�vel fechar a conex�o!");
				
			}

		}

	}
	
	private void contaCliente(Connection c,ContaCorrente conta,Cliente cliente){
		ContaCorrente atual = this.getConta(conta.getNumero());
		String sql = "INSERT INTO cliente_contac (id_cliente,id_contac) VALUES (" +cliente.getId() + ","+atual.getId()+")";
		PreparedStatement statement = null;
		try {

			statement = c.prepareStatement(sql);
			statement.execute();

		} catch (SQLException e) {
			System.out.println("Ocorreu um erro na hora de salvar");
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {

			}
		}
	}

	public void atualizar(ContaCorrente conta) {
		Connection c = ConnectionFactory.getConnection();

		String sql = "UPDATE conta_corrente SET numero=?, saldo=? WHERE id=?";

		PreparedStatement statement = null;
		try {
			statement = c.prepareStatement(sql);

			statement.setString(1, conta.getNumero());
			statement.setDouble(2, conta.getSaldo());
			statement.setInt(3, conta.getId());

			if (statement.executeUpdate() > 0) {
				System.out.println("Conta Atualizada com sucesso!");
			} else {
				throw new ContaNaoEncontradaException();
			}
			statement.close();
			c.close();
		} catch (SQLException e) {
			System.out.println("N�o foi poss�vel atualizar a Conta!");

		} catch (ContaNaoEncontradaException e2) {
			e2.printStackTrace();
		} finally {

			try {
				statement.close();
				c.close();
			} catch (SQLException e) {
				System.out.println("N�o foi poss�vel fechar a conex�o!");
			}
		}
	}

	public ContaCorrente getConta(Cliente cliente) {
		Connection c = ConnectionFactory.getConnection();
		String sql = "SELECT conta_corrente.id as id, conta_corrente.numero as numero,conta_corrente.saldo as saldo,conta_corrente.data_cadastro as data_cadastro FROM cliente_contac INNER JOIN conta_corrente ON id_contac=conta_corrente.id where id_cliente=?";

		PreparedStatement statement = null;
		ResultSet result = null;

		try {

			statement = c.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			statement.setInt(1, cliente.getId());
			result = statement.executeQuery();

			if (!result.next()) {

				throw new ContaNaoEncontradaException();
			}

			result.absolute(0);

			ContaCorrente cp = null;

			while (result.next()) {
				cp = new ContaCorrente();
				cp.setId(result.getInt("id"));
				cp.setNumero(result.getString("numero"));
				cp.setSaldo(result.getDouble("saldo"));
				cp.setData(new java.util.Date(result.getDate("data_cadastro")
						.getTime()));

			}
			return cp;

		} catch (SQLException e) {
			System.out.println("N�o foi poss�vel fazer a Consulta!");
			e.printStackTrace();

		} catch (ContaNaoEncontradaException e2) {
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
	
	public ContaCorrente getConta(String numero) {
		Connection c = ConnectionFactory.getConnection();
		String sql = "SELECT * FROM conta_corrente where numero=?";

		PreparedStatement statement = null;
		ResultSet result = null;

		try {

			statement = c.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			statement.setString(1, numero);
			result = statement.executeQuery();

			if (!result.next()) {

				throw new ContaNaoEncontradaException();
			}

			result.absolute(0);

			ContaCorrente cp = null;

			while (result.next()) {
				cp = new ContaCorrente();
				cp.setId(result.getInt("id"));
				cp.setNumero(result.getString("numero"));
				cp.setSaldo(result.getDouble("saldo"));
				cp.setData(new java.util.Date(result.getDate("data_cadastro")
						.getTime()));

			}
			return cp;

		} catch (SQLException e) {
			System.out.println("N�o foi poss�vel fazer a Consulta!");
			e.printStackTrace();

		} catch (ContaNaoEncontradaException e2) {
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

	public void deletar(ContaCorrente conta) {

		Connection c = ConnectionFactory.getConnection();
		String sql = "DELETE FROM conta_corrente WHERE id=?";
		String sql2 = "DELETE FROM cliente_contac WHERE id_contac=?";

		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		try {
			statement = c.prepareStatement(sql);

			statement.setInt(1, conta.getId());
			
			statement2 = c.prepareStatement(sql2);
			
			statement2.setInt(1, conta.getId());

			if (statement2.executeUpdate() > 0 && statement.executeUpdate() > 0 ) {
				System.out.println("Conta foi deletada com sucesso!");
			} else {
				throw new ContaNaoEncontradaException();
			}
		} catch (SQLException e) {
			System.out.println("N�o foi poss�vel deletar a Conta!");
			e.printStackTrace();
		} catch (ContaNaoEncontradaException e2) {
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

	
	

	public List<ContaCorrente> lista() {

		Connection c = ConnectionFactory.getConnection();
		String sql = "SELECT * FROM conta_corrente";

		Statement statement = null;
		ResultSet result = null;

		try {
			statement = c.createStatement(ResultSet.CONCUR_READ_ONLY,
					ResultSet.CONCUR_UPDATABLE);

			result = statement.executeQuery(sql);

			if (!result.next()) {

				throw new NenhumaContaCadastradaException();
			}

			result.absolute(0);

			ContaCorrente cp = null;
			List<ContaCorrente> list = new ArrayList<ContaCorrente>();

			while (result.next()) {
				cp = new ContaCorrente();
				cp.setId(result.getInt("id"));
				cp.setNumero(result.getString("numero"));
				cp.setSaldo(result.getDouble("saldo"));
				cp.setData(new java.util.Date(result.getDate("data_cadastro")
						.getTime()));

				list.add(cp);
			}
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
