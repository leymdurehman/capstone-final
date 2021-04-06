<template>
  <div id="listing">
    <form v-on:submit.prevent="createListingForArtPiece()">
        <div>
          <div class="label"> 
            <label for="artistName" >Artist Name: </label>
          </div>
            <input
                type="text"
                name="artistName"
                id="artistName"
                v-model.trim="artPiece.artist"
            />
        </div>
        <div>
            <div class="label">
              <label for="title">Title: </label>
            </div>
            <input
                type="text"
                name="title"
                id="title"
                v-model.trim="artPiece.title"
            />
        </div>
        <div>
          <div class="label">
           <label for="dateCreated">Date Created: </label>
          </div>
            <input
                type="date"
                name="dateCreated"
                id="dateCreated"
                v-model.trim="artPiece.dateCreated"
            />
        </div>
        <div>
          <div class="label">
            <label for="price">Price: $</label>
          </div>
            <input
                type="number"
                step="0.01"
                min="0.01"
                name="price"
                id="price"
                v-model.trim="artPiece.price"
            />
        </div>
        <div>
          <div class="label">
           <label>Dealer Name: </label>
          </div>
           
            <input type="text" name="dealerName" v-model.trim="artPiece.dealer" />
        </div>
        <div>
          <div class="label">
               <label for="image">Image: </label>
          </div>
           
            <input type="file" id="image" @change="onFileChanged" />
        </div>
        <div class="label">
            <input type="submit" v-bind:disabled="!isFormValid" />
        </div>
    </form>
  </div>
</template>



<script>
import artPieceService from "@/services/ArtPieceService.js";
import firebase from "firebase";

export default {
  name: "createListing",

  data() {
    return {
      selectedFile: null,
      artPiece: {},
      listingError: false,
      listingErrorMessage: "",
    };
  },
  computed: {
    isFormValid() {
        return this.artPiece.dealer && this.selectedFile 
            && this.artPiece.artist && this.artPiece.title
            && this.artPiece.price && this.artPiece.dateCreated;
    }
  },
  methods: {
    onFileChanged(event) {
      this.selectedFile = event.target.files[0];
      this.artPiece.imgFileName = this.selectedFile.name;
    },
    onUpload() {
      const storageRef = firebase.storage().ref();
      storageRef.child(this.selectedFile.name).put(this.selectedFile);
    },
    createListingForArtPiece() {
      this.onUpload();
      artPieceService
        .createListing(this.artPiece)
        .then((response) => {
          if (response.status == 201) {
            this.$router.push({
              path: "/",
            });
          }
        })
        .catch((error) => {
          const response = error.response;
          this.listingError = true;
          if (response.status !== 201) {
            this.listingErrorMessage =
              "There were problems creating this listing.";
          }
        });
    },
  },
};
</script>

<style>
#listing {
  background-color: #ab3f294b;
  border-radius: 20px;
  color: #f4f4f4eb; 
  font-family: 'Quicksand', sans-serif;
  width: 40%;
  padding: 20px;
  margin: auto;
  display: flex;
  text-align: center;
  flex-direction: column;
  align-items: center;
  
}

img {
  width: 30%;
  margin: auto;
  display: block;
  margin-bottom: 10px;
}

button {
  margin: 10px;
}


.label{
  padding-top: 20px;
}


</style>
