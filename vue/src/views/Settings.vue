<template>
  <div>
    <h1>Settings</h1>

    <register-others />

    <fee-settings/>

    <div>
      <h2 class="override">Override Default Fee</h2>
      <div class="art-container">
        <settings-display-art
          v-for="artPiece in $store.state.artPieceData"
          v-bind:key="artPiece.artID"
          v-bind:artPiece="artPiece"
        />
      </div>
    </div>
  </div>
</template>

<script>
import transactionService from "@/services/TransactionService.js";
import SettingsDisplayArt from "../components/SettingsDisplayArt.vue";
import artPieceService from "@/services/ArtPieceService.js";
import RegisterOthers from "../components/RegisterOthers.vue";
import FeeSettings from '../components/FeeSettings.vue';

export default {
  name: "settings",
  components: {
    SettingsDisplayArt,
    RegisterOthers,
    FeeSettings,
  },

  data() {
    return {
      currentDefaultFees: {},
    };
  },
  created() {
    transactionService
      .getCurrentDefaultFees()
      .then((response) => {
        this.currentDefaultFees = response.data;
      })
      .catch((error) => {
        const response = error.response;
        console.log(response);
      });

    artPieceService
      .getAllListings()
      .then((response) => {
        this.$store.commit("SET_ART_DATA", response.data);
      })
      .catch((err) => console.error(err));
  },

  methods: {
    setFee() {
      transactionService
        .setDefaultFee(this.currentDefaultFees)
        .then((response) => {
          if (response.status == 200) {
            alert("Fees Updated");
          }
        })
        .catch((error) => {
          const response = error.response;
          console.log(response);
        });
    },
  },
};
</script>

<style scoped>


.override {
  color: #ffffff;
  font-family: "Quicksand", sans-serif;
  font-size: 24px;
  font-weight: 700;
  line-height: 25px;
  margin-top: 1em;
  margin-left: 0em;
  margin-bottom: 1em;
  text-shadow: 2px 2px #2b2929fb;

  text-align: center;
}
</style>