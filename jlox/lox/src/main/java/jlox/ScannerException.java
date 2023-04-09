package jlox;

class ScannerException extends Exception {
    private int lineNr;
    private String line;
    private int columnStart;
    private int columnEnd;
    public ScannerException(String errorMessage, String line, int lineNr, int columnStart, int columnEnd) {
        super(errorMessage);
        this.line = line;
        this.lineNr = lineNr;
        this.columnStart = columnStart;
        this.columnEnd = columnEnd;
    }

    public String Line() {
        return line;
    }

    public int LineNr() {
        return lineNr;
    }

    public int columnStart() {
        return columnStart;
    }

    public int columnEnd() {
        return columnEnd;
    }
}
