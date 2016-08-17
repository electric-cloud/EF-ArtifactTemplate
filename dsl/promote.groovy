/*

	DSL Template for creating a plugin to support an artifact management system
	
	At a minimum, provide content for the retrieve artifact procedure below.  This will enable component creation using your artifact management system.  You can also provide a publish procedure.  These two procedures will then be available for process step definition.

*/

// Variables available for use in DSL code
def pluginName = args.pluginName
def pluginKey = getProject("/plugins/$pluginName/project").pluginKey
def pluginDir = getProperty("/server/settings/pluginsDirectory").value + "/" + pluginName
// END Variables

// Make sure the name is different from other artifact plugin procedures
// otherwise the component editor will pick up the wrong parameter dialog
def retrieveProcedure = 'RetrieveUniqueName'
// TODO: create a property with this name, then use in demote.groovy to remove the server level property below:
def publishProcedure = 'publish'

//  NMB-24135 workaround, provide a description
property "/server/ec_customEditors/pickerStep/$pluginKey - $retrieveProcedure", description: "", value: """\
	<step>
	   <project>/plugins/$pluginKey/project</project>
	   <procedure>$retrieveProcedure</procedure>
	   <category>System</category>
	</step>
""".stripIndent()

property "/server/ec_customEditors/pickerStep/$pluginKey - $publishProcedure", description: "", value: """\
	<step>
	   <project>/plugins/$pluginKey/project</project>
	   <procedure>$publishProcedure</procedure>
	   <category>System</category>
	</step>
""".stripIndent()

project pluginName,{
	
	// Make this plugin visible in all contexts
	//property "ec_visibility", value: "all" // Legal values: pickListOnly, hidden, all
	
	procedure retrieveProcedure, {
		formalParameter 'artifactName'
		// XML menu to define parameter dialog.  See http://docs.electric-cloud.com/eflow_doc/6_5/User/HTML/UserflowHTML.htm#help-tutorial_custparamlayout.htm?Highlight=ec_parameterForm for details
		property 'ec_parameterForm', value: '''\
			<editor>
				<formElement>
					<property>artifactName</property>
					<label>Artifact Name</label>
					<type>entry</type>
					<required>1</required>
					<formType>standard</formType>
				</formElement>
			</editor>
		'''.stripIndent()
/*		property 'ec_customEditorData', {
			property 'parameters', {
				property 'artifactName', {
					formType = 'standard'
				}
			}
			nameIdentifier = 'artifactName'
		} // property 'ec_customEditorData'
*/ 
	} // Procedure 'RetrieveUniqueName'
	
	procedure publishProcedure, {
		formalParameter 'artifactName'
		// XML menu to define parameter dialog.  See http://docs.electric-cloud.com/eflow_doc/6_5/User/HTML/UserflowHTML.htm#help-tutorial_custparamlayout.htm for details
		property 'ec_parameterForm', value: '''\
			<editor>
				<formElement>
					<property>artifactName</property>
					<label>Artifact Name</label>
					<type>entry</type>
					<required>1</required>
					<formType>standard</formType>
				</formElement>
			</editor>
		'''.stripIndent()
	} // procedure publishProcedure
	
	property 'ec_component_plugin', {
		description = 'Indicates this plugin can act as a component plug-in'
		property 'pluginType', value: 'Repository', {
			description = 'The back end type this plug-in supports (SCM, Repository or Other)'
		}
		property 'operations', {
			description = '''\
				List of operations supporting component features.
				The propertyName is the name to display,
				the propertyValue is the name of the procedure to use.
			'''.stripIndent()
			property 'Fetch content', value: 'RetrieveUniqueName', {
				description = 'This operation retrieves the content from the repository.'
				expandable = '0'
			} // property 'Fetch content'
		} // property 'operations'
		pluginType = 'Repository'
	} // property 'ec_component_plugin'

}
