package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.util.List;

/**
 * Объект Doc = File, т.к. класс File уже используется Java
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Doc implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String path;
    private long size;
    private String type;
    private String created;

    //attributes
    private boolean hidden;
    private boolean archive;
    private boolean system;

    //accessibility
    private boolean readable;
    private boolean writable;
    private boolean executable;


    public Doc(String path, long size, String type, String created,
               boolean hidden, boolean archive, boolean system,
               boolean readable, boolean writable, boolean executable) {
        this.path = path;
        this.size = size;
        this.type = type;
        this.created = created;
        this.hidden = hidden;
        this.archive = archive;
        this.system = system;
        this.readable = readable;
        this.writable = writable;
        this.executable = executable;
    }

    /**
     * установка атрибутов файла
     * @param path расположение файла/папки
     * @throws IOException
     */
    public void setAttributes(Path path) throws IOException {
        DosFileAttributes attr = Files.readAttributes(path, DosFileAttributes.class);
        this.setHidden(attr.isHidden());
        this.setArchive(attr.isArchive());
        this.setSystem(attr.isSystem());
    }

    /**
     * установка даты создания файла
     * @param path расположение файла/папки
     * @throws IOException
     */
    public void setDate(Path path) throws IOException {
        BasicFileAttributes date = Files.readAttributes(path, BasicFileAttributes.class);
        this.setCreated(date.creationTime().toString());
    }

    /**
     * установка атрибута доступности файла
     * @param path расположение файла/папки
     */
    public void setAccessibility(Path path) {
        this.setReadable(Files.isReadable(path));
        this.setWritable(Files.isWritable(path));
        this.setExecutable(Files.isExecutable(path));
    }

    /**
     * Поиск файлов и каталогов в заданной директории (root)
     *
     * @param root каталог поиска
     * @param list список для добавления в него путей файлов и директорий
     * @return List<File> список найденных файлов и каталогов
     */
    public static List<File> listFile(File root, List<File> list) {
        File[] files = root.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    listFile(file, list);
                }
                list.add(file);
            }
        }
        return list;
    }

    @Override
    public String toString() {
        return "Doc{" +
                "id=" + id +
                ", path='" + path + '\'' +
                '}';
    }
}
