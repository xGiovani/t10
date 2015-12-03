package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import gasstationgui.model.Info;

// DELETE FROM infolog WHERE idfuel = ?

/**
 *
 * @author Giovani
 */
public class InfoLogDAO {
    public Connection abrir() {
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

    public void inserir(Info i) {
	Connection conexao = abrir();
	try {
		PreparedStatement ps = conexao.prepareStatement(
				"INSERT INTO infolog (idposto, idfuel, datacoleta, "
                                        + "preco) VALUES (?, ?, ?, ?)");
		ps.setInt(1, i.getIDGStation());
                ps.setInt(2, i.getIDFuel());
                ps.setString(3, i.getDataColeta());
                ps.setDouble(4, i.getPreco());
		ps.execute();
		ps.close();
		conexao.close();
	} catch (SQLException e){
	}
    }
    
    public void excluirALL(int IDposto) {
	Connection conexao = abrir();
	try {
		PreparedStatement ps = conexao.prepareStatement(
					"DELETE FROM infolog WHERE idposto = ?");
		ps.setInt(1, IDposto);
		ps.execute();
		ps.close();
		conexao.close();
	} catch (SQLException e) {
	}
   }
    public void excluirALL2(int IDfuel) {
	Connection conexao = abrir();
	try {
		PreparedStatement ps = conexao.prepareStatement(
					"DELETE FROM infolog WHERE idfuel = ?");
		ps.setInt(1, IDfuel);
		ps.execute();
		ps.close();
		conexao.close();
	} catch (SQLException e) {
	}
   }
    
    public Collection<Info> buscarTodos() {
	Connection conexao = abrir();
	Collection<Info> info = new ArrayList<Info>();
	try {
		Statement s = conexao.createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM infolog");
		while (rs.next()) {
                        Info temp = new Info();
			temp.setIDGasStation(rs.getInt("idposto"));
                        temp.setIDFuel(rs.getInt("idfuel"));
                        temp.setDataColeta(rs.getString("datacoleta"));
                        temp.setPreco(rs.getDouble("preco"));
			info.add(temp);
		}
		rs.close();
		conexao.close();
	} catch (SQLException e) {
	}
	return info;
    }  
    
    public void createTable(){
        Statement s = null;
        Connection conexao = abrir();
        try {
            s = conexao.createStatement();
            s.executeUpdate("CREATE TABLE infolog("
                    + "idposto INTEGER NOT NULL,"
                    + "idfuel INTEGER NOT NULL,"
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
            s.executeUpdate("DROP TABLE IF EXISTS infolog");
	} catch (SQLException e) {
        }
    }
}
