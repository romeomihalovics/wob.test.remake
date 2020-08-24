<template>
  <div class="reloadReport" @click="reloadReport">
    <span class="icon-reload"></span> Reload
  </div>
</template>

<style lang="scss">
.reloadReport {
  position: sticky;
  float:right;
  top: 0;
  padding: 10px;
  z-index: 1;
  cursor: pointer;
}
</style>

<script>
export default {
  name: 'reloadReportButton',
  methods: {
    reloadReport () {
      this.$store.commit('setLoadingReports', true)
      this.axios.get(this.$store.state.serverUrl + 'api/report').then((response) => {
        this.$store.commit('setReportData', response.data)
        this.$store.commit('setLoadingReports', false)
      })
    }
  }
}
</script>
