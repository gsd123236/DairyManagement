package com.dairy.Services;

import com.dairy.Entity.MilkTypeMaster;
import com.dairy.Repository.ExpenseCategoriesMasterRepository;
import com.dairy.Entity.ExpenseCategoriesMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ExpensiveCategoriesMasterService {
    @Autowired
    private ExpenseCategoriesMasterRepository repository;
    public ExpenseCategoriesMaster add(ExpenseCategoriesMaster expensive ){
        List<ExpenseCategoriesMaster>existingExpenseType=repository.getByName(expensive.getExpense());
        boolean anyNotDelted=existingExpenseType.stream().anyMatch(m->!m.isDeleted(true));
        if(anyNotDelted){
            throw new RuntimeException("Expense with the same name already exists and is not deleted");
        }
        expensive.setUpdateDate(LocalDateTime.now());
        expensive.setActive(true);
        expensive.setUpdateDate(LocalDateTime.now());
        return repository.save(expensive);
    }
    public List<ExpenseCategoriesMaster>activeDeactive(){
        return repository.getAllActiveOrDeactive();
    }
    public List<ExpenseCategoriesMaster>getAll(){
        return repository.getActiveData();
    }
    public ExpenseCategoriesMaster updateExpensive(int id, String expesive){
        Optional<ExpenseCategoriesMaster> data=repository.findById(id);
        if(data.isPresent()){
            ExpenseCategoriesMaster s=data.get();
            s.setExpense(expesive);
            s.setUpdateDate(LocalDateTime.now());
            return repository.save(s);
        }
        else{
            throw new RuntimeException("This is Not Found");
        }
    }
    public ExpenseCategoriesMaster DeActive(int id){
        Optional<ExpenseCategoriesMaster>expense=repository.findById(id);
        if(expense.isPresent()){
            ExpenseCategoriesMaster s=expense.get();
            s.setActive(false);
            s.setDeactive(true);
            s.isDeleted(false);
            return repository.save(s);
        }
        else{
            throw new RuntimeException("The Expensive Not found");
        }
    }
    public ExpenseCategoriesMaster isDeleted(int id){
        Optional<ExpenseCategoriesMaster>expense=repository.findById(id);
        if(expense.isPresent()){
            ExpenseCategoriesMaster s=expense.get();
            s.setActive(false);
            s.setDeactive(false);
            s.setDeleted(true);
            return repository.save(s);
        }
        else{
            throw new RuntimeException("The Expensive Not found");
        }
    }
    public ExpenseCategoriesMaster Active(int id){
        Optional <ExpenseCategoriesMaster>expense=repository.findById(id);
        if(expense.isPresent()){
            ExpenseCategoriesMaster s=expense.get();
            s.setActive(true);
            s.setDeactive(false);
            s.setDeleted(true);
            return repository.save(s);
        }
        else{
            throw new RuntimeException("The Expensive Not Found");
        }
    }
    public Optional<ExpenseCategoriesMaster> getById(int id){
        return repository.findById(id);
    }


}
