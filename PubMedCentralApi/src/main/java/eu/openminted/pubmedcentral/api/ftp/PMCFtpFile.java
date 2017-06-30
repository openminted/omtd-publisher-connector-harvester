/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.openminted.pubmedcentral.api.ftp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

/**
 *
 * @author samuel
 */
public class PMCFtpFile {

    private final PMCFtpClient client;

    private final URL file;

    public PMCFtpFile(PMCFtpClient client, URL file) {
        this.client = client;
        this.file = file;
    }

    public void retrieve(OutputStream outputStream) throws IOException {
        this.client.retrieveFile(file, outputStream);
    }    
}
