<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  beforeMount () {
    this.$store.commit('setServerUrl', 'http://localhost:8080/')
  },
  name: 'App'
}
</script>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
</style>
