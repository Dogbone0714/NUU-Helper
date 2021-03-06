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

public class Test {
    private static HttpClient client = new HttpClient();
    private static GetMethod getMethod = new GetMethod("http://i.cqut.edu.cn/");
    private static PostMethod postMethod = new PostMethod();
    private static Map<String,String> map = new HashMap<String, String>();

    public static void login() throws HttpException, IOException{
        //第一步 获取JSESSION
        int status = client.executeMethod(getMethod);
        map.put("JSESSION", getMethod.getResponseHeader("Set-Cookie").getValue().trim().split(";")[0].split("=")[1]);

        //第二步 获取第二个JSESSION和lt
        getMethod.setURI(new URI("http://i.cqut.edu.cn/zfca/login?service=http%3A%2F%2Fi.cqut.edu.cn%2Fportal.do"));
        getMethod.addRequestHeader(new Header("Cookie", map.get("JSESSION").toString()));
        client.executeMethod(getMethod);
        map.put("JSESSION2", getMethod.getResponseHeader("Set-Cookie").getValue().trim().split(";")[0].split("=")[1]);
        Document doc = Jsoup.parse(getMethod.getResponseBodyAsStream(),"gbk","http://i.cqut.edu.cn");
        map.put("lt",doc.select("[name=lt]").attr("value"));
        System.out.println(map);

        //第三步 发送POST请求
        postMethod.setURI(new URI("http://i.cqut.edu.cn/zfca/login?service=http%3A%2F%2Fi.cqut.edu.cn%2Fportal.do"));
        postMethod.addRequestHeader(new Header("Cookie", "JSESSION="+map.get("JSESSION")+";JSESSION="+map.get("JSESSION2")));
        postMethod.addParameter(new NameValuePair("_eventId", "submit"));
        postMethod.addParameter(new NameValuePair("isremenberme", "0"));
        postMethod.addParameter(new NameValuePair("losetime", "30"));
        postMethod.addParameter(new NameValuePair("password", "這裡填寫密碼"));
        postMethod.addParameter(new NameValuePair("username", "這裡填寫學號"));
        postMethod.addParameter(new NameValuePair("useValidateCode", "0"));
        postMethod.addParameter(new NameValuePair("lt",map.get("lt")));
        client.executeMethod(postMethod);

        //第四步 进入个人中心主页
        getMethod.setURI(new URI(postMethod.getResponseHeader("Location").getValue()));
        getMethod.setRequestHeader(new Header("Cookie", "JSESSION="+map.get("JSESSION")));
        for(int i=0;i<getMethod.getRequestHeaders().length;i++){
            System.out.println(getMethod.getRequestHeaders()[i].getName()+":"+getMethod.getRequestHeaders()[i].getValue());
        }
        status = client.executeMethod(getMethod);

        System.out.println(status);
        System.out.println(getMethod.getResponseBodyAsString());
    }

    public static void main(String[] args) throws HttpException, IOException {
        login();
    }


}