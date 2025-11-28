package com.dairy.Services;

import com.dairy.Dto.DairyExpensiveDTO;
import com.dairy.Entity.DairyExpensive;
import com.dairy.Entity.DairyRegistration;
import com.dairy.Entity.ExpenseCategoriesMaster;
import com.dairy.Entity.FarmerRegistration;
import com.dairy.Repository.DairyExpensiveRepository;
import com.dairy.Repository.DairyRepository;
import com.dairy.Repository.ExpenseCategoriesMasterRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DairyExpensiveService {
    @Autowired
    private DairyExpensiveRepository dairyExpensiveRepository;
    @Autowired
    private ExpenseCategoriesMasterRepository expenseCategoriesMasterRepository;
    @Autowired
    private DairyRepository dairyRegistration;
    public DairyExpensive add(int dairyId,DairyExpensive expensive){
        DairyRegistration dairy=dairyRegistration.findById(dairyId).orElseThrow(()->new RuntimeException("Dairy Not found"));
        expensive.setDairyRegistration(dairy);
        return dairyExpensiveRepository.save(expensive);
    }
    public List<DairyExpensiveDTO> getAllDairyExpenses(int dairy_id) {
        DairyRegistration dairy=dairyRegistration.findById(dairy_id).orElseThrow(()->new RuntimeException("Dairy is not found"));
        List<DairyExpensive> expenses = dairyExpensiveRepository.findByIdExpensive(dairy_id);

        return expenses.stream()
                .map(expense -> {
                    String categoryName = expenseCategoriesMasterRepository.findById(expense.getCategory())
                            .map(ExpenseCategoriesMaster::getExpense)
                            .orElse("Unknown");
                    return new DairyExpensiveDTO(expense, categoryName);
                }).collect(Collectors.toList());
    }
    public DairyExpensive deleteDairyExpenseById(int id) {
        DairyExpensive dairyExpensive = dairyExpensiveRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dairy Expense with ID " + id + " not found."));

        dairyExpensiveRepository.delete(dairyExpensive);

        return dairyExpensive;
    }
    public List<DairyExpensiveDTO> getTransactionsByDateRange(int dairy_id, LocalDate startDate, LocalDate endDate) {
        List<DairyExpensive> expenses = dairyExpensiveRepository.findTransactionsByDateRange(dairy_id,startDate, endDate);
        return expenses.stream()
                .map(expense -> {
                    String categoryName = expenseCategoriesMasterRepository.findById(expense.getCategory())
                            .map(ExpenseCategoriesMaster::getExpense)
                            .orElse("Unknown");
                    return new DairyExpensiveDTO(expense, categoryName);
                }).collect(Collectors.toList());
    }

    public DairyExpensive updateExpensive(int id, DairyExpensive expensive) {
        DairyExpensive existing= dairyExpensiveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Farmer not found with ID: " + id));
        if (expensive.getCategory() != 0) {
            existing.setCategory(expensive.getCategory());
        }
        if (expensive.getAmount() != 0) {
            existing.setAmount(expensive.getAmount());
        }
        if (expensive.getPartyName() != null) {
            existing.setPartyName(expensive.getPartyName());
        }
        if(expensive.getComments()!=null){
            existing.setComments(expensive.getComments());
        }
        if(expensive.getDate()!=null){
            existing.setDate(expensive.getDate());
        }
        return dairyExpensiveRepository.save(existing);
    }





}


