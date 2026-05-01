package scgf.api.funcionario.util;

public final class CpfUtils {

    private CpfUtils() {
    }

    public static String formatarParaExibicao(String cpf) {
        if (cpf == null) {
            return null;
        }

        var cpfSomenteDigitos = cpf.replaceAll("\\D", "");
        if (cpfSomenteDigitos.length() != 11) {
            return cpf;
        }

        return cpfSomenteDigitos.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }
}
