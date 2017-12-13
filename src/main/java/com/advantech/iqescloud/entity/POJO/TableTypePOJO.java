package com.advantech.iqescloud.entity.POJO;

/**
 * @author huqili.tp
 */
public class TableTypePOJO {

    private String  describe;
    private Integer eatMinNumber;
    private Integer eatMaxNumber;
    private Integer quantity;

    public TableTypePOJO(){
    }

    public TableTypePOJO(int i){
       if (i==0){
           this.describe="小桌";
           this.eatMinNumber=1;
           this.eatMaxNumber=4;
           this.quantity=30;
       }else if (i==1){
           this.describe="中桌";
           this.eatMinNumber=5;
           this.eatMaxNumber=8;
           this.quantity=20;
       }else if (i==2){
           this.describe="小桌";
           this.eatMinNumber=9;
           this.eatMaxNumber=14;
           this.quantity=20;
       }
    }
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Integer getEatMinNumber() {
        return eatMinNumber;
    }

    public void setEatMinNumber(Integer eatMinNumber) {
        this.eatMinNumber = eatMinNumber;
    }

    public Integer getEatMaxNumber() {
        return eatMaxNumber;
    }

    public void setEatMaxNumber(Integer eatMaxNumber) {
        this.eatMaxNumber = eatMaxNumber;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


}
