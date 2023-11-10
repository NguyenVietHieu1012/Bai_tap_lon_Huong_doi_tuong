package controller;

public class Main {
    MainFrame frame;
    public Main(){
        frame = new MainFrame();
        class Time extends Thread{
            public void run(){
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    if (frame.getPause()){
                        if(frame.getResume()){
                            frame.time --;
                        }
                    }
                    else {
                        frame.time --;
                    }
                    if(frame.time == 0){
                        frame.showDialogNewGame("Hết giờ\n Bạn có muốn chơi lại hay ko?","Lose",1);
                    }
                }
            }
        }
        Time time = new Time();
        time.start();
        new Thread(frame).start();
    }

    public static void main(String[] args){
        new Main();
    }
}
