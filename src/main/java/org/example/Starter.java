package org.example;

public class Starter {
    private final TerminalIO terminalIO;
    private final FileIO fileIO;

    public Starter(TerminalIO terminalIO, FileIO fileIO) {
        this.terminalIO = terminalIO;
        this.fileIO = fileIO;
    }

    public void start(){
        fileIO.init();
        terminalIO.startSession();
    }
}
