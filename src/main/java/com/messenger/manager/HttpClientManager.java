package com.messenger.manager;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpClientManager {

    private static HttpClientManager httpClientManager = new HttpClientManager();
    private final Logger logger = Logger.getLogger(HttpClientManager.class.getName());
    private final HttpClient httpClient = HttpClientBuilder.create().build();

    private HttpClientManager() {
    }

    public static HttpClientManager getInstance() {
        return httpClientManager;
    }

    public String sendRequest(final String strUrl) {
        String strResponse = null;
        HttpResponse httpResponse = null;
        final HttpGet httpGet = new HttpGet(strUrl);
        try {
            httpResponse = httpClient.execute(httpGet);
            if (httpResponse != null) {
                strResponse = httpResponse.getStatusLine().getReasonPhrase();
            }
        } catch (Exception e) {
            logger.warn("Exception arises while calling url [ " + strUrl + " ] ", e);
        } finally {
            if (httpResponse != null) {
                EntityUtils.consumeQuietly(httpResponse.getEntity());
            }
            httpGet.releaseConnection();
        }
        return strResponse;
    }

}
