<template>
  <div>
    <div id="nav" class="sidenav">
      <router-link id="home" v-bind:to="{ name: 'home' }">Home</router-link>
      <router-link id="login" v-bind:to="{ name: 'login' }">Login</router-link>
      <router-link id="addart" v-bind:to="{ name: 'AddArt' }"
        >Create Listing</router-link
      >
      <router-link
        id="logout"
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        >Logout</router-link
      >
    </div>
    <router-view />
  </div>
</template>

<script>
export default {};
</script>

<style>
</style>