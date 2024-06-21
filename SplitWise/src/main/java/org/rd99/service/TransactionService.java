package org.rd99.service;

import org.rd99.constants.ExpenseType;
import org.rd99.models.User;
import org.rd99.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {
    UserRepository userRepository;
    SplitWiseService splitWiseService;
    @Autowired
    public TransactionService(UserRepository userRepository, SplitWiseService splitWiseService) {
        this.userRepository = userRepository;
        this.splitWiseService = splitWiseService;
    }
    public void splitExpense(String giver, List<String> reciever, Float amount, ExpenseType expenseType) throws Exception {
        if(userRepository.getUsersRepo().containsKey(giver) == false){
            createNewUser(giver);
        }
        User giverUser = userRepository.getUsersRepo().get(giver);
        List<SplitWiseService.recieverPair> amountToBeExchanged = splitWiseService.decideAmount(reciever,amount,expenseType);
        if(amountToBeExchanged.size() == 0){
            throw new Exception("No receivers for the transaction");
        }
        for(SplitWiseService.recieverPair amt : amountToBeExchanged){
            if(!userRepository.getUsersRepo().containsKey(amt.reciever)){
                createNewUser(amt.reciever);
                addRecieverinGiversList(giverUser,amt.reciever);
            }
            if(!giverUser.lendTo.containsKey(amt.reciever)){
                addRecieverinGiversList(giverUser,amt.reciever);
            }
            performTransaction(giverUser,amt.reciever,amt.amount);
        }
    }

    private void performTransaction(User giverUser,String receiver,Float amount) {
        giverUser.lendTo.merge(receiver,amount,Float::sum);
        User recieverUser = userRepository.getUsersRepo().get(receiver);
        recieverUser.lendTo.merge(giverUser.name,-1*amount,Float::sum);
        giverUser.pastTransaction.add(MessageFormat.format("{0},{1},{2}",giverUser.name,receiver,amount));
        recieverUser.pastTransaction.add(MessageFormat.format("{0},{1},{2}",receiver,giverUser.name,-1*amount));
    }

    private void addRecieverinGiversList(User giverUser,String reciever) {
        giverUser.lendTo.put(reciever,0F);
        return;
    }

    private void createNewUser(String reciever) {
        User recieverUser = new User(reciever);
        userRepository.getUserList().add(recieverUser);
        userRepository.getUsersRepo().put(reciever,recieverUser);
    }

}
