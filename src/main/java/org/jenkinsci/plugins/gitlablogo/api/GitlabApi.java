package org.jenkinsci.plugins.gitlablogo.api;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GitlabApi {
  private final String endpointUrl;
  private final String privateToken;
  private final HttpClient httpClient = new HttpClient();
  private static final Map<String, Project> PROJECT_CACHE = new HashMap<String, Project>();

  public GitlabApi(String endpointUrl, String privateToken){
    this.endpointUrl  = endpointUrl;
    this.privateToken = privateToken;
  }

  
  public Project getProject(String repositoryName) {
    try {
      String url = endpointUrl + repositoryName;
      String json = getContent(url);
      ObjectMapper mapper = new ObjectMapper();
      
      return mapper.readValue(json, Project.class);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Project getCachedProject(String repositoryName) {
    Project cache = PROJECT_CACHE.get(repositoryName);
    if (cache != null){
      return cache;
    }

    Project project = getProject(repositoryName);
    PROJECT_CACHE.put(repositoryName, project);
    return project;
  }

  protected String getContent(String url) throws IOException {
    HttpMethod method = new GetMethod(url);
    method.addRequestHeader("PRIVATE-TOKEN", privateToken);

    int statusCode = httpClient.executeMethod(method);

    if (statusCode != HttpStatus.SC_OK) {
      throw new RuntimeException("statusCode is " + statusCode);
    }

    try {
      return method.getResponseBodyAsString();

    } finally {
      method.releaseConnection();
    }
  }

  public static void clearCache(){
    PROJECT_CACHE.clear();
  }
  
}
