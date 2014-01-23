package de.lessvoid.nifty.gdx.render.batch;

import de.lessvoid.nifty.batch.BatchInternal;
import de.lessvoid.nifty.batch.spi.Batch;
import de.lessvoid.nifty.batch.spi.BatchFactory;
import de.lessvoid.nifty.batch.spi.BufferFactory;
import de.lessvoid.nifty.batch.spi.GL;

import javax.annotation.Nonnull;

/**
 * @author Aaron Mahan &lt;aaron@forerunnergames.com&gt;
 */
public class GdxBatchFactory implements BatchFactory {
  @Nonnull
  @Override
  public Batch create(@Nonnull final GL gl, @Nonnull final BufferFactory bufferFactory) {
    return new GdxBatch(new BatchInternal(gl, bufferFactory));
  }
}
