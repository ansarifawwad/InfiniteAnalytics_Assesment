package org.example.ExcelUtilities;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.example.MainClass;

import java.io.*;
import java.nio.file.Paths;

public class ExcelDownload extends MainClass {

    String projectPath = System.getProperty("user.dir");
    String fileLocation = "src/main/java/org/example/ExcelUtilities/InputData.xlsx";
    String downloadPath = Paths.get(projectPath, fileLocation).toString();

    public void downloadExcel() {

        String fileUrl = "https://docs.google.com/spreadsheets/d/e/2PACX-1vQrahxlobnEkCHbVq7h1jAMNtxP7ocdKlZYH0FjGMG5OWmNELDutbW1IbskhUXFcBVl3hqPgITXg6zz/pub?output=xlsx";

        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(fileUrl);

        httpGet.addHeader("Cache-Control", "no-cache");
        httpGet.addHeader("Pragma", "no-cache");

        try {
            HttpResponse response = httpClient.execute(httpGet);
            InputStream inputStream = response.getEntity().getContent();

            File outputFile = new File(downloadPath);
            FileOutputStream outputStream = new FileOutputStream(outputFile);

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream.close();
            inputStream.close();

            System.out.println("File downloaded successfully to: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteExcel() {
        File file = new File(downloadPath);

        if (file.exists()) {
            if (file.delete()) {
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("Failed to delete the file.");
            }
        } else {
            System.out.println("File does not exist.");
        }
    }

    public void deleteReport() {
        String reportPath = Paths.get(projectPath, "test-output").toString();
        File folder = new File(reportPath);

        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.getName().startsWith("Extent")) {
                    file.delete();
                    System.out.println("File deleted: " + file.getAbsolutePath());
                }
            }
        }
    }

    public void deleteScreenShots() {
        String reportPath = Paths.get(projectPath, "src/main/java/com/partnersv2_automation/DataFiles/Screenshots").toString();
        File folder = new File(reportPath);

        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.getName().startsWith("failed_screenshot")) {
                    file.delete();
                    System.out.println("File deleted: " + file.getAbsolutePath());
                }
            }
        }
    }
}
