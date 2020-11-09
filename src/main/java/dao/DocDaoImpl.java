package dao;

import models.Doc;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

/**
 * Доступ к данным объектов класса Doc
 * Логика в классе DocService, оттуда вызываются методы в main() для работы с объектами Doc
 */
public class DocDaoImpl implements DocDao {

    public Doc findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Doc.class, id);
    }

    public void save(Doc doc) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(doc);
        tx1.commit();
        session.close();
    }

    public void update(Doc doc) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(doc);
        tx1.commit();
        session.close();
    }

    public void delete(Doc doc) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(doc);
        tx1.commit();
        session.close();
    }
}
