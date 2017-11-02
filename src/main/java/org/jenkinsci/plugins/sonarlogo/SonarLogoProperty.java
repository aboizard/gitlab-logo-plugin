package org.jenkinsci.plugins.sonarlogo;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import hudson.model.Job;
import hudson.model.Run;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.kohsuke.stapler.DataBoundConstructor;
import org.xml.sax.SAXException;

public class SonarLogoProperty implements Property {
    
    //private static final String NOT_AVAILABLE = "NOT_ABLE_TO_REACH_URL";
    private String repositoryURL;

    @DataBoundConstructor
    public SonarLogoProperty() {
        this.repositoryURL = "NOT_ABLE_TO_REACH_URL";
    }

    public String getRepositoryUrl() {  
        System.out.println("PASSING_HERE?"+repositoryURL+"REALLY_SET?");    
        return repositoryURL; 
    }
    
    public boolean isAvailable() {
        return !("NOT_ABLE_TO_REACH_URL".equals(repositoryURL));
    }
    
    @Override
    public void setRepositoryUrl(Job job) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            Run lastSuccess = job.getLastSuccessfulBuild();
            File buildF = new File(lastSuccess.getRootDir(),"build.xml");
            Document xml = builder.parse(buildF);
            Element root = xml.getDocumentElement();
            XPathFactory xpf = XPathFactory.newInstance();
            XPath path = xpf.newXPath();
            
            String expression = "/com.tikal.jenkins.plugins.multijob.MultiJobBuild/actions/hudson.plugins.sonar.action.SonarAnalysisAction/url";
            repositoryURL = (String)path.evaluate(expression, root);
            System.out.println(repositoryURL);
              
            if (repositoryURL.length()<5){
                repositoryURL = "NOT_ABLE_TO_REACH_URL";
            }
        } catch (IOException e) {
            repositoryURL = "NOT_ABLE_TO_REACH_URL";
        } catch (ParserConfigurationException e) {
            repositoryURL = "NOT_ABLE_TO_REACH_URL";
        } catch (XPathExpressionException e) {
            repositoryURL = "NOT_ABLE_TO_REACH_URL";
        } catch (SAXException e) {
            repositoryURL = "NOT_ABLE_TO_REACH_URL";
        } 
       
    }
}
