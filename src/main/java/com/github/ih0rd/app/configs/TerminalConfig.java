//package com.github.ih0rd.app.configs;
//
//import org.jline.terminal.Terminal;
//import org.jline.terminal.TerminalBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.IOException;
//
//@Configuration
//public class TerminalConfig {
//    @Bean
//    public Terminal terminal() throws IOException {
//        try {
//            Terminal terminal = TerminalBuilder.builder()
//                    .jna(true)
//                    .jansi(true)
//                    .dumb(true)
//                    .build();
//            System.out.println("Terminal successfully created: " + terminal.getType());
//            return terminal;
//        } catch (Exception e) {
//            System.err.println("Failed to create terminal: " + e.getMessage());
//            throw e;
//        }
//    }
//}
