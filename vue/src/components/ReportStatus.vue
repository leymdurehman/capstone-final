<template>
  <div id="report-status">
    <h2>Status Report</h2>
    <h3>Total number of art in Gallery: {{ totalNumberOfArt }}</h3>
    <h3>Total number of sold art: {{ totalNumberSold }}</h3>
    <h3>Available art for sale: {{ totalAvailable }}</h3>
    <h3>Unavailable art for sale: {{ totalUnavailable }}</h3>
  </div>
</template>

<script>
// import firebase from "firebase";
import artPieceService from "@/services/ArtPieceService.js";
import PieChart from "../components/PieChart.vue";
// import transactionService from "@/services/TransactionService.js";
export default {
  name: "ReportStatus",
  components: {
    PieChart,
  },
  data() {
    return {
      grossRevenue: 0,
      netProfits: 0,
      totalCommissionsPaid: 0,
      totalPaidToArtists: 0,
      artPieces: [],
      transactions: null,
    };
  },

  computed: {
    totalNumberOfArt() {
      return this.artPieces.length;
    },
    totalNumberSold() {
      const soldArt = this.artPieces.filter((x) => {
        return x.sold;
      });
      return soldArt.length;
    },
    totalAvailable() {
      const availableArt = this.artPieces.filter((x) => {
        return x.available;
      });
      return availableArt.length;
    },
    totalUnavailable() {
      const unavailableArt = this.artPieces.filter((x) => {
        return !x.available;
      });
      return unavailableArt.length;
    },
  },

  created() {
    artPieceService
      .getAllListings()
      .then((response) => {
        this.artPieces = response.data;
        this.$store.commit("SET_ART_DATA", response.data);
      })
      .catch((err) => console.error(err));
  },
};
</script>

<style scoped>
#report-status {
  background-color: #ab3f294b;
  border-radius: 20px;
  width: fit-content;
  padding: 40px;
  margin: auto;
  margin-bottom: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
  color: #f4f4f4eb;
  font-family: "Quicksand", sans-serif;
}
</style>