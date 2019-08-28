package com.max.core.utils;//package com.max.core.utils;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.betball.core.interceptor.MyHttpRequestInterceptor;
//import com.betball.core.interceptor.MyHttpRequestRetryHandler;
//import com.betball.core.interceptor.MyHttpResponseInterceptor;
//import com.betball.core.result.ResultGenerator;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.http.HttpEntity;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.nio.charset.Charset;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
///**
// * @Author Jesse
// * @Date 2019/6/25 17:18
// * @Description  GameHttpUtil是utils包下的HttpUtils包的升级版本，增加了拦截器来打印特殊日志。
// * <br> GameHttpUtil用在需要格式化一些特定的http日志时，目前gamebet模块使用。不直接修改HttpUtils是防止过度记录日志，不方便运维监控。
// **/
//@Slf4j
//public class GameHttpUtil {
//    /**
//     *  默认五秒超时时间
//     * @param url
//     * @param params
//     * @return
//     * @throws Exception
//     */
//    public static String doJsonPost(String url, Object params) throws Exception {
//        return doJsonPost(url, params, "UTF-8",5000);
//    }
//
//    /**
//     *  默认UTF-8字符集
//     * @param url
//     * @param params
//     * @param timeout
//     * @return
//     * @throws Exception
//     */
//    public static String doJsonPost(String url, Object params,int timeout) throws Exception {
//        return doJsonPost(url, params, "UTF-8",timeout);
//    }
//    public static String doJsonPost(String url, Object params, String charset,int timeout) throws Exception {
//        CloseableHttpClient httpclient = HttpClientBuilder.create().addInterceptorFirst(new MyHttpRequestInterceptor()).addInterceptorLast(new MyHttpResponseInterceptor()).setRetryHandler(new MyHttpRequestRetryHandler()).build();
//        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeout).setSocketTimeout(timeout).build();//设置请求和传输超时时间
//        HttpPost httpPost = new HttpPost(url);
//        httpPost.setConfig(requestConfig);
//        httpPost.addHeader("content-type", "application/json");
//        String entry = JSON.toJSONString(params);
//        httpPost.setEntity(new StringEntity(entry, charset));
//        CloseableHttpResponse response = httpclient.execute(httpPost);
//        String resStr = EntityUtils.toString(response.getEntity(), charset);
//        return resStr;
//    }
//
//    public static String doJsonPost(String url, JSONObject json, String charset) throws Exception {
//        CloseableHttpClient httpclient = HttpClientBuilder.create().addInterceptorFirst(new MyHttpRequestInterceptor()).addInterceptorLast(new MyHttpResponseInterceptor()).setRetryHandler(new MyHttpRequestRetryHandler()).build();
//        HttpPost httpPost = new HttpPost(url);
//        httpPost.addHeader("content-type", "application/json");
//        httpPost.setEntity(new StringEntity(json.toJSONString(), charset));
//        CloseableHttpResponse response2 = httpclient.execute(httpPost);
//        String resStr = EntityUtils.toString(response2.getEntity(), charset);
//        return resStr;
//    }
//
//    public static String doJsonPost(String url, JSONObject json, Map<String, String> headers, String charset) throws Exception {
//        CloseableHttpClient httpclient = HttpClientBuilder.create().addInterceptorFirst(new MyHttpRequestInterceptor()).addInterceptorLast(new MyHttpResponseInterceptor()).setRetryHandler(new MyHttpRequestRetryHandler()).build();
//        HttpPost httpPost = new HttpPost(url);
//        if (headers != null && !headers.isEmpty()) {
//            Iterator<Map.Entry<String, String>> it = headers.entrySet().iterator();
//            while (it.hasNext()) {
//                Map.Entry<String, String> entry = it.next();
//                httpPost.addHeader(entry.getKey(), entry.getValue());
//            }
//        }
//        httpPost.addHeader("content-type", "application/json");
//        httpPost.setEntity(new StringEntity(json.toJSONString(), charset));
//        CloseableHttpResponse response2 = httpclient.execute(httpPost);
//        String resStr = EntityUtils.toString(response2.getEntity(), charset);
//        return resStr;
//    }
//
//    public static String doPost(String url, int timeout, Map<String, Object> params) {
//        CloseableHttpClient httpClient = HttpClientBuilder.create().addInterceptorFirst(new MyHttpRequestInterceptor()).addInterceptorLast(new MyHttpResponseInterceptor()).setRetryHandler(new MyHttpRequestRetryHandler()).build();
//        RequestConfig config = getHttpConfig(timeout);
//        HttpPost httpPost = new HttpPost(url);
//        httpPost.setConfig(config);
//        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
//        Iterator<Map.Entry<String, Object>> it = params.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry<String, Object> entry = it.next();
//            urlParameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
//        }
//        try {
//            HttpEntity postParams = new UrlEncodedFormEntity(urlParameters);
//            httpPost.setEntity(postParams);
//
//            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
//
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//
//            while ((inputLine = reader.readLine()) != null) {
//                response.append(inputLine);
//            }
//            reader.close();
//
//            return response.toString();
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        } finally {
//            try {
//                httpClient.close();
//            } catch (IOException e) {
//            }
//        }
//        return null;
//    }
//
//
//
//    public static String doPost(String url, Map<String, String> params) {
//        CloseableHttpClient httpClient = HttpClientBuilder.create().addInterceptorFirst(new MyHttpRequestInterceptor()).addInterceptorLast(new MyHttpResponseInterceptor()).setRetryHandler(new MyHttpRequestRetryHandler()).build();
//        HttpPost httpPost = new HttpPost(url);
//
//        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
//        Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry<String, String> entry = it.next();
//            urlParameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
//        }
//        try {
//            HttpEntity postParams = new UrlEncodedFormEntity(urlParameters);
//            httpPost.setEntity(postParams);
//
//            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
//
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//
//            while ((inputLine = reader.readLine()) != null) {
//                response.append(inputLine);
//            }
//            reader.close();
//
//            return response.toString();
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        } finally {
//            try {
//                httpClient.close();
//            } catch (IOException e) {
//            }
//        }
//        return null;
//    }
//
//    public static String doPost(String url, Map<String, String> params, String charset) {
//        CloseableHttpClient httpClient = HttpClientBuilder.create().addInterceptorFirst(new MyHttpRequestInterceptor()).addInterceptorLast(new MyHttpResponseInterceptor()).setRetryHandler(new MyHttpRequestRetryHandler()).build();
//        HttpPost httpPost = new HttpPost(url);
//        List<NameValuePair> nvps = new ArrayList<>();
//        Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry<String, String> entry = it.next();
//            nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
//        }
//        try {
//            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(nvps, charset);
//            httpPost.setEntity(uefEntity);
//
//            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
//
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//
//            while ((inputLine = reader.readLine()) != null) {
//                response.append(inputLine);
//            }
//            reader.close();
//
//            return response.toString();
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        } finally {
//            try {
//                httpClient.close();
//            } catch (IOException e) {
//            }
//        }
//        return null;
//    }
//
//
//    public static String doPost(String url, Map<String, String> params, Map<String, String> headers, String charset) {
//        CloseableHttpClient httpClient = HttpClientBuilder.create().addInterceptorFirst(new MyHttpRequestInterceptor()).addInterceptorLast(new MyHttpResponseInterceptor()).setRetryHandler(new MyHttpRequestRetryHandler()).build();
//        HttpPost httpPost = new HttpPost(url);
//
//        if (headers != null && !headers.isEmpty()) {
//            Iterator<Map.Entry<String, String>> it = headers.entrySet().iterator();
//            while (it.hasNext()) {
//                Map.Entry<String, String> entry = it.next();
//                httpPost.addHeader(entry.getKey(), entry.getValue());
//            }
//        }
//        List<NameValuePair> nvps = new ArrayList<>();
//        if (params != null && !params.isEmpty()) {
//            Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
//            while (it.hasNext()) {
//                Map.Entry<String, String> entry = it.next();
//                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
//            }
//        }
//        try {
//            UrlEncodedFormEntity uefEntity = null;
//            if (charset != null) {
//                uefEntity = new UrlEncodedFormEntity(nvps, charset);
//            } else {
//                uefEntity = new UrlEncodedFormEntity(nvps);
//            }
//            httpPost.setEntity(uefEntity);
//
//            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
//
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//
//            while ((inputLine = reader.readLine()) != null) {
//                response.append(inputLine);
//            }
//            reader.close();
//
//            return response.toString();
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        } finally {
//            try {
//                httpClient.close();
//            } catch (IOException e) {
//            }
//        }
//        return null;
//    }
//
//
//    /**
//     * temp method get request ip address 20131009
//     *
//     * @return
//     */
//    public static String getRequestIp(HttpServletRequest request) {
//        String ip = request.getHeader("x-forwarded-for");
//
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getRemoteAddr();
//        }
//        if (ip != null && ip.contains(",")) {
//            return ip.split(",")[0].trim();
//        }
//        return ip;
//    }
//
//    public static String getUrl(HttpServletRequest request) {
//        String url = request.getHeader("Referer");
//        if (null == url || "".equals(url)) {
//            StringBuffer urlTmp = request.getRequestURL();
//            url = urlTmp.delete(urlTmp.length() - request.getRequestURI().length(), urlTmp.length()).append("/").toString();
//        }
//        return url;
//    }
//
//    public static String doGet(String url, Map<String, String> params) {
//        StringBuilder urlTmp = new StringBuilder(url);
//        urlTmp.append("?");
//        Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry<String, String> entry = it.next();
//            try {
//                urlTmp.append(entry.getKey()).append("=").append(java.net.URLEncoder.encode(String.valueOf(entry.getValue()), "utf-8"));
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//                return JSON.toJSONString(ResultGenerator.genFailResult("URL构建异常"));
//            }
//            urlTmp.append("&");
//        }
//        return doGet(urlTmp.toString());
//    }
//
//    public static String doGet(String url) {
//        CloseableHttpClient httpClient = HttpClientBuilder.create().addInterceptorFirst(new MyHttpRequestInterceptor()).addInterceptorLast(new MyHttpResponseInterceptor()).setRetryHandler(new MyHttpRequestRetryHandler()).build();
//        HttpGet httpGet = new HttpGet(url);
//        try {
//            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
//
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//
//            while ((inputLine = reader.readLine()) != null) {
//                response.append(inputLine);
//            }
//            reader.close();
//
//            return response.toString();
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        } finally {
//            try {
//                httpClient.close();
//            } catch (IOException e) {
//            }
//        }
//        return null;
//    }
//
//    public static String doGet(String url, int timeout, String charset) {
//        CloseableHttpClient httpClient = HttpClientBuilder.create().addInterceptorFirst(new MyHttpRequestInterceptor()).addInterceptorLast(new MyHttpResponseInterceptor()).setRetryHandler(new MyHttpRequestRetryHandler()).build();
//        HttpGet httpGet = new HttpGet(url);
//        RequestConfig requestConfig = RequestConfig.custom()
//                .setConnectTimeout(timeout).setConnectionRequestTimeout(timeout)
//                .setSocketTimeout(timeout).build();
//        httpGet.setConfig(requestConfig);
//        try {
//            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
//            return EntityUtils.toString(httpResponse.getEntity(), charset);
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        } finally {
//            try {
//                httpClient.close();
//            } catch (IOException e) {
//            }
//        }
//        return null;
//    }
//
//    public static String doTextPost(String url, String encodeJson, Map<String, String> headers, String charset) throws Exception {
//        CloseableHttpClient httpclient = HttpClientBuilder.create().addInterceptorFirst(new MyHttpRequestInterceptor()).addInterceptorLast(new MyHttpResponseInterceptor()).setRetryHandler(new MyHttpRequestRetryHandler()).build();
//        HttpPost httpPost = new HttpPost(url);
//        if (headers != null && !headers.isEmpty()) {
//            Iterator<Map.Entry<String, String>> it = headers.entrySet().iterator();
//            while (it.hasNext()) {
//                Map.Entry<String, String> entry = it.next();
//                httpPost.addHeader(entry.getKey(), entry.getValue());
//            }
//        }
//        httpPost.setEntity(new StringEntity(encodeJson, charset));
//        CloseableHttpResponse response2 = httpclient.execute(httpPost);
//        String resStr = EntityUtils.toString(response2.getEntity(), charset);
//        return resStr;
//    }
//
//    public static String doPut(String urlPath, String data, String charSet, String type) {
//        return doPut(urlPath, data, 2000000, charSet, type);
//    }
//
//    public static String doPut(String urlPath, String data,int timeout, String charSet, String type) {
//        String result = null;
//        URL url = null;
//        HttpURLConnection httpurlconnection = null;
//        try {
//            url = new URL(urlPath);
//            httpurlconnection = (HttpURLConnection) url.openConnection();
//            httpurlconnection.setDoInput(true);
//            httpurlconnection.setDoOutput(true);
//            httpurlconnection.setConnectTimeout(timeout);// 设置连接主机超时（单位：毫秒）
//            httpurlconnection.setReadTimeout(timeout);// 设置从主机读取数据超时（单位：毫秒）
//
////            if (header != null) {
////                for (int i = 0; i < header.length; i++) {
////                    String[] content = header[i].split(":");
////                    log.info("content[0]==="+content[0]);
////                    log.info("content[1]==="+content[1]);
////                    httpurlconnection.setRequestProperty(content[0], content[1]);
////                }
////            }
//            if(type!=null){
//                httpurlconnection.setRequestProperty("User-Agent", type);
//            }
//            httpurlconnection.setRequestMethod("PUT");
//            httpurlconnection.setRequestProperty("Content-Type", "application/json");
//
//
//            if (StringUtils.isNotBlank(data)) {
//                httpurlconnection.getOutputStream().write(data.getBytes("UTF-8"));
//            }
//            httpurlconnection.getOutputStream().flush();
//            httpurlconnection.getOutputStream().close();
//            int code = httpurlconnection.getResponseCode();
//
//            if (code == 200) {
//                DataInputStream in = new DataInputStream(httpurlconnection.getInputStream());
//                int len = in.available();
//                byte[] by = new byte[len];
//                in.readFully(by);
//                if (StringUtils.isNotBlank(charSet)) {
//                    result = new String(by, Charset.forName(charSet));
//                } else {
//                    result = new String(by);
//                }
//                in.close();
//            } else {
//                log.error("请求地址：" + urlPath + "返回状态异常，异常号为：" + code);
//            }
//        } catch (Exception e) {
//            log.error("访问url地址：" + urlPath + "发生异常", e);
//        } finally {
//            url = null;
//            if (httpurlconnection != null) {
//                httpurlconnection.disconnect();
//            }
//        }
//        return result;
//    }
//
//    private static RequestConfig getHttpConfig(int timeout){
//        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeout).setSocketTimeout(timeout).build();//设置请求和传输超时时间
//        return requestConfig;
//    }
//}
