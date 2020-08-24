import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    serverUrl: '',
    currentlySyncing: false,
    loadingReports: true,
    reportData: {}
  },
  mutations: {
    setServerUrl (state, data) {
      state.serverUrl = data
    },
    setCurrentlySyncing (state, data) {
      state.currentlySyncing = data
    },
    setLoadingReports (state, data) {
      state.loadingReports = data
    },
    setReportData (state, data) {
      state.reportData = data
    }
  },
  actions: {
  },
  modules: {
  }
})
