Desafio Casa do código
======================

<!-- TOC -->
* [Desafio Casa do código](#desafio-casa-do-código)
* [👓 Introdução](#-introdução)
* [🚀 Desafios](#-desafios)
    * [Cadastro de um novo autor](#cadastro-de-um-novo-autor)
* [🗓️ Resumo Desenvolvimento](#-resumo-desenvolvimento)
<!-- TOC -->

# 👓 Introdução

![Badge Java](https://img.shields.io/static/v1?label=Java&message=17&color=orange&style=for-the-badge&logo=java)

![framework_back](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![server_ci](https://img.shields.io/badge/Github%20Actions-282a2e?style=for-the-badge&logo=githubactions&logoColor=367cfe)

Desenvolveremos no repositório implementação para o primeiro desafio da jornada [Dev Eficiente](https://deveficiente.com/), relativo a implementação de loja de livros virtual.

Como *stack*, empregaremos Java e Spring Boot.

# 🚀 Desafios

### Cadastro de um novo autor

Implementaremos inicialmente endpoint para cadastro de novos autores no sistema. As informações pertinentes a um autor são: 
* [x] Nome (obrigatório)
* [x] Email (obrigatório, em formato válido)
* [x] Descrição (obrigatória, não pode passar de 400 caracteres)
* [x] Instante de registro (não nulo)

# 📖 APIs

Abaixo, descrevemos globalmente as APIs implementadas.

## APIS  de cadastro de autor

Nossa API Rest deve suportar a manutenção do cadastro de autores.

O enpdpoint será baseado em `/api/cdc/autor`.

Para o POST, o *body* de cada requisição deve informar JSON no seguinte formato:

```json
{
  "nome": "Marcel Proust do SQL",
  "email": "marcel.proust.sql@cdc.com.br",
  "descricao": "Autor de consultas SQL refinadas"
}
```
Em caso de sucesso, a aplicação deve informar a *location* do recurso criado e o corpo da resposta abaixo:

```json
{
  "nome": "Marcel Proust do SQL",
  "email": "marcel.proust.sql@cdc.com.br",
  "descricao": "Autor de consultas SQL refinadas",
  "registro": "2023-09-12T11:44:57.545529799"
}
```

Se falha nos dados passados pelos clientes, deve informar o erro.

# 🗓️ Resumo Desenvolvimento