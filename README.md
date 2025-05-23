
# 🔗 Encurtador de Links Feito em Java-SpringBoot com Traefik, Docker e Métricas

### Este é um sistema completo de encurtamento de URLs com redirecionamento dinâmico, gerenciamento de links e coleta de métricas de acesso. Utiliza Java-SpringBoot com arquitetura Restfull mais Docker, Docker Compose e Traefik como reverse proxy.

## 🚀 Funcionalidades
### Gerar URLs curtas a partir de links longos

### Redirecionar automaticamente para o link original usando o short_code

### Gerenciar links criados via API REST (listar)

### Coletar métricas como:

#### Número de cliques

### Roteamento dinâmico usando Traefik sem a necessidade de rotas explícitas

## 🧠 Tecnologias Utilizadas
### Backend: Java-Spring FrameWork(Boot, Web, Data Jpa)

### Banco de Dados: Produção (MySQL) & Para Testes (H2 DataBase)

### Reverse Proxy: Traefik

### Containers: Docker e Docker Compose

## ⚙️ Arquitetura
### Diagrama Simplificado

![Fluxo do Encurtador de Links](docs/fluxo-encurtador-links.png)

## 📦 Instalação e Execução
### Pré-requisitos
### Docker
### Docker Compose

## 📄 Endpoints da API
### 1. Criar Link Curto
### POST /encurtador-links/shorten

### Request:

### json Copiar Editar
#### {
####   &nbsp;&nbsp;"url": "https://exemplo.com/minha-url"
#### }

### Response:

### json
#### {
####   &nbsp;&nbsp;"short_code": "https://short.local/ADijsaEjsTPoajk63809",
####   &nbsp;&nbsp;"original_url": "https://exemplo.com/minha-url"
#### }

### 2. Redirecionar Link
### GET /{short_code}

### Redireciona automaticamente para a URL original (302 Found)

### 3. Listar Links
### GET /encurtador-links/links

### Response:

### json
#### [
####   &nbsp; &nbsp;{
####   &nbsp;&nbsp;&nbsp;  "shortCode": "https://short.local/ADijsaEjsTPoajk63809",
####   &nbsp;&nbsp;&nbsp;  "originalUrl": "https://exemplo.com",
####   &nbsp;&nbsp;&nbsp;  "clicks": 42
####   &nbsp;&nbsp;},
####   &nbsp; &nbsp;{
####   &nbsp;&nbsp;&nbsp;  "shortCode": "https://short.local/jToJsaEjsFpHijk53270",
####   &nbsp;&nbsp;&nbsp;  "originalUrl": "https://exemplo2.com",
####   &nbsp;&nbsp;&nbsp;  "clicks": 16
####   &nbsp;&nbsp;}
#### ]

## 📊 Métricas Coletadas
### Total de cliques por link

## 🛠️ Configuração do Traefik
### Traefik é configurado via traefik.yml e labels no docker-compose.yml para permitir o roteamento dinâmico baseado no short_url.

## ✅ Testes

### Este projeto possui testes unitários escritos com **JUnit 5** e **Mockito**.

## 👨‍💻 **Autor**

**Weslei Alves Dos Santos**

**Desenvolvedor Backend**

[LinkedIn](https://www.linkedin.com/in/weslei-alves-dos-santos-b2581633b/) – [GitHub](https://github.com/WesleiKhan)
