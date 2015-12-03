package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import gasstationgui.model.GasStation;

/**
 *
 * @author Giovani
 */
public class GasStationDAO {
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

    public void inserir(GasStation g) {
        Connection conexao = abrir();
	try {
		PreparedStatement ps = conexao.prepareStatement(
				"INSERT INTO gasstation (id, cnpj, razaosocial, nomefantasia,"
                              + " bandeira, endereco, bairro, cep, imagem) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
		ps.setInt(1, g.getID());
		ps.setString(2, g.getCNPJ());
                ps.setString(3, g.getRazaoSocial());
                ps.setString(4, g.getNomeFantasia());
                ps.setString(5, g.getBandeira());
                ps.setString(6, g.getEndereco());
                ps.setString(7, g.getBairro());
                ps.setString(8, g.getCEP());
                ps.setString(9, g.getImagem());
		ps.execute();
		ps.close();
		conexao.close();
	} catch (SQLException e) {
	}
    }
        
    public int GetContGasStation() {
        int cont=0;
        Connection conexao = abrir();
	try {
            Statement s = conexao.createStatement();
            ResultSet rs = s.executeQuery(
                                "SELECT * FROM gasstation ORDER BY contgasstation DESC");
            if (rs.next()) 
            {
                 cont = rs.getInt("contgasstation");
            }
            rs.close();
	    conexao.close();
	} catch (SQLException e) {
	}
        return cont;
    }

    public void alterar(GasStation g) {
	Connection conexao = abrir();
	try {
		PreparedStatement ps = conexao.prepareStatement(
			"UPDATE gasstation SET cnpj= ?, razaosocial= ?, nomefantasia= ?,"
                      + "bandeira= ?, endereco= ?, bairro= ?, cep= ?, imagem= ? WHERE id = ?");
		ps.setString(1, g.getCNPJ());
                ps.setString(2, g.getRazaoSocial());
                ps.setString(3, g.getNomeFantasia());
                ps.setString(4, g.getBandeira());
                ps.setString(5, g.getEndereco());
                ps.setString(6, g.getBairro());
                ps.setString(7, g.getCEP());
                ps.setString(8, g.getImagem());
                ps.setInt(9, g.getID());
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
			"DELETE FROM gasstation WHERE id = ?");
		ps.setInt(1, ID);
		ps.execute();
		ps.close();
		conexao.close();
	} catch (SQLException e) {
	}
    }

    public Collection<GasStation> buscarTodos() {
	Connection conexao = abrir();
	Collection<GasStation> gstations = new ArrayList<GasStation>();
	try {
		Statement s = conexao.createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM gasstation");
		while (rs.next()) {
			GasStation temp = new GasStation(false);
			temp.setID(rs.getInt("id"));
			temp.setCNPJ(rs.getString("cnpj"));
                        temp.setRazaoSocial(rs.getString("razaosocial"));
                        temp.setNomeFantasia(rs.getString("nomefantasia"));
                        temp.setBandeira(rs.getString("bandeira"));
                        temp.setEndereco(rs.getString("endereco"));
                        temp.setBairro(rs.getString("bairro"));
                        temp.setCEP(rs.getString("cep"));
                        temp.setImagem(rs.getString("imagem"));
			gstations.add(temp);
		}
		rs.close();
		conexao.close();
	} catch (SQLException e) {
	}
	return gstations;
    }
    
    public void createTable() {
        Statement s = null;
        Connection conexao = abrir();
        try {
            s = conexao.createStatement();
            s.executeUpdate("CREATE TABLE gasstation("
                    + "contgasstation SERIAL PRIMARY KEY,"
                    + "id integer NOT NULL,"
                    + "cnpj character varying(50) NOT NULL,"
                    + "razaosocial character varying(50) NOT NULL,"
                    + "nomefantasia character varying(50) NOT NULL,"
                    + "bandeira character varying(50) NOT NULL,"
                    + "endereco character varying(50) NOT NULL,"
                    + "bairro character varying(50) NOT NULL,"
                    + "cep character varying(50) NOT NULL,"
                    + "imagem character varying(50) NOT NULL);");
            conexao.close();
	} catch (SQLException e) {
	}
    }
    
    public void dropTable(){
        Statement s = null;
        Connection conexao = abrir();
        try {
            s = conexao.createStatement();
            s.executeUpdate("DROP TABLE IF EXISTS gasstation");
	} catch (SQLException e) {
        }
    }
}
