package services;

import dao.DocDao;
import lombok.NoArgsConstructor;
import models.Doc;

@NoArgsConstructor
public class DocService {
    private DocDao docsDao = new DocDao();

    public Doc findDoc(int id) {
        return docsDao.findById(id);
    }

    public void saveDoc(Doc doc) {
        docsDao.save(doc);
    }

    public void deleteDoc(Doc doc) {
        docsDao.delete(doc);
    }

    public void updateDoc(Doc doc) {
        docsDao.update(doc);
    }

}