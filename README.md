
# Sistema ReclameAli

## Diagrama de Classes

A estrutura prinpipal das classes modelo da aplicação são a seguinte:

![Diagrama de Classes](./docs/class_diagram.jpg)

## Beans

Todas as casses de objetos acima descritos possuem um respectivo bean que replica todos os atributos. Os beans que exigem validação, pois há novas entradas provenientes da interação com a aplicação (como usuários, atendimentos, produtos...) implmentam o método `validate()`, que retorna uma lista de `ValError`, que é um bean com atributos `field` e `message` usado para transmitir erros e onde há problemas nos formulários. 

Tidas essas classes também possuem seus respectivos DAOs. Nem todos possuem todas as operações de inserção, atualização ou exclusão, já que nem todos os objetos serão modificados a partir da aplicação, mas todos tem, ao menos, métodos para consulta (SELECT) que já trazem outros objetos aninhados que façam parte de seus atributos. Por exemplo: ao fazer chamar o método `TicketDAO.listAll()`, o próprio DAo já é responsável por encontrar os objetos que fazer parte de seus atributos, como `TicketStatus`,  `TicketType` e `TicketMessage`.

## Diagrama Relacional (BD)

No banco de daods os modelos ficarâo alocados da seguinte forma:

![Diagrama Relacional](./docs/realational_diagram.jpg)

## Acesso à aplicação

Conforme requisitos, a aplicação possui 3 perfis de acesso diferentes:

- Ciente
- Funcionário
- Gerente

Como esses três perfis são armazenados no banco de dados na tabela `users` (coluna `role`), o tipo de acesso é personalizado a partir do login. o método `UserDAO.authenticate()` recebe 2 parâmetros String (email de login e senha) e retorna uma instância de uma subclasse de `User`  (ou Cliente, ou Funcionario, ou Gerente) e essa instância é salva no escopo da sessão. 

![Autenticação de Usuário](./docs/authentication.png)

A requisição com o formulário de login é enviado via POST para a rota `/entrar?action=signin`, que é processada pela servlet `PublicServlet `. Uma vez o login seja realizado com sucesso, é salva a instância do perfil dp usuário em sessão e a partir daí, todas as rotas restritas são validadas a partir dos filtros, no pacote `br.com.beibe.filter`. Caso não haja permissão suficiente para acessar aquela rota, a aplicação exibe a tela de login, especificando o perfil necessário para acessar aquela rota.

Assim, todas as requisições são interceptadas pelos filters, confirma-se a permissão de acesso, seguindo para a servlet, que processa a requisição, instanciando objetos, interagindo com as classes façade e preparando a resposta, seja encaminhando a requisição para uma JSP, seja montando e respondendo com dados em formato JSON (servlets com prefixo *API*).

![Fluxo de Processamento da Requisição](./docs/request_flow.png)

**Nem todas as requisições retornam uma página JSP. Algumas requisições retornam formato JSON, mas essas requisições são para uso em requisições AJAX (requisições via JavaScript que não requerem o recarregamento da página).**

## Conversão e Validação de Dados

Os dados de formulário são recebidos pelas servlets e a servlet instancia os respectivos objetos e seta os devidos atributos. Se alguma conversão é necessária para setar o atributo na instância do objeto (tipo uma data) a conversão é feita na própria servlet, antes de chamar o método setter to atributo. No mais, os próprios métodos setter dos beans já fazem alguns tipos de conversões:

Por exemplo:
- dados numéricos que são armazenados como String, como CEP e CPF tem todos os caracteres, com excessão dos digitos, removidos na chamada do setter;
- atributos que são objetos, como o endereço do usuário, são instanciados e construídos e somente depois setados na instância de usuário;
- atributos numériocos são parseados.

Uma vez setados os atributos do objeto, a servlet ivoca o método `validate()` e avalia se há erros ou não e prepara a resposta para o usuário.

## JSPs e Tags Customizadas

Todas as páginas JSP incluem tags customizadas, entre elas a base comum para todas as páginas HTML, que inclui os links para as bibliotecas externas de CSS e JavaScript, referenciadas como `baseLayout`. Outras tags customizadas são as de cabeçalho (`baseHeader`) e rodapé (`vaseFooter`), além de algumas tags para impressção formatada de dados, como datas e CPF.

Fora essas tags, a biblioteca JSTL foi amplamente utilizada em todas as páginas, assim como a notação de espressão EL (`${...}`).

As páginas JSP estão todas armazenadas em `/WEB-INF/jsp/` , sendoacessíveis apenas por chamadas internas da aplicação. 

## DAO

Toda comunicação com o banco de dados é feita a partir dos DAOs, que fazem uso do serviço da classe `ConnectionFactiry`.

As classes DAO são todas abstratas, sendo utilizadas com chamadas unicamente de métodos estáticos. Para cada bean armazenado no banco de dados há uma classe DAO equivalente que herda da classe DAO. 

Para cada classe DAO há um *enumeration* que fica lsita o nome das colunas no banco de dados para a tabela respectiva àquele bean. A classe mãe DAO contem alguns métodos que abstraem o SQL esperado para as chamadas chaves como INSERT, SELECT, UPDATE ou DELETE.
