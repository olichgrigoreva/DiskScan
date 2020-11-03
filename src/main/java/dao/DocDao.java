package dao;

import models.Doc;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

public class DocDao implements Dao{
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
