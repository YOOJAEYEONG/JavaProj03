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
	int idx1, idx2, input;
	final int UP = 119;
	final int LEFT = 97;
	final int DOWN = 115;
	final int RIGHT = 100;
	final int RESTART = 49;
	final int EXIT = 50;
	boolean checkSwitch;
	
	
	public Puzzle()  {
		playGame();
	}
	
	public void playGame() {
		
		defaultSetPuzzle();
		shuffle(10);
		do {
			updatePuzzle();
			playGuidePrint();
			scanVal();
			run();
			
		} while (true);
	}

	public void scanVal() {
		checkSwitch = true;
		try {
			input = System.in.read();
			scan.nextLine();
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	public void search(String value) {//X를 가진 배열의 인덱스값을 찾는다.
		for( idx1=0; idx1<=2; idx1++)
			for( idx2=0; idx2<=2 ; idx2++) 
				if(puzzle[idx1][idx2].equals(value)) return;
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
		System.out.print("   ▲w		1.재시작\n");
		System.out.print("◀a   d▶\n");
		System.out.print("   ▼s		");
		System.err.println("2.나가기");
	}
	
	public boolean checkMove(int index, int update) {
		
			if(0<=(index+update) && (index+update)<=2 )	return true;
			else if(checkSwitch){
				System.out.println("xxxxxxxxxx\n"
						+ "xx이동불가xx\n"
						+ "xxxxxxxxxx");
				return false;
			}
			else	return false;
		
	}
	
	public void run() {
		
		int indexChanger ;
		
		
		switch(input) {
		case UP:	case 1:
			search("X");
			indexChanger = -1;
			if(!checkMove(idx1, indexChanger)) return;
			swipe(puzzle[idx1][idx2], puzzle[idx1+indexChanger][idx2], indexChanger, UP);
			break;
			
		case DOWN:	case 2:
			search("X");
			indexChanger = 1;
			if(!checkMove(idx1, indexChanger)) return;
			swipe(puzzle[idx1][idx2], puzzle[idx1+indexChanger][idx2], indexChanger, DOWN);
			break;
			
		case LEFT:	case 3:
			search("X");
			indexChanger = -1;
			if(!checkMove(idx2, +indexChanger)) return;
			swipe(puzzle[idx1][idx2], puzzle[idx1][idx2+indexChanger], indexChanger, LEFT);
			break;
			
		case RIGHT:	case 4:
			search("X");
			indexChanger = 1;
			if(!checkMove(idx2, +indexChanger)) return;
			swipe(puzzle[idx1][idx2], puzzle[idx1][idx2+indexChanger], indexChanger, RIGHT);
			break;
			
		case RESTART:
			System.out.println("다시 시작합니다.");
			shuffle(30);
			break;
		case EXIT:
			System.err.println("나가기");
			System.exit(0);
			
		default :
			System.out.println(input+": 해당메뉴는 없습니다.");
		}
	}
	
	public void swipe(String positionX,String positionOther, int change, int towhere) {

		//여기서 인덱스 값변경이 없음을 확인함
		switch (towhere) {
		case UP: case DOWN:
			puzzle[idx1][idx2] = positionOther;
			puzzle[idx1+change][idx2] = positionX;
			break;

		case LEFT: case RIGHT:
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
				this.input=input;
				run();
			}
		}
	}
	
}
 

public class puzzleMain {

	
	public static void main(String[] args) throws IOException {

		
		 new Puzzle();
		
	      
	}
	

	
}
