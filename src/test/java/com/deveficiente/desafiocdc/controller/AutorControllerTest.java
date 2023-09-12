package com.deveficiente.desafiocdc.controller;

import com.deveficiente.desafiocdc.domain.entity.Autor;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class AutorControllerTest {

    private final String ENDPOINT = "/api/cdc/autor";

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(){

    }

    @AfterEach
    public void tearDown(){

    }

    @DisplayName("Teste de cadastro de autor com dados válidos")
    @Test
    public void testCenario1() throws Exception {
        // Arrange & Act
        this.mockMvc.perform(
                post(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                "{\"nome\": \"Marcel Proust do SQL\", " +
                                "\"email\": \"marcel.proust.sql@cdc.com.br\", " +
                                "\"descricao\": \"Autor de consultas SQL refinadas\"}"
                        )
        )
                // Assert
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", containsString( ENDPOINT+ "/1")));
    }

    @DisplayName("Teste de cadastro de autor sem nome informa erro na requisição")
    @Test
    public void testCenario2() throws Exception {
        // Arrange & Act
        this.mockMvc.perform(
                        post(ENDPOINT)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                    "{\"email\": \"marcel.proust.sql@cdc.com.br\", " +
                                    "\"descricao\": \"Autor de consultas SQL refinadas\"}"
                                )
                )
                // Assert
                .andExpect(status().isBadRequest());
    }

    @DisplayName("Teste de cadastro de autor sem email informa erro na requisição")
    @Test
    public void testCenario3() throws Exception {
        // Arrange & Act
        this.mockMvc.perform(
                        post(ENDPOINT)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                "{\"nome\": \"Marcel Proust do SQL\", " +
                                "\"descricao\": \"Autor de consultas SQL refinadas\"}"
                                )
                )
                // Assert
                .andExpect(status().isBadRequest());
    }

    @DisplayName("Teste de cadastro de autor sem descrição informa erro na requisição")
    @Test
    public void testCenario4() throws Exception {
        // Arrange & Act
        this.mockMvc.perform(
                        post(ENDPOINT)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        "{\"nome\": \"Marcel Proust do SQL\", " +
                                        "\"email\": \"marcel.proust.sql@cdc.com.br\"}"
                                )
                )
                // Assert
                .andExpect(status().isBadRequest());
    }

    @DisplayName("Teste de cadastro de autor com nome em branco informa erro na requisição")
    @Test
    public void testCenario5() throws Exception {
        // Arrange & Act
        this.mockMvc.perform(
                        post(ENDPOINT)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        "{\"nome\": \"\", " +
                                                "\"email\": \"marcel.proust.sql@cdc.com.br\", " +
                                                "\"descricao\": \"Autor de consultas SQL refinadas\"}"
                                )
                )
                // Assert
                .andExpect(status().isBadRequest());
    }

    @DisplayName("Teste de cadastro de autor com email em branco informa erro na requisição")
    @Test
    public void testCenario6() throws Exception {
        // Arrange & Act
        this.mockMvc.perform(
                        post(ENDPOINT)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        "{\"nome\": \"Marcel Proust do SQL\", " +
                                                "\"email\": \"\", " +
                                                "\"descricao\": \"Autor de consultas SQL refinadas\"}"
                                )
                )
                // Assert
                .andExpect(status().isBadRequest());
    }

    @DisplayName("Teste de cadastro de autor com descrição em branco informa erro na requisição")
    @Test
    public void testCenario7() throws Exception {
        // Arrange & Act
        this.mockMvc.perform(
                        post(ENDPOINT)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        "{\"nome\": \"Marcel Proust do SQL\", " +
                                                "\"email\": \"marcel.proust.sql@cdc.com.br\", " +
                                                "\"descricao\": \"\"}"
                                )
                )
                // Assert
                .andExpect(status().isBadRequest());
    }

    @DisplayName("Teste de cadastro de autor com descrição com mais de 400 caracteres lança erro na requisição")
    @Test
    public void testCenario8() throws Exception {
        // Arrange & Act
        this.mockMvc.perform(
                        post(ENDPOINT)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        "{\"nome\": \"Marcel Proust do SQL\", " +
                                                "\"email\": \"marcel.proust.sql@cdc.com.br\", " +
                                                "\"descricao\":" + "A".repeat(401) + "}"
                                )
                )
                // Assert
                .andExpect(status().isBadRequest());
    }

    @DisplayName("Teste de cadastro de autor com email inválido informa erro na requisição")
    @ParameterizedTest
    @CsvSource({"marcel.proust@email", "marcel.proust.email"})
    public void testCenario9(String emailInvalido) throws Exception {
        // Arrange & Act
        this.mockMvc.perform(
                        post(ENDPOINT)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        "{\"nome\": \"Marcel Proust do SQL\", " +
                                                "\"email\": " + emailInvalido + ", " +
                                                "\"descricao\":" + "A".repeat(400) + "}"
                                )
                )
                // Assert
                .andExpect(status().isBadRequest());
    }

    @DisplayName("Teste de detalhamento de autor para id válido na API")
    @Test
    public void testCenario10() throws Exception {
        // Arrange
        this.mockMvc.perform(
                post(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                "{\"nome\": \"Marcel Proust do SQL\", " +
                                        "\"email\": \"marcel.proust.sql@cdc.com.br\", " +
                                        "\"descricao\": \"Autor de consultas SQL refinadas\"}"
                        )
        );

        // Act
        this.mockMvc.perform(
                get(ENDPOINT +"/1")
        )
        // Assert
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome",
                        Matchers.is("Marcel Proust do SQL")))
                .andExpect(jsonPath("$.email",
                        Matchers.is("marcel.proust.sql@cdc.com.br")))
                .andExpect(jsonPath("$.descricao",
                        Matchers.is("Autor de consultas SQL refinadas")))
        ;

    }

    @DisplayName("Teste de detalhamento de autor para id inexistente na API")
    @Test
    public void testCenario11() throws Exception {
        // Arrange & Act
        this.mockMvc.perform(
                get(ENDPOINT + "/1"))
            // Assert
                .andExpect(status().isNotFound());
    }

    @DisplayName("Teste de cadastro de autor com data de registro não armazena a info")
    @Test
    public void testCenario12() throws Exception {
        // Arrange & Act
        this.mockMvc.perform(
                        post(ENDPOINT)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        "{\"nome\": \"Marcel Proust do SQL\", " +
                                                "\"email\": \"marcel.proust.sql@cdc.com.br\", " +
                                                "\"registro\": \"2023-09-11T11:44:57.545529799\", " +
                                                "\"descricao\": \"Autor de consultas SQL refinadas\"}"
                                )
                )
                // Assert
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", containsString( ENDPOINT+ "/1")))
                .andExpect(jsonPath("$.nome",
                        Matchers.is("Marcel Proust do SQL")))
                .andExpect(jsonPath("$.registro",
                        Matchers.not("2023-09-11T11:44:57.545529799")))
        ;
    }


}