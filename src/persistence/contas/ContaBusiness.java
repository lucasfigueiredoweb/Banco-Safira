package persistence.contas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Cliente;
import model.Conta;
import util.ConnectionFactory;

public class ContaBusiness<T extends Conta> implements ContaRepository<T> {
	
	public boolean isDisponivel(String numero) {
		Connection c = ConnectionFactory.getConnection();
		String sql = "SELECT * FROM conta_poupanca where numero=?";
		String sql2 = "SELECT * FROM conta_corrente where numero=?";
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		ResultSet result1 = null;
		ResultSet result2 = null;

		try {
			statement = c.prepareStatement(sql);

			statement.setString(1, numero + "");

			result1 = statement.executeQuery();

			statement2 = c.prepareStatement(sql2);

			statement2.setString(1, numero + "");

			result2 = statement2.executeQuery();

			if (!result1.next() && !result2.next()) {

				return true;

			} else {
				return false;
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível fazer a Consulta!");
			e.printStackTrace();
		} finally {
			try {
				result1.close();
				result2.close();
				statement.close();
				statement2.close();
				c.close();
			} catch (SQLException e) {
				System.out.println("Não foi possível fechar a conexão!");

			}
		}
		return true;
	}

	@Override
	public void salvar(T conta, Cliente cliente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atualizar(T conta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletar(T conta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<T> lista() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getConta(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getConta(String numero) {
		// TODO Auto-generated method stub
		return null;
	}

}
