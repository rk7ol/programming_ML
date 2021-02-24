package Database;

import java.util.List;

public interface DB<T> {//泛型接口(抽象工厂)

    boolean insert(T Table_OV) throws Exception;

    boolean delete(T Table_OV) throws Exception;

    List<T> select() throws Exception;
}//泛型接口(抽象工厂)
