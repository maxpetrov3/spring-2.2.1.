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
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   public User getUserByCar(Car car) {
      Query query = sessionFactory.getCurrentSession().createQuery("from User as us where us.car.model = :carModel and us.car.series = :carSeries");
      query.setParameter("carModel", car.getModel());
      query.setParameter("carSeries", car.getSeries());
      System.out.println(query.getSingleResult());
      return new User();
   }

}
