<template>
  <div>
    <h1>Featured Art</h1>
    <router-link
      v-bind:to="{name: 'AddArt'}">
      Create Listing
    </router-link>
  </div>
</template>

<script>
export default {
   name: "home"

}
</script>

<style>


</style>