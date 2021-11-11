package HomeWork;

import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HomeWork {
	public static void main(String[] args) throws IOException {
		int lengthX = 1;
		int lengthY = 0;

		File file = new File("./src/HomeWork/input.txt");
		FileReader fileReader = new FileReader(file);
		
		int cur = 0;
		boolean isFirstLine = true;

		while((cur = fileReader.read()) != -1) {
			if((char)cur == '\r') {
				lengthX++;
				isFirstLine = false;
			}
			if(isFirstLine)
				lengthY++;
		}
		fileReader.close();
		
		fileReader = new FileReader(file);
		int[][] boards = new int[lengthX][lengthY];

		for(int i = 0; i < lengthX; i++)
			for(int j = 0; j < lengthY + 2; j++) {
				int ch = fileReader.read();
				if(ch == '\r' || ch == '\n')
					continue;
				if(ch == -1)
					break;
				boards[i][j] = Character.getNumericValue(ch);
			}
		
		fileReader.close();

		Point startPoint = new Point(0, 0);
		Point[] results = new Point[100];

		for (int i = 0; i < results.length; i++) 
			results[i] = new Point(0, 0);

		loop:
		for (int i = 0; i < lengthX; i++)
			for (int j = 0; j < lengthY; j++) 
				if (boards[i][j] == 2) { 
					startPoint = new Point(i, j);
					break loop;
				}
			
				
		results[0] = startPoint;
		int index = 0;

		while (true) {
			Point nowPoint = results[index];

			int x = nowPoint.x;
			int y = nowPoint.y;

			if (x - 1 >= 0 && !checkBeforePoint(new Point(x - 1, y), results) && (boards[x - 1][y] == 0 || boards[x - 1][y] == 3)) {
				index++;
				results[index] = new Point(x - 1, y);
				if(boards[x - 1][y] == 3)
					break;
			} else if (y - 1 >= 0 && !checkBeforePoint(new Point(x, y - 1), results) && (boards[x][y - 1] == 0 || boards[x][y - 1] == 3)) {
				index++;
				results[index] = new Point(x, y - 1);
				if(boards[x][y - 1] == 3)
					break;
			} else if (x + 1 < lengthX && !checkBeforePoint(new Point(x + 1, y), results) && (boards[x + 1][y] == 0 || boards[x + 1][y] == 3)) {
				index++;
				results[index] = new Point(x + 1, y);
				if(boards[x + 1][y] == 3)
					break;
			} else if (y + 1 < lengthY && !checkBeforePoint(new Point(x, y + 1), results) && (boards[x][y + 1] == 0 || boards[x][y + 1] == 3)) {
				index++;
				results[index] = new Point(x, y + 1);
				if(boards[x][y + 1] == 3)
					break;
			} else {
				boards[x][y] = 1;
				results[index] = new Point(0, 0);
				index--;
			}

			if(index == 0) { 
				System.out.println("경로 없음");
				return;
			}
		}

		for(int i = 0; i <= index; i++) 
			System.out.printf("%d (%d, %d)\n", i + 1, results[i].x + 1, results[i].y + 1);
	}
	
	public static boolean checkBeforePoint(Point pos, Point[] results) {
		for(int i = 0; i < results.length; i++) 
			if(results[i].equals(pos))
				return true;
		return false;
	}
}
