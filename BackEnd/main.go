package main

import (
	"database/sql"
	"net/http"

	_ "github.com/go-sql-driver/mysql"
	"github.com/gorilla/mux"

	"encoding/json"
	"fmt"
	"io"
)

type Game struct {
	GameID             int    `json:"id"`
	GameName           string `json:"gameName"`
	GameDescription    string `json:"gameDescription"`
	MinNumberOfPlayers int    `json:"minNumberOfPlayers"`
	MaxNumberOfPlayers int    `json:"maxNumberOfPlayers"`
	MinimumAge         int    `json:"minimumAge"`
}

// var db *sql.DB
// var err error

func getDB() (*sql.DB, error) {
	return sql.Open("mysql", "root:root@tcp(127.0.0.1:3306)/MyMobileApp")
}

func main() {

	db, err := getDB()
	if err != nil {
		panic(err.Error())
	}

	defer db.Close()

	router := mux.NewRouter()

	router.HandleFunc("/allgames", getAllGames).Methods("GET")
	router.HandleFunc("/allgames", createGame).Methods("POST")
	router.HandleFunc("/allgames/{id}", getGame).Methods("GET")
	router.HandleFunc("/allgames/{id}", deleteGame).Methods("DELETE")
	router.HandleFunc("/allgames/{id}", updateGame).Methods("PUT")

	http.ListenAndServe(":8000", router)
}

func getAllGames(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")
	var allGames []Game

	db, err := getDB()
	if err != nil {
		panic(err.Error())
	}

	result, err := db.Query("SELECT * FROM tb_allgames")
	if err != nil {
		panic(err.Error())
	}

	defer result.Close()

	for result.Next() {
		var game Game
		err := result.Scan(&game.GameID, &game.GameName, &game.GameDescription, &game.MinNumberOfPlayers, &game.MaxNumberOfPlayers, &game.MinimumAge)
		if err != nil {
			panic(err.Error())
		}
		allGames = append(allGames, game)
	}
	json.NewEncoder(w).Encode(allGames)

}

func createGame(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")

	// game:= Game{0, "Dice Forge", "Super Super Jeu", 2, 4, 10}
	db, err := getDB()
	if err != nil {
		panic(err.Error())
	}

	body, err := io.ReadAll(r.Body)
	if err != nil {
		panic(err.Error())
	}

	stmt, err := db.Prepare("INSERT INTO tb_allgames(GameName, GameDescription, MinNumberOfPlayers, MaxNumberOfPlayers, MinimumAge) VALUES(?, ?, ?, ?, ?)")
	if err != nil {
		panic(err.Error())
	}
	keyValMap := make(map[string]string)
	json.Unmarshal(body, &keyValMap)
	GameName := keyValMap["GameName"]
	GameDescription := keyValMap["GameDescription"]
	GameMinNumberOfPlayers := keyValMap["GameMinNumberOfPlayers"]
	GameMaxNumberOfPlayers := keyValMap["GameMaxNumberOfPlayers"]
	GameMinimumAge := keyValMap["GameMinimumAge"]

	_, err = stmt.Exec(GameName, GameDescription, GameMinNumberOfPlayers, GameMaxNumberOfPlayers, GameMinimumAge)
	if err != nil {
		panic(err.Error())
	}

	fmt.Println("New post was created")
}

func getGame(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")

	db, err := getDB()
	if err != nil {
		panic(err.Error())
	}

	params := mux.Vars(r)
	result, err := db.Query("SELECT * FROM tb_allgames WHERE GameID= ?", params["id"])
	if err != nil {
		panic(err.Error())
	}

	defer result.Close()

	var game Game
	for result.Next() {
		err := result.Scan(&game.GameID, &game.GameName, &game.GameDescription, &game.MinNumberOfPlayers, &game.MaxNumberOfPlayers, &game.MinimumAge)
		if err != nil {
			panic(err.Error())
		}
		json.NewEncoder(w).Encode(game)
	}
}

func deleteGame(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")
	db, err := getDB()
	if err != nil {
		panic(err.Error())
	}

	var params = mux.Vars(r)
	_, err = db.Exec("DELETE FROM tb_allgames where GameID=?", params["id"])
	if err != nil {
		panic(err.Error())
	}
}

func updateGame(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")

	db, err := getDB()
	if err != nil {
		panic(err.Error())
	}
	body, err := io.ReadAll(r.Body)
	params := mux.Vars(r)

	stmt, err := db.Prepare("Update tb_allGame SET GameName = ?, GameDescription = ?, GameMinNumberOfPlayers = ?, GameMaxNumberOfPlayers= ?, MinimumAge = ? WHERE GameID=?")

	keyValMap := make(map[string]string)

	json.Unmarshal(body, &keyValMap)

	GameName := keyValMap["GameName"]
	GameDescription := keyValMap["GameDescription"]
	GameMinNumberOfPlayers := keyValMap["GameMinNumberOfPlayers"]
	GameMaxNumberOfPlayers := keyValMap["GameMaxNumberOfPlayers"]
	GameMinimumAge := keyValMap["GameMinimumAge"]

	_, err = stmt.Exec(GameName, GameDescription, GameMinNumberOfPlayers, GameMaxNumberOfPlayers, GameMinimumAge, params["id"])
	if err != nil {
		panic(err.Error())
	}

}
