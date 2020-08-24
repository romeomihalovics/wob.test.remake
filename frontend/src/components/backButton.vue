<template>
  <router-link to="/" class="backButton">
    <span class="icon-angle-left"></span> Back
  </router-link>
</template>

<style lang="scss">
.backButton {
  color: $color_white;
  padding:10px;
  position: absolute;
  left:10px;
  top:10px;
  & span {
    transition: .2s ease-out;
    vertical-align: middle;
  }
  &:hover {
    color: $color_white;
    & span {
      margin-right: 5px;
      font-size: 20px;
    }
  }
}
</style>

<script>
export default {
  name: 'backButton'
}
</script>
