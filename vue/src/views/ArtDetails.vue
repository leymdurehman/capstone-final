<template>
  <div class = "card">
      <img class="myImg" :src="imgFile" v-bind:alt="artPiece.imgFileName"/>
    <div class="info">
        <h2><i>{{ artPiece.title }}</i>,</h2>
        <h3>{{ artPiece.dateCreated }}</h3>
        <h4>{{ artPiece.artist }}</h4>
        <h5>${{artPiece.price}}</h5>
    </div>
  </div>
</template>

<script>
import firebase from "firebase"
import artPieceService from "@/services/ArtPieceService.js"
export default {
    name: 'art-details',

    data(){
        return {
            artPiece: null,
            imgFile: {},
            foundId: 0,
    
        }
    },

    created() {
        this.foundId = this.$route.params.artId;
        artPieceService.getListingByArtId(this.foundId).then( response => {
            this.artPiece = response.data;

            let storage = firebase.storage();
            let storageRef = storage.ref();
            let imgRef = storageRef.child(this.artPiece.imgFileName)

            imgRef.getDownloadURL()
            .then((url) => {
            this.imgFile = url;
            });

        }).catch (console.log("not working"));

        

    }

}
</script>

<style>

.card{
  background-color: #ab3f294b;
  border-radius: 20px;
  top: 95px;
  width: 400px;
  height: auto;
  padding: 25px;
  margin: auto auto 20px auto; 
  display: flex;
  flex-direction: column;
  /* align-items: center;   */
  color: #f4f4f4eb; 
  font-family: 'Quicksand', sans-serif;
  flex-basis: auto;
}

.myImg{

    width: 100%;
    height: auto;
}

.info{
    margin-right: 100px;
    text-align: left;
}

</style>