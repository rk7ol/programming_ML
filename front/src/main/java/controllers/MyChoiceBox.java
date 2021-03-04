package controllers;

import javafx.scene.control.CheckBox;
import modules.Food;

class MyChoiceBox extends CheckBox {
    private Food food;

    public MyChoiceBox(Food food){
        this.food = food;
        setText(food.getName());
    }

    public Food getFood(){
        return food;
    }
}