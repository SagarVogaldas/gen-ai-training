# gen-ai-training

## Overview
`gen-ai-training` is a Spring Boot application that provides an API for generating AI-based responses. The application uses the Azure OpenAI service to process user inputs and generate responses.

## Prerequisites
- Java 11 or higher
- Maven 3.6.3 or higher
- An Azure OpenAI API key

## Configuration
Configure the application by setting the following properties in `src/main/resources/config/application.properties`:


# Application configuration
application-name: gen_ai_training
spring:
main:
web-application-type: none

semantic-kernel.api-key: your_api_key_here
semantic-kernel.endpoint: https://your_endpoint_here
semantic-kernel.deployment-name: your_deployment_name_here


## Building the Application

To build the application, run the following command:

mvn clean install

## Running the Application

To run the application, run the following command:
mvn spring-boot:run

## API Endpoints
The application exposes the following API endpoints:

POST /api/chat/ask
This endpoint accepts a user input and returns an AI-generated response.

Request

curl --location 'http://localhost:8080/api/chat/ask' \
--header 'Content-Type: application/json' \
--data '{ "input": "your_input_here" }'



Response

[
{"type": "USER", "contents": "your_input_here"},
{"type": "ASSISTANT", "contents": "ai_generated_response_here"}
]


curl --location 'http://localhost:8080/api/chat/ask' \
--header 'Content-Type: application/json' \
--data '{ "input": "list top 10 books related to history" }'

[
{"type": "USER", "contents": "list top 10 books related to history"},
{"type": "ASSISTANT", "contents": "1. \"Guns, Germs, and Steel: The Fates of Human Societies\" by Jared Diamond (1997) - This book offers a groundbreaking analysis of the factors that led to the rise and fall of different societies across the globe.\n\n2. \"Sapiens: A Brief History of Humankind\" by Yuval Noah Harari (2011) - This best-selling book synthesizes a vast amount of historical research to present a big-picture overview of human history from the emergence of Homo sapiens to the present day.\n\n3. \"A People's History of the United States\" by Howard Zinn (1980) - This influential work provides a bottom-up perspective on American history, focusing on the experiences and struggles of ordinary people rather than the actions and decisions of political elites.\n\n4. \"The Silk Roads: A New History of the World\" by Peter Frankopan (2015) - This engaging book reorients the history of the world around the crucial role of the Silk Road, which connected Asia, Europe, and Africa over the course of two millennia.\n\n5. \"The Making of the Atomic Bomb\" by Richard Rhodes (1986) - This Pulitzer Prize-winning account narrates the scientific, political, and military events that led to the development of the first atomic bomb during World War II.\n\n6. \"The Rise and Fall of the Third Reich: A History of Nazi Germany\" by William L. Shirer (1960) - This comprehensive and meticulously researched volume provides an invaluable understanding of the rise and fall of Nazi Germany.\n\n7. \"The Origins of the Second World War\" by A.J.P. Taylor (1961) - This influential study challenges the traditional explanations for the outbreak of World War II and offers a fresh perspective on the causes and motivations for the war.\n\n8. \"The Cold War: A New History\" by John Lewis Gaddis (2005) - This book presents a balanced and accessible account of the events, individuals, and decisions that shaped the Cold War, from its origins to its end.\n\n9. \"1491: New Revelations of the Americas Before Columbus\" by Charles C. Mann (2005) - This groundbreaking work challenges our understanding of the Americas before European contact, revealing the complex and sophisticated civilizations that thrived in North and South America.\n\n10. \"The Fall of the Roman Empire: A New History of Rome and the Barbarians\" by Peter Heather (2005) - This book offers a fresh analysis of the causes and consequences of the fall of the Roman Empire, incorporating recent archaeological and historical research."}
]