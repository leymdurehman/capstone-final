<template>
  <div>
    <h1>Confirm Transaction</h1>
    <div id="transactionDetails">
      <div id="left">
        <img class="artImg" :src="imgFile" v-bind:alt="artPiece.imgFileName" />
      </div>
      <div id="right">
        <h2>
          <i>{{ artPiece.title }}</i>
        </h2>
        <h3>{{ artPiece.dateCreated }}</h3>
        <h4>{{ artPiece.artist }}</h4>
        <h5>${{ artPiece.price }}</h5>
        <router-link v-bind:to="{ name: 'home' }">
          <button class="buy">Confirm</button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import firebase from "firebase";
import artPieceService from "@/services/ArtPieceService.js";
export default {
  name: "transaction-details",
  data() {
    return {
      artPiece: null,
      imgFile: {},
      foundId: 0,
    };
  },
  created() {
    this.foundId = this.$route.params.artId;
    artPieceService
      .getListingByArtId(this.foundId)
      .then((response) => {
        this.artPiece = response.data;

        let storage = firebase.storage();
        let storageRef = storage.ref();
        let imgRef = storageRef.child(this.artPiece.imgFileName);

        imgRef.getDownloadURL().then((url) => {
          this.imgFile = url;
        });
      })
      .catch(console.log("not working"));
  },
};
</script>

<style scoped>
#transactionDetails {
  background-color: #ab3f294b;
  border-radius: 20px;
  color: #f4f4f4eb;
  font-family: "Quicksand", sans-serif;
  margin: auto;
  padding: 20px;
  width: 60%;
}

.artImg {
  width: 40%;
  float: left;
}

/* img {
  float: left;
} */

#right {
  float: right;
}
</style>