package com.example.datasourcetest;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.sql.DataSource;

@SpringBootTest
public class DataSouceTest {
    @Autowired
    DataSource dataSource;
    @Test
    public void getCon() throws Exception{
        System.out.println(dataSource.getConnection("root","ywj990623"));
    }

}
