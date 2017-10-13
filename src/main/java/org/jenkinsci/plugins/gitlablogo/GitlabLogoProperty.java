package org.jenkinsci.plugins.gitlablogo;

import hudson.Extension;
import hudson.model.Job;
import hudson.model.JobProperty;
import hudson.model.JobPropertyDescriptor;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

public class GitlabLogoProperty extends JobProperty<Job<?, ?>> {
  private final String repositoryName;

  @DataBoundConstructor
  public GitlabLogoProperty(String endpointUrl, String repositoryName) {
    this.repositoryName = repositoryName;
  }

  /**
   * We'll use this from the <tt>config.jelly</tt>.
     * @return 
   */
  public String getRepositoryName() {
    return repositoryName;
  }

  public boolean isAvailable(){
    return StringUtils.isNotEmpty(repositoryName);
  }

  public boolean isDefaultIcon(){
    return true;
  }

  @Override
  public DescriptorImpl getDescriptor(){
    return (DescriptorImpl)super.getDescriptor();
  }

  public String getRepositoryUrl(){
    return repositoryName;
  }

  @Extension
  public static final class DescriptorImpl extends JobPropertyDescriptor
  {

    public DescriptorImpl(){
      super(GitlabLogoProperty.class);
      super.load();
    }

    @Override
    public String getDisplayName()
    {
      return "GitLab logo";
    }

    @Override
    public boolean isApplicable(Class<? extends Job> jobType)
    {
      return true;
    }

    @Override
    public GitlabLogoProperty newInstance(StaplerRequest req, JSONObject formData) throws FormException{
      return req.bindJSON(GitlabLogoProperty.class, formData);
    }
    @Override
    public boolean configure(StaplerRequest req, JSONObject formData) throws FormException {
      save();
      return super.configure(req, formData);
    }
  }
}
