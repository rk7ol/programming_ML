package Database.OV;

public class KC_OV {
    private String K_ID = "";//卡机ID
    private String C_ID = "";//菜品ID

    public KC_OV() {
    }

    ;

    public KC_OV(String k_id, String c_id) {
        this.K_ID = k_id;
        this.C_ID = c_id;
    }

    public String getK_ID() {//获取菜品ID
        return K_ID;
    }

    public String getC_ID() {
        return C_ID;
    }

    public void setK_ID(String k_id) {//设置菜品ID
        this.K_ID = k_id;
    }

    public void setC_ID(String c_id) {
        this.C_ID = c_id;
    }
}//卡机-菜品表的映射(提供get,set)
