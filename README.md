WSO2-PIP
========

LDAP PIP implementation for WSO2IS


### Build

$ mvn clean install


### Install

```
cp target/br.ifrn.xacml.pip.ldap-1.0.0.jar IS_HOME/repository/components/lib
```

### Configure

Edit file IS_HOME/repository/conf/identity/entitlement.properties

```
PIP.AttributeDesignators.Designator.3=br.ifrn.xacml.pip.ldap.LdapFinder
```


### Run

- Start WSO2 IS

- Open CARBON web interface

- Home -> Entitlement -> PDP -> Extension


### References

https://stackoverflow.com/questions/47408114/configuring-custom-pip-point-in-wso2

http://xacmlinfo.org/2011/12/18/writing-jdbc-pip-module/

https://stackoverflow.com/questions/20401808/wso2-identity-server-improve-the-performance-of-an-attributefindermodule-for-at?rq=1

http://xacmlinfo.org/2011/12/18/pip-architecture-wso2is/

https://docs.wso2.com/display/IS500/Writing+a+Custom+Policy+Info+Point

http://blog.ashansa.org/2013/09/write-simple-jdbc-pip-attribute-finder.html

http://svn.wso2.org/repos/wso2/trunk/commons/balana/modules/balana-samples/kmarket-trading-sample/src/main/java/org/wso2/balana/samples/kmarket/trading/SampleAttributeFinderModule.java

https://svn.wso2.org/repos/wso2/people/asela/xacml/sample/atm/pip/src/main/org/xacmlinfo/xacml/pip/atm/LegacyBankDataFinder.java

http://pushpalankajaya.blogspot.com.br/2017/07/wso2-identity-server-extension-points.html

https://wso2.com/library/articles/2013/11/fine-grained-xacml-authoriation-with-pip-points/

https://svn.wso2.org/repos/wso2/carbon/platform/trunk/components/identity/org.wso2.carbon.identity.samples.entitlement.pip/

http://www.forumsys.com/tutorials/integration-how-to/ldap/online-ldap-test-server/ (LDAP Server Demo)
