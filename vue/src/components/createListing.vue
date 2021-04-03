<template>
    <div id="app">
        <form>
            <div>
                <label>Artist Name:</label>
                <input type="text" name="artistName" v-model.trim="artPiece.artist"/>
            </div>
            <div>
                <label>Title:</label>
                <input type="text" name="title" v-model.trim="artPiece.title"/>
            </div>

            <div>
                <label>Date Created:</label>
                <input type="date" name="dateCreated" v-model.trim="artPiece.dateCreated"/>
            </div>

            <div>
                <label>Price:</label>
                <input type="number" step="0.01" min="0.01" name="price" v-model.trim="artPiece.price"/>
            </div>

            <div>
                <label>Dealer:</label>
                <input type="text" name="dealerName" v-model.trim="artPiece.dealer"/>
            </div>

            <div>
                <input type="file" @change="onFileChanged">
            </div>

            <div>
                <button type="submit" @click.prevent="onUpload">Submit The Form</button>
            </div>
        </form>
    </div>


</template>



<script>
// const firebase = require('../firebaseConfig.js');


export default {
  name: "createListing",
  data() {
    return {
        selectedFile: null,
        artPiece: {
            dealer: '',
            imgFile: '',
            artist: '',
            title: '',
            price: 0,
            dateCreated: ''
        }
    }

  },
  methods: {

  onFileChanged (event) {
    this.selectedFile = event.target.files[0]
  },
   onUpload() {
  //   const uploadTask = this.firebase.storageRef.child(this.selectedFile.name).put(this.selectedFile);  
        this.artPiece.imgFile = this.selectedFile.name;
    }

  }
}

</script>

<style scoped>
#app {
  text-align: center;
}
img {
  width: 30%;
  margin: auto;
  display: block;
  margin-bottom: 10px;
}
</style>
