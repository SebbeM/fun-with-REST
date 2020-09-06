import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class funWithREST {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide arguments.");
            return;
        }
        //authorize();
        String artistId = "";
        try {
            artistId = args[0];
        } catch (NumberFormatException e) {
            System.out.println("First argument is not a valid integer.");
            return;
        }
        System.out.println(getFollowers(artistId));
    }

    private static int getFollowers(String artistId) {
        int followers = -1;
        try {
            URL artistUrl = new URL("https://api.spotify.com/v1/artists/" + artistId);
            HttpURLConnection connection = (HttpURLConnection) artistUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer BQC2lKl6jegrbdtTVCFClAUwrDDLnxSO-d8SZMuJjeGpSM_S9IjAsnMsl_k40VX-xub_X7Nc52fG04XSbvk");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader inputReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;

                while ((inputLine = inputReader.readLine()) != null) {
                    if (inputLine.contains("total")) {
                        String[] splitLine = inputLine.split(" ");
                        followers = Integer.parseInt(splitLine[splitLine.length - 1]);
                    }
                }
                inputReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return followers;
    }
}
