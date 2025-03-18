package com.epam.training.gen.ai.config;

import com.azure.ai.openai.OpenAIAsyncClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.core.credential.AzureKeyCredential;
import com.microsoft.semantickernel.Kernel;
import com.microsoft.semantickernel.aiservices.openai.chatcompletion.OpenAIChatCompletion;
import com.microsoft.semantickernel.services.chatcompletion.ChatCompletionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KernelConfig {

    @Value("${semantic-kernel.api-key}")
    private String azureOpenAiKey;

    @Value("${semantic-kernel.endpoint}")
    private String azureOpenAiEndpoint;

    @Value("${semantic-kernel.deployment-name}")
    private String azureOpenAiDeploymentName;


    @Bean
    public OpenAIAsyncClient openAIAsyncClient() {
        return new OpenAIClientBuilder()
                .credential(new AzureKeyCredential(azureOpenAiKey))
                .endpoint(azureOpenAiEndpoint)
                .buildAsyncClient();
    }

    @Bean
    public ChatCompletionService openAIChatCompletion(OpenAIAsyncClient client) {
        return OpenAIChatCompletion.builder()
                .withOpenAIAsyncClient(client)
                .withModelId(azureOpenAiDeploymentName)
                .build();
    }

    @Bean
    public Kernel semanticKernel(ChatCompletionService chatCompletionService) {
        return Kernel.builder()
                .withAIService(ChatCompletionService.class, chatCompletionService)
                .build();
    }
}
