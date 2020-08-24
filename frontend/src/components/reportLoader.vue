<template>
  <transition name="loader-transition">
    <div class="reportLoader d-flex justify-content-center align-items-center flex-column" v-if="show">
      <div class="loader"></div>
    </div>
  </transition>
</template>

<style lang="scss">
.reportLoader {
  min-height: 100vh;
}

.loader {
  color: #ffffff;
  text-indent: -9999em;
  margin: 88px auto;
  position: relative;
  font-size: 11px;
  -webkit-transform: translateZ(0);
  -ms-transform: translateZ(0);
  transform: translateZ(0);
  -webkit-animation: load1 1s infinite ease-in-out;
  animation: load1 1s infinite ease-in-out;
  -webkit-animation-delay: -0.16s;
  animation-delay: -0.16s;
  background: #ffffff;
  width: .7em;
  height: 2em;
  &:before {
    background: #ffffff;
    -webkit-animation: load1 1s infinite ease-in-out;
    animation: load1 1s infinite ease-in-out;
    width: .7em;
    height: 2em;
    position: absolute;
    top: 0;
    content: '';
    left: -1.5em;
    -webkit-animation-delay: -0.32s;
    animation-delay: -0.32s;
  }
  &:after {
    background: #ffffff;
    -webkit-animation: load1 1s infinite ease-in-out;
    animation: load1 1s infinite ease-in-out;
    width: .7em;
    height: 2em;
    position: absolute;
    top: 0;
    content: '';
    left: 1.5em;
  }
}

.loader-transition-enter-active, .loader-transition-leave-active {
  transition: .2s;
  opacity: 1;
}
.loader-transition-enter, .loader-transition-leave-to {
  opacity: 0;
}

@-webkit-keyframes load1 {
  0%,
  80%,
  100% {
    box-shadow: 0 0;
    height: 3em;
  }
  40% {
    box-shadow: 0 -2em;
    height: 4em;
  }
}

@keyframes load1 {
  0%,
  80%,
  100% {
    box-shadow: 0 0;
    height: 3em;
  }
  40% {
    box-shadow: 0 -2em;
    height: 4em;
  }
}
</style>

<script>
export default {
  name: 'reportLoader',
  computed: {
    show () {
      return this.$store.state.loadingReports
    }
  }
}
</script>
