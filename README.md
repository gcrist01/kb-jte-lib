
# Jenkins Templating Engine

## The purpose of the Jenkins Templating Engine is three fold:

1. Optimize pipeline code reuse.

Now organizations can coalesce around a portfolio of centralized, reusable Pipeline Libraries representing different tool integrations for their CI/CD pipelines.

2. Simplify pipeline maintainability.

Separating a pipeline into templates, configuration files, and Pipeline Libraries can also be thought of as separating the business logic of your pipeline from the technical implementation. In our experience, it is significantly easier to manage a template backed by modularized Pipeline Libraries than it is to manage application-specific Jenkinsfiles.

3. Provide organizational governance.

With the traditional Jenkinsfile defined within, and duplicated across, source code repositories it can be very challenging to ensure the same process is being followed by disparate application development teams and confirm that organizational policies around code quality and security are being met. JTE brings a level of governance to your pipelines by centralizing the definition of the pipeline workflow to a common place.

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