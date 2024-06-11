package com.github.ih0rd.app.shell;

import com.github.ih0rd.app.clients.JsonPlaceholderApiClient;
import com.github.ih0rd.app.models.Post;
import com.github.ih0rd.app.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import static com.github.ih0rd.app.utils.JsonFormatter.subscribe;

@ShellComponent
@RequiredArgsConstructor
public class JsonPlaceholderShellCommand {
    private final JsonPlaceholderApiClient jsonPlaceholderApiClient;


    @ShellMethod("Get users list.")
    public void getAllUsers() {
        subscribe(jsonPlaceholderApiClient.getUsers(), User.class);
    }


    @ShellMethod("Get all user's posts")
    public void getUserPosts(int userId) {
        subscribe(jsonPlaceholderApiClient.getUserPosts(userId), Post.class);
    }

//    @ShellMethod("Create book")
//    public void createBook(String title, String author, Integer year) {
//        bookApiClient.createBook(new CreateBookRequest(title, author, year))
//                .doOnError(ex -> System.out.println(ex.getMessage()))
//                .onErrorResume(ex -> Mono.empty())
//                .subscribe(responseEntity ->
//                        System.out.printf(FORMAT, responseEntity.getStatusCode(), toJson(responseEntity.getBody()))
//                );
//    }
//
//    @ShellMethod("Update book")
//    public void updateBook(String id,
//                           @ShellOption(defaultValue = ShellOption.NULL) String title,
//                           @ShellOption(defaultValue = ShellOption.NULL) String author,
//                           @ShellOption(defaultValue = ShellOption.NULL) Integer year) {
//        UpdateBookRequest updateBookRequest = new UpdateBookRequest(title, author, year);
//        bookApiClient.updateBook(id, updateBookRequest)
//                .doOnError(ex -> System.out.println(ex.getMessage()))
//                .onErrorResume(ex -> Mono.empty())
//                .subscribe(responseEntity ->
//                        System.out.printf(FORMAT, responseEntity.getStatusCode(), toJson(responseEntity.getBody()))
//                );
//    }
//
//    @ShellMethod("Delete book")
//    public void deleteBook(String id) {
//        bookApiClient.deleteBook(id)
//                .doOnError(ex -> System.out.println(ex.getMessage()))
//                .onErrorResume(ex -> Mono.empty())
//                .subscribe(responseEntity ->
//                        System.out.printf(FORMAT, responseEntity.getStatusCode(), "Book deleted")
//                );
//    }


}
