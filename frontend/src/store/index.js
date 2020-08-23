import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    serverUrl: ''
  },
  mutations: {
    setServerUrl (state, data) {
      state.serverUrl = data
    }
  },
  actions: {
  },
  modules: {
  }
})
