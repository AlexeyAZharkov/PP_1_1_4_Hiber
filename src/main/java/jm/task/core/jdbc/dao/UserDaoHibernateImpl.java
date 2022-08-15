package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import javax.management.Query;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }

// test
    public void saveStudent(User student) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(student);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        String sql = "CREATE TABLE `test1`.`zh22` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastName` VARCHAR(45) NOT NULL,\n" +
                "  `age` INT NOT NULL,\n" +
                "  PRIMARY KEY (`id`));";
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            NativeQuery query = session.createNativeQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        String sql = "DROP TABLE `test1`.`zh22`;";
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            NativeQuery query = session.createNativeQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save("User", user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.remove(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            return session.createQuery("FROM User").list();
        }
    }

//    @Override
//    public List<User> getAllUsers() {
//        List<User> userList = new ArrayList<>();
////        String sql = "select name, lastName, age from zh22";
//        String sql = "select * from zh22";
//
//        Transaction transaction = null;
//        try (Session session = Util.getSessionFactory().openSession()) {
////            transaction = session.beginTransaction();
//            NativeQuery query = session.createNativeQuery(sql).addEntity(User.class);
//            userList = query.list();
////            transaction.commit();
//        } catch (Exception e) {
////            if (transaction != null) {
////                transaction.rollback();
////            }
//            e.printStackTrace();
//        }
//        return userList;
//    }


    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("Delete FROM User").executeUpdate();;
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
