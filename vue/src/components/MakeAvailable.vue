<template>
  <div>
    <div>
        <h1>Listing Availability</h1>
    </div>
    <div>
        <div class='drop-zone'
        @drop='onDrop($event, true)'
        @dragover.prevent
        @dragenter.prevent 
        >
            <div v-for='art in availableArt' 
            :key='art.artID' 
            class='drag-el'
            draggable
            @dragstart='startDrag($event, art)'
            >
                {{ art.title }}
            </div>
        </div>
            <div class='drop-zone'
            @drop='onDrop($event, false)'
            @dragover.prevent
            @dragenter.prevent 
            >
                <div v-for='art in unavailableArt' 
                :key='art.artID' 
                class='drag-el'
                draggable
                @dragstart='startDrag($event, art)'
                >
                    {{ art.title }}
                </div>
            </div>

    </div>
  </div>
</template>


<script>
import artPieceService from "@/services/ArtPieceService.js";
export default {
    name: 'MakeAvailable',

    data(){
        return{
            unsoldArt: []
        
        }
    },

    created(){
        artPieceService
            .getAllListings()
                .then((response) => {
                    this.$store.commit("SET_ART_DATA", response.data);
                    this.assignAvailability();
            })
            .catch((err) => console.error(err));
    },

    computed: {

        availableArt(){
            return this.unsoldArt.filter(art => art.available);
        },

        unavailableArt(){
            return this.unsoldArt.filter(art => !art.available);
        }
    },

    methods: {

        assignAvailability(){
            this.unsoldArt = this.$store.state.artPieceData.filter((art) => {
                return !art.sold;
            });
        },

        startDrag: (evt, art) => {
            evt.dataTransfer.dropEffect = 'move'
            evt.dataTransfer.effectAllowed = 'move'
            evt.dataTransfer.setData('artID', art.artID) 
        },

        onDrop (evt, available) {
            const artID = evt.dataTransfer.getData('artID')
            const artPiece = this.unsoldArt.find(artPiece => artPiece.artID == artID)
            artPiece.available = available
        }

    },




}
</script>

<style scoped>
  .drop-zone {
    background-color: #eee;
    margin-bottom: 10px;
    padding: 10px;
  }

  .drag-el {
    background-color: #fff;
    margin-bottom: 10px;
    padding: 5px;
  }
  
</style>