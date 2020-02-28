import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.*;


public class Cheked {

    FwWriter fwWriter;

        public boolean fileIsExsict() throws Exception {
            if (new File("src\\main\\resources\\23.zabei").exists()) {
                String jwtToken = call_me(fwWriter.fileReader());
                System.out.println(jwtToken);
                return true;
            }
              else{
                Map<String,String> information = profileInput();
                String token = call_me(information);
                fwWriter.fileWriter(information);
                System.out.println(token);
                return true;
            }

        }

        private  Map<String,String> profileInput() {
            Scanner in = new Scanner(System.in);
            Map<String,String> profile = new HashMap<>();
            System.out.println("Input your email: ");
            profile.put("login", in.nextLine());
            System.out.println("Input your password: ");
            profile.put("password", in.nextLine());
            in.close();
            return profile;
        }

        protected static String call_me(Map<String,String> profile) throws Exception {
            String urlLink = profile.get("login") + "&password=" + profile.get("password");
            URL url = new URL(urlLink);
            HttpsURLConnection connection= (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = in.readLine()) !=null) {
                sb.append(line);
            }
            in.close();
            String result = sb.toString();
            return result;
        }

}
