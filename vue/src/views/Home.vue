<template>
  <div class="art-container">
  
    <div>
      <h1> Featured Art </h1>
    </div>
      <div class="card" v-for="artpiece in artPieceData" v-bind:key="artpiece.title">
        <div class="art-title" >
          <h2>{{ artpiece.title }}</h2>
        </div>
        <div class="art-artist">
         <h3> {{artpiece.artist}}</h3>
        </div>
        <div class="art-date">
         <h3> {{artpiece.dateCreated}}</h3>
        </div>
  
      </div>

  </div>
</template>

<script>


export default {
   data(){

     return{

       // 
       artPieceData: [
         {
            dealer: '',
            imgFile: '',
            artist: 'Picasso',
            title: 'Untitled',
            price: 0,
            dateCreated: '01-01-1967'
        },
        {
            dealer: '',
            imgFile: '',
            artist: 'Picasso',
            title: 'Untitled2',
            price: 0,
            dateCreated: '02-02-1967'
        },
        {
            dealer: '',
            imgFile: '',
            artist: 'Picasso',
            title: 'Untitled3',
            price: 0,
            dateCreated: '03-03-1967'
        }



       ]
     }
   }




   }
</script>

<style>
</style>