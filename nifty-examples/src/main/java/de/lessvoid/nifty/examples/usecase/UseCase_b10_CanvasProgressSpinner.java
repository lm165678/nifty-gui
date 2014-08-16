package de.lessvoid.nifty.examples.usecase;

import java.io.IOException;

import de.lessvoid.nifty.api.ChildLayout;
import de.lessvoid.nifty.api.Nifty;
import de.lessvoid.nifty.api.NiftyCallback;
import de.lessvoid.nifty.api.NiftyCanvas;
import de.lessvoid.nifty.api.NiftyCanvasPainter;
import de.lessvoid.nifty.api.NiftyColor;
import de.lessvoid.nifty.api.NiftyMutableColor;
import de.lessvoid.nifty.api.NiftyNode;
import de.lessvoid.nifty.api.UnitValue;
import de.lessvoid.nifty.api.controls.Label;

/**
 * An example how to use an animated NiftyCanvas for a custom progress spinner animation.
 * @author void
 */
public class UseCase_b10_CanvasProgressSpinner {
  private int pos = 0;

  public UseCase_b10_CanvasProgressSpinner(final Nifty nifty) throws IOException {
    NiftyNode rootNode = nifty.createRootNodeFullscreen(ChildLayout.Center);
    rootNode.setBackgroundColor(NiftyColor.BLACK());

    NiftyNode spinner = rootNode.newChildNode(UnitValue.px(128), UnitValue.px(128));
    spinner.addCanvasPainter(new NiftyCanvasPainter() {
      @Override
      public void paint(final NiftyNode node, final NiftyCanvas canvas) {
        canvas.resetTransform();
        canvas.setFillStyle(NiftyColor.fromString("#000f"));
        canvas.fillRect(0, 0, node.getWidth(), node.getHeight());

        NiftyMutableColor color = NiftyMutableColor.fromColor(NiftyColor.fromString("#f00"));
        int max = 24;
        for (int i=0; i<max; i++) {
          int index = i + pos;
          if (index < 0) {
            index = index + max;
          }
          color.setAlpha(index / (float) max);
          canvas.resetTransform();
          canvas.translate(node.getWidth() / 2, node.getHeight() / 2);
          canvas.rotateDegrees(i * 360.f / (float) max);
          canvas.translate(25.f, 0.f);
          canvas.setFillStyle(color.getColor());
          canvas.fillRect(0, -2.5, node.getWidth() * 0.3, 2.5);
        }

        pos--;
        if (pos <= -max) {
          pos = 0;
        }
      }
    });
    spinner.startAnimatedRedraw(0, 50);

    nifty.setRootNodePlacementLayout(ChildLayout.Vertical);
    NiftyNode fpsNode = nifty.createRootNode(UnitValue.percent(100), UnitValue.wildcard(), ChildLayout.Horizontal);
    final Label label = fpsNode.newControl(Label.class);
    label.setFont(nifty.createFont("fonts/aurulent-sans-16.fnt"));
    label.getNode().setBackgroundColor(NiftyColor.BLACK());
    label.getNode().startAnimated(0, 1000, new NiftyCallback<Float>() {
      @Override
      public void execute(final Float t) {
        label.setText(nifty.getStatistics().getFpsText());
      }
    });
  }

  public static void main(final String[] args) throws Exception {
    UseCaseRunner.run(UseCase_b10_CanvasProgressSpinner.class, args);
  }
}