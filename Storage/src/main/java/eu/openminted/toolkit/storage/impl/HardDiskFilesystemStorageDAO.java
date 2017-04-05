package eu.openminted.toolkit.storage.impl;

import eu.openminted.toolkit.storage.StorageConfiguration;
import eu.openminted.toolkit.storage.StorageDAO;
import eu.openminted.toolkit.storage.exceptions.FileDoesNotExistException;
import eu.openminted.toolkit.storage.exceptions.StorageException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.apache.commons.codec.binary.Base64;
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

    @PostConstruct
    private void init() {
        STORAGE_BASE_PATH = storageConfiguration.STORAGE_BASE_PATH;
        STORAGE_SITEMAPS_PATH = storageConfiguration.STORAGE_SITEMAPS_PATH;
        STORAGE_METADATA_PATH = storageConfiguration.STORAGE_METADATA_PATH;
        STORAGE_PDF_PATH = storageConfiguration.STORAGE_PDF_PATH;
    }

    private String STORAGE_BASE_PATH;// = storageConfiguration.STORAGE_BASE_PATH;
    private String STORAGE_SITEMAPS_PATH;
    private String STORAGE_METADATA_PATH;
    private String STORAGE_PDF_PATH;

    public String storeSitemapFile(Integer id, int level, String url, String contents) throws StorageException {
        String sfilename = generateSitemapFilesystemFilename(level, url, contents);
        try {
            storeFile(STORAGE_BASE_PATH + STORAGE_SITEMAPS_PATH, sfilename, contents);
        } catch (IOException iOException) {
            logger.log(Level.ALL, iOException.getMessage());
            throw new StorageException("Error while writing to disk.", iOException.fillInStackTrace());
        }
        return sfilename;
    }

    public void storeFile(String fullFileName, String fileContent) throws IOException {
        FileUtils.writeStringToFile(new File(fullFileName), fileContent, "UTF-8");
    }

    private void storeFile(String directoryPath, String filename, String fileContent) throws IOException {
        System.out.println("Storing in " + directoryPath + " " + filename + " ");

        createFolderIfNotExists(directoryPath);

        if (!directoryPath.endsWith("/")) {
            directoryPath += "/";
        }
        FileUtils.writeStringToFile(new File(directoryPath + filename), fileContent, "UTF-8");
    }

    private String safeForFilesystem(String string) {
        String name = string.replaceAll("\\W+", "");
        return name;
    }

    public String encodeToFilesystemSafe(String doi) {
        byte[] bytesEncoded = Base64.encodeBase64(doi.getBytes());
        String encodedBase64String = new String(bytesEncoded);
        String safeEncodedBase64String = encodedBase64String.replace("/", "_");
        safeEncodedBase64String = encodedBase64String.replace("+", "-");
        return new String(safeEncodedBase64String);
    }

    public String decodeFromFilesystemSafe(String filename) {
        String pureBase64 = filename.replace("_", "/");
        pureBase64 = filename.replace("-", "+");
        byte[] bytesDecoded = Base64.decodeBase64(pureBase64.getBytes());
        String decodeBase64String = new String(bytesDecoded);
        return new String(bytesDecoded);
    }

    private String sha1(String contents) {
        return DigestUtils.sha1Hex(contents);
    }

    private String generateSitemapFilesystemFilename(int level, String url, String contents) {
        String filename = "L" + level + "_" + safeForFilesystem(url) + "_" + sha1(contents) + ".sitemap.xml";
        return filename;
    }

    private String generateMetadataFilesystemFilename(String articleUrl) {
        String filename = encodeToFilesystemSafe(articleUrl) + ".xml";
        return filename;
    }

    private String generatePdfFilesystemFilename(String articleUrl) {
        String filename = encodeToFilesystemSafe(articleUrl) + ".pdf";
        return filename;
    }

    private String generateFilesystemFilename(String doi) {
        String filename = encodeToFilesystemSafe(doi);
        return filename;
    }

    private String getDoiFromFilename(String filename) {
        String doi = decodeFromFilesystemSafe(filename);
        return doi;
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
    public String getSitemapFileContents(String filename) throws StorageException {
        String directoryPath = STORAGE_BASE_PATH;

        if (!directoryPath.endsWith("/")) {
            directoryPath += "/";
        }

        String sitemap = STORAGE_SITEMAPS_PATH;
        String fullDirectoryPath = directoryPath + sitemap;
        if (!fullDirectoryPath.endsWith("/")) {
            fullDirectoryPath += "/";
        }
        return getFileContents(fullDirectoryPath + filename);
    }

    @Override
    public String getFileContents(String fullPath) throws FileDoesNotExistException, StorageException {
        File file = new File(fullPath);
        if (!file.exists()) {
            throw new FileDoesNotExistException("File " + fullPath + "does not exist");
        }
        try {
            return FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException iOException) {
            logger.log(Level.ALL, "IO exception", iOException);
            throw new StorageException("IO exception", iOException);
        }
    }

    private String selectBucket(String articleUrl) {
        String hash = sha1(articleUrl);
        // we want around 5000 files per folder
        // while we expect up to 20m files in total
        // so number of buckets = 20m / 5k = 4000
        // so we select the first log_16(4000)~=3 digits of the hash
        return hash.substring(0, 3);
    }

    private void createFolderIfNotExists(String subFolder) {
        File dir = new File(subFolder);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    @Override
    public String storeMetadataFile(String articleUrl, String articleMetadata) throws StorageException {
        String sfilename = generateMetadataFilesystemFilename(articleUrl);
        String subFolder = selectBucket(articleUrl);
        try {
            storeFile(STORAGE_BASE_PATH + STORAGE_METADATA_PATH + subFolder, sfilename, articleMetadata);
        } catch (IOException iOException) {
            logger.log(Level.ALL, iOException.getMessage());
            throw new StorageException("Error while writing to disk.", iOException.fillInStackTrace());
        }
        return subFolder + "/" + sfilename;
    }

    @Override
    public String storePdfFile(String articleUrl, String articlePdfContents) throws StorageException {
        String sfilename = generatePdfFilesystemFilename(articleUrl);
        String subFolder = selectBucket(articleUrl);
        try {
            storeFile(STORAGE_BASE_PATH + STORAGE_PDF_PATH + subFolder, sfilename, articlePdfContents);
        } catch (IOException iOException) {
            logger.log(Level.ALL, iOException.getMessage());
            throw new StorageException("Error while writing to disk.", iOException.fillInStackTrace());
        }
        return subFolder + "/" + sfilename;
    }

    @Override
    public String getMetadataFileLocation(String articleUrl) {
        String sfilename = generateMetadataFilesystemFilename(articleUrl);
        String subFolder = selectBucket(articleUrl);

        //create subfolder if does not exist
        this.createFolderIfNotExists(STORAGE_BASE_PATH + STORAGE_METADATA_PATH + subFolder);

        return STORAGE_BASE_PATH + STORAGE_METADATA_PATH + subFolder + File.separator + sfilename;
    }

    @Override
    public String getPdfFileLocation(String articleUrl) {
        String sfilename = generatePdfFilesystemFilename(articleUrl);
        String subFolder = selectBucket(articleUrl);

        this.createFolderIfNotExists(STORAGE_BASE_PATH + STORAGE_PDF_PATH + subFolder);

        return STORAGE_BASE_PATH + STORAGE_PDF_PATH + subFolder + File.separator + sfilename;
    }

    @Override
    public String getPdfFileLocation(String publisherPrefix, String doi, String articleUrl) throws StorageException {
        String sfilename = generatePdfFilesystemFilename(articleUrl);
        String subFolder = selectBucket(articleUrl);

        this.createFolderIfNotExists(STORAGE_BASE_PATH + publisherPrefix + "/" + STORAGE_PDF_PATH + subFolder);

        return STORAGE_BASE_PATH + publisherPrefix + "/" + STORAGE_PDF_PATH + subFolder + File.separator + sfilename;
    }

    @Override
    public String getMetadataFileLocation(String publisherPrefix, String doi, String articleUrl) throws StorageException {
        String sfilename = generateMetadataFilesystemFilename(articleUrl);
        String subFolder = selectBucket(articleUrl);

        this.createFolderIfNotExists(STORAGE_BASE_PATH + publisherPrefix + "/" + STORAGE_METADATA_PATH + subFolder);

        return STORAGE_BASE_PATH + publisherPrefix + "/" + STORAGE_METADATA_PATH + subFolder + File.separator + sfilename;
    }

}
