package com.juliomesquita.cdc.shared.exceptions;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final  class DatabaseErrorFormatter {
    private DatabaseErrorFormatter() {
    }

    private static final List<PatternRule> RULES = List.of(
        // Violação de UNIQUE (PostgreSQL)
        new PatternRule(
            Pattern.compile("Key \\((.*?)\\)=\\((.*?)\\).*already exists", Pattern.CASE_INSENSITIVE),
            match -> String.format("O valor '%s' já existe para o campo '%s'.", match.group(2), match.group(1))
        ),

        // Violação de UNIQUE (MySQL)
        new PatternRule(
            Pattern.compile("Duplicate entry '(.+)' for key '(\\w+)'", Pattern.CASE_INSENSITIVE),
            match -> String.format("O valor '%s' já está em uso para o campo '%s'.", match.group(1), match.group(2))
        ),

        // Violação de FOREIGN KEY
        new PatternRule(
            Pattern.compile("violates foreign key constraint \"(.*?)\"", Pattern.CASE_INSENSITIVE),
            match -> String.format("Violação de integridade referencial: constraint '%s'.", match.group(1))
        ),

        // Violação de NOT NULL
        new PatternRule(
            Pattern.compile("null value in column \"(.*?)\" violates not-null constraint", Pattern.CASE_INSENSITIVE),
            match -> String.format("O campo '%s' não pode ser nulo.", match.group(1))
        ),

        // Violação de CHECK constraint
        new PatternRule(
            Pattern.compile("violates check constraint \"(.*?)\"", Pattern.CASE_INSENSITIVE),
            match -> String.format("Restrição de validação '%s' foi violada.", match.group(1))
        )
    );

    public static String extractReadableMessage(final String rawMessage) {
        if (rawMessage == null || rawMessage.isBlank()) {
            return "Erro desconhecido ao processar requisição.";
        }

        for (PatternRule rule : RULES) {
            Matcher matcher = rule.pattern.matcher(rawMessage);
            if (matcher.find()) {
                return rule.formatter.apply(matcher);
            }
        }

        // fallback genérico
        return "Erro de integridade de dados: " + rawMessage.split("\\R")[0];
    }

    private record PatternRule(Pattern pattern, java.util.function.Function<Matcher, String> formatter) {}
}
