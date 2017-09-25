package day3_wait;

public class Drop {
	private String message;
	private boolean empty = true;

	// Empty=false 일때 동작
	public synchronized String take() {

		while (empty) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}

		empty = true;

		notifyAll();
		return message;
	}

	//Empty=true 일때 동작
	public synchronized void put(String message) {

		while (!empty) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		empty = false;
		this.message = message;
		notifyAll();
	}
}
