<template>
  <div class="card">
      <img class="myImg" :src="imgFile" v-bind:alt="artPiece.imgFileName"/>
      <h2>{{ artPiece.title }}</h2>
      <h3>{{ artPiece.artist }}</h3>
      <!-- <h4>{{ artPiece.dateCreated }}</h4> -->
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
  align-items: center;  
  color: #f4f4f4eb; 
  font-family: 'Quicksand', sans-serif;
}

.myImg{

    width: 100%;
    height: auto;
}

</style>