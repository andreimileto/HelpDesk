/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import apoio.*;
import java.util.ArrayList;

/**
 *
 * @author Mileto
 */
public interface IDAO {

    ArrayList<Object> objs = new ArrayList<>();
    Object obj = new Object();

    public boolean salvar(Object o);
    public ArrayList<Object> consultar(Object o);

}
