package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   @Override
   public User owner(String model, int series){
      Query query = sessionFactory.getCurrentSession().createQuery("from Car c where c.model = '" + model + "' and c.series = '" + series+"'");
      Car car = (Car)query.getSingleResult();
      Query query2=sessionFactory.getCurrentSession().createQuery(
              "from User u where u.id = '" + car.getId() + "'");
      User user  = (User)query2.getSingleResult();
      return user;
   }



}
