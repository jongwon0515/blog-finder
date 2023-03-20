package com.blogfinder.modules.apihelper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import com.google.gson.Gson;

@Service
@RequiredArgsConstructor
public class KakaoRestApiHelper {
    private final WebClient restCustomClient;
    public enum HttpMethodType { POST, GET, DELETE }

    private static final String API_SERVER_HOST  = "https://dapi.kakao.com";

    private static final String SEARCH_BLOG_PATH = "/v2/search/blog";
    private static final ObjectMapper JACKSON_OBJECT_MAPPER = new ObjectMapper();

    private static final List<String> adminApiPaths = new ArrayList<String>();

    static {
        adminApiPaths.add(SEARCH_BLOG_PATH);
    }

    private String adminKey;

    public void setAdminKey(final String adminKey) {
        this.adminKey = adminKey;
    }


    ///////////////////////////////////////////////////////////////
    // Kakao Talk
    ///////////////////////////////////////////////////////////////

    public String searchBlog(final Map<String, String> params) {
        return request(HttpMethodType.GET, SEARCH_BLOG_PATH, mapToParams(params));
    }

    public String request(HttpMethodType httpMethod, final String apiPath, final String params) {

        String requestUrl = API_SERVER_HOST + apiPath;
        if (httpMethod == null) {
            httpMethod = HttpMethodType.GET;
        }
        if (params != null && params.length() > 0
                && (httpMethod == HttpMethodType.GET || httpMethod == HttpMethodType.DELETE)) {
            requestUrl += "?";
            requestUrl += params;
        }

        HttpsURLConnection conn;
        OutputStreamWriter writer = null;
        BufferedReader reader = null;
        InputStreamReader isr = null;

        try {
            final URL url = new URL(requestUrl);
            conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod(httpMethod.toString());

            conn.setRequestProperty("Authorization", "KakaoAK " + this.adminKey);

            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");

            if (params != null && params.length() > 0 && httpMethod == HttpMethodType.POST) {
                conn.setDoOutput(true);
                writer = new OutputStreamWriter(conn.getOutputStream());
                writer.write(params);
                writer.flush();
            }

            final int responseCode = conn.getResponseCode();
            System.out.println(String.format("\nSending '%s' request to URL : %s", httpMethod, requestUrl));
            System.out.println("Response Code : " + responseCode);
            if (responseCode == 200)
                isr = new InputStreamReader(conn.getInputStream());
            else
                isr = new InputStreamReader(conn.getErrorStream());

            reader = new BufferedReader(isr);
            final StringBuffer buffer = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            System.out.println(buffer.toString());
            return buffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) try { writer.close(); } catch (Exception ignore) { }
            if (reader != null) try { reader.close(); } catch (Exception ignore) { }
            if (isr != null) try { isr.close(); } catch (Exception ignore) { }
        }

        return null;
    }

    public String urlEncodeUTF8(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public String mapToParams(Map<String, String > map) {
        StringBuilder paramBuilder = new StringBuilder();
        for (String key : map.keySet()) {
            paramBuilder.append(paramBuilder.length() > 0 ? "&" : "");
            paramBuilder.append(String.format("%s=%s", urlEncodeUTF8(key),
                    urlEncodeUTF8(map.get(key).toString())));
        }
        return paramBuilder.toString();
    }

    public String mapToJsonStr(Map<String, String > map) throws JsonProcessingException {
        return JACKSON_OBJECT_MAPPER.writeValueAsString(map);
        // return GSON.toJson(map);
    }

    public void test() {
        // access token 지정
        // 푸시 알림이나 유저 아이디 리스트가 필요할 때 설정 합니다.
        // (디벨로퍼스 내에 앱설정 메뉴를 가시면 있습니다)
        setAdminKey("cf0010428b2be1dc030c91b4ef565584");

        String res = searchBlog(new HashMap<>() {{
            put("query", "개발");
        }});
        System.out.println(res);
    }
}