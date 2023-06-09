package com.gestionusuarios.dashboard.controllers;

//* sirve para manejar las url, por ejemplo si un usuario entra a alguna pagina web
//* le devuelva alguna cosa en especifico

import com.gestionusuarios.dashboard.dao.UsuarioDao;
import com.gestionusuarios.dashboard.models.Usuario;
import com.gestionusuarios.dashboard.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired// * sirve para conectar la clase usuarioDaoImp cree un objeto y la guarda en esta varabiale, y
    // * no hace falta crear tantos objetos de ese tipo
    private UsuarioDao usuarioDao;


    // * comentario de prueba para github

    @Autowired
    private JWTUtil jwtUtil;

    // * especificar la url a la que se entra para retornar esto
    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(@RequestHeader(value="Authorization") String token) {
        if (!validarToken(token)) { return null; }

        return usuarioDao.getUsuarios();
    }

    private boolean validarToken(String token) {
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario) {

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);

        usuarioDao.registrar(usuario);
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@RequestHeader(value="Authorization") String token,
                          @PathVariable Long id) {
        if (!validarToken(token)) { return; }
       usuarioDao.eliminar(id);
    }

}
