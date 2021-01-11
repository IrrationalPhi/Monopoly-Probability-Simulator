import java.util.*;

public class MonopolyGame {
	// board will represent the tiles
	// 0 is go, 39 is boardwalk
	// each entry is number of times landed on it
	public double[] board; 

	// initalize chance and community chest
	private int[] chanceOrder;
	private int[] ccOrder;

	// iterator for chance and cc arrays
	private int chanceIterator;
	private int ccIterator;

	// rng seed
	private Random rand;

	// player instances
	// for now just 1 player
	public Player player1;

	// constructor
	public MonopolyGame() {
		this.board = new double[40];
		this.rand = new Random();
		this.player1 = new Player();
		this.chanceOrder = shuffleConstructor();
		this.chanceIterator = 0;
		this.ccOrder = shuffleConstructor();
		this.ccIterator = 0;
	}

	public void showBoard() {
		for (int i = 0; i < this.board.length; i++) {
			System.out.printf(i + ":\t" + this.board[i] +'\n');
		}
	}

	public double[] getBoard() {
		return this.board;
	}

	// roll for player
	public void doRoll() {
		// roll dice
		int roll1 = rand.nextInt(6) +1;
		int roll2 = rand.nextInt(6) +1;
		int totalRoll = roll1 + roll2;

		// if same, doubleCount increase
		if (roll1 == roll2) {
			player1.incrementDouble();
			// if doubleCount becomes 3, go to jail
			if (player1.getDoubleCount() == 3) {
				player1.toJail();
			} else {
				// if in jail, out now 
				if (player1.isJailed()) {
					player1.releaseJail();
					player1.resetJailTurns();
				}
				// else just a normal turn now
				player1.move(totalRoll);

				// now check what happens
				this.turnChecker(player1);
			}
		} else {
			if (player1.isJailed()) {
				// check number of turns already in jail
				if (player1.getJailTurns() == 2) {
					// need to pay bail and out of jail
					player1.releaseJail();
					player1.move(totalRoll);
					this.turnChecker(player1);
				}
			} else {
				// reset double count
				player1.resetDouble();

				player1.move(totalRoll);
				this.turnChecker(player1);
			}
		}
	}

	// helper to check what happens after landing on spot
	private void turnChecker(Player p) {
		int pos = p.getPosition();
		// increment count
		this.board[pos] += 1;

		// now look at if landed on special spots
		// for now, only go to jail is special spot
		if (pos == 30) {
			p.toJail();
			this.board[10] += 1;
		} else if (pos == 7 || pos == 22 || pos == 36) {
			this.chanceChecker(p, chanceOrder[chanceIterator]);
			// System.out.println(chanceIterator);
		} else if (pos == 2 || pos == 17 || pos == 33) {
			this.ccChecker(p, ccOrder[ccIterator]);
		}
	}

	private void chanceChecker(Player p, int chanceNum) {
		switch (chanceNum) {
			case 0: 
				// advance to go
				// collect 200
				p.toGo();
				this.turnChecker(p);
				break;
			case 1:
				// advance to illinois ave
				// if pass go, collect 200
				p.toIllinois();
				this.turnChecker(p);
				break;
			case 2: 
				// advance to st charles
				// if pass go, collect 200
				p.toStCharles();
				this.turnChecker(p);
				break;
			case 3:
				// advance to nearest utility
				// if pass go, collect 200
				p.toNearestUtility();
				this.turnChecker(p);
				break;
			case 4:
				// advance to nearest railroad
				// if pass go, collect 200
				p.toNearestRailroad();
				this.turnChecker(p);
				break;
			case 5:
				// bank pays you dividend of 50
				break;
			case 6:
				// get out of jail free
				break;
			case 7: 
				// go back 3 spaces
				p.backThree();
				this.turnChecker(p);
				break;
			case 8:
				// go directly to jail
				// do not pass go, do not collect 200
				p.toJail();
				this.turnChecker(p);
				break;
			case 9:
				// make all repairs to property
				// for each house 25, for each hotel 100
				break;
			case 10: 
				// pay poor tax of 15
				break;
			case 11: 
				// take a trip to reading railroad
				// if pass go, collect 200
				p.toReadingRailroad();
				this.turnChecker(p);
				break;
			case 12: 
				// go to boardwalk
				p.toBoardwalk();
				this.turnChecker(p);
				break;
			case 13:
				// pay each player 50
				break;
			case 14:
				// collect 150
				break;
			case 15:
				// collect 100
				break;
		}
		// now move the chance iterator
		chanceIterator += 1;
		if (chanceIterator == 16)
			chanceIterator = 0;
	}

	private void ccChecker(Player p, int ccNum) {
		switch (ccNum) {
			case 0: 
				// advance to go
				// collect 200
				p.toGo();
				this.turnChecker(p);
				break;
			case 1:
				// collect 200
				break;
			case 2: 
				// pay 50
				break;
			case 3:
				// pay 50
				break;
			case 4:
				// get out of jail free
				break;
			case 5:
				// go to jail
				p.toJail();
				this.turnChecker(p);
				break;
			case 6: 
				// receive 100
				break;
			case 7:
				// collect 20
				break;
			case 8:
				// collect 10 from each player
				break;
			case 9: 
				// collect 100
				break;
			case 10: 
				// pay 50
				break;
			case 11: 
				// pay 50
				break;
			case 12:
				// receive 25
				break;
			case 13:
				// pay 40 per house, 115 per hotel
				break;
			case 14:
				// collect 10
				break;
			case 15: 
				// collect 100
				break;
		}
		// now move the chance iterator
		ccIterator += 1;
		if (ccIterator == 16)
			ccIterator = 0;
	}

	private static int[] shuffleConstructor() {
		Integer[] arr = new Integer[16];
		for (int i = 0; i < 16; i++) {
			arr[i] = i;
		}

		List<Integer> intList = Arrays.asList(arr);
		Collections.shuffle(intList);
		intList.toArray(arr);
		int[] res = Arrays.stream(arr).mapToInt(Integer::intValue).toArray();
		return res;
	}
}