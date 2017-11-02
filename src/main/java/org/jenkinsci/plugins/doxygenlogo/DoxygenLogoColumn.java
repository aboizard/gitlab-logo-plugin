package org.jenkinsci.plugins.doxygenlogo;

import hudson.Extension;
import hudson.model.Job;
import hudson.views.ListViewColumn;
import hudson.views.ListViewColumnDescriptor;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.StaplerRequest;

public class DoxygenLogoColumn extends ListViewColumn {
    
    public DoxygenLogoProperty getDoxygenLogo(Job job) {
        DoxygenLogoProperty doxygen = new DoxygenLogoProperty();
        doxygen.setRepositoryUrl(job);
        return doxygen;
    } 
        
    @Extension
    public static class DoxygenLogoColumnDescriptor extends ListViewColumnDescriptor
    {
        @Override
        public boolean shownByDefault() {
            return false;
        }

        @Override
        public String getDisplayName() {
            return "Doxygen Repository";
        }

        @Override
        public ListViewColumn newInstance(StaplerRequest request, JSONObject formData) throws FormException {
            return new DoxygenLogoColumn();
        }
    }
}
