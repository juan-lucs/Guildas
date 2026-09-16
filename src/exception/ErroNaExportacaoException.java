package exception;

public class ErroNaExportacaoException extends Exception{
    public ErroNaExportacaoException(String erroNaExportacao) {
        super(erroNaExportacao);
    }
}
