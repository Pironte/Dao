/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkginterface;

import java.awt.List;
import java.util.ArrayList;

/**
 *
 * @author Cliente
 */
public interface IDao {
    public int inserir(Contato contato);
    public int deletar(Contato contato);
    public int atualizar(Contato contato);
    public ArrayList<Contato> listar();
    public Contato pesquisarPorId(int id);
    public ArrayList<Contato> pesquisarPorNome(String nome);
}
