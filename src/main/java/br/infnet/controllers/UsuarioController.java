package br.infnet.controllers;

import br.infnet.dto.UsuarioDTOInput;
import br.infnet.dto.UsuarioDTOOutput;
import br.infnet.services.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import static spark.Spark.*;

public class UsuarioController {
    private final UsuarioService usuarioService = new UsuarioService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void respostasRequisicoes() {

        get("/usuarios", (req, res) -> {
            res.type("application/json");
            res.status(200);
            return objectMapper.writeValueAsString(usuarioService.listarUsuarios());
        });

        get("/usuarios/:id", (req, res) -> {
            long id = Long.parseLong(req.params(":id"));
            UsuarioDTOOutput usuarioDTOOutput = usuarioService.buscarUsuario(id);

            if (usuarioDTOOutput != null) {
                res.type("application/json");
                res.status(200);
                return objectMapper.writeValueAsString(usuarioDTOOutput);
            } else {
                res.status(404);
                return "Usuário não encontrado";
            }
        });

        delete("/usuarios/:id", (req, res) -> {
            long id = Long.parseLong(req.params(":id"));
            usuarioService.excluirUsuario(id);
            res.status(204);
            return "";
        });

        post("/usuarios", (req, res) -> {
            UsuarioDTOInput usuarioDTOInput = objectMapper.readValue(req.body(), UsuarioDTOInput.class);
            usuarioService.inserirUsuario(usuarioDTOInput);
            res.status(201);
            return "Usuário adicionado com sucesso";
        });

        put("/usuarios", (req, res) -> {
            UsuarioDTOInput usuarioDTOInput = objectMapper.readValue(req.body(), UsuarioDTOInput.class);
            usuarioService.alterarUsuario(usuarioDTOInput);
            res.status(200);
            return "Usuário atualizado com sucesso";
        });
    }
}
