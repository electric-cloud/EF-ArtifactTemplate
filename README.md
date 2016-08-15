# EF-ArtifactTemplate
The plugin template can be use for adding support for currently-unsupported artifact repositories.  It provides a DSL template for adding retrieve and publish procedures.  It includes the necessary properties to make these procedures available in the UI for component and process step creation.

## Steps to creating a custom artifact plugin from this template
1. Copy directory, e.g., cp -r EF-ArtifactTemplate EF-<artifactRepoName>
2. Decide on a version number, e.g., 1.0
3. Edit META-INF/plugin.xml (key, version, label) with the name and version of your plugin
4. Edit dsl/promote.groovy to define the retrieve and publish procedures and any other procedures desired
5. Zip up the directories only (dsl, META-INF, pages and any others you have added) 
6. Import plugin and promote

## Alternative steps to creating a plugin
The PowerShell script createPlugin.ps1 can be used as an alternative to the instructions above.  Edit dsl/promote.groovy to include your own DSL, then
```powershell
ectool --server <your flow server> login <username>
.\createPlugin.ps1 <your plugin name> <version> <description>
```
This will change META-INF/plugin.xml to the values provided, create a jar file plugin from directories, upload  and promote the plugin.

## Debugging
Promotion and Demotion logging information is written to both a property and a file:
- Property: plugin property sheet / ec_setup.log
- File: flow plugins directory / plugin name / ec_setup.log

## Optional functionality
The file dsl/demote.groovy is run when the plugin in demoted.  Use this to clean up any properties, project or other objects that were created by promote.groovy.

