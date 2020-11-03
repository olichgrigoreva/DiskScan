import models.Doc;
import org.apache.commons.io.FilenameUtils;
import services.DocService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        DocService docService = new DocService();
        File file = new File("F:\\100CANON\\");
        List<File> listOfFiles = new ArrayList<>();

        Doc doc = new Doc();

        try {
            //search files
            for (File i : listFile(file, listOfFiles)) {
                //System.out.println(i);

                Path path = Paths.get(i.toString());
                doc.setPath(path.toString()); //path
                doc.setSize(i.length()); //size
                doc.setType(FilenameUtils.getExtension(doc.getPath())); //type

                //date
                BasicFileAttributes date = Files.readAttributes(path, BasicFileAttributes.class);
                doc.setCreated(date.creationTime().toString());

                //attributes
                DosFileAttributes attr = Files.readAttributes(path, DosFileAttributes.class);
                doc.setHidden(attr.isHidden());
                doc.setArchive(attr.isArchive());
                doc.setSystem(attr.isSystem());

                //accessibility
                doc.setReadable(Files.isReadable(path));
                doc.setWritable(Files.isWritable(path));
                doc.setExecutable(Files.isExecutable(path));

                //write to DB
                docService.saveDoc(doc);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

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
}
