<template>
  <router-link
    v-bind:to="{ name: 'ArtDetails', params: { artId: artPiece.artID } }"
  >
    <div class="card">
      <img class="myImg" :src="imgFile" v-bind:alt="artPiece.imgFileName" />
      <h2>{{ artPiece.title }}</h2>
      <h3 id="mainArtist">{{ artPiece.artist }}</h3>
    </div>
  </router-link>
</template>

<script>
import firebase from "firebase";
export default {
  name: "display-art",
  props: ["artPiece"],

  data() {
    return {
      imgFile: {},
    };
  },

  created() {
    let storage = firebase.storage();
    let storageRef = storage.ref();
    let imgRef = storageRef.child(this.artPiece.imgFileName);

    imgRef.getDownloadURL().then((url) => {
      this.imgFile = url;
    });
  },
};
</script>

<style>
.card {
  background-color: #ab3f2969;
  border-width: 3px;
  /* border-style: outset; */
  box-shadow: 2px 2px 2px 2px #ab3f2985;
  border-color: #ab3f29;
  border-radius: 5px;
  top: 95px;
  width: 400px;
  height: auto;
  padding: 15px;
  margin: 20px;
  display: flex;
  flex-direction: column;
  color: #f4f4f4eb;
  font-family: "Quicksand", sans-serif;
}
@media (max-width: 1024px) {
  .card {
    width: 60%;
    min-width: 200px;
  }
}
@media (max-width: 500px) {
  .card {
    width: 250px;
    min-width: 0px;
  }
}
a:link {
  text-decoration: none;
}

.myImg {
  width: 100%;
  height: auto;
  /* border: solid; */
  border-color: #e06614;
  /* border-style: outset; */
  border-radius: 5px;
  box-shadow: 3px 3px 3px 3px #863121;
}

h2 {
  font-size: 1.25rem;
  font-style: italic;
}

#mainArtist {
  font-size: 1rem;
}
</style>