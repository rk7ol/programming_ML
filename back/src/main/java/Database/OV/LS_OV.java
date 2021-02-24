package Database.OV;

public class LS_OV {//流水表的映射
    private String ID = "";//流水ID
    private String C_ID = "";//卡机ID
    private int Price;//总售价

    public LS_OV() {
    }

    ;

    public LS_OV(String id, String c_id, int price) {
        this.ID = id;
        this.C_ID = c_id;
        this.Price = price;
    }

    public String getID() {//获取菜品ID
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

    public void setC_ID(String c_id) {
        this.C_ID = c_id;
    }

    public void setPrice(int price) {//设置价格
        Price = price;
    }
}//流水表的映射(提供get,set)
