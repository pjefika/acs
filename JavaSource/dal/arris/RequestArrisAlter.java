/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.arris;

import entidades.sys.Autenticacao;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import model.AuthFactory;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 *
 * @author G0042204
 */
public class RequestArrisAlter {

    public RequestArrisAlter(Integer deviceId, Autenticacao autenticacao, String frequency) throws UnsupportedEncodingException, IOException {
        Autenticacao a = AuthFactory.getEnd();
        String auth = a.getUser() + ":" + a.getPassword();
        String url = a.getLink() + "capability/execute?capability=" + URLEncoder.encode("\"getLanWiFiInfo\"", "UTF-8") + "&deviceId=" + deviceId + "&input=" + URLEncoder.encode(frequency, "UTF-8");

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(1);
        cm.setDefaultMaxPerRoute(1);
        HttpHost localhost = new HttpHost("10.200.6.150", 80);
        cm.setMaxPerRoute(new HttpRoute(localhost), 50);

        // Cookies
        RequestConfig globalConfig = RequestConfig.custom()
                .setCookieSpec(CookieSpecs.DEFAULT)
                .build();

        CloseableHttpClient httpclient = HttpClients.custom()
                .setConnectionManager(cm)
                .setDefaultRequestConfig(globalConfig)
                .build();

        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(Charset.forName("UTF-8")));
        String authHeader = "Basic " + new String(encodedAuth);

//        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet(url);
            httpget.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
            httpget.setHeader(HttpHeaders.CONTENT_TYPE, "text/html");
            httpget.setHeader("Cookie", "JSESSIONID=aaazqNDcHoduPbavRvVUv; AX-CARE-AGENTS-20480=HECDMIAKJABP");

            RequestConfig localConfig = RequestConfig.copy(globalConfig)
                    .setCookieSpec(CookieSpecs.STANDARD_STRICT)
                    .build();

            HttpGet httpGet = new HttpGet("/");
            httpGet.setConfig(localConfig);

//            httpget.setHeader(n);
            System.out.println("Executing request " + httpget.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httpget);

            try {
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());

                // Get hold of the response entity
                HttpEntity entity = response.getEntity();

                // If the response does not enclose an entity, there is no need
                // to bother about connection release
                if (entity != null) {
                    InputStream instream = entity.getContent();
                    try {
                        instream.read();
                        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                        String line = "";
                        while ((line = rd.readLine()) != null) {
                            System.out.println(line);
                        }
                        // do something useful with the response
                    } catch (IOException ex) {
                        // In case of an IOException the connection will be released
                        // back to the connection manager automatically
                        throw ex;
                    } finally {
                        // Closing the input stream will trigger connection release
                        instream.close();
                    }
                }
            } finally {
                response.close();
                for (Header allHeader : response.getAllHeaders()) {
                    System.out.println("Nome: " + allHeader.getName() + " Valor: " + allHeader.getValue());
                }
            }
        } finally {
            httpclient.close();
        }
        httpclient.close();
    }

}
