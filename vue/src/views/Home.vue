<template>
  <div>
    <div>
      <h1>Featured Art</h1>
    </div>

    <div class="art-container">
      <div
        class="card"
        v-for="artpiece in artPieceData"
        v-bind:key="artpiece.artID">
        <!-- <div class="art-title"> -->
          <h2>{{ artpiece.title }}</h2>
        <!-- </div> -->
        <img id="myimg" :src="downloadPhoto(artpiece.imgFileName)"/>
    
        <!-- <div class="art-artist"> -->
          <h3>{{ artpiece.artist }}</h3>
        <!-- </div> -->
        <!-- <div class="art-date"> -->
          <h4>{{ artpiece.dateCreated }}</h4>
        <!-- </div> -->
      </div>
    </div>
  </div>
</template>

<script>
import artPieceService from "@/services/ArtPieceService.js";
import firebase from "firebase";


export default {
  data() {
    return {
    
      artPieceData: []
    
    };
    
  },
  created() {
        this.getListingsData();
      } ,
      methods: {
        getListingsData() {
            artPieceService.getAllListings().then( response => {
                this.artPieceData = response.data;

            }).catch (err => console.error(err));
        }
        ,

        downloadPhoto(imgFileName){
          let storage = firebase.storage();
          let storageRef = storage.ref();
          let imgRef = storageRef.child(imgFileName)

          imgRef.getDownloadURL()
            .then((url) => {
            let img = document.getElementById('myimg');
            img.setAttribute('src', url)});
        }  


    }
}
</script>

<style>
</style>