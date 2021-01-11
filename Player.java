import java.util.*;

public class Player {
	// need: position, isJailed, doubleCount
	// also seed for rng

	private int position;
	private boolean isJailed;
	private int doubleCount;
	private int jailTurns;

	// constructor
	public Player() {
		this.position = 0;
		this.isJailed = false;
		this.doubleCount = 0;
		this.jailTurns = 0;
	}

	// accessors
	public int getPosition() {
		return this.position;
	}

	public int getDoubleCount() {
		return this.doubleCount;
	}

	public boolean isJailed() {
		return this.isJailed;
	}

	public int getJailTurns() {
		return this.jailTurns;
	}

	// mutators
	// move by roll number
	public void move(int roll) {
		this.position = (this.position + roll) % 40;
	}

	// go to jail
	public void toJail() {
		this.position = 10;
		this.isJailed = true;
		this.doubleCount = 0;
	}

	// advance to go
	public void toGo() {
		this.position = 0;
		// collect 200
	}

	// advance to illinois
	public void toIllinois() {
		// if pass go, collect 200
		if (this.position  >= 25) {
			// collect 200
		}
		// now set position to illinois
		this.position = 24;
	}

	// advance to St. Charles
	public void toStCharles() {
		// if pass go, collect 200
		if (this.position >= 12) {
			// collect 200
		}
		// now set position to st charles
		this.position = 11;
	}

	// advance to nearest utility
	public void toNearestUtility() {
		if (this.position >= 13 && this.position <= 27) {
			// advance to water works
			this.position = 28;
		} else {
			if (this.position >= 29 && this.position <= 39) {
				// collect 200
			}
			this.position = 12;
		}
	}

	// advance to nearest railroad
	public void toNearestRailroad() {
		if (this.position >= 6 && this.position <= 14) {
			this.position = 15;
		} else if (this.position >= 16 && this.position <= 24) {
			this.position = 25;
		} else if (this.position >= 26 && this.position <= 35) {
			this.position = 35;
		} else {
			if (this.position >= 36) {
				// pass go, collect 200
			}
			this.position = 5;
		}
	}

	// move back 3 spaces
	public void backThree() {
		this.position -= 3;
		if (this.position < 0)
			this.position += 40;
	}

	// to reading railroad
	public void toReadingRailroad() {
		if (this.position >= 6) {
			// pass go, collect 200
		}
		this.position = 5;
	}

	// to boardwalk
	public void toBoardwalk() {
		this.position = 39;
	}

	// release from jail
	public void releaseJail() {
		this.isJailed = false;
	}

	// increment double count
	public void incrementDouble() {
		this.doubleCount++;
	}

	// reset double count
	public void resetDouble() {
		this.doubleCount = 0;
	}

	// reset number of turns in jail
	public void resetJailTurns() {
		this.jailTurns = 0;
	}
}