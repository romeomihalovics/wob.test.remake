<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  beforeMount () {
    this.$store.commit('setServerUrl', 'http://192.168.1.6:8080/')
  },
  name: 'App'
}
</script>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  background: linear-gradient(-45deg, $color_blue, $color_red, $color_yellow);
  background-size: 400% 400%;
  animation: gradient 20s ease infinite;
  color: $color_white;
}

@keyframes gradient {
  0% {
    background-position: 0% 50%;
  }
  50% {
    background-position: 100% 50%;
  }
  100% {
    background-position: 0% 50%;
  }
}
</style>
