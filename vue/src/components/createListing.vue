<template>
    <div>
          <!-- <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ listingErrorMessage }}
          </div> -->
        <form id="listing">
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
                <button type="submit" @click.prevent="createListingForArtPiece">Submit The Form</button>
            </div>
        </form>
    </div>


</template>



<script>
import artPieceService from '@/services/ArtPieceService.js'
import firebase from 'firebase'


 
 
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
        },
        listingError: false,
        listingErrorMessage: ''
    }
  },
  methods: {

  onFileChanged (event) {
    this.selectedFile = event.target.files[0];
  },
   onUpload() {
       const storageRef = firebase.storage().ref();
        storageRef.child(this.selectedFile.name).put(this.selectedFile);  
        this.artPiece.imgFile = this.selectedFile.name;
    },

    createListingForArtPiece(){
        this.onUpload();
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

<style scoped>
#listing {
  text-align: center;
}
img {
  width: 30%;
  margin: auto;
  display: block;
  margin-bottom: 10px;
}
</style>
