package HomeWork;

import java.awt.Point;

public class HomeWork {
	public static void main(String[] args) {
		int[][] boards = {  { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, 
						 	{ 2, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1 },
						 	{ 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1 }, 
							{ 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1 },
							{ 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1 }, 
							{ 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1 },
							{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1 } };

		Point startPoint = new Point(0, 0);
		Point[] results = new Point[100];

		for (int i = 0; i < results.length; i++) 
			results[i] = new Point(0, 0);

		int lenthX = boards.length;
		int lenthY = boards[0].length;

		for (int i = 0; i < lenthX; i++)
			for (int j = 0; j < lenthY; j++) 
				if (boards[i][j] == 2) {
					startPoint = new Point(i, j);
					break;
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
			} else if (x + 1 < lenthX && !checkBeforePoint(new Point(x + 1, y), results) && (boards[x + 1][y] == 0 || boards[x + 1][y] == 3)) {
				index++;
				results[index] = new Point(x + 1, y);
				if(boards[x + 1][y] == 3)
					break;
			} else if (y + 1 < lenthY && !checkBeforePoint(new Point(x, y + 1), results) && (boards[x][y + 1] == 0 || boards[x][y + 1] == 3)) {
				index++;
				results[index] = new Point(x, y + 1);
				if(boards[x][y + 1] == 3)
					break;
			} else {
				boards[x][y] = 1;
				results[index] = new Point(0, 0);
				index--;
			}
		}
		
		for(int i = 0; i <= index; i++) {
			System.out.printf("%d (%d, %d)\n", i + 1, results[i].x + 1, results[i].y + 1);
		}
	}
	
	public static boolean checkBeforePoint(Point pos, Point[] results) {
		for(int i = 0; i < results.length; i++) 
			if(results[i].equals(pos))
				return true;
		return false;
	}
}
