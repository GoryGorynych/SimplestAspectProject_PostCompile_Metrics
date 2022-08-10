public class Robot {

    public void sayHello(long sleepTime) throws InterruptedException {
        System.out.println("Robot say hello");
        Thread.sleep(sleepTime);
    }

    public boolean sayBye() {
        System.out.println("Robot say bye");
        return true;
    }
}
