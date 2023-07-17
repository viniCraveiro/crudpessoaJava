package craveiro.vinicius.crudpessoa.view.pessoa;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PessoaRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @SneakyThrows
    @Test
    void create() {
        mockMvc.perform(MockMvcRequestBuilders.post("/v0/pessoa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(PessoaDataProvider.pessoaJson2Contatos()))
                .andExpect(status().isCreated());
    }

    @SneakyThrows
    @Test
    void listAll() {
        mockMvc.perform(MockMvcRequestBuilders.get("/v0/pessoa"))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    //@Test
    void findOneById() {
        mockMvc.perform(MockMvcRequestBuilders.get("/v0/pessoa/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @SneakyThrows
    //@Test
    void update() {

        mockMvc.perform(MockMvcRequestBuilders.put("/v0/pessoa/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(PessoaDataProvider.pessoaJson2Contatos()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }


    @SneakyThrows
    @Test
    void delete() {
        mockMvc.perform(MockMvcRequestBuilders.delete("/v0/pessoa/1"))
                .andExpect(status().isNoContent());
    }
}