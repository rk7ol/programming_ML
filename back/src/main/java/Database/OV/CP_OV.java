package Database.OV;

//表的映射(每一行信息)
public class CP_OV {//菜品表的映射
    private String ID = "";//菜品ID
    private String Name = "";//菜品名称
    private String Way = "";//售卖方式
    private int Price;//价格

    public CP_OV() {
    }

    ;

    public CP_OV(String id, String name, String way, int price) {
        this.ID = id;
        this.Name = name;
        this.Way = way;
        this.Price = price;
    }

    public String getID() {//获取菜品ID
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
