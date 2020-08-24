<template>
  <button @click="syncApi" :disabled="currentlySyncing">
    Sync
  </button>
</template>

<style lang="scss">
.toastmsg {
  box-shadow: none !important;
}
</style>

<script>
export default {
  name: 'syncButton',
  methods: {
    syncApi () {
      this.$store.commit('setCurrentlySyncing', true)

      this.$toastr.Add({
        name: 'syncingToast',
        title: 'Syncing with API',
        msg: 'Please wait..',
        clickClose: false,
        timeout: 0,
        position: 'toast-bottom-right',
        type: 'info',
        preventDuplicates: true,
        classNames: ['animated', 'bounceInRight', 'toastmsg']
      })

      this.axios.get(this.$store.state.serverUrl + 'sync').then((response) => {
        if (response.data) {
          this.$toastr.Add({
            name: 'successToast',
            title: 'Successfully Synced',
            msg: 'The syncing was successful (syncing with api, saving incorrect rows into csv files, uploading report.json to ftp)',
            clickClose: false,
            position: 'toast-bottom-right',
            type: 'success',
            timeout: 10000,
            preventDuplicates: true,
            classNames: ['animated', 'bounceInRight', 'toastmsg']
          })
        } else {
          this.$toastr.Add({
            name: 'failToast',
            title: 'Error while Syncing',
            msg: 'There was an error while syncing. Try again later or check your ftp and api credentials.',
            clickClose: false,
            position: 'toast-bottom-right',
            type: 'error',
            timeout: 10000,
            preventDuplicates: true,
            classNames: ['animated', 'bounceInRight', 'toastmsg']
          })
        }

        this.$toastr.removeByName('syncingToast')
        this.$store.commit('setCurrentlySyncing', false)
      })
    }
  },
  computed: {
    currentlySyncing () {
      return this.$store.state.currentlySyncing
    }
  }
}
</script>
