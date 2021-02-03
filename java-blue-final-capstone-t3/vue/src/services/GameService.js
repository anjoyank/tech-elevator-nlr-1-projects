import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:8080"
});

export default {
    create(newGame) {
        return http.post("/games", newGame);
    },
    getAll() {
        return http.get("/games");
    },
    getPlayers(id) {
        return http.get(`/games/${id}`);
    }

}