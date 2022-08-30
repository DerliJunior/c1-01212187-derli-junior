package avaliacao.continuada.c101212187derlirodriguesjesusjunior;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    List<Usuario> listaUsuarios = new ArrayList<>();

    @PostMapping
    public Usuario cadastraUsuario(@RequestBody Usuario usuario) {
        listaUsuarios.add(usuario);

        return usuario;
    }

    @PostMapping("/autenticacao/{usuario}/{senha}")
    public Usuario autenticarUsuario(@PathVariable String usuario,
                                     @PathVariable String senha) {
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getUsuario().equals(usuario) &&
                    listaUsuarios.get(i).getSenha().equals(senha)) {
                listaUsuarios.get(i).setAutenticado(true);
                return listaUsuarios.get(i);
            }
        }

        return null;
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return listaUsuarios;
    }

    @DeleteMapping("/autenticacao/{usuario}")
    public String logoffUsuario(@PathVariable String usuario) {
        for (Usuario user : listaUsuarios) {
            if (user.getUsuario().equals(usuario)) {
                if (user.isAutenticado()) {
                    user.setAutenticado(false);
                    return String.format("Logoff do usuário %s concluído", user.getNome());
                } else {
                    return String.format("Usuário %s NÃO está autenticado", user.getNome());
                }
            }
        }
        return String.format("Usuario %s não encontrado", usuario);
    }

    /*
        Esse método atualiza a senha do usuário pelo indice, recebendo a nova senha pelo body
        da requisição.
        1) Caso o usuário pelo indice não seja encontrado, retorna mensagem que usuário não existe.
        2) Verifica se o usuário que está tentando alterar a senha está logado, caso não esteja retorna
        mensagem de 'Usuário não autenticado'
        3) Pede para verificar a senha atual, caso o usuário passe a senha incorreta, ele não consiguirar
        fazer alteração da senha
        4) Caso nenhuma das 3 condições acima, a senha do usuário será alterada
    */
    @PatchMapping("editar/{indice}/{senhaAtual}")
    public String alterarSenhaUsuario(@PathVariable int indice,
                                      @PathVariable String senhaAtual,
                                      @RequestBody String novaSenha) {
        if (indice >= 0 && indice < listaUsuarios.size()) {
            if(listaUsuarios.get(indice).isAutenticado()){
                if (listaUsuarios.get(indice).getSenha().equals(senhaAtual)) {
                    listaUsuarios.get(indice).setSenha(novaSenha);

                    return "Senha alterada com sucesso.";
                } else{
                    return "Senha atual inválida";
                }
            }else{
                return "Usuário não autenticado";
            }
        }

        return "Usuario não existe";
    }
}
