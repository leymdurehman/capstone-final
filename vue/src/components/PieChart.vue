<template>
  <div>
   
  </div>
</template>

<script>
import artPieceService from "@/services/ArtPieceService.js";
export default {
    name: 'PieChart',
    
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
}
</script>


<style scoped>
.pie-chart{
    width:300px;
    height: 300px;
    border-radius: 50%;
    border: 2px solid #fefefe;
    /* background: conic-gradient(
        #fefefe 0 30%,
        #e60505 0 70%,
        #46d31b 0 100%
    ); */
}

</style>