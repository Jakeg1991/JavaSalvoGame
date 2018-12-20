var gamesList = new Vue({
    el: "#gameList",
    data: {
//        gamesUrl: "http://localhost:8080/api/games",
        gameData: [],
        isLoading: true,

    },
    created() {
        this.fetchGameData("http://localhost:8080/api/games")
    },
    methods: {
        fetchGameData(url) {
            fetch(url, {
                    method: "GET",
                })
                .then(response => response.json())
                .then(data => {
                    this.gameData = data;
                    console.log(this.gameData)
                    this.isLoading=false;
                });
        }
    }
})
