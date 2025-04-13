package com.example.SpringOllama;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ollama")
@CrossOrigin("*")
public class OllamaController {

    private ChatClient chatClient;

    public OllamaController(OllamaChatModel chatModel){
        this.chatClient = ChatClient.create(chatModel);
    }

    @GetMapping("/{message}")
    public ResponseEntity<String> getAnswer(@PathVariable String message) {
        try {
            String response = chatClient
                    .prompt(message)
                    .call()
                    .content();

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Handle error in a user-friendly manner
            return ResponseEntity.status(500).body("Error processing your request: " + e.getMessage());
        }
    }
}
