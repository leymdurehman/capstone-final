<template>
    <div>
        <div id="reportStatus">
            <h2>{{$store.state.user}}</h2>
        <h1>Status Report</h1>
        <h2>Total number of art in Gallery: {{totalNumberOfArt}}</h2>
        <h2>Total number of sold art: {{totalNumberSold}}</h2>
        <h2>Available art for sale: {{totalAvailable}}</h2>
        <h2>Unavailable art for sale: {{totalUnavailable}}</h2>
        </div>

        <div class="pietest">
        <PieChart/>
        </div>

    </div>
    
</template>

<script>
// import firebase from "firebase";
import artPieceService from "@/services/ArtPieceService.js";
import PieChart from "../components/PieChart.vue"
// import transactionService from "@/services/TransactionService.js";
export default {
  name: "ReportStatus",
  components: {
      PieChart
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
  }
};
</script>

<style scoped>
#reportStatus {
  background-color: #ab3f294b;
  border-radius: 20px;
  top: 80px;
  width: fit-content;
  min-width: 300px;
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