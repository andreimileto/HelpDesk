/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import apoio.ConexaoBD;
import apoio.IDAO;
import entidade.Projeto;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Mileto
 */
public class projetoDAO implements IDAO{

    Projeto projeto;
    
    @Override
    public boolean salvar(Object o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            //executeupdate = insert,update, delete
            //query = select

            if (projeto.getId() == 0) {

                String sql = "INSERT INTO projeo VALUES ("
                        + "DEFAULT," + "'" + projeto.getDescricao() + "',"
                        + "'" + projeto.getDescricao()+ "'"
                        + ")";
                int resultado = st.executeUpdate(sql);
            } else {
                String sql = "UPDATE projeto set descricao='" + projeto.getDescricao()
                        + "', ativo ='" + projeto.getSituacao()
                        + "' where id =" + projeto.getId();

                int resultado = st.executeUpdate(sql);

            }
            return true;
        } catch (Exception e) {

            System.out.println("Erro salvar Projeto = " + e);
        }
        return false;
    }

    @Override
    public ArrayList<Object> consultar(Object o) {
       this.projeto = projeto;
       Projeto projeto = (Projeto) o;
       
        ArrayList<Object> projetos = new ArrayList<>();
        //Projeto projeto = (Projeto) o;  
         
        
        //consulta quando o filtro seja de ativo ou inativo e com descrição específica
        if (projeto.getSituacao()== 'A' || projeto.getSituacao()== 'I') {
            try {
                Statement st = ConexaoBD.getInstance().getConnection().createStatement();
                String sql = "select * from  projeto "
                        + "where descricao ilike '" + projeto.getDescricao() + "%' "
                        + "and situacao = '" + projeto.getSituacao() + "' "
                        + "order by id";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    Projeto proj = new Projeto();
                    proj.setId(resultado.getInt("id"));
                    proj.setDescricao(resultado.getString("descricao"));
                    proj.setSituacao(resultado.getString("situacao").charAt(0));
                    projetos.add(proj);
                }
            } catch (Exception e) {
                System.out.println("Erro ao consultar projeto " + e);
            }
            
            //consulta todos e com a descrição específica
        }else {

            try {
                Statement st = ConexaoBD.getInstance().getConnection().createStatement();
                String sql = "select * from  projeto "
                        + "where  descricao ilike '" + projeto.getDescricao() + "%' "
                        + "order by id";
                ResultSet resultado = st.executeQuery(sql);
                while (resultado.next()) {
                    Projeto proj = new Projeto();
                    proj.setId(resultado.getInt("id"));
                    proj.setDescricao(resultado.getString("descricao"));
                    proj.setSituacao(resultado.getString("situacao").charAt(0));
                    projetos.add(proj);
                }
            } catch (Exception e) {
                System.out.println("Erro ao consultar projeto " + e);
            }

        }
        return projetos;

    }
    
}
