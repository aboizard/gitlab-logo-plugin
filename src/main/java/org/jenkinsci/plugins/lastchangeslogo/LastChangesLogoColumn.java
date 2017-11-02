package org.jenkinsci.plugins.lastchangeslogo;

import hudson.Extension;
import hudson.model.Job;
import hudson.views.ListViewColumn;
import hudson.views.ListViewColumnDescriptor;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.StaplerRequest;

public class LastChangesLogoColumn extends ListViewColumn {
    
    public LastChangesLogoProperty getLastChangesLogo(Job job) {
        System.out.println("LastChanges------------->"+job.getName());
        LastChangesLogoProperty lastchanges = new LastChangesLogoProperty();
        lastchanges.setRepositoryUrl(job);
        return lastchanges;
    } 
        
    @Extension
    public static class LastChangesLogoColumnDescriptor extends ListViewColumnDescriptor
    {
        @Override
        public boolean shownByDefault() {
            return false;
        }

        @Override
        public String getDisplayName() {
            return "Last Changes Repository";
        }

        @Override
        public ListViewColumn newInstance(StaplerRequest request, JSONObject formData) throws FormException {
            return new LastChangesLogoColumn();
        }
    }
}
