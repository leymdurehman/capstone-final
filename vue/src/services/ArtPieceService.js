import axios from 'axios';

export default {

  getAllListings() {
    return axios.get('/home')
  },

  createListing(artPiece) {
    return axios.post('/createListing', artPiece)
  }
}