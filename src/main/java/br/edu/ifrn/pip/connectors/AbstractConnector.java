package br.edu.ifrn.pip.connectors;

public abstract class AbstractConnector implements Connector {
    private String atributoId;

    public String getAtributoId() {
        return atributoId;
    }

    public void setAtributoId(String atributoId) {
        this.atributoId = atributoId;
    }
}
