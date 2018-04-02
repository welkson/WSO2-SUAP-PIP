## WSO2-PIP

LDAP PIP implementation for WSO2IS


## Build

```
mvn clean install
```

## Install

cp target/br.ifrn.xacml.pip.ldap-1.0.0.jar IS_HOME/repository/components/lib


## Configure

Edit file IS_HOME/repository/conf/identity/entitlement.properties

```
PIP.AttributeDesignators.Designator.2=br.ifrn.xacml.pip.ldap.LdapFinder
```


## Run

- Start WSO2 IS

- Open CARBON web interface

- Home -> Entitlement -> PDP -> Extension
