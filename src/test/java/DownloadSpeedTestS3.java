
import java.io.InputStream;
        import java.net.HttpURLConnection;
        import java.net.URL;

public class DownloadSpeedTestS3 {
    public static void main(String[] args) {
        try {
            String targetUrl = "http://localhost:8080/api/v1/files/download?fileName=d2ccc1c6-9eb7-44b9-9b62-e28645e99374_runtopia.zip";
            URL url = new URL(targetUrl);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                System.out.printf("Failed to download file. HTTP Response Code: %d\n", responseCode);
                return;
            }

            long fileSize = 0;
            long startTime = System.currentTimeMillis();

            try (InputStream inputStream = httpURLConnection.getInputStream()) {
                byte[] buffer = new byte[8192]; // increased buffer size
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileSize += bytesRead;
                }
            }

            long endTime = System.currentTimeMillis();
            double elapsedTime = (endTime - startTime) / 1000.0;
            double downloadSpeed = fileSize / elapsedTime;

            System.out.printf("File Size: %.2f MB\n", fileSize / (1024.0 * 1024.0));
            System.out.printf("Time taken: %.2f seconds\n", elapsedTime);
            System.out.printf("Download Speed: %.2f MB/s\n", downloadSpeed / (1024.0 * 1024.0));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
