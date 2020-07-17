package br.com.caelum.jdbc.teste;

import java.util.Calendar;

import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.modelo.Contato;

public class TesteCompleto {

	public static void main(String[] args) {
		
		Contato contato = new Contato();
		contato.setNome("Rafael Sakurai");
		contato.setEmail("rafel@sakurai.com");
		contato.setEndereco("R. Santa Missões 257 lj10");
		contato.setDataNascimento(Calendar.getInstance());
		
		ContatoDao dao = new ContatoDao();
		System.out.println("Salvando o contato: " + contato.getNome());
		contato = dao.adiciona(contato);
		
		contato.setNome("Rafael Guimarães Sakurai");
		contato = dao.altera(contato);
		
		System.out.println("Removendo a pessoa: " + contato.getId());
	    dao.remove(contato.getId());

		
		
	}

}
