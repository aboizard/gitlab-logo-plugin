package org.jenkinsci.plugins.workspacelogo;

import hudson.Extension;
import hudson.model.Job;
import hudson.views.ListViewColumn;
import hudson.views.ListViewColumnDescriptor;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.StaplerRequest;

public class WorkspaceLogoColumn extends ListViewColumn {
    
    public WorkspaceLogoProperty getWorkspaceLogo(Job job) {
        WorkspaceLogoProperty workspace = new WorkspaceLogoProperty();
        workspace.setRepositoryUrl(job);
        return workspace;
    } 
        
    @Extension
    public static class WorkspaceLogoColumnDescriptor extends ListViewColumnDescriptor
    {
        @Override
        public boolean shownByDefault() {
            return false;
        }

        @Override
        public String getDisplayName() {
            return "Workspace Repository";
        }

        @Override
        public ListViewColumn newInstance(StaplerRequest request, JSONObject formData) throws FormException {
            return new WorkspaceLogoColumn();
        }
    }
}
