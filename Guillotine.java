/**
 * This is the Guillotine main application class.
 * @author Boris Brondz
 */

package com.example.prelabs;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;
import java.util.Scanner;

public class Guillotine extends Application {

    /**
     * This of type boolean determines the "turn" condition of a player. Initially,
     * turn is set to true, which means it is player 1's turn. When turn is set to false, it is
     * player 2's turn.
     */
    private static boolean turn = true;

    /**
     * This field of type boolean determines if the application is currently running.
     */
    private final boolean isRunning = true;

    /**
     * This field of type int determines the score of player 1.
     */
    private static int player1Score;

    /**
     * This field of type int determines the score of player 2.
     */
    private static int player2Score;

    /**
     * This field of type int determines the number of royal cards.
     */
    private static int royalCardCount;

    /**
     * This field of type int determines the number of church cards.
     */
    private static int churchCardCount;

    /**
     * This field of type int determines the number of civic cards.
     */
    private static int civicCardCount;

    /**
     * This field of type int determines the number of military cards.
     */
    private static int militaryCardCount;
    /**
     * This field of type int determines the number of commoner cards.
     */
    private static int commonerCardCount;

    /**
     * These two fields of type VBox represent the details for each player.
     */
    private VBox player1Details;
    private VBox player2Details;

    /**
     * These fields of type VBox represents the cards and action buttons for player 1 and 2.
     */
    private VBox player1ActionsAndCards;
    private VBox player2ActionsAndCards;

    /**
     * These fields of type Button represent the action buttons for player 1 and 2.
     */
    private Label scorePlayer1;

    /**
     * This field of type Label represents the score for player 2.
     */
    private Label scorePlayer2;

    /**
     * These fields of type Button act as the action buttons for player 1 and 2.
     */
    private Button move4P1;
    private Button move3P1;
    private Button move2P1;
    private Button move1P1;
    private Button moveLeadP1;
    private Button moveLastP1;
    private Button reverseP1;
    private Button reverseFirst5P1;
    private Button skipP1;
    private Button takeFirstP1;

    /**
     * This method returns whose turn it currently is.
     * @return whose turn it is (true for player1, false for player2)
     */
    public static boolean getTurn() {
        return turn;
    }

    /**
     * This method switches the player's turn.
     */
    public static void switchTurn() {
        turn = !turn;
    }

    /**
     * This method returns the current score of player1.
     * @return the score of player 1.
     */
    public static int getPlayer1Score() {
        return player1Score;
    }

    /**
     * This method returns the current score of player2.
     * @return the score of player 2.
     */
    public static int getPlayer2Score() {
        return player2Score;
    }

    /**
     * This method sets the score of player 1.
     * @param player1Score the score of player 1.
     */
    public static void setPlayer1Score(int player1Score) {
        Guillotine.player1Score = player1Score;
    }

    /**
     * This method sets the score of player 2.
     * @param player2Score the score of player 2.
     */
    public static void setPlayer2Score(int player2Score) {
        Guillotine.player2Score = player2Score;
    }

    /**
     * These two fields of type LinkedList are used to store the cards collected by each respective player.
     */
    LinkedList<GuillotineCards> player1Cards;
    LinkedList<GuillotineCards> player2Cards;

    /**
     * This enum is used to define card types of the Guillotine game.
     */
    public enum GuillotineCards {
        Louis_XVI("Royal", 5),
        Marie_Antoinette("Royal", 5),
        Regent("Royal", 4),
        Duke("Royal", 3),
        Baron("Royal", 3),
        Count("Royal", 2),
        Countess("Royal", 2),
        Lord("Royal", 2),
        Lady("Royal", 2),
        Cardinal("Church", 5),
        Archbishop("Church", 4),
        Nun("Church", 3),
        Bishop("Church", 2),
        Priest("Church", 1),
        Heretic("Church", getChurchCount()),
        Governor("Civic", 4),
        Mayor("Civic", 3),
        Councilman("Civic", 3),
        Judge("Civic", 2),
        Tax_Collector("Civic", getCivicCount()),
        Sheriff("Civic", 1),
        Palace_Guard("Military", getMilitaryCount()),
        General("Military", 4),
        Colonel("Military", 3),
        Captain("Military", 2),
        Lieutenant("Military", 1),
        Tragic_Figure("Commoner", getCommonerCount() * -1),
        Heroic_Figure("Commoner", -3),
        Student("Commoner", -1);

        private final String cardType;
        private final int pointValue;

        GuillotineCards(String cardType, int pointValue) {
            this.cardType = cardType;
            this.pointValue = pointValue;
        }

        public String getCardType() {
            return cardType;
        }

        public int getPointValue() {
            return pointValue;
        }

        public int getRoyalCount() {
            return royalCardCount;
        }

        @Override
        public String toString() {
            return(this.name() + "\n" + getCardType() + "\n" + getPointValue());
        }


        /**
         * This method returns the amount of cards of "church" type.
         * @return amount of church cards.
         */
        public static int getChurchCount() {
            return churchCardCount;
        }

        /**
         * This method returns the amount of cards of "civic" type.
         * @return amount of civic cards.
         */
        public static int getCivicCount() {
            return civicCardCount;
        }

        /**
         * This method returns the amount of cards of "military" type.
         * @return amount of military cards.
         */
        public static int getMilitaryCount() {
            return militaryCardCount;
        }

        /**
         * This method returns the amount of cards of "commoner" type.
         * @return amount of commoner cards.
         */
        public static int getCommonerCount() {
            return commonerCardCount;
        }

    }

    /**
     * This is a helper method to call moveBack of the LinkedList class to move a card back in the deck four times.
     * @param list the LinkedList to be used.
     * @param <T> the type for the LinkedList.
     */
    public static <T> void moveBackFour(LinkedList<T> list) {
        list.moveBack(4);
        LinkedList.printList(list);
        switchTurn();
    }

    /**
     * This is a helper method to call moveBack of the LinkedList class to move a card back in the deck 3 times.
     * @param list the LinkedList to be used.
     * @param <T> the type for the LinkedList.
     */
    public static <T> void moveBackThree(LinkedList<T> list) {
        list.moveBack(3);
        switchTurn();
    }

    /**
     * This is a helper method to call moveBack of the LinkedList class to move a card back in the deck 2 times.
     * @param list the LinkedList to be used.
     * @param <T> the type for the LinkedList.
     */
    public static <T> void moveBackTwo(LinkedList<T> list) {
        list.moveBack(2);
        switchTurn();
    }

    /**
     * This is a helper method to call moveBack of the LinkedList class to move a card back in the deck 1 time.
     * @param list the LinkedList to be used.
     * @param <T> the type for the LinkedList.
     */
    public static <T> void moveBackOne(LinkedList<T> list) {
        list.moveBack(1);
        switchTurn();
    }

    /**
     * This is a helper method to call moveFirstToLast of the LinkedList class to move the card in front of the deck to the very end.
     * @param list the LinkedList to be used as a deck
     * @param <T> the generic type for the LinkedList.
     */
    public static <T> void moveLeadToEnd(LinkedList<T> list) {
        list.moveFirstToLast();
        switchTurn();
    }

    /**
     * This is a helper method to call moveLastToFirst the LinkedList class to move the card in the back of the deck to the very front.
     * @param list the LinkedList to be used as a deck
     * @param <T> the generic type for the LinkedList.
     */
    public static <T> void moveEndToLead(LinkedList<T> list) {
        list.moveLastToFirst();
        switchTurn();
    }

    /**
     * This is a helper method to call reverseList of the LinkedList class to reverse the order of the cards in the deck.
     * @param list the LinkedList to be used as a deck
     * @param <T> the generic type for the LinkedList.
     */
    public static <T> void reverseCards(LinkedList<T> list) {
        list.reverseList();
        switchTurn();
    }

    /**
     * This is a helper method to call reverseFirstK of the LinkedList class to reverse the order of the first 5 cards of the deck.
     * @param list the LinkedList to be used as a deck
     * @param <T> the generic type for the LinkedList.
     */
    public static <T> void reverseFirstFive(LinkedList<T> list) {
        list.reverseFirstK(5);
        switchTurn();
    }

    /**
     * This is a helper method for skipTurn to switch the turn from player 1 to player 2 and vice versa.
     */
    public static void skipTurn() {
        switchTurn();
    }


    /**
     * This is a helper method to take the first card of the deck and add it to a player's list of cards.
     * @param list the arrayList to add the card to.
     * @param playerCards the LinkedList to take the card from.
     * @param <T> the generic type for the LinkedList.
     */
    public static <T> void takeFirstFromLine(LinkedList<GuillotineCards> list, LinkedList<GuillotineCards> playerCards) {
        GuillotineCards save = list.removeFromFront();
        playerCards.addToEnd(save);

        if(list.isEmpty()) return;

        if(getTurn()) {
            setPlayer1Score(getPlayer1Score() + save.getPointValue());
        }
        else if(!getTurn()) {
            setPlayer2Score(getPlayer2Score() + save.getPointValue());
        }

        switchTurn();

    }


    /**
     * This is a helper method to take the last card of the deck and add it to a player's list of cards.
     * @param list the LinkedList to add the card to.
     * @param tile the TilePane to manipulate
     * @param <T> the generic type for the LinkedList.
     */
    public <T> void updateList(LinkedList<T> list, TilePane tile) {
        LinkedListIterator<T> iterator = new LinkedListIterator<>(list.getFirstNode());
        while (iterator.hasNext()) {
            tile.getChildren().add(new Button(iterator.next().toString()));
        }
    }

    /**
     * This is a helper method to take the last card of the deck and add it to a player's list of cards.
     * @param list the LinkedList to add the card to.
     * @param numCards the number of cards to add.
     * @param <T> the generic type for the LinkedList.
     */
    public static <T> void addCardsToLinkedList(LinkedList<GuillotineCards> list, int numCards) {
        Random rand = new Random();
        for(int i = 0; i<numCards; i++) {
            GuillotineCards randCard = GuillotineCards.values()[rand.nextInt(GuillotineCards.values().length)];
            list.addToEnd(randCard);
            
        }
    }

    /**
     * This is a helper method to create the button panels.
     * @param vbox the VBox to add the buttons to.
     * @param list the LinkedList to be used as a deck.
     * @param tile the TilePane to manipulate.
     * @param <T> the generic type for the LinkedList.
     */
    public <T> void createButtons(VBox vbox, LinkedList<T> list, TilePane tile) {
        vbox.setSpacing(25);
        Button move4P1 = new Button("Move Back 4");
        //move4P1.setOnAction(e -> gameCards.moveBack(4));
        vbox.getChildren().add(move4P1);
        //move4P1.setOnAction(e -> moveBackFour(gameCards))
        move4P1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                moveBackFour(list);
                tile.getChildren().clear();
                updateList(list, tile);
                move4P1.setDisable(true);
            }
        });
        Button move3P1 = new Button("Move Back 3");
        vbox.getChildren().add(move3P1);
        move3P1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                moveBackThree(list);
                tile.getChildren().clear();
                updateList(list, tile);
                move3P1.setDisable(true);
            }
        });
        Button move2P1 = new Button("Move Back 2");
        vbox.getChildren().add(move2P1);
        move2P1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                moveBackTwo(list);
                tile.getChildren().clear();
                updateList(list, tile);
                move2P1.setDisable(true);
            }
        });
        Button move1P1 = new Button("Move Back 1");
        vbox.getChildren().add(move1P1);
        move1P1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                moveBackOne(list);
                tile.getChildren().clear();
                updateList(list, tile);
                move1P1.setDisable(true);
            }
        });
        Button moveLeadP1 = new Button("Move Lead Card to End");
        vbox.getChildren().add(moveLeadP1);
        moveLeadP1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                moveLeadToEnd(list);
                tile.getChildren().clear();
                updateList(list, tile);
                moveLeadP1.setDisable(true);
            }
        });
        Button reverseP1 = new Button("Reverse Card Line");
        vbox.getChildren().add(reverseP1);
        reverseP1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                reverseCards(list);
                tile.getChildren().clear();
                updateList(list, tile);
                reverseP1.setDisable(true);
            }
        });
        Button reverseFirst5P1 = new Button("Reverse First 5 Cards");
        vbox.getChildren().add(reverseFirst5P1);
        reverseFirst5P1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                reverseFirstFive(list);
                tile.getChildren().clear();
                updateList(list, tile);
                reverseFirst5P1.setDisable(true);
            }
        });
       Button skipP1 = new Button("Skip Turn");
        vbox.getChildren().add(skipP1);
        skipP1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                skipTurn();
                skipP1.setDisable(true);
            }
        });
        Button takeFirstP1 = new Button("Take First Card");
        vbox.getChildren().add(takeFirstP1);
        takeFirstP1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if(list.isEmpty()) {
                    endGame(list);
                    return;
                }

                Label scorePlayer1 = new Label();
                Label scorePlayer2 = new Label();

                if(getTurn()) {
                    scorePlayer1.setText("Player 1 Score: " + getPlayer1Score());
                }
                else if(!getTurn()) {
                    scorePlayer2.setText("Player 2 Score: " + getPlayer2Score());
                }
                takeFirstFromLine((LinkedList<GuillotineCards>) list, player1Cards);
                tile.getChildren().clear();
                updateList(list, tile);
                System.out.println(getTurn());
            }
        });
    }

    /**
     * This is a helper method to end the game.
     * @param cards the LinkedList to be used as a deck.
     * @param <T> the generic type for the LinkedList.
     */
    public static <T> void endGame(LinkedList<T> cards) {
        if (cards.isEmpty()) {
            if (getPlayer1Score() > getPlayer2Score()) {
                System.out.println("Player 1 Wins!");
            } else if (getPlayer2Score() > getPlayer1Score()) {
                System.out.println("Player 2 Wins!");
            } else {
                System.out.println("Tie Game!");
            }
        }
    }

    /**
     * This is the method that will start the Guillotine game.
     * @param primaryStage the stage to be used.
     * @throws Exception the exception to be thrown.
     */
    public void start(Stage primaryStage) throws Exception {

        //Add scanner input to ask how many cards the user wants in the game
        Scanner reader = new Scanner(System.in);
        System.out.println("How many cards do you want in the game?");
        /**
         * This field of type int determines the amount of cards used to be in a game of Guillotine.
         */
        int amountCards = reader.nextInt();

        //Create a card list for both players.
    player1Cards = new LinkedList<>();
    player2Cards = new LinkedList<>();


    //Create a card list for the game.
        LinkedList<GuillotineCards> gameCards = new LinkedList<>();
        addCardsToLinkedList(gameCards, amountCards);
        TilePane tile = new TilePane();
        updateList(gameCards, tile);

        //Create BorderPane as GUI, set it to center.
    BorderPane pane = new BorderPane();
    pane.setCenter(tile);

    //Create player1 GUI.
    BorderPane player1View = new BorderPane();
    pane.setLeft(player1View);
    player1Details = new VBox();
    Label player1Name = new Label("Player 1");
    player1View.setTop(player1Name);
    Label scorePlayer1 = new Label("Player 1 Score: " + getPlayer1Score());
    player1View.setBottom(scorePlayer1);
    VBox player1Buttons = new VBox();
    createButtons(player1Buttons, gameCards, tile);
    player1View.setCenter(player1Buttons);

    //Create player2 GUI.
    BorderPane player2View = new BorderPane();
    pane.setRight(player2View);
    player2Details = new VBox();
    Label player2Name = new Label("Player 2");
    scorePlayer2 = new Label("Player 2 Score: " + getPlayer2Score());
    player2View.setTop(player2Name);
    player2View.setBottom(scorePlayer2);
    VBox player2Buttons = new VBox();
    createButtons(player2Buttons, gameCards, tile);
    player2View.setCenter(player2Buttons);

    //Determine whether it is the player's turn and not and disable buttons accordingly.
        if(getTurn()) {
            player1Buttons.setDisable(false);
            player2Buttons.setDisable(true);
        } else if (!getTurn()) {
            player1Buttons.setDisable(true);
            player2Buttons.setDisable(false);
        }

//Load the scene.
    Scene scene = new Scene(pane, 450, 600);
    primaryStage.setScene(scene);
    primaryStage.setTitle("CSDS 132 Guillotine Game by Boris Brondz");
    primaryStage.show();
    }

    //Launch the application.
    public static void main (String [] args) {
        Application.launch(args);
    }

}