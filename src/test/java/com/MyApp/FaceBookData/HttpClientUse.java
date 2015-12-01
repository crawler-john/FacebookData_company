package com.MyApp.FaceBookData;

import java.io.IOException;  
import java.util.ArrayList;  
import java.util.List;  
  
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;  
import org.apache.commons.httpclient.Header;  
import org.apache.commons.httpclient.HttpClient;  
import org.apache.commons.httpclient.HttpException;  
import org.apache.commons.httpclient.HttpStatus;  
import org.apache.commons.httpclient.UsernamePasswordCredentials;  
import org.apache.commons.httpclient.auth.AuthScope;  
import org.apache.commons.httpclient.methods.GetMethod;  
import org.apache.commons.httpclient.params.HttpMethodParams;  
  
public class HttpClientUse {  
  
    public static void main(String[] args) throws HttpException, IOException {  
        HttpClient httpClient = new HttpClient();  
  
        httpClient.getHostConfiguration().setProxy("localhost", 8580);  
  
//        //需要验证 
//        UsernamePasswordCredentials creds = new UsernamePasswordCredentials("chenlb", "123456"); 
// 
//        httpClient.getState().setProxyCredentials(AuthScope.ANY, creds); 
//    
  
        //设置http头  
        List<Header> headers = new ArrayList<Header>();  
        headers.add(new Header("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)"));  
        httpClient.getHostConfiguration().getParams().setParameter("http.default-headers", headers);  
  
        GetMethod method = new GetMethod("https://graph.facebook.com/v2.5/105930651606?fields=id,name,about&access_token=CAACEdEose0cBABvLfFX7CnLI7r2pl9Cxw1XmibqGfdQZA5J3GRxbNyxocaOZB8BZCIO0fMhWVJM8yugOJPPmIUtENl7ozXCKpXQnv1Sn0O1YxNuxTB3UQhyomWOe1nJouyj9m0ZAYuL2bJXuJqnKgJG2RZANksXv31IU7i6PEhR12DZCaozrdcvFtv1kxPsl8CP6Xta5ImbE2TAQWqVrdE");  
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,  
                new DefaultHttpMethodRetryHandler(3, false));  
        try {  
            int statusCode = httpClient.executeMethod(method);  
  
            if (statusCode != HttpStatus.SC_OK) {  
                System.out.println("Method failed code="+statusCode+": " + method.getStatusLine());  
  
            } else {  
                System.out.println(new String(method.getResponseBody(), "gb2312"));  
            }  
        } finally {  
            method.releaseConnection();  
        }  
    }  
}  