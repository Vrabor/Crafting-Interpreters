package jlox;

// Basic Console ErrorHandler that prints to std.err
class ErrorHandler {
    public static void handleScannerError(ScannerException error){
        // Ex.:
        // 2 + asdjhio + 9
        //     ^~~~~~~ 
        // Unexpected Token at line X column 5
        System.err.println(error.Line());
        int errorLen = error.columnEnd() - error.columnStart();
        String indicator = " ".repeat(error.columnStart()) + "^" + "~".repeat(errorLen - 1);
        System.err.println(indicator);
        System.err.print(error.getMessage() + " at line " + error.Line() + " column " + error.columnStart());

    }
}
