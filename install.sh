WSO_DIR="/Users/welkson/wso2is-5.4.0-update2"
LIB_DIR="$WSO_DIR/repository/components/lib"

echo "\n\n[*] Copiando jar..."
cp target/WSO2-SUAP-PIP-1.0.1.jar $WSO_DIR/repository/components/lib

echo "\n[*] Listando pasta lib do WSO2..."
ls -lah $LIB_DIR

echo "\n[*] Listando configuracao do atributo no entitlement.properties do WSO2..."
cat $WSO_DIR/repository/conf/identity/entitlement.properties | grep ifrn

echo "\n\nINSTALAÇÃO CONCLUÍDA! (Reinicie o WSO2)"
