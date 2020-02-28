import com.github.windpapi4j.InitializationFailedException;
import com.github.windpapi4j.WinAPICallFailedException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class  FwWriter {


    public  void fileWriter(Map<String,String> profile) throws InitializationFailedException, WinAPICallFailedException, IOException {
        String encryptedLogin = Encrypted.encrypt(profile.get("login"));
        String encryptPassword = Encrypted.encrypt(profile.get("password"));
        FileWriter fw = new FileWriter("src\\main\\resources\\23.zabei");
        fw.write(encryptedLogin);
        fw.append("\n");
        fw.write(encryptPassword);
        fw.flush();
        fw.close();
    }

    public  Map<String,String> fileReader() throws IOException, WinAPICallFailedException, InitializationFailedException {
        FileReader fileReader = new FileReader("src\\main\\resources\\23.zabei");
        BufferedReader reader = new BufferedReader(fileReader);
        Map <String,String> crypted = new HashMap<>();
        String logincrypt = reader.readLine();
        crypted.put("login",logincrypt);
        while  (logincrypt !=null){
            crypted.put("password",logincrypt);
            logincrypt =reader.readLine();
        }
        String loginDecrypt = Encrypted.decrypt(crypted.get("login"));
        String passwordDecrypt = Encrypted.decrypt(crypted.get("password"));
        crypted.remove("login");
        crypted.remove("password");
        crypted.put("login", loginDecrypt);
        crypted.put("password",passwordDecrypt);
        return crypted;

    }

}
