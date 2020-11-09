package services;

import dao.DocDaoImpl;
import lombok.NoArgsConstructor;
import models.Doc;

@NoArgsConstructor
/**
 * Методы для работы с объектами Doc в main()
 */
public class DocService {
    private DocDaoImpl docsDao = new DocDaoImpl();

    /**
     * поиск объекта по id
     * @param id объекта в БД
     * @return doc
     */
    public Doc findDoc(int id) {
        return docsDao.findById(id);
    }

    /**
     * сохранение объекта в БД
     * @param doc объект для сохранения
     */
    public void saveDoc(Doc doc) {
        docsDao.save(doc);
    }

    /**
     * удаление объекта в БД
     * @param doc объект для сохранения
     */
    public void deleteDoc(Doc doc) {
        docsDao.delete(doc);
    }

    /**
     * обновление объекта в БД
     * @param doc объект для сохранения
     */
    public void updateDoc(Doc doc) {
        docsDao.update(doc);
    }

}
