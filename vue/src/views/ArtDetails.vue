<template>
  <div class="card">
    <img class="myImg" :src="imgFile" v-bind:alt="artPiece.imgFileName" />
    <div class="info">
      <h2>
        <i>{{ artPiece.title }}</i>
      </h2>
      <h3>{{ artPiece.dateCreated }}</h3>
      <h4>{{ artPiece.artist }}</h4>
      <h5 id="artPrice" v-if="!artPiece.sold">${{ artPiece.price.toFixed(2) }}</h5>
      <h5 id="soldArt" v-if="artPiece.sold">${{ artPiece.price.toFixed(2) }}</h5>
      <h5 id= "soldTag" v-if="artPiece.sold">SOLD</h5>
    </div>

    <div>
        <router-link v-bind:to="{ name: 'EditArt' , params: { artId: artPiece.artID }}">
            <button v-if="!artPiece.sold" id="editArt">EDIT</button>
        </router-link>
    </div>
    <div>
      <router-link v-bind:to="{ name: 'Transaction' , params: { artId: artPiece.artID }}">
        <button v-if="!artPiece.sold" class="buy">BUY</button>
      </router-link>
    </div>
  </div>
</template>

<script>
import firebase from "firebase";
import artPieceService from "@/services/ArtPieceService.js";
export default {
  name: "art-details",

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
  }
};
</script>

<style>






.myImg {
  width: 100%;
  height: auto;
}

.info {
  text-align: left;
}

#artPrice {
  /* display: flex; */
  float: right;
  margin-top: -57px;
}

.buy {
  float: right;
  margin-top: -35px;
  padding: 5px 15px;
  margin-right: -3px;
  background-color: #ab3f29;
  color: #f4f4f4eb;
  border-radius: 5px;
  border: none;
  box-shadow: 1.5px 1.5px 1.5px 1.5px #310f08b7;
  cursor: pointer;
}
.card {
  margin: auto auto 20px auto;
}

#editArt{
  float: right;
  margin-right: 65px;
  margin-top: -35px;
  padding: 5px 15px;
  background-color: #ab3f29;
  color: #f4f4f4eb;
  border-radius: 5px;
  border: none;
  box-shadow: 1.5px 1.5px 1.5px 1.5px #310f08b7;
  cursor: pointer;
}

@media(max-width: 500px) {
  #editArt{
    padding: 5px;
    margin-right: 50px;
  }
  .buy {
    padding: 5px;
  }
}
#soldArt {
  text-decoration: line-through;
  float: right;
  margin-top: -57px;
}

#soldTag {
  float: right;
  margin-top: -35px;
  margin-right: -3px;
  font-size: 30px;
  color:  #000000d5;
  font-weight: bold;
}
</style>