package dao;

import models.Doc;

public interface Dao {
    Doc findById(int id);
    void save(Doc doc);
    void update(Doc doc);
    void delete(Doc doc);
}
