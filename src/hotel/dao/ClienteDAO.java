package hotel.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hotel.entidades.Cliente;
import hotel.interfaces.IClienteDAO;

public class ClienteDAO implements IClienteDAO {
	private static final String URL = "jdbc:mariadb://localhost:3306/hoteldb?allowMultiQueries=true";
	private static final String USER = "root";
	private static final String PASS = "";
	@Override
	public void adicionar(Cliente cliente) {
		try{
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			String sql = "INSERT INTO clientehotel " 
					+ "(cnome,cemail,csexo, "
					+ "ctelefone, cendereco, ccpf)"
					+ " Values (?, ?, ?, ?, ?, ?)";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, cliente.getNome());
			stm.setString(2, cliente.getEmail());
			stm.setString(3, cliente.getSexo());
			stm.setString(4, cliente.getTelefone());
			stm.setString(5, cliente.getEndereco());
			stm.setString(6, cliente.getCPF());
			stm.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<Cliente> buscarCliente(String codigo) {
		List<Cliente> lista = new ArrayList<>();
		try{
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			String sql = "SELECT * FROM clientehotel "
					+ " WHERE  ccpf = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1,codigo);
			ResultSet resultadoBusca = stm.executeQuery();
			while (resultadoBusca.next()){
				Cliente c = pegarClientes(resultadoBusca);
				lista.add(c);
			}
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		
		return lista;
	}
	private Cliente pegarClientes(ResultSet resultadoBusca) {
		Cliente c = new Cliente();
		try{	
			c.setCPF(resultadoBusca.getString("ccpf"));
			c.setEmail(resultadoBusca.getString("cemail"));
			c.setEndereco(resultadoBusca.getString("cendereco"));
			c.setNome(resultadoBusca.getString("cnome"));
			c.setSexo(resultadoBusca.getString("csexo"));
			c.setTelefone(resultadoBusca.getString("ctelefone"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
	
	@Override
	public void apagar(String codigo) {
		try {
			Connection con = 
					DriverManager.getConnection(URL, USER, PASS);
			String sql = "DELETE FROM clientehotel "
					+ "WHERE ccpf = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, codigo);
			stm.executeUpdate();
			stm.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}		
	}
	
	@Override
	public void atualizar(Cliente cliente, String codigo) {
		try {
			Connection con = DriverManager.getConnection(URL,USER,PASS);
			String sql = "UPDATE clientehotel SET ccpf = ?, "
					+ "cemail = ?, cendereco = ?,"
					+ "cnome = ?, csexo = ?,"
					+ "ctelefone = ? where ccpf = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, cliente.getCPF());
			stm.setString(2, cliente.getEmail());
			stm.setString(3, cliente.getEndereco());
			stm.setString(4, cliente.getNome());
			stm.setString(5, cliente.getSexo());
			stm.setString(6, cliente.getTelefone());
			stm.setString(7, codigo);
			stm.executeUpdate();
			stm.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Cliente> atualizarTabelas() {
		List<Cliente> lista = new ArrayList<>();
		try{
			Connection con = DriverManager.getConnection(URL,USER,PASS);
			String sql = "SELECT ccpf, cemail, "
					+ "cendereco, cnome, csexo,ctelefone FROM clientehotel ";
			PreparedStatement stm = con.prepareStatement(sql);
			ResultSet resultadoBusca = stm.executeQuery();
			while(resultadoBusca.next()){
				Cliente c = pegarClientes(resultadoBusca);
				lista.add(c);
			}
			
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return lista;
	}
}
