package org.jenkinsci.plugins.doxygenlogo;

import hudson.model.Job;
import org.kohsuke.stapler.DataBoundConstructor;

public class DoxygenLogoProperty implements Property {
    //private static final String NOT_AVAILABLE = "NOT_ABLE_TO_REACH_URL";
    
    private String repositoryURL;

    @DataBoundConstructor
    public DoxygenLogoProperty() {
        this.repositoryURL = "NOT_ABLE_TO_REACH_URL";
    }

    public String getRepositoryUrl() {  
        return repositoryURL; 
    }
    
    public boolean isAvailable() {
        return true;
    }
    
    @Override
    public void setRepositoryUrl(Job job) {
        repositoryURL = job.getShortUrl()+"doxygen/";
        System.out.println(repositoryURL);     
    }
}
