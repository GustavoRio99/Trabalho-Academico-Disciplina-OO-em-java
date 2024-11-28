# SGC
Sistema Gerenciador Comissao
Sistema desenvolvido por Gustavo Rio e Alunos do projeto de extensao do curso Programacao orientada a obj em java

Roteiro de Instalação e Configuração do Projeto SGV
1. Instalar o JDK da Oracle (Java 17)
Acesse o site oficial da Oracle: Oracle Java SE Downloads.
Baixe a versão JDK 17 para o seu sistema operacional.
Siga o assistente de instalação e conclua a configuração.
2. Instalar o NetBeans
Faça o download do NetBeans em: NetBeans Downloads.
Instale a versão mais recente, garantindo suporte ao Java.
Abra o NetBeans e configure o JDK instalado (em Ferramentas -> Java Platforms).
3. Clonar o projeto SGV
No NetBeans, clique em:
Menu -> Team -> Git -> Clone...
Na janela aberta:
Cole o endereço do repositório:
https://github.com/pablorangel82/sgv.git.
Não é necessário preencher usuário e senha.
Clique em Next e, depois, em Finish.
O projeto será clonado e adicionado ao NetBeans.
4. Compilar o projeto
No painel de projetos do NetBeans, clique com o botão direito sobre o projeto SGV.
Selecione a opção Build Project.
Aguarde a conclusão da compilação.
5. Executar o projeto
Clique novamente com o botão direito sobre o projeto.
Selecione a opção Run.
O projeto será executado e estará disponível no servidor local.
6. Configurar o banco de dados H2
Abra o navegador e acesse:
https://localhost:8443/h2-console.

No console H2:

JDBC URL: jdbc:h2:~/db
User: sgv
Password: 123
Clique em Connect para acessar o banco de dados.

7. Inserir dados iniciais
No console de comandos SQL, cole o seguinte comando para criar o usuário administrador:
sql
Copiar código
insert into Usuario (login, senha, papel, id) values
('admin', '$2a$10$K6PG.YUsSpMT/LOyPpeB5eUVdPImfDfSH.N0xLHAC1NbgbIBhraHe', 'ADMIN', 1);
Clique no botão Run (ícone de seta verde) para executar o comando.
8. Acessar o sistema
No navegador, acesse o endereço:
https://localhost:8443/.
Faça login com as credenciais:
Usuário: admin
Senha: 123.
🎉 Pronto! O sistema SGV está configurado e funcionando.
