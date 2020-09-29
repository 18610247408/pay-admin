package com.hhm.bussplat.util.util;

import org.apache.http.*;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;


import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Map.Entry;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class HttpUtil {
    private static String charset = "utf-8";
    private static Integer connectTimeout = null;
    private static Integer socketTimeout = null;

    /**
     * Do GET request
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static String doGet(String url,Map<String,String> headers, boolean isProxy) throws Exception {

        URL localURL = new URL(url);

        URLConnection connection = openConnection(localURL, isProxy);
        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
        httpURLConnection.setRequestProperty("Accept-Charset", charset);
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        if(headers != null){
            Iterator<Entry<String, String>> iterator = headers.entrySet().iterator();
            while (iterator.hasNext()){
                Entry<String, String> header = iterator.next();
                httpURLConnection.setRequestProperty(header.getKey(), header.getValue());
            }
        }

        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;

        if (httpURLConnection.getResponseCode() >= 300) {
        	throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode()+","+httpURLConnection.getResponseMessage());
        }

        try {
            inputStream = httpURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);

            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }

        } finally {

            if (reader != null) {
                reader.close();
            }

            if (inputStreamReader != null) {
                inputStreamReader.close();
            }

            if (inputStream != null) {
                inputStream.close();
            }

        }

        return resultBuffer.toString();
    }



    public static byte[] readStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = null;
        try {
            outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = -1;
            while((len = inStream.read(buffer)) != -1){
                outStream.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(outStream != null) outStream.close();
                if(inStream != null) inStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return outStream.toByteArray();
	  }




    public  static String entityToString(HttpEntity entity) throws IOException {
        String result = null;
        if (entity != null) {
            long lenth = entity.getContentLength();
            if (lenth != -1 && lenth < 2048) {
                result = EntityUtils.toString(entity, "UTF-8");
            } else {
                InputStreamReader reader1 = new InputStreamReader(entity.getContent(), "UTF-8");
                CharArrayBuffer buffer = new CharArrayBuffer(2048);
                char[] tmp = new char[1024];
                int l;
                while ((l = reader1.read(tmp)) != -1) {
                    buffer.append(tmp, 0, l);
                }
                result = buffer.toString();
            }
        }
        return result;
    }
    
    public static String doPost(String url, Map parameterMap,boolean isProxy) throws Exception {
        StringBuffer parameterBuffer = new StringBuffer();
        if (parameterMap != null) {
            Iterator iterator = parameterMap.keySet().iterator();
            String key = null;
            String value = null;
            while (iterator.hasNext()) {
                key = (String)iterator.next();
                if (parameterMap.get(key) != null) {
                    value = parameterMap.get(key).toString();
                } else {
                    value = "";
                }

                parameterBuffer.append(key).append("=").append(value);
                if (iterator.hasNext()) {
                    parameterBuffer.append("&");
                }
            }
        }
        URL localURL = new URL(url);

        URLConnection connection = openConnection(localURL,isProxy);
        HttpURLConnection httpURLConnection = (HttpURLConnection)connection;

        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Accept-Charset", charset);
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(parameterBuffer.length()));

        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;

        try {
            outputStream = httpURLConnection.getOutputStream();
            outputStreamWriter = new OutputStreamWriter(outputStream);

            outputStreamWriter.write(parameterBuffer.toString());
            outputStreamWriter.flush();

            if (httpURLConnection.getResponseCode() >= 300) {
                String s = "HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode() + "," + httpURLConnection.getResponseMessage();
                throw new HttpException(s);
            }

            inputStream = httpURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);

            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }
        } finally {

            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }

            if (outputStream != null) {
                outputStream.close();
            }

            if (reader != null) {
                reader.close();
            }

            if (inputStreamReader != null) {
                inputStreamReader.close();
            }

            if (inputStream != null) {
                inputStream.close();
            }

        }
        return resultBuffer.toString();
    }

    private static URLConnection openConnection(URL localURL,boolean isProxy) throws IOException {
        URLConnection connection;
        /*if(isProxy){
            if (proxyHost == null && proxyPort == null) {
                PropertiesUtil sysPro = PropertiesUtil.newInstance("classpath:system.properties");
                proxyHost=sysPro.getProperty("dirsell.http.proxy.host");
                proxyPort=Integer.valueOf(sysPro.getProperty("dirsell.http.proxy.port"));
            }

            if (proxyHost != null && proxyPort != null) {
                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
                connection = localURL.openConnection(proxy);
                System.out.println("com.pay.dirsell.commons.util.HttpUtil 使用代理开启http连接 uri"+localURL.getPath());
            } else {
                connection = localURL.openConnection();
            }
        }else{
            connection = localURL.openConnection();
        }*/
        connection = localURL.openConnection();
        return connection;
    }

    /**
     * Render request according setting
     */
    private static void renderRequest(URLConnection connection) {

        if (connectTimeout != null) {
            connection.setConnectTimeout(connectTimeout);
        }

        if (socketTimeout != null) {
            connection.setReadTimeout(socketTimeout);
        }

    }


    /**
     * map参数转成string
     * @param params
     * @return
     */
    public static String mapParam2String(Map<String, Object> params) {
        StringBuffer paramStr = new StringBuffer();
        if(params != null && params.size() > 0){
            Set<Map.Entry<String, Object>> entrySet = params.entrySet();
            Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
            while (iterator.hasNext()){
                Map.Entry<String, Object> param = iterator.next();
                paramStr.append(param.getKey()).append("=").append(param.getValue()).append("&");
            }
        }
        return paramStr.substring(0, paramStr.length() - 1);
    }

    /**
     * map参数转成string
     * @param param 格式 key1=val1&key2=val2
     * @return
     */
    public static Map<String,String> stringParam2Map(String param) {
        Map<String, String> paramsMap = null;
        if(param != null && !param.isEmpty()){
            paramsMap = new HashMap<>();
            String[] pairs = param.split("&");
            for (String pair :pairs) {
                String[] keyVal = pair.split("=");
                paramsMap.put(keyVal[0],keyVal[1]);
            }
        }
        return paramsMap;
    }

    public static String sendPost(String url, String param,Map<String,String> headers) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
        	URL realUrl = new URL(url);
            // 打开和URL之间的连接
        	URLConnection conn = openConnection(realUrl, true);

            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            if(headers != null){
                Iterator<Entry<String, String>> iterator = headers.entrySet().iterator();
                while (iterator.hasNext()){
                    Entry<String, String> header = iterator.next();
                    conn.setRequestProperty(header.getKey(), header.getValue());
                }
            }
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String str = "key1=val1&key2=val2";
        System.out.println(stringParam2Map(str));
    }
    
    
    public static String post(Map<String, Object> reqMap,String httpUrl,boolean isProxy) {
		HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(httpUrl);

        method.setRequestHeader("Content-type","application/x-www-form-urlencoded");

        method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, new Integer(300000));
        String response = "";
        try {
        	NameValuePair[] nvps = getNameValuePair(reqMap);
        	method.setRequestBody(nvps);
        	if(isProxy){
                client.getParams().setAuthenticationPreemptive(true);
            }
            int rescode = client.executeMethod(method);

            if (rescode == HttpStatus.SC_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), "UTF-8"));
                String curline = "";
                while ((curline = reader.readLine()) != null) {
                    response += curline;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
        }
        return response;
    }
	
	
	 public static NameValuePair[] getNameValuePair(Map<String, Object> bean) {
        List<NameValuePair> x = new ArrayList<NameValuePair>();
        for (Iterator<String> iterator = bean.keySet().iterator(); iterator.hasNext(); ) {
            String type = (String) iterator.next();
            x.add(new NameValuePair(type, String.valueOf(bean.get(type))));
        }
        Object[] y = x.toArray();
        NameValuePair[] n = new NameValuePair[y.length];
        System.arraycopy(y, 0, n, 0, y.length);
        return n;
    }
}
