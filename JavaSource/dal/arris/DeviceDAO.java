/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.arris;

import dal.arris.auth.EnumEndpoint;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;
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
public class DeviceDAO {

    private EnumEndpoint end = EnumEndpoint.ARRIS_PRODUCAO;

    private CloseableHttpClient httpclient;

    private RequestConfig localConfig;

    private String authHeader;

    public DeviceDAO() {
    }

    public ComandoArris request(ComandoArris c) throws IOException {

        try {
            this.prepare();
            HttpGet httpget = new HttpGet(c.getRequestUrl());
            httpget.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
            httpget.setHeader(HttpHeaders.CONTENT_TYPE, "text/html");
            httpget.setHeader("Cookie", "JSESSIONID=aaazqNDcHoduPbavRvVUv; AX-CARE-AGENTS-20480=HECDMIAKJABP");

//            httpget.setConfig(localConfig);
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
                        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                        StringBuffer result = new StringBuffer();
                        String line = "";
                        while ((line = rd.readLine()) != null) {
                            result.append(line);
                        }

                        if (result.toString().contains("execution_error")) {
                            System.out.println(result.toString());
                            System.out.println("execution_error");
                            Thread.sleep(10000);
                            return this.request(c);
                        }

                        c.setResult(result.toString());

                    } catch (IOException ex) {
                        // In case of an IOException the connection will be released
                        // back to the connection manager automatically
                        throw ex;
                    } catch (InterruptedException ex) {
                        Logger.getLogger(DeviceDAO.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        // Closing the input stream will trigger connection release
                        instream.close();
                    }
                }
            } finally {
                response.close();
//                for (Header allHeader : response.getAllHeaders()) {
//                    System.out.println("Nome: " + allHeader.getName() + " Valor: " + allHeader.getValue());
//                }
            }
        } finally {
            httpclient.close();
        }
        return c;
    }

    private void prepare() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(1);
        cm.setDefaultMaxPerRoute(1);
        HttpHost ip = new HttpHost("10.200.6.150", 80);
        cm.setMaxPerRoute(new HttpRoute(ip), 50);

        // Cookies
        RequestConfig globalConfig = RequestConfig.custom()
                .setCookieSpec(CookieSpecs.DEFAULT)
                .build();

        httpclient = HttpClients.custom()
                .setConnectionManager(cm)
                .setDefaultRequestConfig(globalConfig)
                .build();

        String auth = end.getCred().getUser() + ":" + end.getCred().getPass();
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("UTF-8")));
        authHeader = "Basic " + new String(encodedAuth);

        localConfig = RequestConfig.copy(globalConfig)
                .setCookieSpec(CookieSpecs.STANDARD_STRICT)
                .build();

    }
}
