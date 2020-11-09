import models.Doc;
import org.apache.commons.io.FilenameUtils;
import services.DocService;
import services.Query;
import utils.DBConnection;
import java.io.File;
import java.io.IOException;
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

        /*try {
            //search files
            for (File i : Doc.listFile(file, listOfFiles)) {
                //System.out.println(i);

                Path path = Paths.get(i.toString());
                doc.setPath(path.toString()); //path
                doc.setSize(i.length()); //size
                //type
                String fileType = FilenameUtils.getExtension(doc.getPath());
                if (fileType.equals("")) {
                    doc.setType("folder");
                } else {
                    doc.setType(fileType);
                }

                doc.setDate(path); //date
                doc.setAttributes(path); //attributes
                doc.setAccessibility(path); //accessibility
                //write to DB
                docService.saveDoc(doc);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/

        //create DB
        Connection connection = DBConnection.connect();
        //DBConnection.createDB("files", connection);
        Query.createStatement(connection,23);


        DBConnection.closeConnect(connection);
    }
}
