package Repository;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Hamburgueria;

public class HamburgueriaRepository {

    public void insert(Hamburgueria hamburgueria) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();

        try {
            String consulta = "INSERT INTO hamburgueria (codigo, nome, cpf, sexo, formaPagamento, item, preco, quantidade, totalPedido)"
                    + "VALUES (NEXTVAL('hamburgueria_seq'),?,?,?,?,?,?,?,?)";

            PreparedStatement stm;
            stm = conn.prepareStatement(consulta);

            stm.setString(1, hamburgueria.getNome());
            stm.setString(2, hamburgueria.getCpf());
            stm.setString(3, hamburgueria.getSexo());
            stm.setString(4, hamburgueria.getFormaPagamento());
            stm.setString(5, hamburgueria.getItem());
            stm.setDouble(6, hamburgueria.getPreco());
            stm.setInt(7, hamburgueria.getQuantidade());
            stm.setDouble(8, hamburgueria.getTotalPedido());

            stm.execute();

        } catch (Exception e) {

        } finally {
            conexao.desconectar(conn);
        }
    }

    public ArrayList<Hamburgueria> consulta() throws SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        ArrayList<Hamburgueria> hamburguerias = new ArrayList<>();

        String consulta = "SELECT * FROM hamburgueria";

        Statement stm = conn.createStatement();
        ResultSet resultado = stm.executeQuery(consulta);

        try {
            while (resultado.next()) {
                Hamburgueria hamburgueria = new Hamburgueria();
                hamburgueria.setCodigo(resultado.getInt("codigo"));
                hamburgueria.setNome(resultado.getString("nome"));
                hamburgueria.setCpf(resultado.getString("cpf"));
                hamburgueria.setSexo(resultado.getString("sexo"));
                hamburgueria.setFormaPagamento(resultado.getString("formaPagamento"));
                hamburgueria.setItem(resultado.getString("item"));
                hamburgueria.setPreco(resultado.getDouble("preco"));
                hamburgueria.setQuantidade(resultado.getInt("quantidade"));
                hamburgueria.setTotalPedido(resultado.getDouble("totalpedido"));
                hamburguerias.add(hamburgueria);
            }

        } catch (Exception e) {
            System.out.println("Não conseguiu consultar a tabela hamburgueria");

        } finally {
            conexao.desconectar(conn);
        }

        return hamburguerias;
    }

    public void remover(int codigo) throws SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();

        String consulta = "DELETE FROM hamburgueria WHERE codigo = ?";

        PreparedStatement stm;
        stm = conn.prepareStatement(consulta);
        stm.setInt(1, codigo);
        stm.execute();

        try {

        } catch (Exception e) {
            System.out.println("Não foi possível remover");

        } finally {
            conexao.desconectar(conn);
        }
    }

    public void finalizar(Hamburgueria hamburgueria) throws Exception {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();

        try {

            String consulta = "UPDATE hamburgueria SET nome = ?, cpf = ?, sexo = ?, formapagamento = ?, item = ?, preco = ?, quantidade = ?, totalPedido = ? WHERE codigo = ?";

            PreparedStatement stm;
            stm = conn.prepareStatement(consulta);

            stm.setString(1, hamburgueria.getNome());
            stm.setString(2, hamburgueria.getCpf());
            stm.setString(3, hamburgueria.getSexo());
            stm.setString(4, hamburgueria.getFormaPagamento());
            stm.setString(5, hamburgueria.getItem());
            stm.setDouble(6, hamburgueria.getPreco());
            stm.setInt(7, hamburgueria.getQuantidade());
            stm.setDouble(8, hamburgueria.getTotalPedido());
            stm.setInt(9, hamburgueria.getCodigo());

            stm.execute();

        } catch (Exception e) {
            System.out.println("Não foi possível finalizar");

        } finally {
            conexao.desconectar(conn);
        }
    }

    public Hamburgueria findById(int codigo) throws SQLException {
        Hamburgueria hamburgueria = new Hamburgueria();
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();

        String consulta = "SELECT * FROM hamburgueria WHERE codigo =" + codigo;

        Statement stm = conn.createStatement();
        ResultSet resultado = stm.executeQuery(consulta);

        try {
            while (resultado.next()) {
                hamburgueria.setCodigo(resultado.getInt("codigo"));
                hamburgueria.setNome(resultado.getString("nome"));
                hamburgueria.setCpf(resultado.getString("cpf"));
                hamburgueria.setSexo(resultado.getString("sexo"));
                hamburgueria.setFormaPagamento(resultado.getString("formaPagamento"));
                hamburgueria.setItem(resultado.getString("item"));
                hamburgueria.setPreco(resultado.getDouble("preco"));
                hamburgueria.setQuantidade(resultado.getInt("quantidade"));
                hamburgueria.setTotalPedido(resultado.getDouble("totalpedido"));
            }
        } catch (Exception e) {
            System.out.println("Não foi possível consultar a tabela hamburgueria");

        } finally {
            conexao.desconectar(conn);
        }
        return hamburgueria;
    }

}