package step1;
import java.io.*;
public class BitConversion
{
	
	public void convert(int n, int bit[])
	{
		int mask;
		mask = 128;
		int i = 0, k;
		while(mask != 0)
		{
			if((n & mask) == 0)
				k = 0;
			else
				k = 1;
			bit[i++] = k;
			mask = mask >> 1;
		}
	}
	public void toBits(String file1, String file2) throws IOException
	{
		int ch, i;
		int bit[] = new int[8];
		FileInputStream fp1 = new FileInputStream(file1);
		FileOutputStream fp2 = new FileOutputStream(file2);
		while((ch = fp1.read()) != -1)
		{
			convert(ch, bit);
			for(i = 0; i < 8; i++)
				fp2.write(bit[i] + 48);
		}
		fp1.close();
		fp2.close();
	}
}
