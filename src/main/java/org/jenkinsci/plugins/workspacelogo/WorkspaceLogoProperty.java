package org.jenkinsci.plugins.workspacelogo;

import hudson.model.Job;
import org.kohsuke.stapler.DataBoundConstructor;

public class WorkspaceLogoProperty implements Property {
    //private static final String NOT_AVAILABLE = "NOT_ABLE_TO_REACH_URL";
    
    private String repositoryURL;

    @DataBoundConstructor
    public WorkspaceLogoProperty() {
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
        repositoryURL = job.getShortUrl()+"ws";
        System.out.println(repositoryURL);     
    }
}
