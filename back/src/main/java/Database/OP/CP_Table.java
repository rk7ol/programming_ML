package Database.OP;

import Database.DB;
import Database.OV.CP_OV;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//(工厂类)
public class CP_Table implements DB<CP_OV> {
    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://47.95.210.89:3306/myDB";
    // 数据库的用户名与密码
    static final String USER = "myDB";
    static final String PASS = "123456";
    static Connection conn = null;//连接对象

    public CP_Table() throws Exception {//数据库的连接
        // 注册 JDBC 驱动
        Class.forName(JDBC_DRIVER);
        //连接数据库
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
    }


    @Override
    //数据库插入,插入成功返回true,否则返回false(已经存在或者参数错误)
    public boolean insert(CP_OV Table_OV) throws Exception {
        //创建Statement 对象
        Statement stmt = null;
        // 执行查询
        stmt = conn.createStatement();
        String sql;
        int rs;
        sql = "INSERT INTO `菜品表` (`菜品ID`, `菜品名称`, `售卖方式`, `价格`) VALUES (\'" + Table_OV.getID() + "\',\'" + Table_OV.getName() + "\',\'" + Table_OV.getWay() + "\',\'" + Table_OV.getPrice() + "\');";
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
    //数据库查询,返回数据库中的所有的菜品
    public List<CP_OV> select() throws Exception {
        //创建Statement 对象
        Statement stmt = null;
        // 执行查询
        stmt = conn.createStatement();
        String sql;
        sql = "SELECT * FROM 菜品表";
        ResultSet rs = stmt.executeQuery(sql);//查询结果
        List<CP_OV> list = new ArrayList<>();//保存查询结果
        //展开结果集数据库
        while (rs.next()) {
            CP_OV row = new CP_OV();//创建映射
            row.setID(rs.getString("菜品ID"));
            row.setName(rs.getString("菜品名称"));
            row.setWay(rs.getString("售卖方式"));
            row.setPrice(Integer.parseInt(rs.getString("价格")));
            list.add(row);
        }
        // 完成后关闭
        rs.close();
        stmt.close();
        return list;
    }

    @Override
    //数据库删除,成功返回true,失败返回false(不存在),只需要菜品名称
    public boolean delete(CP_OV Table_OV) throws Exception {
        //创建Statement 对象
        Statement stmt = null;
        // 执行查询
        stmt = conn.createStatement();
        String sql;
        sql = "DELETE FROM `菜品表` WHERE `菜品表`.`菜品名称` = \'" + Table_OV.getName() + "\';";
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
}//提供菜品表的insert,delete,select
