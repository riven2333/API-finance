package student.hackthon.team15.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import student.hackthon.team15.entity.BudgetEntity;
import student.hackthon.team15.entity.ExpensesEntity;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;

import org.slf4j.Logger;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ExpensesEntityDaoImpl implements ExpensesEntityDao{
    private static final Logger logger = (Logger) LoggerFactory.getLogger(ExpensesEntityDaoImpl.class);
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<ExpensesEntity> getAllExpenses() {
        return mongoTemplate.findAll(ExpensesEntity.class);
    }

    @Override
    public List<ExpensesEntity> getAllBills() {
        List<ExpensesEntity> expensesEntities = mongoTemplate.findAll(ExpensesEntity.class);
        for(int i = 0; i < expensesEntities.size(); i++){
            ExpensesEntity entity = expensesEntities.get(i);
            if(!entity.isBill()){
                expensesEntities.remove(i);
            }
        }
        return expensesEntities;
     }

    @Override
    public void addItemToExpenses(ExpensesEntity expensesEntity) {
        mongoTemplate.save(expensesEntity,"expenses");
    }

    @Override
    public void deleteItemInExpenses(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        mongoTemplate.remove(query,"expenses");
    }


//    @Override
//    public void updateItemInExpenses(ExpensesEntity expensesEntity) {
//        Query query = new Query();
////        query.addCriteria(Criteria.where("_id").is(expensesEntity.getId()));
//        Update update = new Update();
////        update.set("categoryName", expensesEntity.getCategoryName()).set("value",expensesEntity.getValue()).set("isBill",expensesEntity.getBill()).set("date",expensesEntity.getDate()).set("isPaid",expensesEntity.getPaid());
//        mongoTemplate.updateFirst(query,update,ExpensesEntity.class);
//    }
//
    @Override
    public double getTotalAccout() {
        List<ExpensesEntity> expensesEntities = mongoTemplate.findAll(ExpensesEntity.class);
        double sum = 0;
        for(int i = 0; i < expensesEntities.size(); i++){
            sum += expensesEntities.get(i).getValue();
        }
        return sum;
    }

    @Override
    public double getTotalBills() {
        List<ExpensesEntity> expensesEntities = mongoTemplate.findAll(ExpensesEntity.class);
        double sum = 0;
        for(int i = 0; i < expensesEntities.size(); i++){
            if(expensesEntities.get(i).isBill())
                sum += expensesEntities.get(i).getValue();
        }
        return sum;
    }

    @Override
    public double getTotalBillsUnpaid() {
        List<ExpensesEntity> expensesEntities = mongoTemplate.findAll(ExpensesEntity.class);
        double sum = 0;
        for(int i = 0; i < expensesEntities.size(); i++){
            if(!expensesEntities.get(i).isPaid())
                sum += expensesEntities.get(i).getValue();
        }
        return sum;
    }
}
