import java.io.IOException;
import java.util.Scanner;




class Puzzle {
	
	String[][] puzzle = new String[3][3];
	Scanner scan = new Scanner(System.in);
	int width = 0, height = 0;
	
	
	public Puzzle() throws IOException {
		playGame();
	}
	
	public void playGame() {
		int selectMenu;
		setPuzzle();//1
		playGuide();//2
		//shuffle 3
		do {
			inputValue();
			
			selectMenu = scan.nextInt();
		} while (selectMenu==2);
	}
	
	public void playGuide() {
		System.out.print("   ▲w\n");
		System.out.print("◀a   d▶\n");
		System.out.print("   ▼s");
		System.out.println("\n1.재시작  2.나가기");
	}
	public int checkMove(int index) {
		if(0<index||index<2) {
			return index;
		}
		else {
			if(index>2)	index--;
			if(index<0)	index++;
			System.out.println("xxxxxxxxxx\n"
							 + "xx이동불가xx\n"
							 + "xxxxxxxxxx");
		}
		return index;
	}
	public void inputValue() {
		int input=0;
		final int UP = 119;
		final int LEFT = 97;
		final int DOWN = 115;
		final int RIGHT = 100;
		final int RESTART = 1;
		final int EXIT = 2;
		
		try {
			input = System.in.read();
		} catch (Exception e) {
			e.getMessage();
		}
		
		//x의 초기 위치값 puzzle[i][j]
		switch(input) {
		case UP:
			
			//puzzle[checkMove(width)][checkMove(width)];
			break;
		case DOWN:
			
			break;
		case LEFT:
			
			break;
		case RIGHT:
			
			break;
		case RESTART:
			
			break;
		case EXIT:
			
			break;
			
		default :
			System.out.println("다시 입력");
		}
		
	}
/*
	1	2	3			0,0		0,1		0,2
	4	5	6			1,0		1,1		1,2
	7	8	X			2,0		2,1		2,2
*/
	public void setPuzzle() {
		System.out.println("new Puzzle");
		System.out.println("=============");
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
		System.out.println("=============");
	}
}
 

public class puzzleMain {

	
	public static void main(String[] args) throws IOException {

		
		 new Puzzle();
		
	      
	}
	

	
}
