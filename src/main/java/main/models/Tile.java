package main.models;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Tile {

  public static final int TILE_SIZE = 72;

  private final Image tileType;
  private final boolean isSolid;

  public Tile(String filename, boolean isSolid) {
    this.isSolid = isSolid;

    ImageIcon icon = new ImageIcon(getClass().getResource(filename).getFile());
    tileType = icon.getImage();
  }

  public Image getTileType() {
    return tileType;
  }

  public boolean isSolid() {
    return isSolid;
  }

}
