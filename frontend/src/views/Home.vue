<template>
  <div class="home d-flex justify-content-center align-items-center">
    <syncButton/>
    <viewReportButton/>
  </div>
</template>

<style lang="scss">
.home {
  min-height: 100vh;
}
</style>

<script>
import syncButton from '@/components/syncButton.vue'
import viewReportButton from '@/components/viewReportButton.vue'

export default {
  name: 'home',
  metaInfo () {
    return {
      title: 'Wob Test - Home',
      meta: [
        {
          name: 'description',
          content: 'Sample Java Project with Vue Frontend'
        }
      ]
    }
  },
  components: {
    syncButton,
    viewReportButton
  }
}
</script>
