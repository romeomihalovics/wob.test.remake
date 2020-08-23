<template>
  <button @click="syncApi">
    Sync
  </button>
</template>

<style lang="scss"></style>

<script>
export default {
  name: 'syncButton',
  methods: {
    syncApi () {
      this.axios.get(this.$store.state.serverUrl + 'sync').then((response) => {
        console.log(response)
      })
    }
  }
}
</script>
