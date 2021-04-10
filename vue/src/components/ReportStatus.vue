<template>
  <h1>Report</h1>
</template>

<script>
// import firebase from "firebase";
// import artPieceService from "@/services/ArtPieceService.js";
// import transactionService from "@/services/TransactionService.js";
export default {
    name: 'ReportStatus',
    data(){
        return{
            totalNumberOfArtPieces: 0,
            totalNumberSold: 0,
            totalNumberAvailable: 0,
            totalUnavailable: 0,
            grossRevenue: 0,
            netProfits: 0,
            totalCommissionsPaid: 0,
            totalPaidToArtists: 0,
            artPieces: null,
            transactions: null,
        }
    },



}
</script>

<style>

</style>