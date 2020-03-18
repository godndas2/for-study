const path = require('path');

module.exports = {

    index: path.resolve(__dirname, "../../src/main/resources/static/index.html"),
    assetsRoot: path.resolve(__dirname, "../../src/main/resources/static"),
    assetsSubDirectory: 'static',
    assetsPublicPath: "/"
    // devServer: {
  
    //   overlay: false
  
    // }
  
  }