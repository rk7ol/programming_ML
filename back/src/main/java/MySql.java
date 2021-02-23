import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

interface DB<T>{//泛型接口(抽象工厂)
    boolean insert(T Table_OV) throws Exception;
    boolean delete(T Table_OV) throws Exception;
    List<T> select() throws Exception;
}//泛型接口(抽象工厂)

//表的映射(每一行信息)
class CP_OV{//菜品表的映射
    private String ID="";//菜品ID
    private String Name="";//菜品名称
    private String Way="";//售卖方式
    private int Price;//价格
    public CP_OV(){};
    public CP_OV(String id,String name,String way,int price){
        this.ID=id;
        this.Name=name;
        this.Way=way;
        this.Price=price;
    }
    public String getID(){//获取菜品ID
        return ID;
    }

    public String getName() {//获取菜品名称
        return Name;
    }

    public String getWay() {//获取售卖方式
        return Way;
    }

    public int getPrice() {//获取售卖价格
        return Price;
    }

    public void setID(String id) {//设置菜品ID
        this.ID = id;
    }

    public void setName(String name) {//设置菜品名称
        Name = name;
    }

    public void setWay(String way) {//设置售卖方式
        Way = way;
    }

    public void setPrice(int price) {//设置价格
        Price = price;
    }
}//菜品表的映射(提供get,set)

class LS_OV{//流水表的映射
    private String ID="";//流水ID
    private String C_ID="";//卡机ID
    private int Price;//总售价
    public LS_OV(){};
    public LS_OV(String id,String c_id,int price){
        this.ID=id;
        this.C_ID=c_id;
        this.Price=price;
    }
    public String getID(){//获取菜品ID
        return ID;
    }

    public String getC_ID() {//获取菜品名称
        return C_ID;
    }

    public int getPrice() {//获取售卖价格
        return Price;
    }

    public void setID(String id) {//设置菜品ID
        this.ID = id;
    }

    public void setC_ID(String c_id) { this.C_ID =c_id;}

    public void setPrice(int price) {//设置价格
        Price = price;
    }
}//流水表的映射(提供get,set)

class KJ_OV{
    private String ID="";//卡机ID
    private String Time="";//注册时间
    private int Num;//菜品数量
    public KJ_OV(){};
    public KJ_OV(String id,String time,int num){
        this.ID=id;
        this.Time=time;
        this.Num=num;
    }
    public String getID(){//获取菜品ID
        return ID;
    }

    public String getTime() { return Time;}

    public int getNum() {//获取售卖价格
        return Num;
    }

    public void setID(String id) {//设置菜品ID
        this.ID = id;
    }

    public void setTime(String time) { this.Time =time;}

    public void setNum(int num) { Num=num;}
}//卡机表的映射(提供get,set)

class KC_OV{
    private String K_ID="";//卡机ID
    private String C_ID="";//菜品ID
    public KC_OV(){};
    public KC_OV(String k_id,String c_id){
        this.K_ID=k_id;
        this.C_ID=c_id;
    }
    public String getK_ID(){//获取菜品ID
        return K_ID;
    }

    public String getC_ID() { return C_ID;}

    public void setK_ID(String k_id) {//设置菜品ID
        this.K_ID = k_id;
    }

    public void setC_ID(String c_id) { this.C_ID =c_id;}
}//卡机-菜品表的映射(提供get,set)

//(工厂类)
class CP_Table implements DB<CP_OV>{
    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://47.95.210.89:3306/myDB";
    // 数据库的用户名与密码
    static final String USER = "myDB";
    static final String PASS = "123456";
    static Connection conn = null;//连接对象
    CP_Table() throws Exception{//数据库的连接
        // 注册 JDBC 驱动
        Class.forName(JDBC_DRIVER);
        //连接数据库
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
    }


    @Override
    //数据库插入,插入成功返回true,否则返回false(已经存在或者参数错误)
    public boolean insert(CP_OV Table_OV) throws Exception{
        //创建Statement 对象
        Statement stmt = null;
        // 执行查询
        stmt = conn.createStatement();
        String sql;
        int rs;
        sql = "INSERT INTO `菜品表` (`菜品ID`, `菜品名称`, `售卖方式`, `价格`) VALUES (\'"+Table_OV.getID()+"\',\'"+Table_OV.getName()+"\',\'"+Table_OV.getWay()+"\',\'"+Table_OV.getPrice()+"\');";
        try{
            rs = stmt.executeUpdate(sql);
        }catch(Exception e){//插入失败时
            rs=0;
        }
        boolean result=false;
        if(rs==1)
            result=true;
        // 完成后关闭
        stmt.close();
        return result;
    }


    @Override
    //数据库查询,返回数据库中的所有的菜品
    public List<CP_OV> select()throws Exception{
        //创建Statement 对象
        Statement stmt = null;
        // 执行查询
        stmt = conn.createStatement();
        String sql;
        sql = "SELECT * FROM 菜品表";
        ResultSet rs = stmt.executeQuery(sql);//查询结果
        List<CP_OV> list=new ArrayList<>();//保存查询结果
        //展开结果集数据库
        while(rs.next()){
            CP_OV row=new CP_OV();//创建映射
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
    public boolean delete(CP_OV Table_OV) throws Exception{
        //创建Statement 对象
        Statement stmt = null;
        // 执行查询
        stmt = conn.createStatement();
        String sql;
        sql = "DELETE FROM `菜品表` WHERE `菜品表`.`菜品名称` = \'"+Table_OV.getName()+"\';";
        int rs;
        try{
            rs = stmt.executeUpdate(sql);
        }catch(Exception e){//删除失败
            rs=0;
        }
        boolean result=false;
        if(rs==1)
            result=true;
        // 完成后关闭
        stmt.close();
        return result;
    }
}//提供菜品表的insert,delete,select

class LS_Table implements DB<LS_OV> {
    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://47.95.210.89:3306/myDB";
    // 数据库的用户名与密码
    static final String USER = "myDB";
    static final String PASS = "123456";
    static Connection conn = null;//连接对象

    LS_Table() throws Exception{//数据库的连接
        // 注册 JDBC 驱动
        Class.forName(JDBC_DRIVER);
        //连接数据库
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
    }

    @Override
    public boolean insert(LS_OV Table_OV) throws Exception {
        //创建Statement 对象
        Statement stmt = null;
        // 执行查询
        stmt = conn.createStatement();
        String sql;
        int rs;
        sql = "INSERT INTO `流水表` (`流水ID`, `卡机ID`, `总售价`) VALUES (\'"+Table_OV.getID()+"\',\'"+Table_OV.getC_ID()+"\',\'"+Table_OV.getPrice()+"\');";
        try{
            rs = stmt.executeUpdate(sql);
        }catch(Exception e){//插入失败时
            rs=0;
        }
        boolean result=false;
        if(rs==1)
            result=true;
        // 完成后关闭
        stmt.close();
        return result;
    }

    @Override
    public List<LS_OV> select() throws Exception {
        //创建Statement 对象
        Statement stmt = null;
        // 执行查询
        stmt = conn.createStatement();
        String sql;
        sql = "SELECT * FROM 流水表";
        ResultSet rs = stmt.executeQuery(sql);//查询结果
        List<LS_OV> list=new ArrayList<>();//保存查询结果
        //展开结果集数据库
        while(rs.next()){
            LS_OV row=new LS_OV();//创建映射
            row.setID(rs.getString("流水ID"));
            row.setC_ID(rs.getString("卡机ID"));
            row.setPrice(Integer.parseInt(rs.getString("总售价")));
            list.add(row);
        }
        // 完成后关闭
        rs.close();
        stmt.close();
        return list;
    }

    @Override
    public boolean delete(LS_OV Table_OV) throws Exception {//需要流水ID
        //创建Statement 对象
        Statement stmt = null;
        // 执行查询
        stmt = conn.createStatement();
        String sql;
        sql = "DELETE FROM `流水表` WHERE `流水表`.`流水ID` = \'"+Table_OV.getID()+"\';";
        int rs;
        try{
            rs = stmt.executeUpdate(sql);
        }catch(Exception e){//删除失败
            rs=0;
        }
        boolean result=false;
        if(rs==1)
            result=true;
        // 完成后关闭
        stmt.close();
        return result;
    }


}//提供流水表的insert,delete,select

class KJ_Table implements DB<KJ_OV> {
    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://47.95.210.89:3306/myDB";
    // 数据库的用户名与密码
    static final String USER = "myDB";
    static final String PASS = "123456";
    static Connection conn = null;//连接对象

    KJ_Table() throws Exception{//数据库的连接
        // 注册 JDBC 驱动
        Class.forName(JDBC_DRIVER);
        //连接数据库
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
    }

    @Override
    public boolean insert(KJ_OV Table_OV) throws Exception {
        //创建Statement 对象
        Statement stmt = null;
        // 执行查询
        stmt = conn.createStatement();
        String sql;
        int rs;
        sql = "INSERT INTO `卡机表` (`卡机ID`, `注册时间`, `菜品个数`) VALUES (\'"+Table_OV.getID()+"\',\'"+Table_OV.getTime()+"\',\'"+Table_OV.getNum()+"\');";
        try{
            rs = stmt.executeUpdate(sql);
        }catch(Exception e){//插入失败时
            rs=0;
        }
        boolean result=false;
        if(rs==1)
            result=true;
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
        List<KJ_OV> list=new ArrayList<>();//保存查询结果
        //展开结果集数据库
        while(rs.next()){
            KJ_OV row=new KJ_OV();//创建映射
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
        sql = "DELETE FROM `卡机表` WHERE `卡机表`.`卡机ID` = \'"+Table_OV.getID()+"\';";
        int rs=0;
        try{
            rs = stmt.executeUpdate(sql);
        }catch(Exception e){//删除失败
            rs=0;
        }
        boolean result=false;
        if(rs==1)
            result=true;
        // 完成后关闭
        stmt.close();
        return result;
    }
}//提供卡机表的insert,delete,select

class KC_Table implements DB<KC_OV> {
    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://47.95.210.89:3306/myDB";
    // 数据库的用户名与密码
    static final String USER = "myDB";
    static final String PASS = "123456";
    static Connection conn = null;//连接对象

    KC_Table() throws Exception{//数据库的连接
        // 注册 JDBC 驱动
        Class.forName(JDBC_DRIVER);
        //连接数据库
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
    }

    @Override
    public boolean insert(KC_OV Table_OV) throws Exception {
        //创建Statement 对象
        Statement stmt = null;
        // 执行查询
        stmt = conn.createStatement();
        String sql;
        int rs;
        sql = "INSERT INTO `卡机-菜品表` (`卡机ID`, `菜品ID`) VALUES (\'"+Table_OV.getK_ID()+"\',\'"+Table_OV.getC_ID()+"\');";
        try{
            rs = stmt.executeUpdate(sql);
        }catch(Exception e){//插入失败时
            rs=0;
        }
        boolean result=false;
        if(rs==1)
            result=true;
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
        List<KC_OV> list=new ArrayList<>();//保存查询结果
        //展开结果集数据库
        while(rs.next()){
            KC_OV row=new KC_OV();//创建映射
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
        sql =  "DELETE FROM `卡机-菜品表` WHERE `卡机-菜品表`.`卡机ID` = \'"+Table_OV.getK_ID()+"\' AND `卡机-菜品表`.`菜品ID` = \'"+Table_OV.getC_ID()+"\';";
        int rs;
        try{
            rs = stmt.executeUpdate(sql);
        }catch(Exception e){//删除失败
            rs=0;
        }
        boolean result=false;
        if(rs==1)
            result=true;
        // 完成后关闭
        stmt.close();
        return result;
    }
}//提供卡机-菜品表的insert,delete,select

//测试程序,用法示例
public class MySql{
    //测试程序
    public static void main(String[] args) throws Exception{
        //菜品表的演示
        DB CP=new CP_Table();
        List<CP_OV> rs=CP.select();//数据库查询
        for(int i=0;i<rs.size();i++){
            System.out.print(rs.get(i).getID());
            System.out.print("   " +rs.get(i).getName());
            System.out.print("   " +rs.get(i).getWay());
            System.out.print("   " +rs.get(i).getPrice());
            System.out.print("\n");
        }
        CP_OV food=new CP_OV("A003","爆炒牛肉","荤菜",15);
        boolean a=CP.insert(food);
        boolean b=CP.delete(food);
        System.out.println(b+"  \n");

        //流水表的演示
        DB LS=new LS_Table();
        List<LS_OV> rs1=LS.select();//数据库查询
        for(int i=0;i<rs1.size();i++){
            System.out.print(rs1.get(i).getID());
            System.out.print("   " +rs1.get(i).getC_ID());
            System.out.print("   " +rs1.get(i).getPrice());
            System.out.print("\n");
        }
        LS_OV ls=new LS_OV("20210228","001",100);
        //boolean c=LS.insert(ls);
        boolean d=LS.delete(ls);
        System.out.println(d+"  \n");

        //卡机表演示
        DB KJ=new KJ_Table();
        List<KJ_OV> rs2=KJ.select();//数据库查询
        for(int i=0;i<rs2.size();i++){
            System.out.print(rs2.get(i).getID());
            System.out.print("   " +rs2.get(i).getTime());
            System.out.print("   " +rs2.get(i).getNum());
            System.out.print("\n");
        }
        KJ_OV kj=new KJ_OV("003","2021-02-17 18:35:54",100);
        //boolean e=KJ.insert(kj);
        boolean f=KJ.delete(kj);
        System.out.println(f+"  \n");


        //卡机表演示
        DB KC=new KC_Table();
        List<KC_OV> rs3=KC.select();//数据库查询
        for(int i=0;i<rs3.size();i++){
            System.out.print(rs3.get(i).getK_ID());
            System.out.print("   " +rs3.get(i).getC_ID());
            System.out.print("\n");
        }
        KC_OV kc=new KC_OV("002","A002");
        //boolean g=KC.insert(kc);
        boolean h=KC.delete(kc);
        System.out.println(h+"\n");
    }


}
