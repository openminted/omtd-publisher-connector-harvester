/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.openminted.pubmedcentral.api.ftp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author samuel
 */
public class PMCFtpClient {

    Logger logger = LoggerFactory.getLogger("PMCFtpClient");

    private String FTPSERVER = "ftp.ncbi.nlm.nih.gov";
    
    
    public void retrieveFile(URL file, OutputStream outputStream) throws IOException {
        FTPClient client = new FTPClient();
        //client.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        
        
        try {
            client.connect(FTPSERVER);
            int reply = client.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                client.disconnect();
                throw new IOException("Exception in connecting to FTP Server");
            }
            client.setFileType(FTP.BINARY_FILE_TYPE);

            // ftp://ftp.ncbi.nlm.nih.gov/README.ftp says use 32MB
            //client.setBufferSize(33554432);
            client.setBufferSize(0);
            client.pasv();
            
            client.user("anonymous");
            client.pass("anonymous");
            // Download file from FTP server.
            client.retrieveFile("/" + file.getPath(), outputStream);

        } finally {
            client.disconnect();            
        }
    }

}
