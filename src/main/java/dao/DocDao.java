package dao;

import models.Doc;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import utils.HibernateSessionFactoryUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

//    public static void select(Doc doc) {
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx1 = session.beginTransaction();
//        //Query q1 = session.createQuery("from Doc");
//        CriteriaBuilder cb = session.getCriteriaBuilder();
//        CriteriaQuery<Doc> cr = cb.createQuery(Doc.class);
//        Root<Doc> root = cr.from(Doc.class);
//        cr.select(root);
//        cr.select(root).where(cb.gt(root.get("itemPrice"), 1000));
//
//        Query<Doc> query = session.createQuery(cr);
//        List<Doc> results = query.getResultList();
//
//        for (Doc cnt : results) {
//            System.out.print(cnt.getId() + " " + cnt.getPath() + " " + cnt.getType());
//            System.out.println();
//        }
//
//        tx1.commit();
//        session.close();
//    }
}
