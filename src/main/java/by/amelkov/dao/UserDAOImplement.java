package by.amelkov.dao;

import by.amelkov.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImplement implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    public void editUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    public void deleteUser(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    public User getUser(String login) {
        User tmp;
        Session tmp2 = sessionFactory.getCurrentSession();
        tmp = (User) tmp2.get(User.class, login);
        return tmp;
    }

    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession().createCriteria(User.class).list();
    }

    public List<User> getAllUsersAndLastDate() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.isNotNull("lastDateVisit"));
        criteria.setProjection(Projections.projectionList().add(Projections.property("username"), "username").add(Projections.property("lastDateVisit"), "lastDateVisit"));
        criteria.setResultTransformer(Transformers.aliasToBean(User.class));
        return criteria.list();
    }
}