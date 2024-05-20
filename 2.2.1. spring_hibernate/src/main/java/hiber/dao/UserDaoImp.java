package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public List<User> getUserForModelAndSeries(String model, int series) {
        String hgl = " from User WHERE car.model = :model and car.series = :series";
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hgl);
        query.setParameter("model", model);
        query.setParameter("series", series);
        return query.getResultList();
    }

}
