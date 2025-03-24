package controller;

import data.Post;
import sys.Request;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostController {

    private Scanner sc = new Scanner(System.in);
    private int sequence = 0;
    private List<Post> postList = new ArrayList<>();

    public void requestHandler(Request request) {
        switch(request.getTarget()) {
            case "add" -> {

                System.out.println("게시물을 작성합니다.");
                System.out.println("제목 : ");
                String title = sc.nextLine().trim();

                System.out.println("내용 : ");
                String content = sc.nextLine().trim();

                Post post = new Post(++sequence, title, content);
                postList.add(post);

                System.out.println(post.getId() + "번 게시물 작성을 완료했습니다.");
            }
            case "view" -> {
                try {
                    System.out.println("게시물을 조회합니다.");
                    System.out.print("게시물 번호 : ");
                    String targetPostId = sc.nextLine().trim();
                    int targetId = Integer.parseInt(targetPostId);
                    Post findPost = postList.get(targetId - 1);

                    System.out.println("게시물 번호 : " + findPost.getId());
                    System.out.println("게시물 제목 : " + findPost.getTitle());
                    System.out.println("게시물 내용 : " + findPost.getContent());
                    System.out.println("게시물 생성일 : " + findPost.getCreateTime());
                } catch (NullPointerException e) {
                    System.out.println("해당 게시물은 존재하지 않습니다.");
                } catch (NumberFormatException e) {
                    System.out.println("게시물은 정수로 입력해주세요.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("게시물 번호를 확인해주세요.");
                } catch (Exception e) {
                    System.out.println("알 수 없는 오류가 발생했습니다.");
                    e.printStackTrace(); // 오류 상세정보
                }
            }
            case "edit" -> {
                try {
                    System.out.println("게시물을 수정합니다.");
                    System.out.print("게시물 번호 : ");
                    String targetPostId = sc.nextLine().trim();
                    int targetId = Integer.parseInt(targetPostId);
                    Post findPost = postList.get(targetId - 1);

                    System.out.print("제목 : ");
                    String title = sc.nextLine().trim();

                    System.out.println("내용 : ");
                    String content = sc.nextLine().trim();

                    findPost.setTitle(title);
                    findPost.setContent(content);
                    findPost.setUpdateTime(LocalDate.now());
                    System.out.println("게시물 수정이 완료되었습니다.");
                } catch (NullPointerException e) {
                    System.out.println("해당 게시물은 존재하지 않습니다.");
                } catch (NumberFormatException e) {
                    System.out.println("게시물은 정수로 입력해주세요.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("게시물 번호를 확인해주세요.");
                } catch (Exception e) {
                    System.out.println("알 수 없는 오류가 발생했습니다.");
                    e.printStackTrace(); // 오류 상세정보
                }
            }
            case "remove" -> {
                try {
                    System.out.println("게시물을 삭제합니다.");
                    System.out.println("게시물 번호 : ");
                    String targetPostId = sc.nextLine().trim();
                    int targetId = Integer.parseInt(targetPostId);

                    Post findPost = postList.get(targetId - 1);
                    if (findPost != null) {
                        postList.set(targetId - 1, null);
                        System.out.println("성공적으로 삭제되었습니다.");
                    }
                } catch (NullPointerException e) {
                    System.out.println("해당 게시물은 존재하지 않습니다.");
                } catch (NumberFormatException e) {
                    System.out.println("게시물은 정수로 입력해주세요.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("게시물 번호를 확인해주세요.");
                } catch (Exception e) {
                    System.out.println("알 수 없는 오류가 발생했습니다.");
                    e.printStackTrace(); // 오류 상세정보
                }

            }
        }
    }
}
