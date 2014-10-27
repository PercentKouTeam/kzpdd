package com.hpercent.snail.app.network;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;

public class HttpClient {
    URLConnection conn = null;
    OutputStream os = null;

    private String url;

    private final static int cacheSize = 1024;
    private final static int resultSize = 200 * 1024;

    public HttpClient(String url) throws MalformedURLException, IOException {
        this(new URL(url));
        this.url = url;
    }

    public HttpClient(URL url) throws IOException {
        this(url.openConnection());
    }

    public HttpClient(URLConnection conn) {
        this.conn = conn;
        conn.setDoOutput(true);
    }

    protected void connect() throws IOException {
        if (null == os) {
            os = conn.getOutputStream();
        }
    }

    public void write(String postData) throws IOException {
        connect();
        OutputStreamWriter wr = new OutputStreamWriter(os);
        wr.write(postData);
        wr.flush();
        wr.close();
    }


    public String post() throws IOException {
        try{
            HttpGet httpRequest = new HttpGet(url);
            HttpConnectionParams.setConnectionTimeout(httpRequest.getParams(), 5000);
            HttpConnectionParams.setSoTimeout(httpRequest.getParams(), 5000);

            httpRequest.addHeader("Accept-Encoding", "gzip");

            HttpResponse httpResponse = new DefaultHttpClient()
                    .execute(httpRequest);
            HttpEntity httpEntity = httpResponse.getEntity();
            InputStream gl = null;
            InputStream is = httpEntity.getContent();
            GZIPInputStream gis = null;
            if (httpEntity.getContentEncoding() != null
                    && httpEntity.getContentEncoding().getValue().contains("gzip")) {
                gis = new GZIPInputStream(is);
                gl = gis;
            } else {
                gl = is;
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            // Transfer bytes from the compressed stream to the output stream
            byte[] buf = new byte[cacheSize];
            int len;
            while ((len = gl.read(buf)) > 0) {
                out.write(buf, 0, len);
            }

            // Close the file and stream
            if (gis != null) {
                gis.close();
            }
            if (gl != null) {
                gl.close();
            }
            if (is != null) {
                is.close();
            }
            out.close();
            String s = new String(out.toByteArray(), 0, out.toByteArray().length,
                    "utf-8");

            return s;
        } catch (Exception e){
            return " {\"status\":1,\"data\":0}";
        }

    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    private byte[] copyArray(byte[] to, byte[] from, int index) {
        for (int i = index; i < from.length; i++) {
            to[i] = from[i];
        }
        return to;
    }

    public static byte[] unZipByte(byte[] data) {
        Inflater decompresser = new Inflater();
        // decompresser.reset();
        decompresser.setInput(data);
        // decompresser.finish();
        byte result[] = new byte[0];
        ByteArrayOutputStream o = new ByteArrayOutputStream(data.length);
        try {
            byte[] buf = new byte[cacheSize];
            int got = 0;
            while (!decompresser.finished()) {
                got = decompresser.inflate(buf);
                o.write(buf, 0, got);
            }
            result = o.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                o.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        decompresser.end();
        return result;
    }

    /***
     * ��ѹZip���ΪString
     *
     * @param data
     * @return
     */
    public static String unZipByteToString(byte[] data) {
        byte[] result = unZipByte(data);
        String outputString = null;
        try {
            outputString = new String(result, 0, result.length, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return outputString;
    }

    public String post(String postData) throws IOException {
        write(postData);
        return post();
    }

    public static String post(URL url, String postData) throws IOException {
        return new HttpClient(url).post(postData);
    }

    public static String post(String url, String postData)
            throws MalformedURLException, IOException {
        return new HttpClient(url).post(postData);
    }
}
