package day0324.test;

/**
 * Singleton 패턴이 도입된 클래스를 사용하는 클래스
 */
public class UseSingleton {

	public static void main(String[] args) {
//		생성자가 private 이므로 외부에서 객체화 불가.
//		Singleton s = new Singleton();
//		Singleton s2 = new Singleton();

		Singleton s=Singleton.getInstance();
		Singleton s2=Singleton.getInstance();
		
		System.out.println(s);
		System.out.println(s2);
		
	} //main

} //class
