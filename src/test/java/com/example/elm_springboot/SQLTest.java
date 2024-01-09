package com.example.elm_springboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootTest
public class SQLTest {
    private Connection con;
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/elm_db?useSSL=false&&characterEncoding=utf-8","root","123465");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/elm_db?useSSL=false&&characterEncoding=utf-8","root","123465");
        String sql = "insert into business"+
        "(business_address, business_explain, business_img, business_name, delivery_price, order_type_id, remarks, star_price)"+
        "values ('软件园','各种饺子','http://localhost:8000/api/Img/sj01.png','麦当劳麦乐送（全运路店）',3,6,'2个活动',15)," +
                "('全运路','小锅米饭','http://localhost:8000/api/Img/sj02.png','小锅饭豆腐馆（全运店）',3,7,'2个活动',15)," +
                "('全运路','麦当劳','http://localhost:8000/api/Img/sj03.png','麦当劳麦乐送（全运路店）',4,4,'1个活动',18)," +
                "('浑南路','拌饭','http://localhost:8000/api/Img/sj04.png','米村拌饭（浑南店）',2,6,'',14)," +
                "('中海康城路','各种烧烤','http://localhost:8000/api/Img/sj05.png','申记串道（中海康城店）',3,10,'2个活动',20)," +
                "('全运路','炒菜配饭','http://localhost:8000/api/Img/sj06.png','半亩良田',3,1,'2个活动',18)";
        Statement statement = con.createStatement();
        statement.executeUpdate(sql);
        statement.close();
        con.close();
        System.out.println("successful");
    }

//    @Test
//    void addFood() throws SQLException {
//        String sql = "insert into food ( food_explain, food_img, food_name, food_price, business_business_id)
//          VALUES ('非常好吃','http://localhost:8000/api/Img/sp01.png','纯肉鲜肉（水饺）',15,1),
//          ('非常好吃','http://localhost:8000/api/Img/sp02.png','玉米鲜肉（水饺）',16,1),
//          ('非常好吃','http://localhost:8000/api/Img/sp03.png','虾仁三鲜（蒸饺）',15,1),
//          ('非常好吃','http://localhost:8000/api/Img/sp04.png','素三鲜（蒸饺）',15,1),
//          ('非常好吃','http://localhost:8000/api/Img/sp05.png','角爪鸡蛋（蒸饺）',15,1),
//          ('非常好吃','http://localhost:8000/api/Img/sp06.png','小白菜肉（水饺）',15,1);";
//        Statement statement = con.createStatement();
//        statement.executeUpdate(sql);
//        statement.close();
//        con.close();
//        System.out.println("successful");
//
//    }
}
