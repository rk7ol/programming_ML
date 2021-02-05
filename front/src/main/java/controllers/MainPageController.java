package controllers;

//import controllers.Food;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;



public class MainPageController<Combobox, string> {



    @FXML
    private Button buttonAdd;

    @FXML
    private Button button0;

    @FXML
    private Button button1;

    @FXML
    private Combobox Combobox;


    @FXML
    private TextField textfield1;

    @FXML
    private TextField textfield2;

    @FXML
    private TextField textfield3;

    @FXML
    //确认所选择菜品
    private void yes() {
    }

    @FXML
    private void button1ClickEvent() {
    }

    @FXML
    //菜品名称
    private void addaction1()
    {
        Food Name = new Food();
        Name.names = textfield1.getText();//获取当前输入的菜品名称
        Food food =new Food();
        food.getNames();
       // System.out.println(names);
    }

    @FXML
    //销售方式
    private  void addaction2() {
        Food Way=new Food();
        Way.ways=textfield2.getText();
        Food food =new Food();
        food.getWays();

    }

    @FXML
    //价格
    private void addaction3() {
        Food Price=new Food();
        Price.price=textfield3.getText();
       // System.out.println(price);
        Food food =new Food();
        food.getPrice();
    }

    @FXML
    //添加菜品按钮
    private void eventAddclick()
    {
        addaction1();
        addaction2();
        addaction3();
    }
}

