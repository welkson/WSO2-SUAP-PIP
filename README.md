WSO2-PIP-SUAP
========

Implementação de referência de um WSO2 PIP de integração com LDAP/AD e PostgreSQL para o SUAP (IFRN)


### Compilação

```
$ mvn clean install

ou sem testes:

$ mvn clean install -DskipTests 
```


### Instalação

```
cp target/WSO2-SUAP-PIP-1.0.1.jar IS_HOME/repository/components/lib
cp ~/.m2/repository/org/postgresql/postgresql/42.2.2/postgresql-42.2.2.jar IS_HOME/bin

```


### Configuração no WSO2

Editar o arquivo IS_HOME/repository/conf/identity/entitlement.properties

```
PIP.AttributeDesignators.Designator.3=br.edu.ifrn.pip.SuapAttributeFinder
```

Onde "3" é o ID na sequência dos AttributeDesignators. 
ATENÇÃO: Se no properties já tiver o AttributeDesignators 1 e 2 por exemplo, e o novo for definido como 4 (fora de sequência), o mesmo NÃO SERÁ CARREGADO.


### Execução

- Inicia o WSO2 IS

- Abre a interface Web CARBON 

- Home -> Entitlement -> PDP -> Extension e verifica se o PIP é listado

- Em PAP insere a política de exemplo que se encontra em Extra/Policy.xml e por fim publica a política no PDP (Publish To PDP)

- Ainda em PAP clique em "Try It" e informe os seguintes valores:
	- Resource: /new_ticket/
	- Subject Name: 1956951
	- Action Name: GET
	- Environment Name: 
	
	- Clique em "Test Evaluate".
	
```
Comentário: a política (Extra/Policy.xml) espera um atributo de recurso /new_ticket/,
uma ação GET, e "COINRE" como um atributo departamento do LDAP (AttributeId=http://ifrn.edu.br/ldap/departamento), 
entretanto na requisição de teste (TryIt) só informamos o action, subject, e resource, 
portanto o WSO2 irá verificar se existe algum PIP registrado no sistema que possa responder 
por esse novo atributo (departamento), e encontrará a implementação deste PIP, que será executado, 
efetuará a consulta dos grupos do usuário "1956951"(subjectId) no AD, e retornará para 
avaliação no PDP.
```  

### Ambiente de desenvolvimento

O projeto foi construído usando Maven e Eclipse. Após ajustes no pom.xml clique com botão direito no projeto -> Maven -> Update Project.



