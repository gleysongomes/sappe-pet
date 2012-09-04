package br.ufc.si.pet.sappe.dao.config;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import java.io.Reader;

/**
 *
 * @author jefferson
 */
public class PostGresMapConfig {

    private static final SqlMapClient sqlMapClient;

    static {
        try {
            //Definindo o caminho para o SqlMapConfig e criando o reader
            String res = "SqlMapConfig.xml";
            Reader reader = Resources.getResourceAsReader(res);

            //Recuperando o client para o SqlMap
            sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }



}

