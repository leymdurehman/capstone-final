<template>
  <div>
    <h1>My Account</h1>
    <report-status />

    <!-- <div id="buttonContainer">
      <button v-bind:disabled="!isFormValid" v-on:click="getAll()">
        All Time
      </button>
      <button v-bind:disabled="!isFormValid" v-on:click="get30days()">
        Last 30 Days
      </button>
      <button v-bind:disabled="!isFormValid" v-on:click="get7days()">
        Last 7 Days
      </button>
    </div> -->

    <div id="reportStatus">
      <transaction-status />
    </div>
  </div>
</template>


<script>
import ReportStatus from "../components/ReportStatus.vue";
import TransactionStatus from "../components/TransactionStatus.vue";
//import transactionService from "@/services/TransactionService.js";

export default {
  name: "MyAccount",
  data() {
    return {
      transactions: [],
    };
  },
  components: {
    ReportStatus,
    TransactionStatus,
  },
  // computed: {
  //   getAll() {
  //     transactionService
  //     .getAllTransactions()
  //       .then((response) => {
  //         this.transactions = response.data;
  //         this.getArtistTransactions();
  //         this.getDealerTransactions();
  //       })
  //       .catch((err) => console.error(err));

  //   },
  //   get30days() {
  //     transactionService
  //       .getTransactions30Days()
  //       .then((response) => {
  //         this.transactions = response.data;
  //         this.getArtistTransactions();
  //         this.getDealerTransactions();
  //       })
  //       .catch((err) => console.error(err));
  //   },
  //   get7days() {
  //     transactionService
  //       .getTransactions7Days()
  //       .then((response) => {
  //         this.transactions = response.data;
  //         this.getArtistTransactions();
  //         this.getDealerTransactions();
  //       })
  //       .catch((err) => console.error(err));
  //   }
  //}
};
</script>


<style scoped>
#buttonContainer {
  display: flex;
  justify-content: center;
}

button {
  border: none;
  padding: 5px;
  width: 100px;
  background-color: #ab3f29;
  border-radius: 5px;
  box-shadow: 1.5px 1.5px 1.5px 1.5px #310f08b7;
  cursor: pointer;
  margin: 10px;
  color: #fff;
}
</style>
