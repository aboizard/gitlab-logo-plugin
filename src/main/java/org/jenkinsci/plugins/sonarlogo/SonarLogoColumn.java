package org.jenkinsci.plugins.sonarlogo;

import hudson.Extension;
import hudson.model.Job;
import hudson.views.ListViewColumn;
import hudson.views.ListViewColumnDescriptor;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.StaplerRequest;

public class SonarLogoColumn extends ListViewColumn {
     
    public SonarLogoProperty getSonarLogo(Job job) {
        SonarLogoProperty sonar = new SonarLogoProperty();
        sonar.setRepositoryUrl(job);
        return sonar;
    } 
        
    @Extension
    public static class SonarLogoColumnDescriptor extends ListViewColumnDescriptor
    {
        @Override
        public boolean shownByDefault() {
            return false;
        }

        @Override
        public String getDisplayName() {
            return "Sonar Repository";
        }

        @Override
        public ListViewColumn newInstance(StaplerRequest request, JSONObject formData) throws FormException {
            return new SonarLogoColumn();
        }
    }
}
