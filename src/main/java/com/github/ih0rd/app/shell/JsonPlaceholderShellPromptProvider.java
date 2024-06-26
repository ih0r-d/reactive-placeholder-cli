package com.github.ih0rd.app.shell;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class JsonPlaceholderShellPromptProvider implements PromptProvider {

    @Override
    public AttributedString getPrompt() {
        return new AttributedString("json-shell> ", AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
    }
}
