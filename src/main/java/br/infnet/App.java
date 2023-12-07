package br.infnet;

import br.infnet.controllers.UsuarioController;

public class App 
{
    public static void main( String[] args )
    {
        UsuarioController usuarioController = new UsuarioController();
        usuarioController.respostasRequisicoes();
    }
}
