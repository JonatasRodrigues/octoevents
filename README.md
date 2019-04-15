# octoevents

O WebHook recebe eventos do github e os salva em uma base de dados.

# Pré-requisitos
Kotlin 1.3.10

Javalin 2.8.0

Exposed 0.10.4

H2Database 1.4.197

koin 1.0.0

Khttp 0.1.0


# Instruções
Todos os comandos deverão ser executado via terminal.

Fazer o checkout via GitHub e na raiz do projeto executar o comando abaixo:

<b>mvn clean install</b>

Após realizar o build, executar o arquivo DockerFile

<b>docker build -t kotlin . && docker run kotlin</b>

 Em seguida ir até a pasta do ngrok e executar o comando <b>./ngrok http 8080</b>, copiar o endereço http gerado. Ir ao github, entrar em repositories, clicar em octoevents,
 settings, Webhooks, edit no webhook payload e colar o endereço gerado pelo nghok.

Para execução da gravação do evento, no github, criar issue no repositório octoevents


# Endpoints


Criar issues

POST - http://localhost:8080/

buscar issue

GET - http://localhost:8080/issues/{issue-number}/events
