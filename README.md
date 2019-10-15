In Firebase, setup production and development projects.
Place the google-services.json file for each environment in either the dev or prod folders under the environment_files inside the src/main/resources folder.
Ensure you have a local.properties file in the root of the android project.

In build.gradle, set the applicationId to localProperties["applicationId"].

Using Android Studio, you can create a build configuration that launches the runner script with the $Prompt$ argument.
In the popup, pass in either prod or dev.

You should see the correct google-services.json file show up in the app folder, and the environment and applicationId properties set in the local.properties file.

Ios is not currently supported. Pull requests welcome.
