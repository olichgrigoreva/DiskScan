import models.Doc;
import org.apache.commons.io.FilenameUtils;
import services.DocService;
import services.Query;
import utils.DBConnection;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        DocService docService = new DocService();
        File file = new File("F:\\100CANON\\");
        List<File> listOfFiles = new ArrayList<>();
        Doc doc = new Doc();

        //connect to DB
        Connection connection = DBConnection.connect();

        //search files
        try {
            for (File i : Doc.listFile(file, listOfFiles)) {

                //filling doc object
                Path path = Paths.get(i.toString());
                doc.setPath(path.toString()); //path of file or directory
                doc.setSize(i.length()); //size of file

                //type of file/directory
                String fileType = FilenameUtils.getExtension(doc.getPath());
                if (fileType.equals("")) {
                    doc.setType("folder");
                } else {
                    doc.setType(fileType);
                }

                doc.setDate(path); //file date of creation
                doc.setAttributes(path); //attributes of file
                doc.setAccessibility(path); //accessibility of file

                //write to DB
                docService.saveDoc(doc);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Query.createStatement(connection, 24);
        DBConnection.closeConnect(connection);
    }
}
