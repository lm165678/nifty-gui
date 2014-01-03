package de.lessvoid.nifty.internal.canvas;

import de.lessvoid.nifty.spi.NiftyTexture;

public class CommandLineWidth implements Command {
  private double lineWidth;

  public CommandLineWidth(final double lineWidth) {
    this.lineWidth = lineWidth;
  }

  @Override
  public void execute(final NiftyTexture renderTarget, final Context context) {
    context.setLineWidth((float) lineWidth);
  }
}