package spr.args;

import spr.ex.ColsAndRows;
import spr.ex.Padding;

import java.io.File;

public interface Task {
    File getFile();

    RenderFormat getFormat();

    ColsAndRows getCells();

    boolean isFlip();

    boolean isTrim();

    Padding getPadding();
}
