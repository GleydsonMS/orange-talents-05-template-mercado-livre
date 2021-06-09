package br.com.zup.mercadolivre.shopping;

public enum ReturnPagseguroStatus {
    SUCCESS, ERROR;

    public StatusTransation normalize() {
        if (this.equals(SUCCESS)) {
            return StatusTransation.success;
        }
        return  StatusTransation.error;
    }
}
