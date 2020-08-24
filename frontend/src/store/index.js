import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    serverUrl: '',
    currentlySyncing: false
  },
  mutations: {
    setServerUrl (state, data) {
      state.serverUrl = data
    },
    setCurrentlySyncing (state, data) {
      state.currentlySyncing = data
    }
  },
  actions: {
  },
  modules: {
  }
})
