package com.zonvan;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MIME;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;
/**
 * Created by tommy on 2017/4/26.
 */
public class FileUpLoadTest {

	public static void main(String[] args) throws IOException {

		HttpClient client = HttpClientBuilder.create().build();

		HttpPost post = new HttpPost("http://localhost:8080/myPay/zv/fileUpLoad/addFuRsa");

		List<String> filesNames = new ArrayList<>();
		filesNames.add("2728_PFX_MER_PRIVATE_KEY.png");
		filesNames.add("2728_PFX_SER_PUBLIC_KEY.gif");
		filesNames.add("2737_PFX_SER_PUBLIC_KEY.jpg");

		String parentPath = "D:/pfx/";
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		Charset chars = Charset.forName("UTF-8");
		builder.setCharset(chars);

		for (String filesName : filesNames) {
			File file = new File(parentPath + filesName);

			builder.addPart(filesName, new FileBody(file, ContentType.create("image/jpeg")));
		}

		builder.addTextBody("pyMerchantId", "2747", ContentType.create("text/plain", MIME.UTF8_CHARSET));

		//
		HttpEntity entity = builder.build();
		post.setEntity(entity);
		HttpResponse response = client.execute(post);
	}

}
