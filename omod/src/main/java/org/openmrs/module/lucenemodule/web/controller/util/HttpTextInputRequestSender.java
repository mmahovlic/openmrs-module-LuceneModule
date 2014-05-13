package org.openmrs.module.lucenemodule.web.controller.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.openmrs.module.lucenemodule.web.controller.model.IndexingResult;
import org.openmrs.module.lucenemodule.web.controller.model.PatientInfo;

public class HttpTextInputRequestSender {
	
	private static String ElasticSearchURL = "http://localhost:9200/openmrs/patient/";
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static IndexingResult sendHttpPostRequest(PatientInfo patientInfo){
		
		HttpClient client = HttpClientBuilder.create().build();
		IndexingResult indexingResult = new IndexingResult();
		HttpPost httpPost = new HttpPost(ElasticSearchURL);
		httpPost.setHeader("Content-Type", "application/json");
		String patientInfoJson = null;
		try {
			patientInfoJson = mapper.writeValueAsString(patientInfo);
		} catch (Exception e) {
			indexingResult.setError(indexingResult.getError()+e.getMessage());
			e.printStackTrace();
		}
		StringEntity jsonBody = null;
		try {
			jsonBody = new StringEntity(patientInfoJson);
		} catch (UnsupportedEncodingException e) {
			indexingResult.setError(indexingResult.getError()+e.getMessage());
			e.printStackTrace();
		}
		
		httpPost.setEntity(jsonBody);
		HttpResponse httpResponse = null;
		try {
			httpResponse = client.execute(httpPost);
		} catch (Exception e) {
			indexingResult.setError(indexingResult.getError()+e.getMessage());
			e.printStackTrace();
		}
		indexingResult.setHttpResponse(httpResponse);
		return indexingResult;
		
	}

}
