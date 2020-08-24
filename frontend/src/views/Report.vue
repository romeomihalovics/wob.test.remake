<template>
  <div class="report">
    <backButton/>
    <reportLoader/>
    <transition name="result-transition">
      <div class="d-flex result-wrapper"  :if="showResult">
        <div class="container result-container">
          <reloadReportButton/>
          <vue-json-pretty
            :data="result"
            :deep="1"
            :highlightMouseoverNode="true"
            :showLength="true"
          />
        </div>
      </div>
    </transition>
  </div>
</template>

<style lang="scss">
.report {
  min-height: 100vh;
}

.result-wrapper {
  min-height: 100vh;
}

.result-container {
  vertical-align: middle;
  max-height: 70vh;
  height: 100%;
  margin:auto;
  overflow: auto;
  padding: 15px;
  border-radius: 5px;
  background: $color_gray;
  position: relative;
}

.result-transition-enter-active, .result-transition-leave-active {
  transition: .2s;
  opacity: 1;
}
.result-transition-enter, .result-transition-leave-to {
  opacity: 0;
}
</style>

<script>
import backButton from '@/components/backButton.vue'
import reportLoader from '@/components/reportLoader.vue'
import VueJsonPretty from 'vue-json-pretty'
import reloadReportButton from '@/components/reloadReportButton.vue'

export default {
  name: 'report',
  mounted () {
    this.axios.get(this.$store.state.serverUrl + 'api/report').then((response) => {
      this.$store.commit('setReportData', response.data)
      this.$store.commit('setLoadingReports', false)
    })
  },
  components: {
    backButton,
    reportLoader,
    VueJsonPretty,
    reloadReportButton
  },
  metaInfo () {
    return {
      title: 'Wob Test - Report',
      meta: [
        {
          name: 'description',
          content: 'Sample Java Project with Vue Frontend'
        }
      ]
    }
  },
  computed: {
    result () {
      return this.$store.state.reportData
    },
    showResult () {
      return !this.$store.state.loadingReports
    }
  }
}
</script>
