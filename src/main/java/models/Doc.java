package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.DosFileAttributes;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Doc {
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

    public void setAttributed(Path path) throws IOException {
        DosFileAttributes attr = Files.readAttributes(path, DosFileAttributes.class);
        this.setHidden(attr.isHidden());
        this.setArchive(attr.isArchive());
        this.setSystem(attr.isSystem());
    }
}
