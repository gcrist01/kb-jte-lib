## Configure the Library Source

Now that we have a GitHub repository containing Pipeline Libraries, we have to tell JTE where to find them.

This is done by configuring a Library Source in Jenkins.

To make our libraries accessible to every job configured to use JTE on the Jenkins instance:

- In the left-hand navigation menu, click Manage Jenkins.
- User System Configuration, click Configure System.
- Scroll down to the Jenkins Templating Engine configuration section.
- Click Add under Library Sources -- don't edit the Pipeline Configuration section.
- Ensure the Library Provider is set to From SCM.
- Select Git as the SCM type.
- Enter the https repository URL to your library repository you pushed the Groovy scripts to. It should end in .git.
- Under Branch Specifier, specify whatever branch you have been pushing changes to, be it */main, */master, or something else.
- In the Credentials drop down menu, select the github credential created during the prerequisites.
- Enter libraries in the Base Directory text box.
- Click Save.