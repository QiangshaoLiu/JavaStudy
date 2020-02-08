import java.lang.*;

//学习多线程的使用，isAlive方法测试线程是否执行完毕，join方法用于等待子线程执行完毕，主线程应在所有子线程执行完毕后结束

class NewThread implements Runnable{
	String name;
        Thread t;

	NewThread(String threadname){
		name = threadname;
		t = new Thread(this,name);
		System.out.println("My new thread:"+t);
		t.start();
	}

	public void run(){
		try{
		 for(int i = 5; i > 0; i--){
			System.out.println(name + ":" + i);
			Thread.sleep(1000);
		   }
		}
		catch(InterruptedException e){
		   System.out.println(name + "interrupted.");
		}
		System.out.println(name + "exiting.");
}
}
class Demojoin{
	public static void main(String args[]){
		NewThread t1 = new NewThread("One");
		NewThread t2 = new NewThread("Two");
		NewThread t3 = new NewThread("Three");

		System.out.println("Thread one is alive:" + t1.t.isAlive());
		System.out.println("Thread two is alive:" + t2.t.isAlive());
  		System.out.println("Thread three is alive:" + t3.t.isAlive());
		
		try{
			System.out.println("Waiting for threads to finish.");
			t1.t.join();
			t2.t.join();
			t3.t.join();
		} catch (InterruptedException e) {
			System.out.println("Main thread interrupted.");
		}

		System.out.println("Thread one is alive:" + t1.t.isAlive());
		System.out.println("Thread two is alive:" + t2.t.isAlive());
		System.out.println("Thread three is alive:" + t3.t.isAlive());
		
		System.out.println("Main thread exiting.");
}
}




