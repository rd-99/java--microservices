package org.rd99.models;

import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;
import java.util.*;

@Getter
public class User {
    public String name;
    public UUID Id;
    public float overallBalance;
    public Map<String,Float> lendTo;
    public List<String> pastTransaction;
    public User(String name){
        this.overallBalance = 0;
        this.name = name;
        this.Id = UUID.randomUUID();
        this.lendTo = new HashMap<>();
        this.pastTransaction = new ArrayList<>();
    }

    public List<String> UserLendingDetails(User giver){
        List<String> userDetails = new ArrayList<>();
        for(String k : giver.lendTo.keySet()){
            if (lendTo.get(k) < 0){
                userDetails.add(MessageFormat.format("{0} owes {1} Rs. {2}"  , name,k,-1*lendTo.get(k)));
            }
            else{
                userDetails.add(MessageFormat.format("{0} lends {1} Rs. {2}"  , name,k,lendTo.get(k)));

            }
        }
        return userDetails;
    }
}
