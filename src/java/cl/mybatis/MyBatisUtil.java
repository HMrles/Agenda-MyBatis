/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.mybatis;

import java.io.IOException;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author Hugo
 */
public class MyBatisUtil {
    private String resource="cl/mybatis/mybatis-config.xml"; //El archivo que se teien que cargar
    private SqlSession session = null;
    
    public SqlSession getSession(){
        try {
            Reader reader  = Resources.getResourceAsReader(resource);
            SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
            session = sqlMapper.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return session;
    }
}
