package org.rd99.controller;

import org.rd99.constants.ExpenseType;
import org.rd99.models.Transaction;
import org.rd99.models.User;
import org.rd99.repository.UserRepository;
import org.rd99.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ExpenseController {

    private final TransactionService transactionService;
    private final UserRepository userRepository;

    @Autowired
    public ExpenseController(TransactionService transactionService,UserRepository userRepository) {
        this.transactionService = transactionService;
        this.userRepository = userRepository;
    }

    @PostMapping("/split")
    public List<String> splitExpenseController(@RequestBody Transaction transaction) throws Exception {
        String giver = transaction.giver;
        List<String> reciever = transaction.reciever;
        Float amount = transaction.amount;
        transactionService.splitExpense(giver,reciever,amount, ExpenseType.EQUAL);
        User giverUser = userRepository.getUsersRepo().get(giver);
        return giverUser.UserLendingDetails(giverUser);
    }

    @GetMapping("/all")
    public List<List<String>> allUsersController(){
        List<List<String>> answer = new ArrayList<>();
        List<User>users = userRepository.getUserList();
        for(User user : users){
            String userName=user.name;
            Map<String, Float> lendTo = user.lendTo;
            List<String> iterAns = new ArrayList<>();
            for (String lT : user.lendTo.keySet()){
                if(lendTo.get(lT) < 0){
                    iterAns.add(MessageFormat.format("{0} owes {1} to {2}",userName,lendTo.get(lT),lT));
                }
                else{
                    iterAns.add(MessageFormat.format("{0} lends {1} to {2}",userName,lendTo.get(lT),lT));

                }
            }
            answer.add(iterAns);
        }
        return answer;
    }
    @GetMapping("/users")
    public List<String> allUsersDetailsController(){
        List<String> answer = new ArrayList<>();
        List<User>users = userRepository.getUserList();
        for(User user : users){
            String userName= user.name;
            //Map<String, Float> lendTo = user.lendTo;
             answer.add(userName);
        }
        return answer;
    }
}
