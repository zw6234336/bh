package com.zhang.httpclient;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

public class HttpConnectionManager {

	private static final Charset CHAR_SET = Charset.forName("utf-8");
	/**
	 * 设置从connect Manager获取Connection 超时时间，单位毫秒
	 */
	private int defaultConnectionRequestTimeout = 1 * 1000;

	/**
	 * 请求超时时间
	 */
	private int defaultConnectionTimeout = 2 * 1000;

	/**
	 * 请求等待时间
	 */
	private int defaultSocketTimeout = 2 * 1000;

	// 默认每个主机最大连接数
	private int defaultMaxConnPerHost = 80;

	// 默认最大连接数
	private int defaultMaxTotalConn = 400;

	PoolingHttpClientConnectionManager pool = null;

	private static HttpConnectionManager httpConnectionManager = new HttpConnectionManager();

	/**
	 * 工厂方法
	 * 
	 * @return
	 */
	public static HttpConnectionManager getInstance() {
		return httpConnectionManager;
	}

	/**
	 * 私有的构造方法
	 */
	public HttpConnectionManager() {

		LayeredConnectionSocketFactory sslsf = null;
		try {
			sslsf = new SSLConnectionSocketFactory(SSLContext.getDefault());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
				.<ConnectionSocketFactory> create().register("https", sslsf)
				.register("http", new PlainConnectionSocketFactory()).build();

		pool = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		pool.setMaxTotal(defaultMaxTotalConn);
		pool.setDefaultMaxPerRoute(defaultMaxConnPerHost);
		pool.setDefaultConnectionConfig(ConnectionConfig.custom()
				.setCharset(CHAR_SET).build());
	}

	public void postExecute(String url, Map<String, String> params) {
		execute(url, "post", params);
	}

	public InputStream getExecute(String url) {
		return execute(url, "get", null);
	}

	private InputStream execute(String url, String method,Map<String, String> params) {
		InputStream result = null;
		CloseableHttpResponse response = null;
		HttpGet httpGet = null;
		ConnectionKeepAliveStrategy kaStrategy = new DefaultConnectionKeepAliveStrategy();

		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(defaultConnectionRequestTimeout)
				.setSocketTimeout(defaultSocketTimeout)
				.setConnectTimeout(defaultConnectionTimeout)
				.setCookieSpec("easy").setRedirectsEnabled(false).build();

		CloseableHttpClient httpClient = HttpClients.custom()
				.setConnectionManager(pool).setKeepAliveStrategy(kaStrategy)
				.setDefaultRequestConfig(requestConfig).build();

		try {
			httpGet = new HttpGet(new URI(url));
			response = httpClient.execute(httpGet);
			EntityUtils.consume(response.getEntity());
			result = response.getEntity().getContent();
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				result.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
