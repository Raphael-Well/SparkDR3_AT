package br.infnet.services;


import br.infnet.dto.UsuarioDTOInput;
import br.infnet.dto.UsuarioDTOOutput;
import br.infnet.model.Usuario;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UsuarioService {
    private final List<Usuario> listaUsuarios = new ArrayList<>();
    private final ModelMapper modelMapper = new ModelMapper();

    public List<UsuarioDTOOutput> listarUsuarios() {
        List<UsuarioDTOOutput> listaUsuarioDTOOutputs = new ArrayList<>();
        for (Usuario usuario : listaUsuarios) {
            listaUsuarioDTOOutputs.add(modelMapper.map(usuario, UsuarioDTOOutput.class));
        }
        return listaUsuarioDTOOutputs;
    }

    public void inserirUsuario(UsuarioDTOInput usuarioDTOInput) {
        Usuario usuario = modelMapper.map(usuarioDTOInput, Usuario.class);
        listaUsuarios.add(usuario);
    }

    public void alterarUsuario(UsuarioDTOInput usuarioDTOInput) {
        Usuario usuario = modelMapper.map(usuarioDTOInput, Usuario.class);
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getId() == usuario.getId()) {
                listaUsuarios.set(i, usuario);
                break;
            }
        }
    }

    public UsuarioDTOOutput buscarUsuario(long id) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getId() == id) {
                return modelMapper.map(usuario, UsuarioDTOOutput.class);
            }
        }
        return null;
    }

    public void excluirUsuario(long id) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getId() == id) {
                listaUsuarios.remove(usuario);
                break;
            }
        }
    }
}
