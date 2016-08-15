def pluginName = args.pluginName
def pluginKey = getProject("/plugins/$pluginName/project").pluginKey
def pluginDir = getProperty("/server/settings/pluginsDirectory").value + "/" + pluginName

def retrieveProcedure = 'RetrieveUniqueName'
def publishProcedure = 'publish'

deleteProperty "/server/ec_customEditors/pickerStep/$pluginKey - $retrieveProcedure"
deleteProperty "/server/ec_customEditors/pickerStep/$pluginKey - $publishProcedure"
return "Demoting plugin"
