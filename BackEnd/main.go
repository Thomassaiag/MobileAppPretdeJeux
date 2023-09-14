package main

import (
	"database/sql"
	"io"
	"net/http"

	_ "github.com/go-sql-driver/mysql"
	"github.com/gorilla/mux"

	"encoding/json"
	_ "fmt"
	_ "io"
)

 


type Game struct {
	
	GameID int `json:"id"`
	GameName string `json:"gameName"`
	GameDescription string `json:"gameDescription"`
	MinNumberOfPlayers int `json:"minNumberOfPlayers"`
	MaxNumberOfPlayers int `json:"maxNumberOfPlayers"`
	MinimumAge int `json:"minimumAge"`
	
}

// var db *sql.DB
// var err error

func getDB()(*sql.DB, error){
	return sql.Open("mysql","root:root@tcp(127.0.0.1:3306)/MyMobileApp")
}

func main(){

	db, err:=getDB()
	if err!=nil{
		panic(err.Error())
	}

	defer db.Close()

	router := mux.NewRouter()
	
	
	router.HandleFunc("/allgames", getAllGames).Methods("GET")
	router.HandleFunc("/allgames", createGame).Methods("POST")
	router.HandleFunc("/allgames/{id}", getGame).Methods("GET")
	// router.HandleFunc("/allgames/{id}", updateGame).Methods("PUT")
	
	http.ListenAndServe(":8000", router)
}


func getAllGames(w http.ResponseWriter, r *http.Request){
	w.Header().Set("Content-Type", "application/json")
	var allGames []Game

	db, err:=getDB()
	if err!=nil{
		panic(err.Error())
	}

	result, err:=db.Query("SELECT * FROM tb_allgames")
	if err !=nil{
		panic(err.Error())
	}

	defer result.Close()

	for result.Next(){
		var game Game
		err:=result.Scan(&game.GameID, &game.GameName, &game.GameDescription, &game.MinNumberOfPlayers, &game.MaxNumberOfPlayers, &game.MinimumAge)
		if err!=nil{
			panic(err.Error())
		}
		allGames=append(allGames, game)
	}
	json.NewEncoder(w).Encode(allGames)
	
}


func createGame2(w http.ResponseWriter, r *http.Request){
	w.Header().Set("Content-Type", "application/json")

	db, err :=getDB()
	if err != nil {
		panic(err.Error())
	}

	result, err :=  db.Exec("INSERT INTO tb_allgames(GameName, GameDescription, MinNumberOfPlayers, MaxNumberOfPlayers, MinimumAge) VALUES('Dice Forge', 'Super Jeu', 2, 4, 10)")

}

func createGame(w http.ResponseWriter, r *http.Request){
	w.Header().Set("Content-Type", "application/json")

	// game:= Game{0, "Dice Forge", "Super Super Jeu", 2, 4, 10}

  
	db, err :=getDB()
	if err != nil {
		panic(err.Error())
	}

	result, err :=  db.Exec("INSERT INTO tb_allgames(GameName, GameDescription, MinNumberOfPlayers, MaxNumberOfPlayers, MinimumAge) VALUES('Dice Forge', 'Super Jeu', 2, 4, 10)")
// _, err:=db.Exec("INSERT INTO tb_allgames(GameName, GameDescription, MinNumberOfPlayers, MaxNumberOfPlayers, MinimumAge) VALUES(?, ?, ?, ?, ?)", game.GameName, game.GameDescription, game.MinNumberOfPlayers, game.MaxNumberOfPlayers, game.MinimumAge)
	if err!=nil{
		panic(err.Error())
	}
// query:=`INSERT INTO tb_allgames() VALUES INSERT INTO tb_allgames(GameName, GameDescription, MinNumberOfPlayers, MaxNumberOfPlayers, MinimumAge) 
// VALUES($1, $2, $3, $4, $5) RETURNING ID`

	
	

	// result:= db.QueryRow(query, game.GameName, game.GameDescription, game.MinNumberOfPlayers, game.MaxNumberOfPlayers, game.MinimumAge).Scan(&pk)
	// if result!=nil{
	// 	panic(err.Error())
	// }

}

	
func getGame(w http.ResponseWriter, r *http.Request){
	w.Header().Set("Content-Type", "application/json")

	db, err:=getDB()
	if err!=nil{
		panic(err.Error())
	}

	params:=mux.Vars(r)
	result, err:= db.Query("SELECT * FROM tb_allgames WHERE GameID= ?",params["id"])
	if err!=nil{
		panic(err.Error())
	}

	defer result.Close()

	var game Game
	for result.Next(){
		err:=result.Scan(&game.GameID, &game.GameName, &game.GameDescription, &game.MinNumberOfPlayers, &game.MaxNumberOfPlayers, &game.MinimumAge)
		if err!=nil{
			panic(err.Error())
		}
		json.NewEncoder(w).Encode(game)
	}
}

// func updateGame(w http.ResponseWriter, r *http.Request){
// 	db, err:=sql.Open("mysql",
// 	"root:root@tcp(127.0.0.1:3306)/MyMobileApp")

// 	w.Header().Set("Content-Type", "application/json")
// 	params:=mux.Var(r)

// 	stmt, err :=db.Prepare("Update tb_allGame SET GameName = ?, GameDescription = ?, GameMinNumberOfPlayers = ?, GameMaxNumberOfPlayers= ?, MinimumAge = ? WHERE GameID=?")

// }
