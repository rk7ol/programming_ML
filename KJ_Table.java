package Database.OP;

import Database.DB;
import Database.OV.KJ_OV;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class KJ_Table implements DB<KJ_OV> {
    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://47.95.210.89:3306/myDB";
    // 数据库的用户名与密码
    static final String USER = "myDB";
    static final String PASS = "123456";
    static Connection conn = null;//连接对象

    public KJ_Table() throws Exception {//数据库的连接
        // 注册 JDBC 驱动
        Class.forName(JDBC_DRIVER);
        //连接数据库
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
    }

    @Override
    public boolean insert(KJ_OV Table_OV) throws Exception {
        //创建Statement 对象
        Statement stmt = null;
        // 执行查询
        stmt = conn.createStatement();
        String sql;
        int rs;
        sql = "INSERT INTO `卡机表` (`卡机ID`, `注册时间`, `菜品个数`) VALUES (\'" + Table_OV.getID() + "\',\'" + Table_OV.getTime() + "\',\'" + Table_OV.getNum() + "\');";
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
    public List<KJ_OV> select() throws Exception {
        //创建Statement 对象
        Statement stmt = null;
        // 执行查询
        stmt = conn.createStatement();
        String sql;
        sql = "SELECT * FROM 卡机表";
        ResultSet rs = stmt.executeQuery(sql);//查询结果
        List<KJ_OV> list = new ArrayList<>();//保存查询结果
        //展开结果集数据库
        while (rs.next()) {
            KJ_OV row = new KJ_OV();//创建映射
            row.setID(rs.getString("卡机ID"));
            row.setTime(rs.getString("注册时间"));
            row.setNum(Integer.parseInt(rs.getString("菜品个数")));
            list.add(row);
        }
        // 完成后关闭
        rs.close();
        stmt.close();
        return list;
    }

    @Override
    public boolean delete(KJ_OV Table_OV) throws Exception {
        //创建Statement 对象
        Statement stmt = null;
        // 执行查询
        stmt = conn.createStatement();
        String sql;
        sql = "DELETE FROM `卡机表` WHERE `卡机表`.`卡机ID` = \'" + Table_OV.getID() + "\';";
        int rs = 0;
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
}//提供卡机表的insert,delete,select
