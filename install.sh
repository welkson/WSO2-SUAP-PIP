WSO_DIR="/Users/welkson/wso2is-5.4.0-update2"
LIB_DIR="$WSO_DIR/repository/components/lib"

echo "\n\n[*] Copiando jar..."
cp target/WSO2-SUAP-PIP-1.0.1.jar $WSO_DIR/repository/components/lib

echo "\n[*] Listando pasta lib do WSO2..."
ls -lah $LIB_DIR

echo "\n[*] Listando configuracao do atributo no entitlement.properties do WSO2..."
cat $WSO_DIR/repository/conf/identity/entitlement.properties | grep ifrn

echo "\n[*] Limpando cache do WSO2..."
rm -rf $WSO_DIR/repository/components/dropins/WSO2_SUAP_PIP*
rm -rf $WSO_DIR/repository/components/default/configuration/org.eclipse.osgi/bundles/4/1/.cp/WSO2-SUAP*

echo "\n\nINSTALAÇÃO CONCLUÍDA! (Reinicie o WSO2)"

# cache search
# find ~/wso2is-5.4.0-update2/ -type f -name "*SUAP*"
