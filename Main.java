import java.io.*;
class Main
{
	public static void main(String[] args) throws IOException
	{
		String file1, file2;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter Input File Name: ");
		file1 = br.readLine();
		System.out.print("Enter Output File Name: ");
		file2 = br.readLine();
		BitConversion BC = new BitConversion();
		BC.toBits(file1, file2);
	}
}
