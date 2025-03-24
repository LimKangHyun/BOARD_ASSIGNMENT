package sys;

import controller.PostController;

import java.util.Scanner;

public class Application {

    private Scanner sc = new Scanner(System.in);
    private String domain;
    private boolean programStatus = true;

    public Application(String domain) {
        this.domain = domain;
    }

    public void run() {

        PostController postController = new PostController();

        while(programStatus) {

            String line = "https://" + domain;

            System.out.print(line);
            String command = sc.nextLine().trim();

            if (command.equals(".exit")) {
                System.out.println("Application exited.");
                break;
            }

            Request request = new Request("/post" + command);

            if (!request.isValid()) {
                System.out.println("잘못된 형식의 입력입니다.");
                System.out.println("입력된 URI: " + command); // 디버깅
                System.out.println("target: " + request.getTarget()); // 여기가 "add"여야 함
                continue;
            }
            System.out.println("명령 통과!");

            // Request 객체 생성 및 PostController로 전달
            postController.requestHandler(request);


        }
    }
}
