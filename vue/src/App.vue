<template>
  <div id="app">
    <img src="./assets/Infinity ART (2) main.png" alt="logo" />
    <div>
      <div id="nav" class="sidenav">
        <div id="menuTab">Menu</div>
        <router-link id="home" v-bind:to="{ name: 'home' }">Home</router-link>
        <router-link id="login" v-bind:to="{ name: 'login' }"
          >Login</router-link
        >
        <router-link id="addart" v-bind:to="{ name: 'AddArt' }"
          >Add Listing</router-link
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
  </div>
</template>

<style scoped>
img {
  width: 150px;
  padding: 20px;
}
</style>