package spr.args;

import lombok.Getter;
import spr.ex.ColsAndRows;
import spr.ex.Padding;

import java.io.File;

import static picocli.CommandLine.Option;
import static picocli.CommandLine.Parameters;

@Getter
public class Arguments implements Task {
    @Parameters(paramLabel = "INPUT_FILE", description = "file or folder with frames")
    File file;

    @Parameters(paramLabel = "EXPORT", description = "save frames as: ${COMPLETION-CANDIDATES}", defaultValue = "SINGLE_IMAGE")
    RenderFormat format;

    @Option(names = "--cells", description = "columns and rows of frames in file")
    ColsAndRows cells;

    @Option(names = "--flip", description = "flip frames horizontally")
    boolean flip;
    @Option(names = "--trim", description = "trim the transparency")
    boolean trim;

    @Option(names = "--padding", description = "appends padding to each frame", defaultValue = "0")
    Padding padding;

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "display a help message")
    boolean helpRequested = false;
}
