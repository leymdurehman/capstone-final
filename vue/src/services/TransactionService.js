import axios from 'axios';

export default {

    postTransaction(transactions) {
      return axios.post('/orders', transactions)
    }
}