package org.example.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageCodes {
    NOT_FOUND_FILE(1L, "\n----------------------------------------------------------------" +
            "\nФормат файлу не співпадає, використовуйте .txt формат\n" +
            "Повторіть спробу або використайте слово 'exit' для виходу з програми\n" +
            "----------------------------------------------------------------\n\n"),
    INVALID_TEXT_FORMAT(2L, "Error: invalid markdown ");

    private final Long codeID;
    private final String message;

}
