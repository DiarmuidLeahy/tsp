public class testFile
{
  /**
  *Find the sum of all the primes below two million
  *
  **/
  public static void main (String [] args)
  {
    long sum=0;
		long start = System.currentTimeMillis();
		for(int i=2;i<2000000;i++)
		{
			if(isPrime(i))
			{
				sum+=i;
			}
		}
		System.out.println(System.currentTimeMillis()-start);
		System.out.println(sum);
	}
	public static boolean isPrime(int num)
	{
		if (num==2) return true;//2 is prime
		if (num % 2 == 0) return false;//discount all evens
		for (int i = 3; i * i <= num; i += 2)//starts at 3 and goes up in steps of 2 until the square root of num
		{
			if (num % i == 0) return false; //order n^2 checks
		}
		return true; //if the if statement wasn't triggered, the number is prime
	}
}
