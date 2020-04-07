import java.util.Scanner;

public class GaussianElimination 
{
	private static int i;
	public static void main(String[] args)
	{
		System.out.println("Enter Number of Variables:");
		Scanner scan = new Scanner(System.in);
		i = scan.nextInt();
		float [][] augmentedMatrix = new float[i][i+1];
		augmentedMatrix = getAugmentedMatrix(scan);
		System.out.println("Augmented Matrix:");
		printAugmentedMatrix(augmentedMatrix);
		augmentedMatrix = applyGaussianElimination(augmentedMatrix);
		System.out.println("After Applying Gaussian Elimination:");
		printAugmentedMatrix(augmentedMatrix);
		printSolution(augmentedMatrix);
	}//main()
	
	
	private static float[][] getAugmentedMatrix(Scanner scan)
	{
		float [][] augmentedMatrix = new float[i][i+1];
		System.out.println("Fill Augmeneted Matrix, Press enter after each element:");
		
		for(int j=0; j<i; j++)
		{
			System.out.println("Enter Elements of ROW# " + (j+1) + ":");
			for(int k=0; k<=i; k++)
			{
				float x = scan.nextFloat();
				augmentedMatrix[j][k] = x;
			}//for
		}//for
		scan.close();
		return augmentedMatrix;
	}//getAugmentedMatrix()
	
	
	private static void printAugmentedMatrix(float [][] augmentedMatrix)
	{	
		for(int j=0; j<i; j++)
		{
			System.out.print("[");
			for(int k=0; k<=i; k++)
			{
				if(k==i)
					System.out.print("|");
				System.out.print(" " + augmentedMatrix[j][k] + " ");
			}//for
			System.out.println("]");
		}//for
	}//printAugmentedMatrix
	
	
	private static float[][] applyGaussianElimination(float [][] augmentedMatrix)
	{
		int b=0;
		float pivot;
			
		while(b<i)
		{
			pivot = augmentedMatrix[b][b];
			augmentedMatrix = echelon(augmentedMatrix, pivot, b+1);
			b++;
		}//while
		
		b=i-1;
		while(b>=0)
		{
			pivot = augmentedMatrix[b][b];
			augmentedMatrix = reducedEchelon(augmentedMatrix, pivot, b-1);
			b--;
		}//while
		
		b=0;
		while(b<i)
		{
			int x=0;
			while(x<=i)
			{
				augmentedMatrix[b][i-x] /= augmentedMatrix[b][b];
				x++;
			}
			b++;
		}//while
		
		return augmentedMatrix;
	}//applyGaussianElimination()
	
	
	private static float [][] echelon(float [][] augmentedMatrix, float pivot, int a)
	{
		for(int l=a; l<i; l++)
		{
			for(int m=0; m<=i; m++)
			{
				augmentedMatrix [l][i-m] = (pivot * augmentedMatrix [l][i-m]) - (augmentedMatrix[l][a-1] * augmentedMatrix[a-1][i-m]);
			}//for
		}//for
		return augmentedMatrix;
	}//echelon()
	
	
	private static float [][] reducedEchelon(float [][] augmentedMatrix, float pivot, int a)
	{
		for(int l=a; l>=0; l--)
		{
			for(int m=0; m<=i; m++)
			{
				augmentedMatrix [l][i-m] = (pivot * augmentedMatrix [l][i-m]) - (augmentedMatrix[l][a+1] * augmentedMatrix[a+1][i-m]);
			}//for
		}//for
		return augmentedMatrix;
	}//reducedEchelon()
	
	
	private static void printSolution(float [][] augmentedMatrix)
	{
		int a=0;
		while(a<i)
		{
			System.out.println("var " + (a+1) + ": " + augmentedMatrix[a][i]);
			a++;
		}
	}//printSolution()

}//GaussianElimination
