echo "\n\n[*] Copiando jar..."
cp target/WSO2-SUAP-PIP-1.0.1.jar /Users/welkson/wso2is-5.4.0-update2/repository/components/lib

echo "\n[*] Listando pasta lib do WSO2..."
ls -lah /Users/welkson/wso2is-5.4.0-update2/repository/components/lib

echo "\n[*] Listando configuracao do atributo no entitlement.properties do WSO2..."
cat ~/wso2is-5.4.0-update2/repository/conf/identity/entitlement.properties | grep ifrn

echo "\n\nINSTALAÇÃO CONCLUÍDA! (Reinicie o WSO2)"
