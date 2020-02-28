import com.github.windpapi4j.InitializationFailedException;
import com.github.windpapi4j.WinAPICallFailedException;
import com.github.windpapi4j.WinDPAPI;

import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Encrypted {

    private static WinDPAPI winDPAPI;


    public static String encrypt(String plaintext) throws WinAPICallFailedException, InitializationFailedException { // Шифровка
        winDPAPI = WinDPAPI.newInstance(WinDPAPI.CryptProtectFlag.CRYPTPROTECT_UI_FORBIDDEN);
        byte[] encryptedBytes = winDPAPI.protectData(plaintext.getBytes(UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

        public static String decrypt(String encryptedString) throws WinAPICallFailedException, InitializationFailedException { // Дешифровка
        winDPAPI = WinDPAPI.newInstance(WinDPAPI.CryptProtectFlag.CRYPTPROTECT_UI_FORBIDDEN);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedString);
        return new String(winDPAPI.unprotectData(encryptedBytes), UTF_8);
    }
}
