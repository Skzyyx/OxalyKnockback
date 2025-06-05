package org.oxaly.Utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorUtil {

    private static final Pattern HEX_PATTERN = Pattern.compile("#[a-fA-F0-9]{6}");
    private static final Pattern FORMAT_PATTERN = Pattern.compile("&[0-9a-fk-orA-FK-OR]");

    /**
     * Traduce texto con colores tipo '&' y hexadecimales '#RRGGBB' a un Component formateado.
     *
     * @param rawText El texto con formato (por ejemplo "&aHola #FF00FFmundo")
     * @return Component con colores y estilos aplicados
     */
    public static Component format(String rawText) {
        Component component = Component.empty();

        Matcher matcher = Pattern.compile("((#[a-fA-F0-9]{6})|(&[0-9a-fk-orA-FK-OR])|([^&#]+))").matcher(rawText);

        TextColor currentColor = NamedTextColor.WHITE;
        boolean bold = false, italic = false, underline = false, strikethrough = false, obfuscated = false;

        while (matcher.find()) {
            String match = matcher.group();

            if (match.startsWith("&")) {
                char code = Character.toLowerCase(match.charAt(1));
                switch (code) {
                    case '0': currentColor = NamedTextColor.BLACK; break;
                    case '1': currentColor = NamedTextColor.DARK_BLUE; break;
                    case '2': currentColor = NamedTextColor.DARK_GREEN; break;
                    case '3': currentColor = NamedTextColor.DARK_AQUA; break;
                    case '4': currentColor = NamedTextColor.DARK_RED; break;
                    case '5': currentColor = NamedTextColor.DARK_PURPLE; break;
                    case '6': currentColor = NamedTextColor.GOLD; break;
                    case '7': currentColor = NamedTextColor.GRAY; break;
                    case '8': currentColor = NamedTextColor.DARK_GRAY; break;
                    case '9': currentColor = NamedTextColor.BLUE; break;
                    case 'a': currentColor = NamedTextColor.GREEN; break;
                    case 'b': currentColor = NamedTextColor.AQUA; break;
                    case 'c': currentColor = NamedTextColor.RED; break;
                    case 'd': currentColor = NamedTextColor.LIGHT_PURPLE; break;
                    case 'e': currentColor = NamedTextColor.YELLOW; break;
                    case 'f': currentColor = NamedTextColor.WHITE; break;
                    case 'k': obfuscated = true; break;
                    case 'l': bold = true; break;
                    case 'm': strikethrough = true; break;
                    case 'n': underline = true; break;
                    case 'o': italic = true; break;
                    case 'r':
                        currentColor = NamedTextColor.WHITE;
                        bold = italic = underline = strikethrough = obfuscated = false;
                        break;
                }
            } else if (match.startsWith("#")) {
                currentColor = TextColor.fromHexString(match);
            } else {
                Component textPart = Component.text(match).color(currentColor);
                if (bold) textPart = textPart.decorate(TextDecoration.BOLD);
                if (italic) textPart = textPart.decorate(TextDecoration.ITALIC);
                if (underline) textPart = textPart.decorate(TextDecoration.UNDERLINED);
                if (strikethrough) textPart = textPart.decorate(TextDecoration.STRIKETHROUGH);
                if (obfuscated) textPart = textPart.decorate(TextDecoration.OBFUSCATED);
                component = component.append(textPart);
            }
        }

        return component;
    }

    /**
     * Aplica formato a múltiples líneas y las devuelve como lista de Component.
     *
     * @param lines Múltiples strings con formato
     * @return Lista de Component con formato aplicado
     */
    public static List<Component> formatList(String... lines) {
        List<Component> formatted = new ArrayList<>();
        for (String line : lines) {
            formatted.add(format(line));
        }
        return formatted;
    }
}
