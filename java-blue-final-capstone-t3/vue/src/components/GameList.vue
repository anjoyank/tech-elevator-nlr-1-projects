<template>
  <div class="mainDiv">
      <h3>List Of Games</h3>
      <!-- <router-link v-bind:to="{name: 'add'}">New Puppy?</router-link> -->

      <div class='gameCard' v-bind:class="{inactive: game.status == 'Inactive'}" v-for='game in gameList' v-bind:key='game.id'>
        {{game.gameName}}
        <br>
        {{game.status}}
        <br>
        {{game.startDate}}
        <br>
        {{game.endDate}}
        <br>
        {{game.organizerId}}
        <ul>
            <li v-for="player in gameList.players" v-bind:key='player.id' />
        </ul>

        <!-- <router-link v-bind:to="{name: 'newGame', params: {id: dog.id}}">See Details</router-link> -->
  </div>
  </div>
</template>

<script>
import GameService from '../services/GameService.js';
export default {
  data() {
    return {
      gameList : [],
      playerList: []
    }
  },
  created() {
    GameService.getAll().then(
      (response) => {
        this.gameList = response.data;
      }
    ),
    GameService.getPlayers(this.id).then(
        (response) => {
            this.playerList = response.data;
        }
    )
  }

}
</script>

<style>

.gameCard {
  margin: 5px;
  padding: 5px;
  border-radius: 3px;
  background-color: cornflowerblue;
}

.inactive {
  background-color: grey;
}

.mainDiv {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
}

</style>