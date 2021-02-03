<template>
  <div class="newGame">
    <h2>Create New Game!</h2>
    <form class="newGameForm" v-on:submit.prevent="createGame">
      <span>Game Title:<input type="text" placeholder="Game Name" v-model="game.gameName" /></span>
      <span>Start Date:<input type="date" v-model="game.startDate" /></span>
      <span>End Date:<input type="date" v-model="game.endDate" /></span>
      <button>Submit</button>
      {{ this.game.startDate }}
      {{ this.game.endDate }}
    </form>
  </div>
</template>

<script>
import gameService from "../services/GameService.js";
export default {
  data() {
    return {
      game: {
        gameName: "",
        status: "",
        startDate: "",
        endDate: "",
        organizerId: this.$store.state.user.id,
      },
    };
  },
  methods: {
    createGame() {
      this.setStatus();
      gameService
        .create(this.game)
        .then((response) => {
          if (response.status == 201) {
            this.game = {
              gameName: "",
              status: "",
              startDate: "",
              endDate: "",
              organizerId: this.$store.state.user.id,
            };
            this.$router.push("/");
          }
        })
        .catch((error) => {
          if (error.response) {
            this.$router.push("/");
            window.alert(`${error.response.statusText}`);
          } else if (error.request) {
            window.alert("server can not be reached");
          }
        });
    },
    setStatus() {
      let currentDate = new Date();
      let startDate = new Date(this.game.startDate);
      let endDate = new Date(this.game.endDate);

      if (startDate <= currentDate && endDate > currentDate) {
        return (this.game.status = "Active");
      } else {
        return (this.game.status = "Inactive");
      }
    },
  },
};
</script>

<style>
.newGame {
  text-align: center;
}

.newGameForm {
  display: flex;
  flex-direction: column;
  align-items: center;
  
}

input {
padding: 2px;
margin: 1rem;
}
</style>