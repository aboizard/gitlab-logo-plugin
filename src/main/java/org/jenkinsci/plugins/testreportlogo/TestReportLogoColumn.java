package org.jenkinsci.plugins.testreportlogo;

import hudson.Extension;
import hudson.model.Job;
import hudson.views.ListViewColumn;
import hudson.views.ListViewColumnDescriptor;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.StaplerRequest;

public class TestReportLogoColumn extends ListViewColumn {
 
    public TestReportLogoProperty getTestReportLogo(Job job) {
        TestReportLogoProperty testreport = new TestReportLogoProperty();
        testreport.setRepositoryUrl(job);
        return testreport;
    } 
        
    @Extension
    public static class TestReportLogoColumnDescriptor extends ListViewColumnDescriptor
    {
        @Override
        public boolean shownByDefault() {
            return false;
        }

        @Override
        public String getDisplayName() {
            return "Test Report Repository";
        }

        @Override
        public ListViewColumn newInstance(StaplerRequest request, JSONObject formData) throws FormException {
            return new TestReportLogoColumn();
        }
    }
}
