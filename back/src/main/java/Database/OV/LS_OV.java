package Database.OV;

public class  LS_OV {//流水表的映射
    private String ID = "";//流水ID
    private String C_ID = "";//卡机ID
    private double Price;//总售价
    private String Time = "";//注册时间

    public LS_OV() {
    }

    ;

    public LS_OV(String id, String c_id, double price,String Time) {
        this.ID = id;
        this.C_ID = c_id;
        this.Price = price;
        this.Time=Time;
    }

    public String getTime() {
        return Time;
    }

    public String getID() {//获取菜品ID
        return ID;
    }

    public String getC_ID() {//获取菜品名称
        return C_ID;
    }

    public double getPrice() {//获取售卖价格
        return Price;
    }

    public void setTime(String Time){this.Time=Time;}

    public void setID(String id) {//设置菜品ID
        this.ID = id;
    }

    public void setC_ID(String c_id) {
        this.C_ID = c_id;
    }

    public void setPrice(double price) {//设置价格
        Price = price;
    }
}//流水表的映射(提供get,set)
