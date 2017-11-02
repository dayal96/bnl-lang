package parser;

/**
 * Created by amogh on 6/27/17.
 */
public class ParseUtils {

  /**
   * Is the given token a number?
   * @param token  the token read from input.
   * @return true if the given token is a number, false otherwise.
   */
  public static boolean isNumber(String token) {

    for (int i = 0; i < token.length(); i++) {
      if ((token.charAt(i) > '9' || token.charAt(i) < '0') && token.charAt(i) != '/') {
        return false;
      }
    }

    return token.charAt(0) != '/' && token.charAt(token.length() - 1) != '/';
  }

  /**
   * Is the given String a whitespace character?
   * @param str the given String.
   * @return true if the given String is a whitespace character, false otherwise.
   */
  public static boolean isWhitespace(String str) {
    return str.equals("\n") || str.equals(" ") || str.equals("\t")
        || str.equals("\r") || str.equals("\r\n");
  }

  /**
   * Is the given String an opening bracket?
   * @param str the given String.
   * @return true if the string is an open parenthesis, bracket or brace.
   */
  public static boolean isOpenParenth(String str) {
    return str.equals("(") || str.equals("[") || str.equals("{");
  }

  /**
   * Is the given String a closing bracket?
   * @param str the given String.
   * @return true if the string is a close parenthesis, bracket or brace.
   */
  public static boolean isCloseParenth(String str) {
    return str.equals(")") || str.equals("]") || str.equals("}");
  }
}
