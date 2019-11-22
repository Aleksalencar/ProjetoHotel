package hotel.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hotel.entidades.Produto;
import hotel.interfaces.IEstoqueDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EstoqueDAO implements IEstoqueDAO {
	private static final String URL = "jdbc:mariadb://localhost:3306/hoteldb?allowMultiQueries=true";
	private static final String USER = "root";
	private static final String PASS = "";
	
	@Override
	public void adicionar(Produto prod) {
		try{
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			String sql = "INSERT INTO estoquehotel " 
					+ "(id_produto,nome_produto,descricao, "
					+ "quantidade, valor)"
					+ " Values (?, ?, ?, ?, ?)";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, prod.getCodigo());
			stm.setString(2, prod.getNome());
			stm.setString(3, prod.getDescricao());
			stm.setInt(4, prod.getQtd());
			stm.setDouble(5, prod.getValor());
			stm.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Produto> buscarProduto(String cod) {
		List<Produto> lista = new ArrayList<>();
		try{
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			String sql = "SELECT * FROM estoquehotel "
					+ " WHERE  id_produto = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1,cod);
			ResultSet resultadoBusca = stm.executeQuery();
			while (resultadoBusca.next()){
				Produto p = pegarProdutos(resultadoBusca);
				lista.add(p);
			}
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		
		return lista;
	}

	@Override
	public void apagar(String codigo) {
		try {
			Connection con = 
					DriverManager.getConnection(URL, USER, PASS);
			String sql = "DELETE FROM estoquehotel "
					+ "WHERE id_produto = ?";
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

	public List<Produto> atualizarTabelas() {
		List<Produto> lista = new ArrayList<>();
		try{
			Connection con = DriverManager.getConnection(URL,USER,PASS);
			String sql = "SELECT id_produto, nome_produto, "
					+ "descricao, quantidade, valor FROM estoquehotel ";
			PreparedStatement stm = con.prepareStatement(sql);
			ResultSet resultadoBusca = stm.executeQuery();
			while(resultadoBusca.next()){
				Produto p = pegarProdutos(resultadoBusca);
				lista.add(p);
			}
			
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return lista;
	}
	
	private Produto pegarProdutos(ResultSet resultadoBusca){
		Produto p = new Produto();
		try{	
			p.setCodigo(resultadoBusca.getString("id_produto"));
			p.setNome(resultadoBusca.getString("nome_produto"));
			p.setDescricao(resultadoBusca.getString("descricao"));
			p.setQtd(resultadoBusca.getInt("quantidade"));
			p.setValor(resultadoBusca.getDouble("valor"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	public void atualizar(Produto prod, String codigo) {
		try {
			Connection con = DriverManager.getConnection(URL,USER,PASS);
			String sql = "UPDATE estoquehotel SET nome_produto = ?, "
					+ "descricao = ?, quantidade = ?,"
					+ "valor = ? where id_produto = ?";
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, prod.getNome());
			stm.setString(2, prod.getDescricao());
			stm.setInt(3, prod.getQtd());
			stm.setDouble(4, prod.getValor());
			stm.setString(5, codigo);
			stm.executeUpdate();
			stm.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
