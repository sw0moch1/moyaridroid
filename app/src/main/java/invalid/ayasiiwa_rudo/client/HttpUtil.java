package invalid.ayasiiwa_rudo.client;

import java.net.URISyntaxException;

import org.apache.http.HttpHost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import invalid.ayasiiwa_rudo.client.android.BuildConfig;

public class HttpUtil {
    static final public String USER_AGENT = BuildConfig.VERSION_NAME;

    // Set the timeout in milliseconds until a connection is established.
    static final public int timeoutConnection = 60000;
    // Set the default socket timeout (SO_TIMEOUT)
    // in milliseconds which is the timeout for waiting for data.
    static final public int timeoutSocket = 60000;

    static public String PROXY_HOST = null;
    static public int PROXY_PORT = -1;

    static public DefaultHttpClient buildHttpClient() throws URISyntaxException {
        HttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
        HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
        DefaultHttpClient hc = new DefaultHttpClient(httpParameters);
        hc.getParams().setParameter(CoreProtocolPNames.USER_AGENT, USER_AGENT);
        hc.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(3, false));
        if (PROXY_HOST != null) {
            HttpHost proxy = new HttpHost(PROXY_HOST, PROXY_PORT);
            hc.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
        }
        return hc;
    }
}
