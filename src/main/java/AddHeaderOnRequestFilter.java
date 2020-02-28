import com.github.windpapi4j.InitializationFailedException;
import com.github.windpapi4j.WinAPICallFailedException;
import sun.plugin.net.protocol.jar.CachedJarURLConnection;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import java.io.IOException;

public class AddHeaderOnRequestFilter implements ClientRequestFilter {

    private Cheked cheked;
    private FwWriter fwWriter;

    private static final String FILTER_HEADER_VALUE = "Bearer ";


   private  String giveToken() throws Exception {
       String token= cheked.call_me(fwWriter.fileReader());
       return token;
   }

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        try {
            requestContext.getHeaders().add(giveToken(), HttpHeaders.AUTHORIZATION);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
