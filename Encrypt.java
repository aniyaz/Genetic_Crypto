import java.io.*;


class Encrypt{
	//Global variables
	static int size;
	public static void main(String[] args) throws IOException{
		String file1, file2;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter Input File Name: ");
		file1 = br.readLine();
		System.out.print("Enter Output File Name: ");
		file2 = br.readLine();
		//Step 1
		toBits(file1, "f1.txt");
		//Step2
		bitprime("f1.txt", "f2.txt");
		//Step3
		reverse("f2.txt","f3.txt", size);
		//Step4
		bitprime("f3.txt","f4.txt");
		XOR("f4.txt","f5.txt",size);
		//Step5
		DNA("f5.txt", file2);
	}

	
	public static void convert(int n, int bit[])
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
		size = i;
	}
	

	public static void toBits(String file1, String file2) throws IOException
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

	//Bitwise Complement operation at prime positions
	public static void bitprime(String file1, String file2) throws IOException 
	{
		FileInputStream fi = new FileInputStream(file1);
		FileOutputStream fo = new FileOutputStream(file2);
		int size = 0;
		int ch;
		while((ch=fi.read()) != -1)
		{
			size++;
			if(size == 1)
			{
				fo.write(ch);
			}
			else
				if(isPrime(size))
				{
					if(ch==48)
					{
						fo.write(49);
					}
					else
					{
						fo.write(48);
					}
				}
				else
				{
					fo.write(ch);
				}
		}
		fi.close();
		fo.close();
	}

	//Prime number check
	public static boolean isPrime(int num) 
	{
        if (num < 2)
         	return false;
        if (num == 2) 
        	return true;
        if (num % 2 == 0) 
        	return false;
        for (int i = 3; i * i <= num; i += 2)
            if (num % i == 0) 
            	return false;
        return true;
	}

	//Function to reverse the bit string
	public static void reverse(String file1, String file2,int n) throws IOException
	{
		FileInputStream fi = new FileInputStream(file1);
		FileOutputStream fo = new FileOutputStream(file2);
		String p = "";
		int ch;
		while((ch = fi.read()) != -1)
		{
			p = (char)ch + p;
		}
		for(int i = 0; i < p.length(); i++)
		{
			char c = p.charAt(i);
			fo.write(c);
		}
		fi.close();
		fo.close();
	}
     	// To perform XOR operation 
	public static void XOR(String file1, String file2,int n) throws IOException
	{
		int i,j,ch;
		FileInputStream fi =  new FileInputStream(file1);
		FileOutputStream fo = new FileOutputStream(file2);
		int a[] = new int[n];
		int arr[] = new int[n];
		for (int i1=0;i1<n;i1++)
		{
			if ((ch=fi.read())!=-1)
				arr[i1]=ch-48;
		}
		for (i=0,j=n-1;i<n/2 && j>=0;i++,j-- )
		 {
		    int	ch1=arr[j];
		    int ch2=arr[i];
		    if (ch1==0 && ch2==0 || ch1==1 && ch2==1)
		     {
		    	a[j]=0;
		    	a[i]=ch2;
		    }
		    if (ch1==1 && ch2==0 || ch1==0 && ch2==1)
		     {
		    	a[j]=1;
		    	a[i]=ch2;
		    }
		}
		for (i=0;i<n ; i++) 
		{
			fo.write(a[i]+48);
		}
		fi.close();
		fo.close();
	}

	//Step5: Convert in DNA Sequence
	public static void DNA(String file1, String file2) throws IOException
	{
		char bit[] = new char[8];
		int ch, i = 0;
		char s;
		FileInputStream fi = new FileInputStream(file1);
		FileOutputStream fo = new FileOutputStream(file2);
		while((ch = fi.read()) != -1)
		{
			
			bit[i] = (char)ch;
			i++;
		}
		for(i = 0; i < 8; i += 2)
		{
			if(bit[i] == '0' && bit[i+1] == '0')
				s = 'A';
			else if(bit[i] == '0' && bit[i+1] == '1')
				s = 'C';
			else if(bit[i] == '1' && bit[i+1] == '0')
				s = 'T';
			else
				s = 'G';
			fo.write((int)s);
		}
		fi.close();
		fo.close();

	}
}



