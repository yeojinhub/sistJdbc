package day0324.test;

/**
 * Singleton 패턴이 도입된 클래스
 */
public class Singleton {

	private static Singleton s;
	
//	1. 클래스 외부에서 직접 객체화 할 수 없도록 생성자를 private 으로 설정.
	private Singleton() {
		System.out.println("객체생성");
	} //Singleton
	
//	2. 객체명 없이 instance 를 반환하는 method 작성.
	public static Singleton getInstance() {

//		3. 객체가 없거나, 사용중에 객체가 소멸되었다면 객체 생성.
		if(s==null) {
			s=new Singleton();
		} //end if
		
		return s;
	} //Singleton getInstance
	
} //class
