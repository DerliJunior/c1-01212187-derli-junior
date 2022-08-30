# c1-01212187-derli-junior

Questão Única:

Crie uma REST API que:

a) (1,0pt) Seja criada em Spring Boot para Java 11 e tenha a dependência Spring Web

b) (2,0pt) Tenha a chamada POST /usuarios que recebe um JSON como este:
{
  "usuario": "loginloko",
  "senha": "senhaloka",
  "nome": "Zé Buduia Loko"
}
Uma vez que essa chamada é invocado, esse "usuário" fica "cadastrado" na API de alguma de tal forma que pode vir a ser acessível em alguns outras chamadas da API.
Após sua execução, ele retorna o JSON do usuário recém cadastrado. Só que esse JSON na resposta NÃO deve conter a propriedade "senha".

Todo usuário recém cadastrado é um usuário "não autenticado". Veja o item d) para entender.


c) (2,0pt) Tenha a chamada POST /usuarios/autenticacao/{usuario}/{senha}
Ele tentar "autenticar" um usuário com base nos usuario e senha informados.

    c.1) Caso encontre, retorna o JSON do usuário encontrado. Só que esse JSON na resposta NÃO deve conter a propriedade "senha". Nesse caso o usuário passa a ficar "autenticado" no sistema. Para entender o que isso significa, leia o enunciado do item d), a seguir

    c.2) Caso não encontre, retorna "nada" (null).

#ficadica: Você terá uma lista de usuários que fará o papel de "banco de dados", certo? A dica é iterar na lista e comparar o usuario e senha passados no método com o usuario e senha de cada um dos usuários. Caso encontre, faça o usuário ficar "autenticado" (para entender o que isso significa, leia o enunciado do item d), a seguir) e retorne a frase do item c.1). Caso a iteração termine e os usuario e senha não sejam de nenhum usuário, a API retorna a frase do item c.2).



d) (2,0pt) Tenha a chamada GET /usuarios
Ele retorna uma lista de JSONs como neste exemplo (note que não tem "senha" nesse JSON):
[
  {
    "usuario": "loginloko",
    "nome": "Zé Buduia Loko",
    "autenticado": true
  },
  {
    "usuario": "logindahora",
    "nome": "Maria Ruela",
    "autenticado": false
  } 
]

OU, se nenhum usuário estiver cadastrado no sistema, ele retorna uma lista JSON vazia, o seja, apenas [ ].


e) (1,0pt) Tenha a chamada DELETE /usuarios/autenticacao/{usuario}

Ele tentar fazer o "logoff" de um usuário com base no usuario informado. NÃO OCORRE a exclusão do usuário da base! O que é "excluída" é a "autenticação" do usuário, pois o usuário passa a não ficar mais autenticado.

Caso encontre o usuário na "base de dados" da API....
    - Se ele estiver autenticado , retorna a frase "Logoff do usuário X concluído", onde X é o nome, NÃO o usuario.
    - Se ele NÃO estiver autenticado , retorna a frase "Usuário X NÃO está autenticado", onde X é o nome, NÃO o usuario.

Caso não encontre o usuário na "base de dados" da API, retorna a frase "Usuário Z não encontrado", onde Z é o usuario enviado `à chamada.


f) (1,0pt) Crie uma chamada conforme sua criatividade que faça uso dos dados da API aqui descritos. Pode ser qualquer tipo de operação (cadastro, consulta, exclusão etc). Escreva um comentário de código acima do método explicando qual sua intenção com essa chamada.

g) (1,0pt) Use as boas práticas de...
- Organização em pacotes
- Nomes de pacotes, classes, atributos, métodos, URI etc conforme as convenções
- Demais boas práticas de código (nomes coerentes, tamanho de uma linha de código etc)
