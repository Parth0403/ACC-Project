package res;

public class editdistance 
{
	public static Integer ed(String s1, String s2) 
	{
		int l1 = s1.length();
		int l2 = s2.length();

		int[][] arr = new int[l1 + 1][l2 + 1];

		for (int i = 0; i <= l1; i++) 
		{
			arr[i][0] = i;
		}

		for (int j = 0; j <= l2; j++) 
		{
			arr[0][j] = j;
		}

		// iterate though, and check last char
		for (int i = 0; i < l1; i++) {
			char c1 = s1.charAt(i);
			for (int j = 0; j < l2; j++) 
			{
				char c2 = s2.charAt(j);

				if (c1 == c2) 
					arr[i + 1][j + 1] = arr[i][j];
				
				else 
				{
					int r = arr[i][j] + 1;
					int in = arr[i][j + 1] + 1;
					int del = arr[i + 1][j] + 1;

					int min = r > in ? in : r;
					min = del > min ? min : del;
					arr[i + 1][j + 1] = min;
				}
			}
		}

		return arr[l1][l2];
	}
}
