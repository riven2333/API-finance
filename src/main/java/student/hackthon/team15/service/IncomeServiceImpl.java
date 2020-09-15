package student.hackthon.team15.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.hackthon.team15.dao.AccountEntityDao;
import student.hackthon.team15.dao.IncomeEntityDao;
import student.hackthon.team15.entity.IncomeEntity;

import java.util.List;

@Service
public class IncomeServiceImpl implements IncomeService{
    @Autowired
    IncomeEntityDao incomeEntityDao;
    @Autowired
    AccountEntityDao accountEntityDao;

    @Override
    public List<IncomeEntity> getIncomesList() {
        return incomeEntityDao.getAllIncome();
    }

    @Override
    public void addIncome(IncomeEntity incomeEntity) {
        incomeEntityDao.addItemToIncome(incomeEntity);
        double account = accountEntityDao.getAccountValue();
        account += incomeEntity.getValue();
        accountEntityDao.updateAccount(account);
    }

    @Override
    public void deleteIncome(IncomeEntity incomeEntity){
        incomeEntityDao.deleteItemInIncome(incomeEntity);
    }

    @Override
    public void updateIncome(IncomeEntity incomeEntity){
        incomeEntityDao.updateItemInIncome(incomeEntity);
    }

}
