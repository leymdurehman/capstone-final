import axios from 'axios';

export default {

    postTransaction(transactions) {
      return axios.post('/orders', transactions)
    },

    setDefaultFee(fee){
      return axios.put('/fees', fee)
    },

    getCurrentDefaultFees(){
      return axios.get('/fees')
    }
}