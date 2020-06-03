package spr.args;

import picocli.CommandLine;
import spr.ex.ColsAndRows;
import spr.ex.ColsAndRowsFactory;
import spr.ex.Padding;
import spr.ex.PaddingFactory;

public class ArgumentParser {
    public static Arguments parse(String[] args) {
        Arguments command = new Arguments();
        CommandLine cl = new CommandLine(command);
        cl.registerConverter(ColsAndRows.class, ColsAndRowsFactory::parseColsAndRows);
        cl.registerConverter(Padding.class, PaddingFactory::parsePadding);
        cl.parseArgs(args);
        return command;
    }

    public static void usage() {
        new CommandLine(new Arguments()).usage(System.out);
    }
}
