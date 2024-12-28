package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserSentimentRepository {

      @Autowired
      private MongoTemplate mongoTemplate;
      public List<User> getUserSA(){
          Query query = new Query();
          Criteria criteria = new Criteria();
          query.addCriteria(criteria.andOperator(
                  Criteria.where("email").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"),
                  Criteria.where("sentimentAnalysis").is(true)
          ));
          List<User> users = mongoTemplate.find(query,User.class);
          return users;
      }
}
