<template>
  <div id="app">
    <img src="./assets/Infinity ART (2) main.png" alt="logo" />
    <div>
      <div id="nav" class="sidenav">
        <div id="menuTab">Menu</div>
        <router-link id="home" v-bind:to="{ name: 'home' }">Home</router-link>
        <router-link id="login" v-bind:to="{ name: 'login' }" v-if="$store.state.token == ''">
          Login </router-link>
        
        <div id="authListings" v-if="$store.state.token != ''">
           <router-link id="addart" v-bind:to="{ name: 'AddArt' }" v-if="$store.state.user.authorities[0].name != ('ROLE_USER')"> 
             Add Listing</router-link>
        </div>
       
        <router-link
          id="logout"
          v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token != ''"
          >Logout</router-link>
          <div id="authSettings" v-if="$store.state.token != ''">
            <router-link id="settings" v-bind:to="{name: 'Settings'}" v-if="$store.state.user.authorities[0].name == ('ROLE_ADMIN')">Settings</router-link>
          </div>
          
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