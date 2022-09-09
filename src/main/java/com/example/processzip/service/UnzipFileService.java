package com.example.processzip.service;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Service
public class UnzipFileService {

    final String unZipFilePath;

    @Autowired
    public UnzipFileService() {
        unZipFilePath = System.getProperty("user.dir") + "/src/main/resources/data";
    }

    public void processZipFile(String zipFilePath) throws IOException {
        ZipFile zipFile = new ZipFile(zipFilePath);

        Enumeration<? extends ZipEntry> entries = zipFile.entries();

        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            InputStream inputStream = zipFile.getInputStream(entry);

            File file = createNewFile(entry);
            FileUtils.copyInputStreamToFile(inputStream, file);
        }
    }

    private File createNewFile(ZipEntry zipEntry) throws IOException {
        File unzipFileDir = new File(unZipFilePath);

        File destFile = new File(unzipFileDir, zipEntry.getName());

        String destDirPath = unzipFileDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }
        return destFile;
    }
}
