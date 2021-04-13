<template>
  <div id="listing">
    <div class="success">
      <h2 v-if="statusMessage">{{ statusMessage }}</h2>
    </div>
    <form>
      <div>
        <div class="label">
          <label id="artist-name" for="artistName">Artist Name: </label>
        </div>
        <input
          type="text"
          name="artistName"
          id="artistName"
          v-model.trim="artPiece.artist"
        />
      </div>
      <div>
        <div class="label">
          <label for="title">Title: </label>
        </div>
        <input
          type="text"
          name="title"
          id="title"
          v-model.trim="artPiece.title"
        />
      </div>
      <div>
        <div class="label">
          <label for="dateCreated">Date Created: </label>
        </div>
        <input
          type="date"
          name="dateCreated"
          id="dateCreated"
          v-model.trim="artPiece.dateCreated"
        />
      </div>
      <div>
        <div class="label">
          <label for="price">Price:</label>
        </div>
        <input
          type="number"
          step="0.01"
          min="0.01"
          name="price"
          id="price"
          v-model.trim="artPiece.price"
        />
      </div>
      <div>
        <div class="label">
          <label>Dealer Name: </label>
        </div>
        <input type="text" name="dealerName" v-model.trim="artPiece.dealer" />
      </div>
      <div>
        <div class="label">
          <label for="image">Image: </label>
        </div>
        <input type="file" id="file" @change="onFileChanged" accept="image/*" />
      </div>
    </form>
    <div>Or</div>
    <div id="file-drag-drop">
      <form id="dragDrop" ref="fileform" @change="onFileChanged">
        <span class="drop-files">Drop Image Here</span>
      </form>
    </div>
    <p v-if="files[0]">{{ files[0].name }}</p>
    <p v-if="selectedFile">{{ selectedFile.name }}</p>
    <div id="preview">
      <img v-if="url" :src="url" />
    </div>
    <div class="remove-container">
      <button
        class="remove"
        v-if="files[0] || selectedFile"
        v-on:click="removeFile(files[0])"
      >
        Reset Image
      </button>
    </div>
    <div class="label">
      <input
        type="submit"
        v-bind:disabled="!isFormValid"
        v-on:click="createListingForArtPiece()"
      />
    </div>
    <div>
      <button class="cancel" @click="returnHome()">Return Home</button>
    </div>
  </div>
</template>
<script>
import artPieceService from "@/services/ArtPieceService.js";
import firebase from "firebase";
export default {
  name: "createListing",
  data() {
    return {
      selectedFile: null,
      artPiece: {},
      listingError: false,
      listingErrorMessage: "",
      dragAndDropCapable: false,
      files: [],
      url: null,
      uploadPercentage: 0,
      statusMessage: "",
    };
  },
  mounted() {
    this.dragAndDropCapable = this.determineDragAndDropCapable();
    if (this.dragAndDropCapable) {
      [
        "drag",
        "dragstart",
        "dragend",
        "dragover",
        "dragenter",
        "dragleave",
        "drop",
      ].forEach(
        function (evt) {
          this.$refs.fileform.addEventListener(
            evt,
            function (e) {
              e.preventDefault();
              e.stopPropagation();
            }.bind(this),
            false
          );
        }.bind(this)
      );
      this.$refs.fileform.addEventListener(
        "drop",
        function (e) {
          if (/\.(jpe?g|png|gif)$/i.test(e.dataTransfer.files[0].name)) {
            this.removeFile();
            this.files.push(e.dataTransfer.files[0]);
            this.showPreview(this.files[0]);
            this.selectedFile = null;
            this.resetInputText();
          }
        }.bind(this)
      );
    }
  },
  computed: {
    isFormValid() {
      return (
        (this.files[0] || this.selectedFile) &&
        this.artPiece.artist &&
        this.artPiece.title &&
        this.artPiece.price &&
        this.artPiece.dateCreated
      );
    },
  },
  methods: {
    resetInputText() {
      document.getElementById("file").value = "";
    },
    returnHome() {
      this.$router.push({ path: "/" });
    },
    clearForm() {
      this.removeFile();
      this.selectedFile = null;
      this.artPiece = {};
      this.listingError = false;
      this.listingErrorMessage = "";
      this.dragAndDropCapable = false;
      this.files = [];
      this.url = null;
      this.uploadPercentage = 0;
    },
    removeFile(key) {
      this.files.splice(key, 1);
      this.url = null;
      this.selectedFile = null;
      this.showPreview(this.url);
      this.resetInputText();
    },
    showPreview(file) {
      if (file) {
        this.url = URL.createObjectURL(file);
      } else {
        return;
      }
    },
    determineDragAndDropCapable() {
      var divDrop = document.createElement("div");
      return (
        ("draggable" in divDrop ||
          ("ondragstart" in divDrop && "ondrop" in divDrop)) &&
        "FormData" in window &&
        "FileReader" in window
      );
    },
    onFileChanged(event) {
      this.selectedFile = event.target.files[0];
      this.artPiece.imgFileName = this.selectedFile.name;
      this.showPreview(this.selectedFile);
    },
    onUpload() {
      const storageRef = firebase.storage().ref();
      if (!this.selectedFile) {
        storageRef.child(this.files[0].name).put(this.files[0]);
        this.artPiece.imgFileName = this.files[0].name;
      } else {
        storageRef.child(this.selectedFile.name).put(this.selectedFile);
      }
    },
    createListingForArtPiece() {
      this.onUpload();
      artPieceService
        .createListing(this.artPiece)
        .then((response) => {
          if (response.status == 201) {
            this.clearForm();
            this.statusMessage = "Listing created!";
          }
        })
        .catch((error) => {
          const response = error.response;
          this.listingError = true;
          if (response.status !== 201) {
            this.statusMessage = "There were problems creating this listing.";
          }
        });
    },
  },
};
</script>
<style scoped>
#listing {
  background-color: #ab3f294b;
  border-radius: 20px;
  color: #fff;
  font-family: "Quicksand", sans-serif;
  width: 40%;
  min-width: 300px;
  padding: 0px 20px 10px 20px;
  margin: auto;
  display: flex;
  text-align: center;
  flex-direction: column;
  align-items: center;
  margin-bottom: 10px;
}
#artist-name {
  margin-top: 0;
  padding-top: 0;
  line-height: 0;
}
img {
  width: 30%;
  margin: auto;
  display: block;
  margin-bottom: 10px;
}
p {
  line-height: 2rem;
}
#file {
  /* margin-left: 20px; */
  margin-top: 5px;
  margin-bottom: 10px;
  background-color: #ab3f29;
  color: #fff;
  border-radius: 7px;
  border: none;
  box-shadow: 1.5px 1.5px 1.5px 1.5px #310f08b7;
}
.remove {
  margin: 10px;
  background-color: #ab3f29;
  color: #fff;
  border-radius: 2px;
  border: none;
  box-shadow: 1.5px 1.5px 1.5px 1.5px #310f08b7;
  padding: 5px 15px;
  cursor: pointer;
}
.label {
  padding-top: 20px;
}
#dragDrop {
  display: block;
  height: 80px;
  width: 90%;
  background: #ccc;
  margin: auto;
  margin-top: 5px;
  text-align: center;
  border-radius: 4px;
}
#preview {
  display: flex;
  justify-content: center;
  align-items: center;
}
#preview img {
  width: 50%;
  max-height: 100%;
}
.cancel {
  margin: 10px;
  background-color: #ab3f29;
  color: #fff;
  border-radius: 2px;
  border: none;
  box-shadow: 1.5px 1.5px 1.5px 1.5px #310f08b7;
  padding: 5px 15px;
  cursor: pointer;
}
input[type="submit"] {
  background-color: #ab3f29;
  color: #fff;
  border-radius: 2px;
  border: none;
  box-shadow: 1.5px 1.5px 1.5px 1.5px #310f08b7;
  padding: 5px 15px;
  cursor: pointer;
}
input[type="submit"]:disabled {
  background-color: #ab3f293f;
  color: #f4f4f43a;
  border-radius: 2px;
  border: none;
  box-shadow: 1.5px 1.5px 1.5px 1.5px #310f083a;
  padding: 5px 15px;
  cursor: default;
}
input[type="file"] {
  color: #fff;
  width: 250px;
  border-radius: 2px;
}
#dragDrop {
  display: flex;
  justify-content: center;
  background-color: #ab3f29;
  color: #fff;
  border-radius: 7px;
  border: none;
  border: inset;
  border-color: #310f08b7;
  /* box-shadow: 1.5px 1.5px 1.5px 1.5px #310f08b7; */
}
#dragDrop:hover {
  display: flex;
  justify-content: center;
  background-color: #702a1bc9;
  color: #fff;
  border-radius: 7px;
  border: none;
  border: inset;
  border-color: #310f08b7;
}
.drop-files {
  margin-top: 15px;
}

h2 {
  padding-top: 1.5rem;
}
</style>