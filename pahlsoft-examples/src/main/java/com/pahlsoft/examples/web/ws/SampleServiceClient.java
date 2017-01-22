package com.pahlsoft.examples.web.ws;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class SampleServiceClient {

	public static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(com.pahlsoft.examples.web.ws.SampleServiceClient.class);

	private String uri = null;

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	@SuppressWarnings("unused")
	public int post(String message) throws Exception {
		int statusCode = HttpStatus.SC_INTERNAL_SERVER_ERROR;
		DefaultHttpClient client = null;
		UrlEncodedFormEntity requestEntity = null;
		try {
			HttpPost post = new HttpPost(uri);
			post.addHeader("Content-Type", "text/xml");
			post.addHeader("SOAPAction", "abcd");
			post.setEntity(new StringEntity(message));

			if (log.isDebugEnabled()) {
				log.debug("POST message: " + pretty(post.getEntity()));
			}

			client = new DefaultHttpClient();
			HttpResponse response = client.execute(post);
			log.info(response.getStatusLine());

			statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK && log.isDebugEnabled()) {
				log.debug("POST message: " + pretty(response.getEntity()));
			} else {
				log.info(response.getStatusLine());
			}
		} catch (Exception ex) {
			String postMessage = null;
			if (requestEntity != null) {
				java.io.ByteArrayOutputStream os = new java.io.ByteArrayOutputStream();
				requestEntity.writeTo(os);
				postMessage = os.toString();
			}
			log.error("Unexpected error on POST: " + postMessage, ex);
		} finally {
			try { client.getConnectionManager().shutdown(); } catch (Exception e) { }
		}
		return statusCode;
	}

	public static String pretty(HttpEntity entity) {
		String xml = "";
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
			StringWriter writer = new StringWriter();
			pretty(reader, writer);
			xml = writer.toString();
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
		return xml;
	}

	public static void pretty(Reader reader, Writer writer) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource(reader);
			final Document document = db.parse(is);
			OutputFormat format = new OutputFormat(document);
			format.setLineWidth(72);
			format.setIndenting(true);
			format.setIndent(2);
			XMLSerializer serializer = new XMLSerializer(writer, format);
			serializer.serialize(document);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
	}

	public static void main(String[] arg) {
		try {
			StringWriter sw = new StringWriter();

			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("message.xml");
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line = reader.readLine();
			while (line != null) {
				sw.append(line);
				line = reader.readLine();
			}
			reader.close();
			
			String message = sw.toString();

			SampleServiceClient client = new SampleServiceClient();
			client.setUri("http://o340vpdc0lpr10.svr.us.jpmchase.net:9180/RSISoap/services/SampleService");
			client.setUri("http://localhost:9080/RSISoap/services/SampleService");
			client.post(message);

		} catch (Exception ex) {
			log.error("Unhandled error " + ex.getMessage(), ex);
		}
	}

}
