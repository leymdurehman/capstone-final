<template>
  <div>
    <h1>Confirm Transaction</h1>
    <div id="transactionDetails">
      <div id="left">
        <img class="artImg" :src="imgFile" v-bind:alt="artPiece.imgFileName" />
      </div>
      <div id="right">
        <h3>Title: {{ artPiece.title }}</h3>
        <h3>Date: {{ artPiece.dateCreated }}</h3>
        <h3>Artist: {{ artPiece.artist }}</h3>
        <h3>Price: ${{ artPiece.price.toFixed(2) }}</h3>
        <h3>Fee: ${{transaction.fee}}</h3>
        <h3>Commission: $ {{transaction.commission}}</h3>
        <h3>Total Price: $ {{transaction.totalPrice}}</h3>
      </div>
      <div id="buyNow">
        <button id="confirm" @click="startTransaction()">Confirm</button>
        <div class="failed">
          <h2 v-if="statusMessage">{{ statusMessage }}</h2>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import firebase from "firebase";
import artPieceService from "@/services/ArtPieceService.js";
import transactionService from "@/services/TransactionService.js";
export default {
  name: "transaction-details",
  data() {
    return {
      artPiece: null,
      imgFile: {},
      foundId: 0,
      statusMessage: null,
      transaction: {},
    };
  },

  methods: {

    startTransaction(){

       
      
       transactionService.postTransaction(this.transaction)
       .then((response) => {
                if (response.status == 201) {

                  alert("Order has been confirmed! \nThank you for your purchase!");
                  this.$router.push({ path: '/'});
            }
        })
        .catch((error) => {
            const response = error.response;
            if (response.status !== 201) {
                this.statusMessage =
                "There were problems placing your order...";
            }
        });
    },
  },
  created() {
    this.foundId = this.$route.params.artId;
    artPieceService
      .getListingByArtId(this.foundId)
      .then((response) => {
        this.artPiece = response.data;
        this.transaction.customerId = this.$store.state.customerId;
        this.transaction.artID = this.artPiece.artID;
        this.transaction.fee = this.$store.state.fee * this.artPiece.price;
        this.transaction.commission = this.$store.state.commission * this.artPiece.price;
        this.transaction.totalPrice = (this.$store.state.fee * this.artPiece.price) + 
         (this.$store.state.commission * this.artPiece.price) + this.artPiece.price;
    

        let storage = firebase.storage();
        let storageRef = storage.ref();
        let imgRef = storageRef.child(this.artPiece.imgFileName);

        imgRef.getDownloadURL().then((url) => {
          this.imgFile = url;
        });
      })
      .catch(console.log("not working"));
  },
};
</script>

<style scoped>
#transactionDetails {
  background-color: #ab3f294b;
  border-radius: 20px;
  color: #f4f4f4eb;
  font-family: "Quicksand", sans-serif;
  margin: auto;
  padding: 20px;
  width: 60%;
  height: auto;
}

.artImg {
  width: 90%;
}

#left {
  grid-area: image;
}

#right {
  grid-area: details;
}

#buyNow {
  grid-area: confirm;
}

div#transactionDetails {
  display: grid;
  grid-template-columns: 1fr 200px 200px 1fr;
  grid-template-areas:
    ". image details ."
    ". . confirm .";
}

#confirm {
  padding: 5px 15px;
  background-color: #ab3f29;
  color: #f4f4f4eb;
  border-radius: 5px;
  border: none;
  box-shadow: 1.5px 1.5px 1.5px 1.5px #310f08b7;
  cursor: pointer;
  width: 100%;
}
</style>