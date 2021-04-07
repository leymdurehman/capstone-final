<template>
  <div class = "card">
      <img class="myImg" :src="imgFile" v-bind:alt="artPiece.imgFileName"/>
    <div class="info">
        <h2><i>{{ artPiece.title }}</i>,</h2>
        <h3>{{ artPiece.dateCreated }}</h3>
        <h4>{{ artPiece.artist }}</h4>
        <h5>${{artPiece.price}}</h5>
    </div>
    <div>
        <button class="buy">BUY</button>
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

.myImg{

    width: 100%;
    height: auto;
}

.info{
    
    text-align: left;
}

h5{
    /* display: flex; */
    float: right;
    margin-top: -57px;
}

.buy{
    float: right;
    margin-top: -35px;
    padding: 5px 15px;
    margin-right: -3px;
    background-color: #ab3f29;
    color: #f4f4f4eb;
    border-radius: 5px;
    border: none;
    box-shadow: 1.5px 1.5px 1.5px 1.5px #310f08b7;
   
}

</style>