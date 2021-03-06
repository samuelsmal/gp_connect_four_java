# Using Generic Programming to evolve a CONNECT FOUR playing AI

## Requirements

* Java 7
* An unix-shell
* An Ant build system

## Building

Releases can be found [online](https://github.com/samuelsmal/gp_connect_four_java/releases).

### Using Ant

    ant compile

### Using Eclipse

Help can you find [here](http://stackoverflow.com/questions/17768845/exporting-usable-jar-files-from-github-project).

## Running

From your console:

    java -jar gp_connect_four.jar

## Notes on running

You can set the options in the `Main.java` file.

In the  main method you can select what you want to do:

* Play against a player (calling the  play method), or
* let the evolve a player for you (calling the  evolve method).

You then have to compile the program, as of now no command line options have been integrated.
Can take a long while with the right/wrong options.

## Saving an evolved player

1. Run the programing evolving an player. Save the code yourself, or redirect its output like this `java -jar gp_connect_four.jar > mi_01_only_ternary.txt`
1. Create a new class in the package `org.samuelsmal.game.EvolvedPlayers`, extending `GPPlayer`.
2. Implement the required methods (see below).
3. Insert into the `play()`-method of the `Main`-class in the `Main.java`-file an new Instance of your new class created in step 2, replace one of the old players. The argument sequence corresponds to the start player.


    @Override
    protected long evalGame(char playerColour, char enemyColour, Game game) {
      // PAST CODE FROM PROGRAMM HERE
    }

## Using the data

You can extract the data using `grep`:

    grep '^[0-9]' mi_01_only_ternary.txt > mi_01_only_ternary_points.txt
    
This will extract all the datapoints. If you want to extract only the winner use

    grep '^0' mi_01_only_ternary.txt > mi_01_only_ternary_only_winner.txt
    
If you want to extract the number of nodes use:

    grep 'Winner count of nodes' mi_01_only_ternary.txt > mi_01_only_ternary_node_count.txt
    
And some replacement tool of your own.

You can use my [Gist](https://gist.github.com/samuelsmal/26ed285beb8f96879f41) to plot the data, it's written in [R](http://www.r-project.org/).
