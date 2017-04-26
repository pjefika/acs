/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import entidades.sys.Autenticacao;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import model.AuthFactory;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 *
 * @author G0042204
 */
public class RequestArris {

    public RequestArris(Integer deviceId, Autenticacao autenticacao, String frequency) throws UnsupportedEncodingException, IOException {

        Autenticacao a = AuthFactory.getEnd();

        HttpClient client = HttpClientBuilder.create().build();

        String url = a.getLink() + "capability/execute?capability=" + URLEncoder.encode("\"getLanWiFiInfo\"", "UTF-8") + "&deviceId=" + deviceId + "&input=" + URLEncoder.encode(frequency, "UTF-8");

        HttpPost post = new HttpPost(url);

        String auth = a.getUser() + ":" + a.getPassword();
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(Charset.forName("UTF-8")));
        String authHeader = "Basic " + new String(encodedAuth);
        post.setHeader(HttpHeaders.AUTHORIZATION, authHeader);

        StringEntity input = new StringEntity("product");
        input.setContentType("application/json");

        post.setEntity(input);
        HttpResponse response = client.execute(post);

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
        }
    }

}
