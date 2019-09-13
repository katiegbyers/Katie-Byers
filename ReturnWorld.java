package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.Random;

public class ReturnWorld {
    private Point current = new Point(0, 0);
    public static final int WIDTH = 40;
    public static final int HEIGHT = 40;
    TERenderer ter;

    public ReturnWorld() {
        ter = new TERenderer();
        ter.initialize(40, 40);
        TETile[][] popUp = new TETile[40][40];
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 40; j++) {
                popUp[i][j] = Tileset.FLOOR;
            }
        }
        for (int i = 15; i < 25; i++) {
            for (int j = 15; j < 25; j++) {
                popUp[i][j] = Tileset.FLOWER;
            }
        }
        popUp[current.x][current.y] = Tileset.AVATAR;
        ter.renderFrame(popUp);
        moves(popUp);
    }

    private void moves(TETile[][] world) {
        System.out.println("In moves");
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                c = Character.toUpperCase(c);
                switch (c) {
                    case 'W':
                        if (current.y + 1 >= 0 && current.y + 1 < HEIGHT) {
                            TETile tile = world[current.x][current.y + 1];
                            if (tile.equals(Tileset.FLOOR)) {
                                world[current.x][current.y] = Tileset.FLOOR;
                                current.y = current.y + 1;
                                world[current.x][current.y] = Tileset.AVATAR;
                            } else if (tile.equals(Tileset.FLOWER)) {
                                return;
                            }
                            ter.renderFrame(world);
                        }
                        System.out.println(current.x);
                        System.out.println(current.y);
                        break;
                    case 'S':
                        if (current.y - 1 >= 0 && current.y - 1 < HEIGHT) {
                            TETile tileTwo = world[current.x][current.y - 1];
                            if (tileTwo.equals(Tileset.FLOOR)) {
                                world[current.x][current.y] = Tileset.FLOOR;
                                current.y = current.y - 1;
                                world[current.x][current.y] = Tileset.AVATAR;
                            } else if (tileTwo.equals(Tileset.FLOWER)) {
                                return;
                            }
                            ter.renderFrame(world);
                        }
                        System.out.println(current.x);
                        System.out.println(current.y);
                        break;
                    case 'A':
                        if (current.x - 1 >= 0 && current.x - 1 < WIDTH) {
                            TETile tileThree = world[current.x - 1][current.y];
                            if (tileThree.equals(Tileset.FLOOR)) {
                                world[current.x][current.y] = Tileset.FLOOR;
                                current.x = current.x - 1;
                                world[current.x][current.y] = Tileset.AVATAR;
                            } else if (tileThree.equals(Tileset.FLOWER)) {
                                return;
                            }
                            ter.renderFrame(world);
                        }
                        System.out.println(current.x);
                        System.out.println(current.y);
                        break;
                    case 'D':
                        if (current.x + 1 >= 0 && current.x + 1 < WIDTH) {
                            TETile tileFour = world[current.x + 1][current.y];
                            if (tileFour.equals(Tileset.FLOOR)) {
                                world[current.x][current.y] = Tileset.FLOOR;
                                current.x = current.x + 1;
                                world[current.x][current.y] = Tileset.AVATAR;
                            } else if (tileFour.equals(Tileset.FLOWER)) {
                                return;
                            }
                            ter.renderFrame(world);
                        }
                        System.out.println(current.x);
                        System.out.println(current.y);
                        break;
                    case ':':
                        while (true) {
                            if (StdDraw.hasNextKeyTyped()) {
                                if (StdDraw.nextKeyTyped() == 'Q') {
                                    quit();
                                } else {
                                    break;
                                }
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private String quit() {
        System.out.println("Quit");
        System.exit(5);
        return "Q";
    }

}