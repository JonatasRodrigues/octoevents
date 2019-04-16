# octoevents

O WebHook recebe eventos do github e os salva em uma base de dados.

# Pré-requisitos
Kotlin 1.3.10

Javalin 2.8.0

Exposed 0.10.4

H2Database 1.4.197

koin 1.0.0

Khttp 0.1.0

Docker 18.09.5


# Instruções
Todos os comandos deverão ser executado via terminal.

Fazer o checkout via GitHub e na raiz do projeto executar o comando abaixo:

<b>mvn clean compile install</b>

Após realizar o build, executar o arquivo DockerFile

<b>docker build -t kotlin . && docker run kotlin</b>

Criar uma network

<b>docker network create myngroknet</b>

Executar o container www baseado no image kotlin gerada acima e associá-la com a network

<b>docker run -d -p 8080 --net myngroknet --name www kotlin</b>

Executar o container do ngrok e associar a network para permitir que o ngrok acesse o container www

<b>docker run -d -p 4040:4040 --net myngroknet --name ngrok wernight/ngrok ngrok http www:8080</b>

Após alguns segundos, executar o comando abaixo para obter a public_url

<b>curl $(docker port ngrok 4040)/api/tunnels</b>

 Em seguida, <b>copiar a public_url</b>, acessar o github, entrar em repositories, clicar em octoevents,
 settings, Webhooks, edit no webhook payload e colar o endereço gerado pelo nghok.

Para execução da gravação do evento, no github, criar issue no repositório octoevents


# Endpoints


<b>Criar issues</b>

POST - http://localhost:8080/

<b>buscar issue</b>

GET - http://localhost:8080/issues/{issue-number}/events
