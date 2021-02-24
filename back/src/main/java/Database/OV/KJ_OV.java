package Database.OV;

public class KJ_OV {
    private String ID = "";//卡机ID
    private String Time = "";//注册时间
    private int Num;//菜品数量

    public KJ_OV() {
    }

    ;

    public KJ_OV(String id, String time, int num) {
        this.ID = id;
        this.Time = time;
        this.Num = num;
    }

    public String getID() {//获取菜品ID
        return ID;
    }

    public String getTime() {
        return Time;
    }

    public int getNum() {//获取售卖价格
        return Num;
    }

    public void setID(String id) {//设置菜品ID
        this.ID = id;
    }

    public void setTime(String time) {
        this.Time = time;
    }

    public void setNum(int num) {
        Num = num;
    }
}//卡机表的映射(提供get,set)
