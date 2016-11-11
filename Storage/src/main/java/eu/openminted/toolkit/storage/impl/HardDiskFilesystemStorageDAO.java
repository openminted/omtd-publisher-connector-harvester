package eu.openminted.toolkit.storage.impl;

import eu.openminted.toolkit.storage.StorageConfiguration;
import eu.openminted.toolkit.storage.StorageDAO;
import eu.openminted.toolkit.storage.exceptions.FileDoesNotExistException;
import eu.openminted.toolkit.storage.exceptions.StorageException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucasanastasiou
 */
@Service
public class HardDiskFilesystemStorageDAO implements StorageDAO {

    private static final Logger logger = Logger.getLogger(HardDiskFilesystemStorageDAO.class.getName());

    @Autowired
    StorageConfiguration storageConfiguration;
    
    private final String STORAGE_BASE_PATH = storageConfiguration.STORAGE_BASE_PATH;

    public String storeSitemapFile(Integer id, int level, String url, String contents) throws StorageException {
        String sfilename = generateFilesystemFilename(level, url, contents);
        try {
            storeFile(STORAGE_BASE_PATH, sfilename, contents);
        } catch (IOException iOException) {
            logger.log(Level.ALL, iOException.getMessage());
            throw new StorageException("Error while writing to disk.", iOException.fillInStackTrace());
        }
        return sfilename;
    }

    private void storeFile(String directoryPath, String filename, String fileContent) throws IOException {
        System.out.println("Storing in " + directoryPath + " " + filename + " ");

        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
        if (!directoryPath.endsWith("/")) {
            directoryPath += "/";
        }
        FileUtils.writeStringToFile(new File(directoryPath + filename), fileContent, "UTF-8");
    }

    private String safeForFilesystem(String string) {
        String name = string.replaceAll("\\W+", "");
        return name;
    }

    private String sha1(String contents) {
        return DigestUtils.sha1Hex(contents);
    }

    private String generateFilesystemFilename(int level, String url, String contents) {
        String filename = "L" + level + "_" + safeForFilesystem(url) + "_" + sha1(contents) + ".sitemap.xml";
        return filename;
    }

//    //very quick test
//    public static void main(String args[]) {
//        String url = "http://api.elsevier.com/sitemap/page/sitemap/serial/journals/w/01722190/Volume_20_Issue_1.html";
//        String contents = "<html><head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8' /><meta name='ROBOTS' content='NOINDEX, FOLLOW' /></head><body><a href=\"http://api.elsevier.com/content/serial/title/issn/01722190\">Volume_20_Issue_1</a><BR><a href=\"http://api.elsevier.com/content/article/pii/S0172219098000076\">S0172219098000076</a><BR><a href=\"http://api.elsevier.com/content/article/pii/S0172219098000088\">S0172219098000088</a><BR><a href=\"http://api.elsevier.com/content/article/pii/S017221909800009X\">S017221909800009X</a><BR><a href=\"http://api.elsevier.com/content/article/pii/S0172219098000106\">S0172219098000106</a><BR><a href=\"http://api.elsevier.com/content/article/pii/S0172219098000118\">S0172219098000118</a><BR><a href=\"http://api.elsevier.com/content/article/pii/S017221909800012X\">S017221909800012X</a><BR><a href=\"http://api.elsevier.com/content/article/pii/S0172219098000131\">S0172219098000131</a><BR><a href=\"http://api.elsevier.com/content/article/pii/S0172219098900813\">S0172219098900813</a><BR><a href=\"http://api.elsevier.com/content/article/pii/S0172219098900837\">S0172219098900837</a><BR><a href=\"http://api.elsevier.com/content/article/pii/S0172219098900850\">S0172219098900850</a><BR><a href=\"http://api.elsevier.com/content/article/pii/S0172219098900874\">S0172219098900874</a><BR><a href=\"http://api.elsevier.com/content/article/pii/S0172219098900898\">S0172219098900898</a><BR><a href=\"http://api.elsevier.com/content/article/pii/S0172219098900916\">S0172219098900916</a><BR><a href=\"http://api.elsevier.com/content/article/pii/S017221909890093X\">S017221909890093X</a><BR><a href=\"http://api.elsevier.com/content/article/pii/S0172219098900953\">S0172219098900953</a><BR><a href=\"http://api.elsevier.com/content/article/pii/S0172219098900977\">S0172219098900977</a><BR><a href=\"http://api.elsevier.com/content/article/pii/S0172219098900990\">S0172219098900990</a><BR><a href=\"http://api.elsevier.com/content/article/pii/S0172219098901016\">S0172219098901016</a><BR><a href=\"http://api.elsevier.com/content/article/pii/S017221909890103X\">S017221909890103X</a><BR><a href=\"http://api.elsevier.com/content/article/pii/S0172219098901053\">S0172219098901053</a><BR><BR></body></html>";
//
//        String gene = new HardDiskFilesystemStorageDAO().generateFilesystemFilename(4, url, contents);
//        System.out.println("gene = " + gene);
//        System.out.println("l=" + gene.length());
//    }
    @Override
    public String getFileContents(String filename) throws FileDoesNotExistException,StorageException {
        String directoryPath = STORAGE_BASE_PATH;
        File directory = new File(directoryPath);

        if (!directoryPath.endsWith("/")) {
            directoryPath += "/";
        }
        File file = new File(directoryPath + filename);
        if (!file.exists()) {
            throw new FileDoesNotExistException("File " + filename + "does not exist");
        }
        try {
            return FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException iOException) {
            logger.log(Level.ALL, "IO exception", iOException);
            throw new StorageException("IO exception", iOException);
        }
    }

}
