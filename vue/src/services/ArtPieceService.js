import axios from 'axios';

export default {

  getAllListings() {
    return axios.get('/home')
  },

  createListing(artPiece) {
    return axios.post('/createListing', artPiece)
  },

  getListingByArtId(artId){
    return axios.get(`/artDetails/${artId}`)
  },

  editListing(artPiece) {
    return axios.put('/edit', artPiece)
  },

  deleteListing(artID) {
    return axios.delete(`/delete/${artID}`)
  },




}