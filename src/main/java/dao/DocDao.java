package dao;

import models.Doc;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateSessionFactoryUtil;

import javax.print.attribute.standard.Media;
import java.util.List;

public class DocDao implements Dao {
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

    public static void select(Doc doc) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        //Query q1 = session.createQuery("from Doc");
        Query q1 = session.createQuery("from Doc where type = 'pdf'");
        List<Doc> l = q1.list();
        for (Doc cnt : l) {
            System.out.print(cnt.getId() + " " + cnt.getPath() + " " + cnt.getType());
            System.out.println();
        }
        tx1.commit();
        session.close();
    }
}
