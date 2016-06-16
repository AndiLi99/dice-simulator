package stats.simulator;

public class Dice {
	int roll;


	public Dice (){
		roll = (int)(Math.random()*6)+1;
	}

	public void reRoll (){
		roll = (int)(Math.random()*6)+1;
	}

	public int getRoll (){
		return roll;
	}
	
	static Dice d1 = new Dice();
	static Dice d2 = new Dice();
	static Dice d3 = new Dice();
	
	public static void main (String args[]){
		int point =0;
		boolean troll = true;
		
		int [] countPair = new int [6];
		int [] countSingle = new int [6];
		int [] countTriple = new int [6];

		int countInstantWin = 0;
		int countInstantLoss = 0;
		int countSetPoint = 0;
		int countReRoll = 0;

		int countWin = 0;
		int countLoss = 0;
		int countDraws = 0;
		
		int trials = 10;
		System.out.println("trials: " + trials);
		
		for (int i = 0; i < trials; i ++){
			d1.reRoll();
			d2.reRoll();
			d3.reRoll();
			
			System.out.println(d1.roll + "" + d2.roll + "" + d3.roll);
			
			int pairsDigit = checkPairs();
			int singlesDigit = checkSingle();
			
			if (pairsDigit != 0){
				countPair[pairsDigit-1] ++;
				countSingle [singlesDigit-1]++;
			}
			
			if (checkTriples()){
				countTriple [d1.roll -1] ++;
			}
			
			if (checkInstantWin()){
				countInstantWin++;
				if (troll)
					countLoss++;
				else
					countWin++;
				
				troll = true;
			}
			
			else if (checkInstantLoss()){
				countInstantLoss++;
				if (troll)
					countWin++;
				else
					countLoss++;
				
				troll = true;
			}
			
			else if (checkSetPoint()){
				countSetPoint++;
				if (troll)
					point = singlesDigit;
					troll = false;
				if (!troll){
					if (singlesDigit > point)
						countWin++;
					if (singlesDigit == point)
						countDraws++;
					if (singlesDigit < point)
						countLoss++;
					troll = true;
				}
			}
			
			else{
				countReRoll ++;
				
			}
				
		}
		
		for (int i = 0; i < 6; i ++)
			System.out.println("#pairs of " + (i+1) + ": " + countPair[i]);
		for (int i = 0; i < 6; i ++)
			System.out.println("#singles of " + (i+1) + ": " + countSingle[i]);
		for (int i = 0; i < 6; i ++)
			System.out.println("#triples of " + (i+1) + ": " + countTriple[i]);

			
		
		
		System.out.println("# instant wins: " + countInstantWin);
		System.out.println("# instant loss: " + countInstantLoss);
		System.out.println("# set Points: " + countSetPoint);
		System.out.println("# rerolls " + countReRoll);
		System.out.println("# wins: " + countWin);
		System.out.println("# losses: " + countLoss);

	}

	public static int checkPairs (){
		if (d1.roll == d2.roll)
			return d1.roll;
		else if (d2.roll == d3.roll)
			return d2.roll;
		else if (d1.roll == d3.roll)
			return d3.roll;
		else
			return 0;
	}

	public static int checkSingle(){
		if (d1.roll == d2.roll)
			return d3.roll;
		else if (d2.roll == d3.roll)
			return d1.roll;
		else if (d1.roll == d3.roll)
			return d2.roll;
		else
			return 0;

	}

	public static boolean checkTriples (){
		if (d1.roll == d2.roll && d2.roll == d3.roll)
			return true;
		else
			return false;
	}

	public static boolean checkInstantWin (){
		if (checkTriples())
			return true;
		
		if (d1.roll + d2.roll + d3.roll == 15 && checkPairs() == 0)
			return true;
		
		if (checkSingle() == 6)
			return true;
		
		return false;
	}
	
	public static boolean checkInstantLoss (){
		if (checkSingle() == 1)
			return true;
		if (d1.roll + d2.roll + d3.roll == 6 && checkPairs() == 0)
			return true;
		
		return false;
	}
	
	public static boolean checkSetPoint(){
		if (checkSingle () > 1 && checkSingle() < 6)
			return true;
		
		return false;
	}
	
	
	
}
