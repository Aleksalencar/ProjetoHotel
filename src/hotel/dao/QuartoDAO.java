package hotel.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hotel.entidades.Quarto;
import hotel.interfaces.IQuartoDAO;

public class QuartoDAO implements IQuartoDAO {
	private static final String URL = "jdbc:mariadb://localhost:3306/hoteldb?allowMultiQueries=true";
	private static final String USER = "root";
	private static final String PASS = "";
	@Override
	public void adicionar(Quarto quarto) {
		try{
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			String sql = "INSERT INTO quartohotel " 
					+ "(cnumero,candar,calugado, "
					+ "ctipo)"
					+ " Values (?, ?, ?, ?)";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, quarto.getNumero());
			stm.setString(2, quarto.getAndar());
			stm.setString(3, quarto.getAlugado());
			stm.setString(4, quarto.getTipo());

			stm.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Quarto> buscarQuarto(String numero) {
		List<Quarto> lista = new ArrayList<>();
		try{
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			String sql = "SELECT * FROM quartohotel "
					+ " WHERE  cnum = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1,numero);
			ResultSet resultadoBusca = stm.executeQuery();
			while (resultadoBusca.next()){
				Quarto c = pegarQuartos(resultadoBusca);
				lista.add(c);
			}
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		
		return lista;
	}
	private Quarto pegarQuartos(ResultSet resultadoBusca) {
		Quarto c = new Quarto();
		try{	
			c.setNumero(resultadoBusca.getString("cnumero"));
			c.setAndar(resultadoBusca.getString("candar"));
			c.setAlugado(resultadoBusca.getString("calugado"));
			c.setTipo(resultadoBusca.getString("ctipo"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
	
	@Override
	public void apagar(String numero) {
		try {
			Connection con = 
					DriverManager.getConnection(URL, USER, PASS);
			String sql = "DELETE FROM quartohotel "
					+ "WHERE cnumero = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, numero);
			stm.executeUpdate();
			stm.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}		
	}
	
	@Override
	public void atualizar(Quarto quarto, String numero) {
		try {
			Connection con = DriverManager.getConnection(URL,USER,PASS);
			String sql = "UPDATE clientehotel SET cnumero = ?, "
					+ "candar = ?, calugado = ?,"
					+ "ctipo = ? where cnumero = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, quarto.getNumero());
			stm.setString(2, quarto.getAndar());
			stm.setString(3, quarto.getAlugado());
			stm.setString(4, quarto.getTipo());
			stm.setString(7, numero);
			stm.executeUpdate();
			stm.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Quarto> atualizarTabelas() {
		List<Quarto> lista = new ArrayList<>();
		try{
			Connection con = DriverManager.getConnection(URL,USER,PASS);
			String sql = "SELECT cnumero, candar, "
					+ "calugado, ctipo FROM quartohotel ";
			PreparedStatement stm = con.prepareStatement(sql);
			ResultSet resultadoBusca = stm.executeQuery();
			while(resultadoBusca.next()){
				Quarto c = pegarQuartos(resultadoBusca);
				lista.add(c);
			}
			
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return lista;
	}
}
