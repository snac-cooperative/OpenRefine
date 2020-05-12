
importPackage(org.snaccooperative.commands);

/*
 * Function invoked to initialize the extension.
 */
function init() {
    var RefineServlet = Packages.com.google.refine.RefineServlet;

    /*
     *  Exporters
     */
    var ExporterRegistry = Packages.com.google.refine.exporters.ExporterRegistry;
    var QSExporter = Packages.org.openrefine.wikidata.exporters.QuickStatementsExporter;
    var SchemaExporter = Packages.org.openrefine.wikidata.exporters.SchemaExporter;

    /*
     * Commands
     */
    RefineServlet.registerCommand(module, "resource", new SNACResourceCommand());
    RefineServlet.registerCommand(module, "constellation", new SNACConstellationCommand());
    RefineServlet.registerCommand(module, "apikey", new SNACLoginCommand());
    RefineServlet.registerCommand(module, "upload", new SNACUploadCommand());
    RefineServlet.registerCommand(module, "issue-snac-schema", new SNACSchemaIssuesCommand());
    RefineServlet.registerCommand(module, "preview-res-snac-schema", new SNACResourcePreviewSchemaCommand());
    RefineServlet.registerCommand(module, "preview-con-snac-schema", new SNACConstellationPreviewSchemaCommand());

    /*
     * Resources
     */
    ClientSideResourceManager.addPaths(
      "project/scripts",
      module,
      [
        "scripts/menu-bar-extension.js",
        "scripts/dialogs/manage-key-dialog.js",
        "scripts/dialogs/manage-upload-dialog.js",
        "scripts/dialogs/schema-alignment-dialog.js",
        "scripts/dialogs/import-schema-dialog.js",
      ]);

    ClientSideResourceManager.addPaths(
      "project/styles",
      module,
      [
        "styles/dialogs/manage-key-dialog.less",
        "styles/dialogs/manage-upload-dialog.less",
        "styles/dialogs/schema-alignment-dialog.css",
      ]);

}
