package com.dogbone0714.nuuhelper.utils;


import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginService {
    private static HttpClient client = new HttpClient();
    private static GetMethod getMethod = new GetMethod("eap10.nuu.edu.tw");
    private static PostMethod postMethod = new PostMethod();
    private static Map<String,String> map = new HashMap<String, String>();

    public static void login() throws HttpException, IOException{

        int status = client.executeMethod(getMethod);
        map.put("ASP.NET_SessionId", getMethod.getResponseHeader("Set-Cookie").getValue().trim().split(";")[0].split("=")[1]);


        getMethod.setURI(new URI("https://eap10.nuu.edu.tw/Login.aspx?logintype=S"));
        getMethod.addRequestHeader(new Header("Cookie", map.get("ASP.NET_SessionId").toString()));
        client.executeMethod(getMethod);
        map.put("ASP.NET_SessionId2", getMethod.getResponseHeader("Set-Cookie").getValue().trim().split(";")[0].split("=")[1]);
        Document doc = Jsoup.parse(getMethod.getResponseBodyAsStream(),"gbk","https://eap10.nuu.edu.tw/");
        //map.put("lt",doc.select("[name=lt]").attr("value"));
        System.out.println(map);





        postMethod.setURI(new URI("https://eap10.nuu.edu.tw/Login.aspx?logintype=S"));
        postMethod.setRequestHeader("Host", "eap10.nuu.edu.tw");
        postMethod.setRequestHeader("Referer", "https://eap10.nuu.edu.tw/Login.aspx?logintype=S");
        postMethod.addRequestHeader(new Header("Cookie", "ASP.NET_SessionId="+map.get("ASP.NET_SessionId")+";ASP.NET_SessionId="+map.get("ASP.NET_SessionId2")));
        postMethod.addParameter(new NameValuePair("_eventId", "submit"));
        postMethod.addParameter(new NameValuePair("isremenberme", "0"));
        postMethod.addParameter(new NameValuePair("losetime", "120"));
        postMethod.addParameter(new NameValuePair("password", "這裡填寫密碼"));
        postMethod.addParameter(new NameValuePair("username", "這裡填寫學號"));
        postMethod.addParameter(new NameValuePair("useValidateCode", "0"));
        //postMethod.addParameter(new NameValuePair("lt",map.get("lt")));
        client.executeMethod(postMethod);


        getMethod.setURI(new URI(postMethod.getResponseHeader("Location").getValue()));
        getMethod.setRequestHeader("Cookie","ASP.NET_SessionId="+map.get("ASP.NET_SessionId"));
        getMethod.setRequestHeader("Host", "eap10.nuu.edu.tw");
        getMethod.setRequestHeader("Referer", "https://eap10.nuu.edu.tw/Login.aspx?logintype=S");
        status = client.executeMethod(getMethod);

    }

    //public static void main(String[] args) throws HttpException, IOException {
   //     login();
  //  }

   // public static void main(String[] args) throws HttpException, IOException {
   //     login();
  //  }


  //  public static String get_score_course_kb(String type2, String toString, String toString1, String toString2, String xy, String zy, String kb) {
  //  }
}