package day3_immutable;
class Thread4 extends Thread{
	private SynchronizedRGB rgb;
	
	public Thread4(SynchronizedRGB rgb){
		this.rgb = rgb;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		rgb.set(255, 0, 0, "red");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("rgb:"+rgb.getRGB()+"/"+rgb.getName());
	}
	
}
class Thread5 extends Thread{
	private SynchronizedRGB rgb;
	
	public Thread5(SynchronizedRGB rgb){
		this.rgb = rgb;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		rgb.set(0, 255, 0, "green");
	}
	
}
public class RGBMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SynchronizedRGB rgb = new SynchronizedRGB(0,0,0,"black");
		new Thread4(rgb).start();
		new Thread5(rgb).start();
	}

}
