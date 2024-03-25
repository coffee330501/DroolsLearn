package io.github.coffee330501.droolsLearn.entity;
 
import lombok.Data;
 
@Data
public class Order {
 
    /**
     * 订单金额
     */
    private int amount;
 
    /**
     * 积分
     */
    private int score;
 
    @Override
    public String toString() {
        return "Order{" +
                "amount=" + amount +
                ", score=" + score +
                '}';
    }
}