package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import persistence.usuarios.ClienteBusiness;
import persistence.usuarios.ClienteRepository;

public class AtualizadorTaxa {

	private static AtualizadorTaxa instance = null;
	private static Set<ClienteContas> iteracoes = new HashSet<ClienteContas>();

	public void atualizarTaxa(Set<ClienteContas> set) {
		
		iteracoes = set;
		
		for (ClienteContas x : iteracoes) {
			Double taxaFidelidade = 0.0;
			Double taxaContaC = 0.0;
			Double taxaContaP = 0.0;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			
			
			for(ContaCorrente conta : x.getContac()){
				int diferenca = Integer.parseInt(sdf.format(Calendar.getInstance().getTime())) - Integer.parseInt(sdf.format(conta.getData()));
				if( diferenca > 0){
					System.out.println(diferenca);
					taxaContaC += 0.1*diferenca ;
				}
			}
			
			for(ContaPoupanca conta : x.getContap()){
				int diferenca = Integer.parseInt(sdf.format(Calendar.getInstance().getTime())) - Integer.parseInt(sdf.format(conta.getData()));
				if( diferenca > 0){
					System.out.println(diferenca);
					taxaContaP += 0.1*diferenca ;
				}
			}
			
			taxaFidelidade  = taxaContaC + taxaContaP;
			
			x.getCliente().setTaxaFidelidade(taxaFidelidade);
			
			ClienteRepository cRepository = new ClienteBusiness();
			cRepository.atualizar(x.getCliente());
			
		}
	}

	private AtualizadorTaxa() {

	}

	public static AtualizadorTaxa getInstance() {
		if (instance == null) {
			instance = new AtualizadorTaxa();
		}
		return instance;
	}

}
