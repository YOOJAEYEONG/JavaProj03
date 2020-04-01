import java.io.IOException;
import java.util.Random;
import java.util.Scanner;



/*
1	2	3			0,0		0,1		0,2
4	5	6			1,0		1,1		1,2
7	8	X			2,0		2,1		2,2
*/


class Puzzle {
	
	String[][] puzzle = new String[3][3];
	Scanner scan = new Scanner(System.in);
	int idx1, idx2;
	String input;
	final String UP = "w";
	final String LEFT = "a";
	final String DOWN = "s";
	final String RIGHT = "d";
	final String RESTART = "y";
	final String EXIT = "e";
	boolean checkSwitch;
	
	
	
	public void playGame() {
		
		defaultSetPuzzle();
		shuffle(100);
		do {
			updatePuzzle();
			playGuidePrint();
			scanVal();
			run();
			resultCheck();
		} while (true);
	}

	public void scanVal() {
		checkSwitch = true;
		try {
			input = scan.nextLine();
			
		} catch (Exception e) {
			scan.nextLine();
		}
	}
	
	public void initializeIndex() {
		for( idx1=0; idx1<=2; idx1++)
			for( idx2=0; idx2<=2 ; idx2++) 
				if(puzzle[idx1][idx2].equals("X")) return;
	}
	public void resultCheck() {
		
		int val = 1;
		for(int i=0 ; i<=2 ; i++) {
			for(int j=0 ; j<=2 ; j++) {
				if(val==9) {
					if(puzzle[i][j].equals("X")) {
						System.out.println("==^^정답입니다==");
						updatePuzzle();
						System.out.println("재시작하시겠습니까?(y 누르면 재시작, 나머지는 종료");
						scanVal();
						if(input=="y") shuffle(100);
						else System.exit(0);
					}
				}
				else if(!puzzle[i][j].equals(Integer.toString((val++)))) {
					return;
				}
			}
		}
	
	}
	
	public void updatePuzzle() {
		System.out.println("=======");
		for(int i=0 ; i<=2 ; i++) {
			for(int j=0 ; j<=2 ; j++) {
				System.out.print(puzzle[i][j]+"  ");
			}
			System.out.println();
		}
		System.out.println("=======");
		
	}
	
	public void playGuidePrint() {
		System.out.print("   ▲w		r.재시작\n"
						+"◀a   d▶		  \n"
						+"   ▼s		e.나가기\n");
	}
	
	public boolean checkMove(int index, int update) {
		
			if(0<=(index+update) && (index+update)<=2 )	{
				return true;
			}
			else if(checkSwitch==true){
				System.out.println(
						  "xxxxxxxxx\n"
						+ "xx이동불가xx\n"
						+ "xxxxxxxxx");
				return false;
			}
			else	return false;
	}
	
	public void run() {
		
		int indexChanger ;
		initializeIndex();
		
		
		
		switch(input) {
		
		case UP:	case "1":
			indexChanger = -1;
			if(!checkMove(idx1, indexChanger)) return;
			swipe(puzzle[idx1][idx2], puzzle[idx1+indexChanger][idx2], indexChanger, UP);
			break;
			
		case DOWN:	case "2":
			indexChanger = 1;
			if(!checkMove(idx1, indexChanger)) return;
			swipe(puzzle[idx1][idx2], puzzle[idx1+indexChanger][idx2], indexChanger, DOWN);
			break;
			
		case LEFT:	case "3":
			indexChanger = -1;
			if(!checkMove(idx2, +indexChanger)) return;
			swipe(puzzle[idx1][idx2], puzzle[idx1][idx2+indexChanger], indexChanger, LEFT);
			break;
			
		case RIGHT:	case "4":
			indexChanger = 1;
			if(!checkMove(idx2, +indexChanger)) return;
			swipe(puzzle[idx1][idx2], puzzle[idx1][idx2+indexChanger], indexChanger, RIGHT);
			break;
			
		case RESTART:
			System.err.println("다시 시작합니다.");
			shuffle(30);
			break;
		case EXIT:
			System.err.println("나가기");
			System.exit(0);
			
		default :
			System.out.println(input+": 해당메뉴는 없습니다.");
		}
	}
	
	public void swipe(String positionX,String positionOther, int change, String towhere) {

		//여기서 인덱스 값변경이 없음을 확인함
		switch (towhere) {
		case "w": case DOWN:
			puzzle[idx1][idx2] = positionOther;
			puzzle[idx1+change][idx2] = positionX;
			break;

		case "a": case RIGHT:
			puzzle[idx1][idx2] = positionOther;
			puzzle[idx1][idx2+change] = positionX;
			break;
		}
	}

	
	public void defaultSetPuzzle() {
		System.out.println("new Puzzle");
		System.out.println("=======");
		int val = 1;
		for(int i=0 ; i<=2 ; i++) {
			for(int j=0 ; j<=2 ; j++) {
				if(val==9) {
					puzzle[i][j] = "X";
					System.out.print(puzzle[i][j]);
				}
				else {
				puzzle[i][j] = Integer.toString((val++));
				System.out.print(puzzle[i][j]+"  ");
				}
			}
			System.out.println();
		}
		System.out.println("=======");
	}
	
	
	public void shuffle(int shuffles) {

		for(int i=shuffles ; i>=0 ; i--) {
			int input = (new Random().nextInt(100))/10;
			if(input>=5||input==0)	i++;
			else	{
				checkSwitch = false;
				this.input=Integer.toString(input);
				run();
			}
		}
	}
}
 

public class puzzleMain {

	
	public static void main(String[] args) throws IOException {

		
		 new Puzzle().playGame();
		
	      
	}
	

	
}
