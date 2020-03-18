 module.exports = {
    assetsDir: "static",
    outputDir: "../../../src/main/resources/static",
    indexPath: "../../../src/main/resources/static/index.html",
    devServer: {
      proxy: "http://localhost"
    }
  };