package sys;

import java.util.Scanner;

public class Application {

    private Scanner sc = new Scanner(System.in);
    private String domain;
    private boolean programStatus = true;

    public Application(String domain) {
        this.domain = domain;
    }

    public void run() {
        while(programStatus) {
            String line = "https://" + domain;

            System.out.print(line);
            String command = sc.nextLine().trim();

            if (command.equals(".exit")) {
                System.out.println("Application exited.");
                break;
            }

            Request request = new Request(command);
            if (!request.isValid()) {
                System.out.println("잘못된 형식의 입력입니다.");
                continue;
            }
            System.out.println("명령 통과!");

        }
    }
}
