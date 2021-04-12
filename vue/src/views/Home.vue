<template>
  <div>
    <h1>Featured Art</h1>
    <div class="art-container">
      <display-art class="art-card"
        v-for="artPiece in $store.state.artPieceData"
        v-bind:key="artPiece.artID"
        v-bind:artPiece="artPiece"
      />
    </div>
  </div>
</template>

<script>
import DisplayArt from "../components/DisplayArt.vue";
import artPieceService from "@/services/ArtPieceService.js";

export default {
  name: "home",
  components: {
    DisplayArt,
  },
  created() {
    artPieceService
      .getAllListings()
      .then((response) => {
        this.$store.commit("SET_ART_DATA", response.data);
      })
      .catch((err) => console.error(err));
  },
};
</script>

<style scoped>
.art-container {
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
  max-width: 75%;
  margin: auto;
}

.art-card {
  display: flex;
  align-self: center;
  justify-self: center;
  margin: 30px;
  margin-top: 0;
  width: 400px;
}

</style>