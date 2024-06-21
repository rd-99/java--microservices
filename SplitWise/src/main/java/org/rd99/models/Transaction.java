package org.rd99.models;

import lombok.Getter;
import lombok.Setter;
import org.rd99.constants.ExpenseType;

import java.util.List;

@Getter
@Setter
public class Transaction {
    public String giver;
    public List<String> reciever;
    public Float amount;
    public ExpenseType tType;
    public Transaction(String giver, List<String> reciever, ExpenseType tType,Float amount ){
        this.giver = giver;
        this.reciever = reciever;
        this.tType = tType;
        this.amount = amount;
    }
}
