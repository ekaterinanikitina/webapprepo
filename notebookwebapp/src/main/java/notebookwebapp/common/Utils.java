package notebookwebapp.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <code> Класс Utils</code> содержит только статические методы,
 * доступные из любой точки программы
 * @version 1.0
 *
 */
public class Utils {
	
	/**
	 * Отправляет запрос к 
	 * @see <a href="https://avatars.dicebear.com" />
	 * для получения сгенерированного svg-файла
	 * @return содержимое svg-файла
	 */
	public static String getSvg() {
		String iconName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		String url = "https://avatars.dicebear.com/v2/identicon/" + iconName + ".svg";
        try {
            URL urlObject = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }
            bufferedReader.close();
            return response.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public static String getTitle(String text) {
		if (text == null || text.isEmpty())
			return "";
		return text.split(" ")[0] + "....";
	}

}
