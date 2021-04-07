import axios from 'axios';

export default {

    postTransaction() {
      return axios.post('/orderDetails')
    }
}