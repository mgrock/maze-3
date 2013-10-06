package app;

import logic.mazeBuilder.RandomMazeBuilder;
import logic.mazeBuilder.MazeBuilder;
import logic.mazeBuilder.DefaultMazeBuilder;
import cli.*;
import java.util.ArrayList;
import java.util.Random;
import logic.*;
import logic.blocks.characters.Dragon;

public class App {

    private static int minDragons = 1;
    private static int maxDragons;
    private static int dragonCount;
    private static int mazeSize;
    private static MazeBuilder mazeBuilder;
    private static Maze maze;
    private static char command;

    // TODO Maybe pass this into the Maze Class
    private static int calcMaxDragonCount(int mazeSize) {

        return (int) Math.floor(Math.pow(mazeSize - 2, 2) / 3);
    }

    public static void setMazeBuilder(MazeBuilder _mazeBuilder) {
        mazeBuilder = _mazeBuilder;
    }

    public static void constructMaze() {

        mazeBuilder.init(mazeSize);

        mazeBuilder.buildWalls();
        mazeBuilder.buildHero();
        mazeBuilder.buildExit();
        mazeBuilder.buildSword();
        mazeBuilder.buildDragons(dragonCount);
        mazeBuilder.buildEagle();

    }

    public static void processCommand(char command) {

        switch (command) {

            case 'w':
                maze.moveCharacter(-1, 0, maze.getHero());
                break;

            case 'a':
                maze.moveCharacter(0, -1, maze.getHero());
                break;

            case 's':
                maze.moveCharacter(1, 0, maze.getHero());
                break;

            case 'd':
                maze.moveCharacter(0, 1, maze.getHero());
                break;

            case 'e':
                break;

        }

        maze.updateHeroStatus();
    }

    // Should this be inside the Maze Class??
    public static void moveDragons() {

        ArrayList<Dragon> dragons = maze.getDragons();
        Random rand = new Random();

        for (int i = 0; i < dragons.size(); i++) {

            if (!dragons.get(i).isAsleep()) {

                int x = rand.nextInt(3) - 1;
                int y = rand.nextInt(3) - 1;
                y = x != 0 ? 0 : y;

                maze.moveCharacter(x, y, dragons.get(i));
            }
        }
    }

    public static void putDragonsToSleep() {

        ArrayList<Dragon> dragons = maze.getDragons();
        Random rand = new Random();

        for (int i = 0; i < dragons.size(); i++) {

            if (!dragons.get(i).isAsleep()) {
                int action = rand.nextInt(2);
                if (action == 1) {
                    dragons.get(i).sleep();
                }
            }
        }
    }

    public static void wakeDragons() {

        ArrayList<Dragon> dragons = maze.getDragons();
        Random rand = new Random();

        for (int i = 0; i < dragons.size(); i++) {

            if (dragons.get(i).isAsleep()) {

                int action = rand.nextInt(2);
                if (action == 1) {
                    dragons.get(i).wake();
                }

            }
        }
    }

    public static void main(String[] args) {

        Screens.splash();
        Screens.separator();

        mazeSize = Screens.mazeSize();
        maxDragons = calcMaxDragonCount(mazeSize);
        dragonCount = Screens.dragonCount(minDragons, maxDragons);

        if (mazeSize == 0) {
            mazeBuilder = new DefaultMazeBuilder();
        } else {
            mazeBuilder = new RandomMazeBuilder();
        }

        constructMaze();
        maze = mazeBuilder.getMaze();

        Draw.maze(maze);

        // TODO Add or has reached exit
        while (maze.getHero().isAlive()) {

            Screens.actionList();
            command = Screens.heroAction();
            processCommand(command);

            if (maze.hearHasReachedExit()) {
                Draw.maze(maze);
                Screens.won();
                break;
            }

            wakeDragons();
            putDragonsToSleep();
            moveDragons();
            Draw.maze(maze);
        }

        if (!maze.hearHasReachedExit()) {            
            Screens.gameOver();
        }
    }
}
