package org.openmrs.module.lucenemodule.web.controller.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.map.ObjectMapper;
import org.openmrs.module.lucenemodule.web.controller.model.IndexingResult;
import org.openmrs.module.lucenemodule.web.controller.model.PatientInfo;
import org.openmrs.module.lucenemodule.web.controller.model.SearchRequest;
import org.openmrs.module.lucenemodule.web.controller.model.SearchResult;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.apache.http.entity.*;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;

public class HttpRequestSenderUtility {
	
	private static String ApacheSolrPostJsonURL = "http://localhost:8983/solr/update/json?commit=true";
	private static String ApacheSolrGetURL = "http://localhost:8983/solr/collection1/select";
	private static String ApacheSolrPostFileURL ="http://localhost:8983/solr/update/csv?commit=true";
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static IndexingResult indexPatient(PatientInfo patientInfo){
		
		HttpClient client = HttpClientBuilder.create().build();
		IndexingResult indexingResult = new IndexingResult();
		
		if(patientInfo.getId()==null || patientInfo.getId().equals("")){
			indexingResult.setError("ID Pacijenta mora biti specificiran!");
			return indexingResult;
		}
		
		HttpPost httpPost = new HttpPost(ApacheSolrPostJsonURL);
		httpPost.setHeader("Content-Type", "application/json");
		String patientInfoJson = null;
		try {
			patientInfoJson = mapper.writeValueAsString(patientInfo);
		} catch (Exception e) {
			indexingResult.setError(indexingResult.getError()+e.getMessage());
			e.printStackTrace();
		}
		patientInfoJson = "[" + patientInfoJson + "]";
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
	
	public static SearchResult searchIndex(SearchRequest searchRequest){
		
		HttpClient client = HttpClientBuilder.create().build();
		SearchResult searchResult = new SearchResult();
		
		if((searchRequest.getId() == "" && searchRequest.getQuery() == "") || (searchRequest.getId() != "" && searchRequest.getQuery() != "")){
			searchResult.setError("Morate zadati ili pacijentov ID, ili upit za pretragu teksta! ID:"+searchRequest.getId()+", Query:" + searchRequest.getQuery());
			return searchResult;
		}
		
		HttpResponse httpResponse = null;
		
		if(searchRequest.getId()!=""){
			StringBuilder paramsBuilder = new StringBuilder(ApacheSolrGetURL);
			paramsBuilder.append("?q=id:");
			paramsBuilder.append(searchRequest.getId());
			paramsBuilder.append("&wt=json&indent=true");
			HttpGet httpGet = new HttpGet(paramsBuilder.toString());
			try {
				httpResponse = client.execute(httpGet);
			} catch (Exception e) {
				searchResult.setError(searchResult.getError()+e.getMessage());
				e.printStackTrace();
			}
			searchResult.setSearchResultJson(getJsonResultFromResponse(httpResponse, searchResult));
			return searchResult;
		}
		
		StringBuilder paramsBuilder = new StringBuilder(ApacheSolrGetURL);
		paramsBuilder.append("?q=");
		paramsBuilder.append(searchRequest.getQuery());
		paramsBuilder.append("&wt=json&indent=true");
		HttpGet httpGet = new HttpGet(paramsBuilder.toString());
		try {
			httpResponse = client.execute(httpGet);
		} catch (Exception e) {
			searchResult.setError(searchResult.getError() + e.getMessage());
			e.printStackTrace();
		}
		searchResult.setSearchResultJson(getJsonResultFromResponse(httpResponse, searchResult));
		
		return searchResult;
		
	}
	
	private static String getJsonResultFromResponse(HttpResponse httpResponse, SearchResult searchResult){
		try {
			return IOUtils.toString(httpResponse.getEntity().getContent(), "UTF-8");
		} catch (Exception e) {
			searchResult.setError("Failed to get JSON result from HttpResponse!");
			return null;
		}
	}
	
	public static IndexingResult indexFile(MultipartFile multipartFile){
		HttpClient client = HttpClientBuilder.create().build();
		IndexingResult indexingResult = new IndexingResult();
		if (multipartFile == null) {
			indexingResult.setError("Datoteka nije odabrana!");
			return indexingResult;
		}
		
		HttpPost httpPost = new HttpPost(ApacheSolrPostFileURL);
		if(multipartFile.getOriginalFilename().contains("csv")){
			httpPost.setHeader("Content-Type", "text/csv");
		}
		else{
		httpPost.setHeader("Content-Type", "text/xml");
		}
		httpPost.setHeader("charset", "utf-8");
//		File file = null;
//		try {
//			file = multipartToFile(multipartFile);
//		} catch (Exception e) {
//			indexingResult.setError(indexingResult.getError()+e.getMessage());
//			e.printStackTrace();
//		}
//		
		MultipartEntityBuilder entity = MultipartEntityBuilder.create();
		//entity.addPart("", new FileBody(file));
		try {
			entity.addBinaryBody("file", multipartFile.getBytes());
		} catch (IOException e) {
			indexingResult.setError(indexingResult.getError()+e.getMessage());
			e.printStackTrace();
		}
		
		httpPost.setEntity(entity.build());
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
	
    private static File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
        File tmpFile = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + 
                                multipart.getOriginalFilename());
        multipart.transferTo(tmpFile);
        return tmpFile;
    }
}
