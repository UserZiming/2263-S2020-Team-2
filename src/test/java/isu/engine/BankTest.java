package isu.engine;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BankTest {
    private Bank fixture;
    private Player fixturePlayer;
    private Player fixturePlayer2;
    private Player fixturePlayer3;
    private Player fixturePlayer4;
    private List<Player> players;
    private GameEngine gameEngine;

    public BankTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        gameEngine = new GameEngine();
        fixture = new Bank(gameEngine.getHotelChains(), GameEngine.MAX_STOCK_COUNT);
        fixturePlayer = new Player("test");
        fixturePlayer2 = new Player("test_2");
        fixturePlayer3 = new Player("test_3");
        fixturePlayer4 = new Player("test_4");
        players = new ArrayList<>();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test buyStocks methods.
     */
    @Test
    public void testBuyStocks() {
        players.add(fixturePlayer);
        players.add(fixturePlayer2);

        gameEngine.getHotelChains()[1].addTile(new Tile(0, 1));
        gameEngine.getHotelChains()[1].addTile(new Tile(0, 2));
        gameEngine.getHotelChains()[1].addTile(new Tile(0, 3));

        fixturePlayer.addMoney(2000);
        fixturePlayer.addStocks(gameEngine.getHotelChains()[1], 6);
        fixture.buyStocksFromPlayer(fixturePlayer, gameEngine.getHotelChains()[1], 2);

        assertEquals(4, fixturePlayer.getStocks(gameEngine.getHotelChains()[1]));
        assertEquals(3, gameEngine.getHotelChains()[1].size());
        assertEquals(2600, fixturePlayer.getMoney());

        gameEngine.getHotelChains()[1].clearTiles();
    }
    /**
     * Test buyStocks methods.
     */
    @Test
    public void testSellStocks(){

        gameEngine.getHotelChains()[0].addTile(new Tile(0, 1));
        gameEngine.getHotelChains()[0].addTile(new Tile(0, 2));
        gameEngine.getHotelChains()[0].addTile(new Tile(0, 3));

        fixturePlayer.addMoney(2000);

        fixture.sellStocksToPlayer(fixturePlayer, gameEngine.getHotelChains()[0], 3);

        assertEquals(3, fixturePlayer.getStocks(gameEngine.getHotelChains()[0]));
        assertEquals(3, gameEngine.getHotelChains()[0].size());
        assertEquals(1100, fixturePlayer.getMoney());

        gameEngine.getHotelChains()[0].clearTiles();
    }
    /**
     * Test payBonus methods for one major stockholder.
     */
    @Test
    public void testPayBonus_1(){

        players.add(fixturePlayer);
        players.add(fixturePlayer2);

        gameEngine.getHotelChains()[2].addTile(new Tile(0, 1));
        gameEngine.getHotelChains()[2].addTile(new Tile(0, 2));

        fixturePlayer.addMoney(2000);
        fixturePlayer2.addMoney(2000);

        fixturePlayer.addStocks(gameEngine.getHotelChains()[2], 0);
        fixturePlayer2.addStocks(gameEngine.getHotelChains()[2], 1);

        fixture.payBonus(gameEngine.getHotelChains()[2], players);

        assertEquals(2000, fixturePlayer.getMoney());
        assertEquals(6500, fixturePlayer2.getMoney());

        gameEngine.getHotelChains()[2].clearTiles();
    }
    /**
     * Test payBonus methods for two persons, one major stockholder and one minor stockholder
     */
    @Test
    public void testPayBonus_2(){

        players.add(fixturePlayer);
        players.add(fixturePlayer2);

        gameEngine.getHotelChains()[0].addTile(new Tile(0, 1));
        gameEngine.getHotelChains()[0].addTile(new Tile(0, 2));

        fixturePlayer.addStocks(gameEngine.getHotelChains()[0], 2);
        fixturePlayer2.addStocks(gameEngine.getHotelChains()[0], 1);

        fixture.payBonus(gameEngine.getHotelChains()[0], players);

        assertEquals(2000, fixturePlayer.getMoney());
        assertEquals(1000, fixturePlayer2.getMoney());

        gameEngine.getHotelChains()[0].clearTiles();
    }

    /**
     * Test payBonus methods for two players who have same stocks
     */
    @Test
    public void testPayBonus_3(){
        players.add(fixturePlayer);
        players.add(fixturePlayer2);

        fixturePlayer.addStocks(gameEngine.getHotelChains()[0], 3);
        fixturePlayer2.addStocks(gameEngine.getHotelChains()[0], 3);

        gameEngine.getHotelChains()[0].addTile(new Tile(0, 1));
        gameEngine.getHotelChains()[0].addTile(new Tile(0, 2));
        fixture.payBonus(gameEngine.getHotelChains()[0], players);

        System.out.println(fixturePlayer.getMoney());
        System.out.println(fixturePlayer2.getMoney());
        assertEquals(1500,fixturePlayer.getMoney());
        assertEquals(1500, fixturePlayer2.getMoney());

        gameEngine.getHotelChains()[0].clearTiles();
    }
    /**
     * Test payBonus methods for Three players, one major stockholder, one minor stockholder and one third stockholder
     */
    @Test
    public void testPayBonus_4(){
        players.add(fixturePlayer);
        players.add(fixturePlayer2);
        players.add(fixturePlayer3);

        fixturePlayer.addStocks(gameEngine.getHotelChains()[0], 3);
        fixturePlayer2.addStocks(gameEngine.getHotelChains()[0], 2);
        fixturePlayer3.addStocks(gameEngine.getHotelChains()[0], 1);

        gameEngine.getHotelChains()[0].addTile(new Tile(0, 1));
        gameEngine.getHotelChains()[0].addTile(new Tile(0, 2));
        fixture.payBonus(gameEngine.getHotelChains()[0], players);

        assertEquals(2000,fixturePlayer.getMoney());
        assertEquals(1000, fixturePlayer2.getMoney());
        assertEquals(0, fixturePlayer3.getMoney());

        gameEngine.getHotelChains()[0].clearTiles();
    }
    /**
     * Test payBonus methods for Three players, two major stockholders and one minor stockholder
     */
    @Test
    public void testPayBonus_5(){
        players.add(fixturePlayer);
        players.add(fixturePlayer2);
        players.add(fixturePlayer3);

        fixturePlayer.addStocks(gameEngine.getHotelChains()[0], 3);
        fixturePlayer2.addStocks(gameEngine.getHotelChains()[0], 3);
        fixturePlayer3.addStocks(gameEngine.getHotelChains()[0], 1);

        gameEngine.getHotelChains()[0].addTile(new Tile(0, 1));
        gameEngine.getHotelChains()[0].addTile(new Tile(0, 2));
        fixture.payBonus(gameEngine.getHotelChains()[0], players);

        assertEquals(1000,fixturePlayer.getMoney());
        assertEquals(1000, fixturePlayer2.getMoney());
        assertEquals(1000, fixturePlayer3.getMoney());

        gameEngine.getHotelChains()[0].clearTiles();
    }

    /**
     * Test payBonus methods for Three players, one major stockholder and two minor stockholders
     */
    @Test
    public void testPayBonus_6(){
        players.add(fixturePlayer);
        players.add(fixturePlayer2);
        players.add(fixturePlayer3);

        fixturePlayer.addStocks(gameEngine.getHotelChains()[0], 3);
        fixturePlayer2.addStocks(gameEngine.getHotelChains()[0], 1);
        fixturePlayer3.addStocks(gameEngine.getHotelChains()[0], 1);

        gameEngine.getHotelChains()[0].addTile(new Tile(0, 1));
        gameEngine.getHotelChains()[0].addTile(new Tile(0, 2));
        fixture.payBonus(gameEngine.getHotelChains()[0], players);

        assertEquals(2000,fixturePlayer.getMoney());
        assertEquals(500, fixturePlayer2.getMoney());
        assertEquals(500, fixturePlayer3.getMoney());

        gameEngine.getHotelChains()[0].clearTiles();
    }
    /**
     * Test payBonus methods for three players, three major stockholders
     */
    @Test
    public void testPayBonus_7(){
        players.add(fixturePlayer);
        players.add(fixturePlayer2);
        players.add(fixturePlayer3);

        fixturePlayer.addStocks(gameEngine.getHotelChains()[0], 2);
        fixturePlayer2.addStocks(gameEngine.getHotelChains()[0], 2);
        fixturePlayer3.addStocks(gameEngine.getHotelChains()[0], 2);

        gameEngine.getHotelChains()[0].addTile(new Tile(0, 1));
        gameEngine.getHotelChains()[0].addTile(new Tile(0, 2));
        fixture.payBonus(gameEngine.getHotelChains()[0], players);

        assertEquals(1000,fixturePlayer.getMoney());
        assertEquals(1000, fixturePlayer2.getMoney());
        assertEquals(1000, fixturePlayer3.getMoney());

        gameEngine.getHotelChains()[0].clearTiles();
    }
    /**
     * Test payBonus methods for four players, two major stockholders and two minor stockholders
     */
    @Test
    public void testPayBonus_8(){
        players.add(fixturePlayer);
        players.add(fixturePlayer2);
        players.add(fixturePlayer3);
        players.add(fixturePlayer4);

        fixturePlayer.addStocks(gameEngine.getHotelChains()[0], 3);
        fixturePlayer2.addStocks(gameEngine.getHotelChains()[0], 3);
        fixturePlayer3.addStocks(gameEngine.getHotelChains()[0], 1);
        fixturePlayer4.addStocks(gameEngine.getHotelChains()[0], 1);

        gameEngine.getHotelChains()[0].addTile(new Tile(0, 1));
        gameEngine.getHotelChains()[0].addTile(new Tile(0, 2));
        fixture.payBonus(gameEngine.getHotelChains()[0], players);

        assertEquals(1000,fixturePlayer.getMoney());
        assertEquals(1000, fixturePlayer2.getMoney());
        assertEquals(500, fixturePlayer3.getMoney());
        assertEquals(500, fixturePlayer4.getMoney());

        gameEngine.getHotelChains()[0].clearTiles();
    }
    /**
     * Test payBonus methods for four players, one major stockholder and three minor stockholders
     */
    @Test
    public void testPayBonus_9(){
        players.add(fixturePlayer);
        players.add(fixturePlayer2);
        players.add(fixturePlayer3);
        players.add(fixturePlayer4);

        fixturePlayer.addStocks(gameEngine.getHotelChains()[0], 3);
        fixturePlayer2.addStocks(gameEngine.getHotelChains()[0], 1);
        fixturePlayer3.addStocks(gameEngine.getHotelChains()[0], 1);
        fixturePlayer4.addStocks(gameEngine.getHotelChains()[0], 1);

        gameEngine.getHotelChains()[0].addTile(new Tile(0, 1));
        gameEngine.getHotelChains()[0].addTile(new Tile(0, 2));
        fixture.payBonus(gameEngine.getHotelChains()[0], players);

        assertEquals(2000,fixturePlayer.getMoney());
        assertEquals(333, fixturePlayer2.getMoney());
        assertEquals(333, fixturePlayer3.getMoney());
        assertEquals(333, fixturePlayer4.getMoney());

        gameEngine.getHotelChains()[0].clearTiles();
    }
    /**
     * Test payBonus methods for four players, one major stockholder and three minor stockholders
     */
    @Test
    public void testPayBonus_10(){
        players.add(fixturePlayer);
        players.add(fixturePlayer2);
        players.add(fixturePlayer3);
        players.add(fixturePlayer4);

        fixturePlayer.addStocks(gameEngine.getHotelChains()[0], 3);
        fixturePlayer2.addStocks(gameEngine.getHotelChains()[0], 3);
        fixturePlayer3.addStocks(gameEngine.getHotelChains()[0], 3);
        fixturePlayer4.addStocks(gameEngine.getHotelChains()[0], 1);

        gameEngine.getHotelChains()[0].addTile(new Tile(0, 1));
        gameEngine.getHotelChains()[0].addTile(new Tile(0, 2));
        fixture.payBonus(gameEngine.getHotelChains()[0], players);

        assertEquals(666,fixturePlayer.getMoney());
        assertEquals(666, fixturePlayer2.getMoney());
        assertEquals(666, fixturePlayer3.getMoney());
        assertEquals(1000, fixturePlayer4.getMoney());

        gameEngine.getHotelChains()[0].clearTiles();
    }
    /**
     * Test payBonus methods for four players, one major stockholder and one minor stockholders
     */
    @Test
    public void testPayBonus_11(){
        players.add(fixturePlayer);
        players.add(fixturePlayer2);
        players.add(fixturePlayer3);
        players.add(fixturePlayer4);

        fixturePlayer.addStocks(gameEngine.getHotelChains()[0], 4);
        fixturePlayer2.addStocks(gameEngine.getHotelChains()[0], 3);
        fixturePlayer3.addStocks(gameEngine.getHotelChains()[0], 2);
        fixturePlayer4.addStocks(gameEngine.getHotelChains()[0], 1);

        gameEngine.getHotelChains()[0].addTile(new Tile(0, 1));
        gameEngine.getHotelChains()[0].addTile(new Tile(0, 2));
        fixture.payBonus(gameEngine.getHotelChains()[0], players);

        assertEquals(2000,fixturePlayer.getMoney());
        assertEquals(1000, fixturePlayer2.getMoney());
        assertEquals(0, fixturePlayer3.getMoney());
        assertEquals(0, fixturePlayer4.getMoney());

        gameEngine.getHotelChains()[0].clearTiles();
    }
}
