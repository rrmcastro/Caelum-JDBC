package br.com.caelum.jdbc.teste;

import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.modelo.Contato;

public class TestaRemove {

	public static void main(String[] args) {

		Contato contato = new Contato();

		ContatoDao dao = new ContatoDao();
		
		
		dao.remove(contato.getId());
	}

}
