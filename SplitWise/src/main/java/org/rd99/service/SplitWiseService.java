package org.rd99.service;

import java.util.ArrayList;
import java.util.List;

import org.rd99.constants.ExpenseType;
import org.rd99.models.User;
import org.springframework.stereotype.Service;

@Service
public class SplitWiseService {
    public List<recieverPair> decideAmount( List<String> reciever, float amount, ExpenseType expenseType) {
        List<recieverPair> recieversAmount = new ArrayList<>();
        switch (expenseType) {
            case EQUAL:
                // split equally
                int totalRecievers = reciever.size();
                for (String user : reciever) {
                    recieversAmount.add(new recieverPair(user, amount / totalRecievers));
                }
                break;
            case EXACT:
                // split exactly

                break;
            case PERCENT:
                // split by percentage
                break;
        }
        return recieversAmount;

    }

    public class recieverPair {
        String reciever;
        float amount;

        public recieverPair(String reciever, float amount) {
            this.reciever = reciever;
            this.amount = amount;
        }
    }

}
