<template>
  <div id="app">
    <img id="logo" src="./assets/Infinity ART (2) main.png" alt="logo" />
    <div>
      <div id="nav" class="sidenav">
        <div id="menuTab">Menu</div>
        <router-link class="tab" id="home" v-bind:to="{ name: 'home' }"
          >Home</router-link
        >
        <router-link
          class="tab"
          id="login"
          v-bind:to="{ name: 'login' }"
          v-if="$store.state.token == ''"
        >
          Login
        </router-link>

        <div id="authListings" v-if="$store.state.token != ''">
          <router-link
            class="tab"
            id="addart"
            v-bind:to="{ name: 'AddArt' }"
            v-if="$store.state.user.authorities[0].name != 'ROLE_USER'"
          >
            Add Listing</router-link
          >
        </div>

        <router-link
          class="tab"
          id="logout"
          v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token != ''"
          >Logout</router-link
        >

        <div id="authSettings" v-if="$store.state.token != ''">
          <router-link
            class="tab"
            id="settings"
            v-bind:to="{ name: 'Settings' }"
            v-if="$store.state.user.authorities[0].name == 'ROLE_ADMIN'"
            >Settings</router-link
          >
        </div>
      </div>
      <router-view />
    </div>
  </div>
</template>

<style scoped>
#logo {
  width: 180px;
  padding: 20px;
}

.sidenav {
  display: flex;
  flex-direction: column;
  position: fixed; /* Position them relative to the browser window */
  color: #f4f4f4eb;
  font-family: "Quicksand", sans-serif;
  text-align: right;
  top: 20px;
  padding: 20px;
  width: 400px;
  font-size: 20px; /* Increase font size */
  left: -250px;
}

#menuTab {
  background-color: #ab3f29;
  padding: 20px;
  margin: 10px 10px;
  border-radius: 5px;
  font-weight: bolder;
}

/* Style the links inside the sidenav */
.tab {
  color: #f4f4f4eb;
  font-family: "Quicksand", sans-serif;
  transition: 0.2s;
  padding: 20px;
  padding-right: 30px;
  margin: 5px;
  text-align: right;
  border-radius: 5px;
  background-color: #ab3f29d0;
  margin-right: 100px;
  display: flex;
  justify-content: end;
  border-right: 15px solid #ab3f29;
}

.tab:hover {
  margin-right: -30px;
}
.router-link-exact-active{
  margin-right: -30px;
  background-color: #ab3f29;
}
</style>