import firebase from 'firebase/app';
import 'firebase/firestore';


const firebaseConfig = {
    apiKey: "AIzaSyB2OuALYE_tTi9yzDIQnPtPIexwpQihMTM",
    authDomain: "artsales-cd212.firebaseapp.com",
    projectId: "artsales-cd212",
    storageBucket: "artsales-cd212.appspot.com",
    messagingSenderId: "1030614952064",
    appId: "1:1030614952064:web:16d788bae0087fc2e31e65"
  };

 
 firebase.initializeApp(firebaseConfig);

 const db = firebase.firestore();

 const storageRef = firebase.storage().ref();

 const settings = {
  timestampsInSnapshots: true
};
db.settings(settings);

 export default {
   db,
   storageRef
 };