import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide arguments.");
            return;
        }
        int artistId = 0;
        try {
            artistId = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("First argument is not a valid integer.");
            return;
        }
        String artist = getArtist(artistId);
        System.out.println(artist);
    }
    private static String getArtist(int artistId) {
        String response = "Could not GET artist with ID: " + artistId;
        try {
            URL artistUrl = new URL("https://api.spotify.com/v1/artists/" + artistId);
            HttpURLConnection connection = (HttpURLConnection) artistUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization:", "Bearer {BQDaEHJvR9yVD4TxPmMPVyV_-60ldN6a7_tp9rsQLcz_TGDMrki-B79pRribgt0LmYYotwAVSXghF4GVcu7ZHguQpls3N33Z1q8-Iu2Tg-BZ-gRC0k107i6CC-kF_NAhWoJXoZ04np0}");
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader inputReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer sb = new StringBuffer();

                while ((inputLine = inputReader.readLine()) != null) {
                    sb.append(inputLine);
                }

                inputReader.close();
                response = sb.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
