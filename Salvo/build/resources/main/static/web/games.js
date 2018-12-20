var gamesList = new Vue({
    el: "#gameList",
    data: {
        gamesUrl: "http://localhost:8080/api/games",
        gameData: [],

    },
    created() {this.fetchGameData(this.gamesUrl)},
    method: {
        fetchGameData(url) {
            fetch(url, {
                    method: "GET"
                })
                .then(function (response) {
                    return response.json()
                })
                .then(function (data) {
                    this.gameData = data,
                    console.log("THIS IS WORKING"),
                })
        }
    }
})
