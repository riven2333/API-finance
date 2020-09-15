package student.hackthon.team15.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.hackthon.team15.dao.AccountEntityDao;
import student.hackthon.team15.dao.CategoriesEntityDao;
import student.hackthon.team15.dao.ExpensesEntityDao;
import student.hackthon.team15.entity.CategoriesEntity;
import student.hackthon.team15.entity.ExpensesEntity;

import java.util.List;

@Service
public class ExpensesServiceImple implements ExpensesService{

    @Autowired
    ExpensesEntityDao expensesEntityDao;
    @Autowired
    CategoriesEntityDao categoriesEntityDao;
    @Autowired
    AccountEntityDao accountEntityDao;

    @Override
    public List<ExpensesEntity> getAllExpenses() {
        return expensesEntityDao.getAllExpenses();
    }

    @Override
    public void addItemsToExpenses(ExpensesEntity expensesEntity) {
        expensesEntityDao.addItemToExpenses(expensesEntity);
        double account = accountEntityDao.getAccountValue();
        account -= expensesEntity.getValue();
        accountEntityDao.updateAccount(50.00);
    }

    @Override
    public void addItemsToCategory(CategoriesEntity categoriesEntity) {
        categoriesEntityDao.addItemToCategories(categoriesEntity);
    }

    @Override
    public List<CategoriesEntity> getAllCategories() {
        return categoriesEntityDao.getAllCategories();
    }

//    @Override
//    public Boolean IfCategoriesContainsId(String id) {
//        return categoriesEntityDao.ifCategoryContainsId(id);
//    }
//
//    @Override
//    public void modifyItemInCategories(CategoriesEntity categoriesEntity) {
//        categoriesEntityDao.updateItemInCategories(categoriesEntity);
//    }

    @Override
    public void deleteItemInCategories(CategoriesEntity categoriesEntity) {
        categoriesEntityDao.deleteItemInCategories(categoriesEntity);
    }
    @Override
    public void deleteItemInCategoriesbyId(String id){
        categoriesEntityDao.deleteItemInCategoriesbyId(id);
    }
    @Override
    public void deleteExpensebyId(String id){
        expensesEntityDao.deleteItemInExpenses(id);
    }

}
