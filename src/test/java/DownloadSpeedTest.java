import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadSpeedTest {
    public static void main(String[] args) {
        try {
            String targetUrl = "http://localhost:8080/api/v1/file/download/runtopia2.zip";
            URL url = new URL(targetUrl);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            long fileSize = 0; // 파일 크기를 동적으로 계산
            long startTime = System.currentTimeMillis();

            try (InputStream inputStream = httpURLConnection.getInputStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileSize += bytesRead; // 읽은 바이트 수를 누적
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
