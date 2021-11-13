package HomeWork;

import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HomeWork {
	public static void main(String[] args) throws IOException {
		File file = new File("./src/HomeWork/input.txt");

		Point length = calculateBoardLength(file);

		int[][] boards = new int[length.x][length.y];
		Point[] results = new Point[100];

		initBoards(file, length, boards, results);
		Point startPoint = initResultsAndgetStartPoint(results, boards, length); 
				
		results[0] = startPoint;
		int index = move(results, boards, length);

		if(index != 0)
			printResults(index, results);
	}

	public static int move(Point[] results, int[][] boards, Point length) {
		int index = 0;
		while (true) {
			Point nowPoint = results[index];
			int x = nowPoint.x;
			int y = nowPoint.y;

			if (x - 1 >= 0 && !checkBeforePoint(new Point(x - 1, y), results) && (boards[x - 1][y] == 0 || boards[x - 1][y] == 3)) {
				index++;
				if(moveAndLastPoint(index, nowPoint, new Point(-1, 0), results, boards))
					break;
			} else if (y - 1 >= 0 && !checkBeforePoint(new Point(x, y - 1), results) && (boards[x][y - 1] == 0 || boards[x][y - 1] == 3)) {
				index++;
				if(moveAndLastPoint(index, nowPoint, new Point(0, -1), results, boards))
					break;
			} else if (x + 1 < length.x && !checkBeforePoint(new Point(x + 1, y), results) && (boards[x + 1][y] == 0 || boards[x + 1][y] == 3)) {
				index++;
				if(moveAndLastPoint(index, nowPoint, new Point(1, 0), results, boards))
					break;
			} else if (y + 1 < length.y && !checkBeforePoint(new Point(x, y + 1), results) && (boards[x][y + 1] == 0 || boards[x][y + 1] == 3)) {
				index++;
				if(moveAndLastPoint(index, nowPoint, new Point(0, 1), results, boards))
					break;
			} else {
				boards[x][y] = 1;
				results[index] = new Point(0, 0);
				index--;
			}

			if(index == 0) { 
				System.out.println("경로 없음");
				return 0;
			}
		}
		return index;
	}
	
	public static void initBoards(File file, Point length, int[][] boards, Point[] results) throws IOException{
		FileReader fileReader = new FileReader(file);

		for(int i = 0; i < length.x; i++)
			for(int j = 0; j < length.y+ 2; j++) {
				int ch = fileReader.read();
				if(ch == '\r' || ch == '\n')
					continue;
				if(ch == -1)
					break;
				boards[i][j] = Character.getNumericValue(ch);
			}
		
		fileReader.close();
	}
	
	public static Point initResultsAndgetStartPoint(Point[] results, int[][] boards, Point length) {

		for (int i = 0; i < results.length; i++) 
			results[i] = new Point(0, 0);

		for (int i = 0; i < length.x; i++)
			for (int j = 0; j < length.y; j++) 
				if (boards[i][j] == 2)  
					return new Point(i, j);
		
		return new Point(0, 0);
	}
	
	public static Point calculateBoardLength(File file) throws IOException {
		int cur = 0;
		boolean isFirstLine = true;
		FileReader fileReader = new FileReader(file);

		int lengthX = 1;
		int lengthY = 0;
		while((cur = fileReader.read()) != -1) {
			if((char)cur == '\r') {
				lengthX++;
				isFirstLine = false;
			}
			if(isFirstLine)
				lengthY++;
		}
		fileReader.close();
		
		return new Point(lengthX, lengthY);
	}
	
	public static boolean moveAndLastPoint(int index, Point nowPoint, Point movePoint, Point[] results, int[][] boards) {
		results[index] = new Point(nowPoint.x + movePoint.x, nowPoint.y + movePoint.y); 

		int x = results[index].x;
		int y = results[index].y;
		if(boards[x][y] == 3)
			return true;
		return false;
	}
	
	public static boolean checkBeforePoint(Point pos, Point[] results) {
		for(int i = 0; i < results.length; i++) 
			if(results[i].equals(pos))
				return true;
		return false;
	}
	
	public static void printResults(int index, Point[] results) {
		for(int i = 0; i <= index; i++) 
			System.out.printf("%d (%d, %d)\n", i + 1, results[i].x + 1, results[i].y + 1);
	}
}
