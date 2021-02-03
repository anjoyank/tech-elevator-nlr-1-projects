<template>
  <div class="home">
    <h1>Home</h1>
    <p>Hello {{CurrentUser.username}}, Welcome Home</p>
    <router-link class="homeLinks" v-bind:to="{name : 'newGame'}">Create Game</router-link>
    <router-link class="homeLinks" v-bind:to="{name: 'games'}">View Games</router-link>
  </div>
</template>

<script>
export default {
  name: "home",
  data() {
    return {
      CurrentUser: this.$store.state.user
    }
  }
};
</script>

<style scoped>
  .home {
    text-align: center;
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  .homeLinks {
    text-decoration: none;
    color: black;
    background-color: hotpink;
    border-radius: 4px;
    padding: 2rem;
    margin: 2rem;
  }

</style>
