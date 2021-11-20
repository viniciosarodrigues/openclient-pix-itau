# Lib Client para API PIX do Itau

# Introdução 
O Pix Itaú Client é um rest-client criado para consumir a API de Openbanking do Itaú focado nos serviços relacionados ao PIX. Para a efetiva utilização, faz-se necessário algumas configurações prévias, segue um passo-à-passo:

# Ambiente e ferramentas
1.	Java 8+
2.	Última release disponível estável (main)
5.	URL base dos serviços do Itaú
6.	ClientId da API do Itaú
7.	SecretId da API do Itaú

# Build e Teste
Atualmente o fonte contém um conjunto de testes integrados com a API sandbox do Itaú, com eles é possível entender como funcionam os fluxos implementados no Client.

Obs: O ambiente deve ter acesso à rede do fornecedor (Itaú) e o fornecedor deve ter acesso à aplicação que estará consumindo os serviços.

- Build com testes: <b>mvn install</b>

<p>Para build ou deploy sem testes, adiciona-se a flag <b>-DskiptTests</b> no fim do comando, ex: <b>mvn install -DskipTests</b></p>

<p>Obs: Para deploy e integração com o Nexus, as configurações de autenticação devem estar configuradas no Maven.</p>