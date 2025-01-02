package com.shambhviii.expense_tracker_application.service.income;

import com.shambhviii.expense_tracker_application.dto.IncomeDto;
import com.shambhviii.expense_tracker_application.entity.Income;
import com.shambhviii.expense_tracker_application.repository.IncomeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class IncomeServiceImpl implements IncomeService{

    @Autowired
    private final IncomeRepository incomeRepository;

    public Income postIncome(IncomeDto incomeDto){
        return saveOrupdate(new Income(), incomeDto);
    }
    private Income saveOrupdate(Income income, IncomeDto incomeDto){
        income.setTitle(incomeDto.getTitle());
        income.setDate(incomeDto.getDate());
        income.setDescription(incomeDto.getDescription());
        income.setCategory(incomeDto.getCategory());
        income.setAmount(incomeDto.getAmount());
        return incomeRepository.save(income);
    }

    public Income updateIncome(Long id, IncomeDto incomeDto){
        Optional<Income> optional = incomeRepository.findById(id);
        if(optional.isPresent()){
            return saveOrupdate(optional.get(), incomeDto);
        }
        else{
            throw new EntityNotFoundException(id+"Id is not found");
        }
    }

    public List<IncomeDto> getAllIncome(){
        return incomeRepository.findAll().stream()
                .sorted(Comparator.comparing(Income::getDate).reversed())
                .map(Income::getIncomeDto)
                .collect(Collectors.toList());
    }

    public IncomeDto getIncomeById(Long id){
        Optional<Income> optional = incomeRepository.findById(id);
        if(optional.isPresent()){
            return optional.get().getIncomeDto();
        }
        else{
            throw new EntityNotFoundException(id + "Id is not found!!!");
        }
    }

    public void deleteIncome(Long id){
        Optional<Income> optional = incomeRepository.findById(id);
        if(optional.isPresent()){
            incomeRepository.deleteById(id);
        }
        else {
            throw new EntityNotFoundException(id+"Id is not found");
        }
    }





}
