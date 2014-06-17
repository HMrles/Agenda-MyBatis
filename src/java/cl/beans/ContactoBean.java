/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.beans;

import cl.mybatis.MyBatisUtil;
import cl.mybatis.pojos.Contacto;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Hugo
 */
@ManagedBean
@RequestScoped
public class ContactoBean {
    private Contacto contacto;


    public ContactoBean() {
        contacto = new Contacto();
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }
    
    
    public String guardar(){
        //Asignar un id por defecto
        contacto.setId(-1);// si es -1 es autoincrementable
        SqlSession session = new MyBatisUtil().getSession();
        if(session != null){
            try{
                session.insert("Contacto.guardarContacto", contacto); //el nombre del namespace
                session.commit();
            }finally{
                session.close();
            }
        }else{
            System.out.println("Error");
        }
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Contacto Creado"));
        return "index";   
    }
    
    public List<Contacto>getContactos(){
        List<Contacto> lista = null;
        SqlSession session = new MyBatisUtil().getSession();
        if(session != null){
            try{
                lista = session.selectList("Contacto.obtenerContactos"); //el nombre del namespace
                
            }finally{
                session.close();
            }
        }else{
            System.out.println("Error");
        }
        return lista;
    }
    
    
    
}
