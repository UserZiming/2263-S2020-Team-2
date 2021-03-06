package isu.engine.manager;

import isu.engine.Player;
import isu.util.CircularlyLinkedList;

import java.util.List;

public class TurnManager {
    private CircularlyLinkedList<Player> playerOrder;
    private Player currentPlayer;

    public TurnManager(List<Player> players){
        this(new CircularlyLinkedList<>(players));
    }


    public TurnManager(CircularlyLinkedList<Player> playerOrder) {
        this.playerOrder = playerOrder;
        this.currentPlayer = playerOrder.getFirst();
    }

    public TurnManager(CircularlyLinkedList<Player> playerOrder, Player currentPlayer) {
        this.playerOrder = playerOrder;
        setCurrentPlayer(currentPlayer);
        this.currentPlayer = currentPlayer;
    }

    /**
     * Sets the current player to the next player and updates the UI
     * @return The current Player
     */
    public Player nextTurn(){
        currentPlayer = playerOrder.rotate();

        return currentPlayer;
    }

    /**
     * @return The circularly linked list of all the players
     */
    public CircularlyLinkedList<Player> getPlayerOrder(){
        return playerOrder;
    }

    /**
     * @return The current player
     */
    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    /**
     * Sets the current player and makes them the head of the circularly linked list
     * @param p
     */
    public void setCurrentPlayer(Player p){
        while (playerOrder.getFirst() != p){
            currentPlayer = playerOrder.rotate();
        }
    }
}
