<template>
  <div>
    <h1>Confirm Transaction</h1>
    <div id="confirm-grid">
      <div id="left-confirm">
        <img class="artImg" :src="imgFile" v-bind:alt="artPiece.imgFileName" />
      </div>
      <div id="right-confirm">
        <h3>Title: {{ artPiece.title }}</h3>
        <h3>Date: {{ artPiece.dateCreated }}</h3>
        <h3>Artist: {{ artPiece.artist }}</h3>
        <h3>Price: ${{ artPiece.price }}</h3>
        <!-- <h3>Fee: ${{transaction.fee.toFixed(2)}}</h3>
        <h3>Commission: $ {{transaction.commission.toFixed(2)}}</h3> -->
        <h5>+ Additional Fees</h5>
        <h3>Total Price: ${{ transaction.totalPrice.toFixed(2) }}</h3>
        <button id="confirm" @click="startTransaction()">Confirm</button>

        <router-link
          v-bind:to="{ name: 'ArtDetails', params: { artId: artPiece.artID } }"
        >
          <button id="confirm">Cancel</button>
        </router-link>
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
      currentDefaultFees: {},
    };
  },
  methods: {
    isOverride() {
      if (this.artPiece.hasOverride) {
        this.transaction.commission = this.artPiece.commissionOverride;
        this.transaction.fee = this.artPiece.feeOverride;

        this.transaction.fee =
          (this.artPiece.feeOverride / 100) * this.artPiece.price;
        this.transaction.commission =
          (this.artPiece.commissionOverride / 100) * this.artPiece.price;
        this.transaction.totalPrice =
          (this.artPiece.feeOverride / 100) * this.artPiece.price +
          (this.artPiece.commissionOverride / 100) * this.artPiece.price +
          this.artPiece.price;
      }
    },
    startTransaction() {
      transactionService
        .postTransaction(this.transaction)
        .then((response) => {
          if (response.status == 201) {
            alert("Order has been confirmed! \nThank you for your purchase!");
            this.$router.push({ path: "/" });
          }
        })
        .catch((error) => {
          const response = error.response;
          if (response.status !== 201) {
            this.statusMessage = "There were problems placing your order...";
          }
        });
    },
    returnToArtDetail() {
      this.$router.push({ path: "/home" });
    },
  },
  created() {
    this.foundId = this.$route.params.artId;
    artPieceService
      .getListingByArtId(this.foundId)
      .then((response) => {
        this.artPiece = response.data;
        this.transaction.artID = this.foundId;
        this.transaction.customerId = this.$store.state.customerId;
        let storage = firebase.storage();
        let storageRef = storage.ref();
        let imgRef = storageRef.child(this.artPiece.imgFileName);

        imgRef.getDownloadURL().then((url) => {
          this.imgFile = url;
        });
      })
      .catch(console.log("not working"));
    transactionService
      .getCurrentDefaultFees()
      .then((response) => {
        this.currentDefaultFees = response.data;
        this.transaction.fee =
          (this.currentDefaultFees.fee / 100) * this.artPiece.price;
        this.transaction.commission =
          (this.currentDefaultFees.commission / 100) * this.artPiece.price;
        this.transaction.totalPrice =
          (this.currentDefaultFees.fee / 100) * this.artPiece.price +
          (this.currentDefaultFees.commission / 100) * this.artPiece.price +
          this.artPiece.price;
        this.isOverride();
      })
      .catch((error) => {
        const response = error.response;
        console.log(response);
      });
  },
};
</script>

<style scoped>
#confirm-grid {
  background-color: #ab3f294b;
  border-radius: 20px;
  color: #f4f4f4eb;
  font-family: "Quicksand", sans-serif;
  margin: 20px auto;
  padding: 20px;
  width: 60%;
  height: auto;
}

.artImg {
  width: 100%;
}

#left-confirm {
  grid-area: image;
  align-self: center;
  margin-right: 20px;
}

#right-confirm {
  grid-area: details;
  padding: 5px;
}

#right-confirm > h3 {
  padding-bottom: 5px;
}

#confirm {
  margin-top: 10px;
}

div#confirm-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, 400px);
  grid-template-areas: ". image details";
  justify-content: center;
}

@media (max-width: 720px) {
  div#confirm-grid {
    grid-template-areas:
      ". image"
      ". details";
    padding: 20px;
  }
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