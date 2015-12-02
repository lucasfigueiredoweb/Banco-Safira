package persistence.contausuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Cliente;
import model.ClienteContas;
import model.ContaCorrente;
import model.ContaPoupanca;
import persistence.usuarios.ClienteBusiness;
import persistence.usuarios.ClienteRepository;
import util.ConnectionFactory;

public class ClienteContasBusiness {

	public Set<ClienteContas> getClienteContas() {

		Connection c = ConnectionFactory.getConnection();
		ClienteRepository cRepository = new ClienteBusiness();

		List<Cliente> listC = cRepository.lista();

		String sql = "SELECT conta_corrente.id as id,conta_corrente.numero as numero,conta_corrente.saldo as saldo,conta_corrente.data_cadastro FROM cliente_contac INNER JOIN conta_corrente ON id_contac=conta_corrente.id WHERE id_cliente=?";
		String sql2 = "SELECT conta_poupanca.id as id,conta_poupanca.numero as numero,conta_poupanca.saldo as saldo,conta_poupanca.data_cadastro FROM cliente_contap INNER JOIN conta_poupanca ON id_contap=conta_poupanca.id WHERE id_cliente=?";

		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		ResultSet result = null;
		ResultSet result2 = null;
		Set<ClienteContas> list = new HashSet<ClienteContas>();
		ClienteContas ccontas = null;

		try {
			statement = c.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			statement2 = c.prepareStatement(sql2,ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			for (Cliente cliente : listC) {
				System.out.println(cliente);
				statement.setInt(1, cliente.getId());
				statement2.setInt(1,cliente.getId());
				result = statement.executeQuery();
				result2 = statement2.executeQuery();
				result.absolute(0);
				result2.absolute(0);

				ContaPoupanca cp = null;
				ContaCorrente cc = null;
				
				Set<ContaPoupanca> setCp = new HashSet<ContaPoupanca>();
				Set<ContaCorrente> setCc = new HashSet<ContaCorrente>();

				while (result.next()) {
					cc = new ContaCorrente();
					cc.setId(result.getInt("id"));
					cc.setNumero(result.getString("numero"));
					cc.setSaldo(result.getDouble("saldo"));
					cc.setData(new java.util.Date(result.getDate("data_cadastro").getTime()));

					setCc.add(cc);
				}
					

				while (result2.next()) {
					cp = new ContaPoupanca();
					cp.setId(result2.getInt("id"));
					cp.setNumero(result2.getString("numero"));
					cp.setSaldo(result2.getDouble("saldo"));
					cp.setData(new java.util.Date(result2.getDate("data_cadastro").getTime()));

					setCp.add(cp);
				}
				
				ccontas = new ClienteContas();
				ccontas.setCliente(cliente);
				ccontas.setContac(setCc);
				ccontas.setContap(setCp);
				
				list.add(ccontas);

			}
			System.out.println(list);
			return list;
		} catch (SQLException e) {
			System.out.println("Não foi possível fazer a Consulta!");
			e.printStackTrace();

		} finally {
			try {
				result.close();
				result2.close();
				statement.close();
				statement2.close();
				c.close();
			} catch (SQLException e) {
				System.out.println("Não foi possível fechar a conexão!");

			}
		}
		return null;
	}

}
