package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.modelo.Contato;

public class ContatoDao {

	private String sqlQuery = "";
	private PreparedStatement stmt;
	// a conexao com banco de dados
	private Connection connection;

	public ContatoDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Contato contato) {
		String sql = "INSERT INTO contatos " + "(nome, email, endereco, dataNascimento)" + "VALUES (?, ?, ?, ?)";

		try {
			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);

			// seta of valores
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);

		}
	}

	public List<Contato> getLista() {
		try {
			List<Contato> contatos = new ArrayList<Contato>();
			PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT * FROM contatos");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto Contato
				Contato contato = new Contato();
				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));

				// montando a data através do Calendar
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataNascimento"));
				contato.setDataNascimento(data);

				// adicionando o objeto à lista
				contatos.add(contato);
			}
			rs.close();
			stmt.close();
			return contatos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public void altera(Contato contato) {
		String sql = "UPDATE contatos SET nome=?, email=?, endereco=?, dataNascimento=? WHERE id=?";

		try {
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
			
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
			stmt.setLong(5, contato.getId());
			
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Contato contato) {
			String sql = "DELETE FROM contatos WHERE id=?";
		try {
			
			
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
			
			stmt.setLong(1, contato.getId());
			
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
	}
	}
}
