package Database.OP;

import Database.DB;
import Database.OV.KC_OV;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class KC_Table implements DB<KC_OV> {
    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://47.95.210.89:3306/myDB";
    // 数据库的用户名与密码
    static final String USER = "myDB";
    static final String PASS = "123456";
    static Connection conn = null;//连接对象

    public KC_Table() throws Exception {//数据库的连接
        // 注册 JDBC 驱动
        Class.forName(JDBC_DRIVER);
        //连接数据库
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
    }

    @Override
    public boolean insert(KC_OV Table_OV) throws Exception {
        //创建Statement 对象
        Statement stmt = null;
        // 执行查询
        stmt = conn.createStatement();
        String sql;
        int rs;
        sql = "INSERT INTO `卡机-菜品表` (`卡机ID`, `菜品ID`) VALUES (\'" + Table_OV.getK_ID() + "\',\'" + Table_OV.getC_ID() + "\');";
        try {
            rs = stmt.executeUpdate(sql);
        } catch (Exception e) {//插入失败时
            rs = 0;
        }
        boolean result = false;
        if (rs == 1)
            result = true;
        // 完成后关闭
        stmt.close();
        return result;
    }

    @Override
    public List<KC_OV> select() throws Exception {
        //创建Statement 对象
        Statement stmt = null;
        // 执行查询
        stmt = conn.createStatement();
        String sql;
        sql = "SELECT * FROM `卡机-菜品表`";
        ResultSet rs = stmt.executeQuery(sql);//查询结果
        List<KC_OV> list = new ArrayList<>();//保存查询结果
        //展开结果集数据库
        while (rs.next()) {
            KC_OV row = new KC_OV();//创建映射
            row.setK_ID(rs.getString("卡机ID"));
            row.setC_ID(rs.getString("菜品ID"));
            list.add(row);
        }
        // 完成后关闭
        rs.close();
        stmt.close();
        return list;
    }

    @Override
    public boolean delete(KC_OV Table_OV) throws Exception {//需要卡机id和菜品id
        //创建Statement 对象
        Statement stmt = null;
        // 执行查询
        stmt = conn.createStatement();
        String sql;
        sql = "DELETE FROM `卡机-菜品表` WHERE  `卡机-菜品表`.`菜品ID` = \'" + Table_OV.getC_ID() + "\';";
        int rs;
        try {
            rs = stmt.executeUpdate(sql);
        } catch (Exception e) {//删除失败
            rs = 0;
        }
        boolean result = false;
        if (rs == 1)
            result = true;
        // 完成后关闭
        stmt.close();
        return result;
    }
}//提供卡机-菜品表的insert,delete,select
