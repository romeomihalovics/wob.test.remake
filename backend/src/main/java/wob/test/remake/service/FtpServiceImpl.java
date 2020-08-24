package wob.test.remake.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import wob.test.remake.util.FtpConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FtpServiceImpl implements FtpService {
    private final FTPClient client = new FTPClient();

    private final FtpConfig ftpConfig;

    @Autowired
    public FtpServiceImpl(FtpConfig ftpConfig) {
        this.ftpConfig = ftpConfig;
    }

    @Override
    public void uploadReport() throws IOException {
        client.connect(ftpConfig.getUrl());
        client.login(ftpConfig.getUser(), ftpConfig.getPassword());
        client.enterLocalPassiveMode();
        client.setFileType(FTP.BINARY_FILE_TYPE);

        File reportFile = new File("report.json");
        InputStream inputStream = new FileInputStream(reportFile);
        client.storeFile("report.json", inputStream);
        inputStream.close();
    }
}
