package database;

import gasstationgui.model.Fuel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import gasstationgui.model.Info;

/**
 *
 * @author Giovani
 */
public class FuelDAO {
    public Connection abrir(){
	Connection c = null;
	try {
		Class.forName(DbUtils.JDBC_DRIVER);
	} catch (ClassNotFoundException e) {
		System.out.println("Nao encontrou o driver chamado " + DbUtils.JDBC_DRIVER + " na memoria");
	}
	try {
		c = DriverManager.getConnection(DbUtils.URL_CONEXAO, DbUtils.USUARIO, DbUtils.SENHA);
	} catch (SQLException e) {
	}
	return c;
    }

    public void inserir(Fuel f) {
	Connection conexao = abrir();
	try {
		PreparedStatement ps = conexao.prepareStatement(
				"INSERT INTO fuel (id, idposto, tipo, datacoleta,"
                                               + " preco) VALUES (?, ?, ?, ?, ?)");
		ps.setInt(1, f.getID());
		ps.setInt(2, f.getIDGStation());
                ps.setString(3, f.getTipo());
                ps.setString(4, f.getInfo().getDataColeta());
                ps.setDouble(5, f.getInfo().getPreco());
		ps.execute();
		ps.close();
		conexao.close();
	} catch (SQLException e){
	}
    }
        
    public int GetContFuel() {
        int cont=0;
	Connection conexao = abrir();
	try {
                Statement s = conexao.createStatement();
		ResultSet rs = s.executeQuery(
                               "SELECT * FROM fuel ORDER BY contfuel DESC");
                if (rs.next()) 
                {
                    cont = rs.getInt("contfuel");
                }
                rs.close();
		conexao.close();
		} catch (SQLException e) {
		}
            return cont;
	}

    public void alterar(Info i) {
	Connection conexao = abrir();
	try {
		PreparedStatement ps = conexao.prepareStatement(
					"UPDATE fuel SET datacoleta= ?, preco= ? WHERE id = ?");
                ps.setString(1, i.getDataColeta());
                ps.setDouble(2, i.getPreco());
                ps.setInt(3, i.getIDFuel());
		ps.execute();
		ps.close();
		conexao.close();
	} catch (SQLException e) {
	}
    }

    public void excluir(int ID) {
	Connection conexao = abrir();
	try {
		PreparedStatement ps = conexao.prepareStatement(
					"DELETE FROM fuel WHERE id = ?");
		ps.setInt(1, ID);
		ps.execute();
		ps.close();
		conexao.close();
	} catch (SQLException e) {
	}
   }
        
    public void excluirAll(int idposto) {
	Connection conexao = abrir();
	try {
		PreparedStatement ps = conexao.prepareStatement(
					"DELETE FROM fuel WHERE idposto = ?");
		ps.setInt(1, idposto);
		ps.execute();
		ps.close();
		conexao.close();
	} catch (SQLException e) {
	}
    }

    public Collection<Fuel> buscarTodos() {
	Connection conexao = abrir();
	Collection<Fuel> fuels = new ArrayList<Fuel>();
	try {
		Statement s = conexao.createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM fuel");
		while (rs.next()) {
			Fuel temp = new Fuel();
                        Info info = new Info();
			temp.setID(rs.getInt("id"));
                        temp.setIDGStation(rs.getInt("idposto"));
                        temp.setTipo(rs.getString("tipo"));
                        info.setIDFuel(rs.getInt("id"));
                        info.setDataColeta(rs.getString("datacoleta"));
                        info.setPreco(rs.getFloat("preco"));
                        temp.setInfo(info);
			fuels.add(temp);
		}
		rs.close();
		conexao.close();
	} catch (SQLException e) {
	}
	return fuels;
    }  
    
    public void createTable(){
        Statement s = null;
        Connection conexao = abrir();
        try {
            s = conexao.createStatement();
            s.executeUpdate("CREATE TABLE fuel("
                    + "contfuel SERIAL PRIMARY KEY,"
                    + "id INTEGER NOT NULL,"
                    + "idposto INTEGER NOT NULL,"
                    + "tipo character varying(50) NOT NULL,"
                    + "datacoleta character varying(50) NOT NULL,"
                    + "preco DOUBLE PRECISION NOT NULL);");
            conexao.close();
	} catch (SQLException e) {
	}
    }
    
    public void dropTable(){
        Statement s = null;
        Connection conexao = abrir();
        try {
            s = conexao.createStatement();
            s.executeUpdate("DROP TABLE IF EXISTS fuel");
	} catch (SQLException e) {
        }
    }
}
