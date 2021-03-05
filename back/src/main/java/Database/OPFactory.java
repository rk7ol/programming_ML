package Database;

import Database.OP.CP_Table;
import Database.OP.KC_Table;
import Database.OP.KJ_Table;
import Database.OP.LS_Table;

public class OPFactory {
    DB createTable(String tableName) throws Exception{
        switch(tableName){
            case "CP_Table":
                return new CP_Table();
            case "KC_Table":
                return new KC_Table();
            case "KJ_Table":
                return new KJ_Table();
            case "LS_Table":
                return new LS_Table();
        }
        return null;
    }
}
