package org.openmrs.module.lucenemodule.web.controller.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.codehaus.jackson.map.ObjectMapper;
import org.openmrs.module.lucenemodule.web.controller.model.IndexingResult;
import org.openmrs.module.lucenemodule.web.controller.model.PatientInfo;
import org.openmrs.module.lucenemodule.web.controller.model.SearchRequest;
import org.openmrs.module.lucenemodule.web.controller.model.SearchResult;
import org.springframework.web.multipart.MultipartFile;

public class HttpRequestSenderUtility {
	
	private static String ApacheSolrPostJsonURL = "http://localhost:8983/solr/update/json?commit=true";
	private static String ApacheSolrGetURL = "http://localhost:8983/solr/collection1/select";
	private static String ApacheSolrPostFileURL ="http://localhost:8983/solr/update/csv?commit=true";
	private static ObjectMapper mapper = new ObjectMapper();
	static SolrServer server = new HttpSolrServer("http://localhost:8983/solr/");
	
	public static IndexingResult indexPatient(PatientInfo patientInfo){
		
//		HttpClient client = HttpClientBuilder.create().build();
		IndexingResult indexingResult = new IndexingResult();
		
		if(patientInfo.getId()==null || patientInfo.getId().equals("")){
			indexingResult.setError("ID Pacijenta mora biti specificiran!");
			return indexingResult;
		}
		try {
			server.addBean(patientInfo);
			server.commit();
		} catch (Exception e) {
			indexingResult.setError(indexingResult.getError()+e.getMessage());
			e.printStackTrace();
		}
		
//		HttpPost httpPost = new HttpPost(ApacheSolrPostJsonURL);
//		httpPost.setHeader("Content-Type", "application/json");
//		String patientInfoJson = null;
//		try {
//			patientInfoJson = mapper.writeValueAsString(patientInfo);
//		} catch (Exception e) {
//			indexingResult.setError(indexingResult.getError()+e.getMessage());
//			e.printStackTrace();
//		}
//		patientInfoJson = "[" + patientInfoJson + "]";
//		StringEntity jsonBody = null;
//		try {
//			jsonBody = new StringEntity(patientInfoJson);
//		} catch (UnsupportedEncodingException e) {
//			indexingResult.setError(indexingResult.getError()+e.getMessage());
//			e.printStackTrace();
//		}
//		
//		httpPost.setEntity(jsonBody);
//		HttpResponse httpResponse = null;
//		try {
//			httpResponse = client.execute(httpPost);
//		} catch (Exception e) {
//			indexingResult.setError(indexingResult.getError()+e.getMessage());
//			e.printStackTrace();
//		}
//		indexingResult.setHttpResponse();
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
		else if(multipartFile.getOriginalFilename().contains("xml")) {
			httpPost.setHeader("Content-Type", "text/xml");
		}
		else{
			indexingResult.setError("Datoteka mora biti XML ili CSV formata!");
			return indexingResult;
		}
		httpPost.setHeader("charset", "utf-8");
		ByteArrayEntity byteArrayEntity = null;
		try {
			byteArrayEntity = new ByteArrayEntity(multipartFile.getBytes());
		} catch (Exception e) {
			indexingResult.setError(indexingResult.getError()+e.getMessage());
			e.printStackTrace();
		}
		
		httpPost.setEntity(byteArrayEntity);
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
