/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import apoio.ConexaoBD;
import apoio.IDAO;
import entidade.Projeto;
import entidade.Usuario;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author Mileto
 */
public class usuarioDAO implements IDAO{
    Usuario usuario;
    @Override
    public boolean salvar(Object o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            //executeupdate = insert,update, delete
            //query = select

            if (usuario.getId() == 0) {

                String sql = "INSERT INTO usuario VALUES ("
                        + "DEFAULT," + "'" + usuario.getNome()+ "',"
                        + "'" + usuario.getLogin()+ "'"
                        + "'" + usuario.getSenha()+ "'"
                        + "'T'"
                        + ")";
                int resultado = st.executeUpdate(sql);
            } else {
                String sql = "UPDATE usuario set nome ='" + usuario.getNome()+"', "
                        + "login = '"+usuario.getLogin()+"', "
                        + "senha = '"+usuario.getSenha()+"', "
                        + "ativo ='" + usuario.getSituacao()+"' "
                        + "where id =" + usuario.getId();

                int resultado = st.executeUpdate(sql);

            }
            return true;
        } catch (Exception e) {

            System.out.println("Erro salvar usuario = " + e);
        }
        return false;
    }

    @Override
    public ArrayList<Object> consultar(Object o) {
         this.usuario = usuario;
       Usuario usuario = (Usuario) o;
       
        ArrayList<Object> usuarios = new ArrayList<>();
        //Projeto projeto = (Projeto) o;  
         
        
        //consulta quando o filtro seja de ativo ou inativo e com descrição específica
        if (usuario.getSituacao()== 'A' || usuario.getSituacao()== 'I') {
            try {
                Statement st = ConexaoBD.getInstance().getConnection().createStatement();
                String sql = "select * from  usuario "
                        + "where nome ilike '" + usuario.getNome()+ "%' "
                        + "and situacao = '" + usuario.getSituacao() + "' "
                        + "order by id";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    Usuario user = new Usuario();
                    user.setId(resultado.getInt("id"));
                    user.setNome(resultado.getString("nome"));
                    user.setLogin(resultado.getString("login"));
                    user.setSenha(resultado.getString("senha"));
                    user.setSituacao(resultado.getString("situacao").charAt(0));
                    usuarios.add(user);
                }
            } catch (Exception e) {
                System.out.println("Erro ao consultar usuario " + e);
            }
            
            //consulta todos e com a descrição específica
        }else {

            try {
                Statement st = ConexaoBD.getInstance().getConnection().createStatement();
                String sql = "select * from  usuario "
                        + "where  nome ilike '" + usuario.getNome()+ "%' "
                        + "order by id";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    Usuario user = new Usuario();
                    user.setId(resultado.getInt("id"));
                    user.setNome(resultado.getString("nome"));
                    user.setLogin(resultado.getString("login"));
                    user.setSenha(resultado.getString("senha"));
                    user.setSituacao(resultado.getString("situacao").charAt(0));
                    usuarios.add(user);
                }
            } catch (Exception e) {
                System.out.println("Erro ao consultar usuario " + e);
            }

        }
        return usuarios;
    }
    
}
