package com.epam.training.gen.ai.service;

import com.microsoft.semantickernel.Kernel;
import com.microsoft.semantickernel.semanticfunctions.KernelFunction;
import com.microsoft.semantickernel.semanticfunctions.KernelFunctionArguments;
import com.microsoft.semantickernel.services.chatcompletion.ChatHistory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ChatService {

    private final Kernel kernel;

    public ChatService(Kernel kernel) {
        this.kernel = kernel;
    }

   /* public KernalChatHistory(Kernel kernel) {
        this.kernel = kernel;
    }*/

    public ChatHistory processWithHistory(String prompt) {

        var chatHistory = new ChatHistory();

        /*var response = kernel.invokeAsync(getChat())
                .withArguments(getKernelFunctionArguments(prompt, chatHistory))
                .block();*/

        // After
        var chatFunction = getChat();
        var response = chatFunction.invokeAsync(kernel)
                .withArguments(getKernelFunctionArguments(prompt, chatHistory))
                .block();

        chatHistory.addUserMessage(prompt);
        chatHistory.addAssistantMessage(response.getResult());
        log.info("AI answer:" + response.getResult());
        return chatHistory;
    }

    /**
     * Creates a kernel function for generating a chat response using a predefined prompt template.
     * <p>
     * The template includes the chat history and the user's message as variables.
     *
     * @return a {@link KernelFunction} for handling chat-based AI interactions
     */
    private KernelFunction<String> getChat() {
        return KernelFunction.<String>createFromPrompt("""
                        {{$chatHistory}}
                        <message role="user">{{$request}}</message>""")
                .build();
    }

    /**
     * Creates the kernel function arguments with the user prompt and chat history.
     *
     * @param prompt the user's input
     * @param chatHistory the current chat history
     * @return a {@link KernelFunctionArguments} instance containing the variables for the AI model
     */
    private KernelFunctionArguments getKernelFunctionArguments(String prompt, ChatHistory chatHistory) {
        return KernelFunctionArguments.builder()
                .withVariable("request", prompt)
                .withVariable("chatHistory", chatHistory)
                .build();
    }
}
