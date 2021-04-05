<template>
  <div class="card">
      <h2>{{ artPiece.title }}</h2>
      <img class="myImg" :src="imgFile" v-bind:alt="artPiece.imgFileName"/>
      <h3>{{ artPiece.artist }}</h3>
      <h4>{{ artPiece.dateCreated }}</h4>
  </div>
</template>

<script>
import firebase from "firebase";
export default {
    name: 'display-art',
    props: ['artPiece'],

    data(){
        return {
            imgFile: {
            }
        }
    },

    created() {
        let storage = firebase.storage();
          let storageRef = storage.ref();
          let imgRef = storageRef.child(this.artPiece.imgFileName)

          imgRef.getDownloadURL()
            .then((url) => {
            this.imgFile = url;
            });
    }
}
</script>

<style>

</style>