public class RemoveWhiteSpace {
	public static void main(String[] args)
	{
		String str1 = "This   is    a   practice     question";
		String str2="";
		for(int i=0;i<str1.length();i++) {
			if((str1.charAt(i)==' ') && (str1.charAt(i+1)==' ') ){
				continue;
			}
			else {
				str2=str2+str1.charAt(i);
			}
		}
		System.out.println(str2);
	}
}
