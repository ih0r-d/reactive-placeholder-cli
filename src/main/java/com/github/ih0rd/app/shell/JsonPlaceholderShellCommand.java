package com.github.ih0rd.app.shell;

import com.github.ih0rd.app.clients.JsonPlaceholderApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.context.InteractionMode;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import static com.github.ih0rd.app.utils.OutputFormatter.subscribe;

@ShellComponent
@RequiredArgsConstructor
@Command
public class JsonPlaceholderShellCommand {
    private final JsonPlaceholderApiClient jsonPlaceholderApiClient;


    @ShellMethod(value = "Get users list.", key = "users")
    public void getAllUsers() {
        subscribe(jsonPlaceholderApiClient.getUsers());
    }


    @ShellMethod(value = "Get user's posts", key = "user-posts")
    public void getUserPosts(@ShellOption int userId) {
        subscribe(jsonPlaceholderApiClient.getUserPosts(userId));
    }

    @ShellMethod(value = "Get  user's albums", key = "user-albums")
    public void getUserAlbums(@ShellOption int userId) {
        subscribe(jsonPlaceholderApiClient.getUserAlbums(userId));
    }

    @ShellMethod(value = "Get user's todos", key = "user-todos")
    public void getUserTodos(@ShellOption int userId) {
        subscribe(jsonPlaceholderApiClient.getUserToDos(userId));
    }

    @ShellMethod(value = "Get album's photos", key = "albums-photos")
    public void getAlbumsPhotos(@ShellOption int albumId) {
        subscribe(jsonPlaceholderApiClient.getAlbumPhotos(albumId));
    }

}
