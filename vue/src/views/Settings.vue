<template>
  <div>
    <h1>Settings</h1>
    <div id="changeSettings">
      <form>
        <div>
          <label>Set Default Fee Percent (%): </label>
        </div>
        <input
          type="number"
          step="0.01"
          min="0.01"
          max="99.99"
          v-model.trim="currentDefaultFees.fee"
        />

        <div>
          <label>Set Default Commission Percent (%): </label>
        </div>
        <input
          type="number"
          step="0.01"
          min="0.01"
          max="99.99"
          v-model.trim="currentDefaultFees.commission"
        />

        <div>
          <button id="setChanges" @click.prevent="setFee()">
            Set Default Fees
          </button>
        </div>
      </form>
    </div>

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

export default {
  name: 'settings',
  components: {
    SettingsDisplayArt,
  },


  data(){
    return{
      currentDefaultFees: {},
    }
  },
  created(){

    transactionService.getCurrentDefaultFees().then((response) => {
        this.currentDefaultFees = response.data;
    }).catch((error) => {
      const response = error.response
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
    
    setFee(){

      transactionService.setDefaultFee(this.currentDefaultFees).then((response) => {
        if(response.status == 200){
           alert("Fees Updated");
        }
       
    }).catch((error) => {
      const response = error.response
      console.log(response);
      });
    }
  }
}
</script>

<style scoped>
#changeSettings {
  background-color: #ab3f294b;
  border-radius: 20px;
  color: #f4f4f4eb;
  font-family: "Quicksand", sans-serif;
  width: 40%;
  min-width: 300px;
  padding: 20px;
  margin: auto;
  display: flex;
  text-align: center;
  flex-direction: column;
  align-items: center;
}

#setChanges {
  margin-top: 10px;
  padding: 5px 15px;
  background-color: #ab3f29;
  color: #f4f4f4eb;
  border-radius: 5px;
  border: none;
  box-shadow: 1.5px 1.5px 1.5px 1.5px #310f08b7;
  cursor: pointer;
  width: 50%;
}

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