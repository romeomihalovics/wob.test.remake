module.exports = {
  devServer: {
  	port: 8000
  },
  css: {
    loaderOptions: {
      sass: {
        prependData: '@import \'@/scss/_variables.scss\'; @import \'@/scss/_defaults.scss\'; @import \'@/scss/_animate.scss\';'
      }
    }
  }
}