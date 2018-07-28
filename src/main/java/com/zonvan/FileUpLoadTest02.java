package com.zonvan;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

/**
 * Created by tommy on 2017/4/26.
 */
public class FileUpLoadTest02 {


	private final static Logger logger = Logger.getLogger(FileUpLoadTest02.class.getName());

	@Test
	public void addFuRsa() throws IOException {

		File f1 = new File("D:/pfx/2728_PFX_MER_PRIVATE_KEY.png");
		File f2 = new File("D:/pfx/2737_PFX_SER_PUBLIC_KEY.jpg");

		HttpClient httpClient = HttpClientBuilder.create().build();


		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.addTextBody("clip", "rickroll").addTextBody("tos", "agree");

		builder.addTextBody("merchantId", "2747");

		builder.addBinaryBody(f1.getName(), f1, ContentType.create("application/octet-stream"), f1.getName());

		builder.addBinaryBody(f2.getName(), f2, ContentType.create("application/octet-stream"), f2.getName());

		HttpEntity entity = builder.build();

		HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/myPay/zv/fileUpLoad/addFuRsa.zv?merchantId=2747");
//		HttpPost httpPost = new HttpPost("http://zonvan.yudady.dynu.net/myPay/zv/fileUpLoad/addFuQrcode.zv?t=1&merchantId=2747");
		httpPost.setEntity(entity);
		HttpResponse response = httpClient.execute(httpPost);
		HttpEntity result = response.getEntity();
		System.out.println(result);

	}


	@Test
	public void deleteFuRsa() throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/myPay/zv/fileUpLoad/deleteFuRsa.zv");
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		nvps.add(new BasicNameValuePair("merchantId", "2747"));
		nvps.add(new BasicNameValuePair("fileName", "2728_PFX_MER_PRIVATE_KEY.png"));
		nvps.add(new BasicNameValuePair("fileName", "2737_PFX_SER_PUBLIC_KEY.jpg"));
		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		CloseableHttpResponse result = httpclient.execute(httpPost);


		System.out.println(result);

	}

	@Test
	public void addFuQrcode() throws IOException {


		String fileUrl = "http://61.216.133.169/rd/zv/fileUpLoad/addFuQrcode.zv";

		File f1 = new File("D:/pfx/2728_PFX_MER_PRIVATE_KEY.png");
		File f2 = new File("D:/pfx/2737_PFX_SER_PUBLIC_KEY.jpg");

		HttpClient httpClient = HttpClientBuilder.create().build();


		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.addTextBody("clip", "rickroll").addTextBody("tos", "agree");

		builder.addTextBody("merchantId", "2788");

		builder.addBinaryBody(f1.getName(), f1, ContentType.create("application/octet-stream"), f1.getName());

		//builder.addBinaryBody(f2.getName(), f2, ContentType.create("application/octet-stream"), f2.getName());

		HttpEntity entity = builder.build();


		HttpPost httpPost = new HttpPost(fileUrl+"&merchantId=2788");
		httpPost.setEntity(entity);
		HttpResponse response = httpClient.execute(httpPost);
		HttpEntity result = response.getEntity();
		System.out.println(result);

	}
}
