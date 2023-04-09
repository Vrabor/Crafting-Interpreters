package jlox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import jlox.ErrorHandler;
import jlox.ScannerException;

public class Lox {
  public static void main(String[] args) throws IOException {
    if (args.length > 1) {
      System.out.println("Usage: jlox [script]");
      // UNIX exit code for "USAGE": command was user incorrectly
      System.exit(64); 
    } else if (args.length == 1) {
      runFile(args[0]);
    } else {
      runPrompt();
    }
  }

  private static void runFile(String path) throws IOException {
    byte[] bytes = Files.readAllBytes(Paths.get(path));
    try {
        run(new String(bytes, Charset.defaultCharset()));
    } catch (ScannerException se){
        ErrorHandler.handleScannerError(se);
        // UNIX Error code for "DATAERR": wrong input data
        System.exit(65);
    }
  }

  private static void runPrompt() throws IOException {
    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(input);

    for (;;) { 
      System.out.print("> ");
      String line = reader.readLine();
      if (line == null) break;

      try{  
        run(line);
      } catch (Exception e){
          // Do error reporting and continue, because REPL
          // just some rudimentary handling
          System.err.println(e.getMessage());
      }
    }
  }

  private static void run(String source) throws ScannerException{
    Scanner scanner = new Scanner(source);
    List<Token> tokens = scanner.scanTokens();

    // For now, just print the tokens.
    for (Token token : tokens) {
      System.out.println(token);
    }
  }
}
