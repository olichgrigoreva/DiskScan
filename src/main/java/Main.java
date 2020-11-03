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
            for (File i : Doc.listFile(file, listOfFiles)) {
                //System.out.println(i);

                Path path = Paths.get(i.toString());
                doc.setPath(path.toString()); //path
                doc.setSize(i.length()); //size
                doc.setType(FilenameUtils.getExtension(doc.getPath())); //type
                doc.setDate(path); //date
                doc.setAttributes(path); //attributes
                doc.setAccessibility(path); //accessibility

                //write to DB
                docService.saveDoc(doc);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        String
    }
}
