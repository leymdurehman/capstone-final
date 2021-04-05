<template>
    <div>
        <div>
            <h1>Create Listing</h1>
        </div>
          <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ listingErrorMessage }}
          </div>
        <form id="listing">
            <span class="list-input">
                <label for="artistName">Artist Name: </label>
                <input type="text" name="artistName" id="artistName" v-model.trim="artPiece.artist"/>
            </span>

            <span class="list-input">
                <label for="title">Title: </label>
                <input type="text" name="title" id="title" v-model.trim="artPiece.title"/>
            </span>

            <span class="list-input">
                <label for="dateCreated">Date Created: </label>
                <input type="date" name="dateCreated" id="dateCreated" v-model.trim="artPiece.dateCreated"/>
            </span>

            <span class="list-input">
                <label for="price">Price: $</label>
                <input type="number" step="0.01" min="0.01" name="price" id="price" v-model.trim="artPiece.price"/>
            </span>

            <!-- <span class="list-input">
                <label>Dealer Name: </label>
                <input type="text" name="dealerName" v-model.trim="artPiece.dealer"/>
            </span> -->

            <span class="list-input">
                <label for="image">Image: </label>
                <input type="file" id="image" @change="onFileChanged">
            </span>

            <span>
                <button type="submit" @click.prevent="onUpload">Submit Art</button>
            </span>
        </form>
    </div>


</template>



<script>
import artPieceService from '@/services/ArtPieceService.js'
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
            price: '',
            dateCreated: ''
        },
        listingError: false,
        listingErrorMessage: ''
   
   
   }

  },
  methods: {

  onFileChanged (event) {
    this.selectedFile = event.target.files[0]
  },
   onUpload() {
  //   const uploadTask = this.firebase.storageRef.child(this.selectedFile.name).put(this.selectedFile);  
        this.artPiece.imgFile = this.selectedFile.name;

    },

    createListingForArtPiece(){
        artPieceService.createListing(this.artPiece).then((response)=> {
            if (response.status == 201) {
            this.$router.push({
                path: 'home'
            });
            }
        })
        .catch((error) => {
            const response = error.response;
            this.listingError = true;
            if (response.status !== 201) {
              
            this.listingErrorMessage = 'There were problems creating this listing.';
            }
          });

    }

  }
}

</script>

<style>
#listing {
    display: flex;
    flex-direction: column;
    align-items: center;
}

img {
  width: 30%;
  margin: auto;
  display: block;
  margin-bottom: 10px;
}

.list-input {
    margin: 10px;
    width: 400px;
}

button {
    margin: 10px;
}
</style>
