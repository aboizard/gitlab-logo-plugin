package org.jenkinsci.plugins.lastchangeslogo;

import hudson.model.Job;
import org.kohsuke.stapler.DataBoundConstructor;

public class LastChangesLogoProperty implements Property {
    //private static final String NOT_AVAILABLE = "NOT_ABLE_TO_REACH_URL";
    
    private String repositoryURL;

    @DataBoundConstructor
    public LastChangesLogoProperty() {
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
        repositoryURL = job.getShortUrl()+"changes";
        System.out.println(repositoryURL);     
    }
}
