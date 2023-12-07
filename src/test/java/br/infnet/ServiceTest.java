package br.infnet;

import br.infnet.dto.UsuarioDTOInput;
import br.infnet.services.UsuarioService;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class ServiceTest {
    @Test
    public void testInsercaoEListagemUsuarios() {
        UsuarioService usuarioService = new UsuarioService();
        UsuarioDTOInput usuarioDTOInput = new UsuarioDTOInput();
        usuarioDTOInput.setId(1);
        usuarioDTOInput.setNome("Raphael");
        usuarioDTOInput.setSenha("123");

        usuarioService.inserirUsuario(usuarioDTOInput);

        int tamanhoLista = usuarioService.listarUsuarios().size();

        assertEquals(1, tamanhoLista);
    }

    @Test
    public void testListagemUsuariosApi() throws Exception {
        String apiUrl = "http://localhost:4567/usuarios";

        URL url = new URL(apiUrl);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        assertEquals(200, responseCode);

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();

        connection.disconnect();
    }
}
