# Connect 4
![connect4-big](https://github.com/mkennedm/Connect4/assets/8769212/94bb5cd3-dffb-4ff5-9a95-e09416112199)

This one started as a class project when I was at Boston University. The group I was in got the game to function, but we had to make some shortcuts due to time constraints. I remade the project on my own the following summer. Liberated from deadlines, I was able to go above and beyond the original requirements of the assignment.

One of those requirements was the inclusion of a four line java file called PlayerClass that my professor used to grade the class's submissions. The bulk of my work is in two files: [Player18.java](https://github.com/mkennedm/Connect4/blob/master/src/connect4/ai/Player18.java) (the artificial intelligence) and [Connect4.java](https://github.com/mkennedm/Connect4/blob/master/src/connect4/Connect4.java) (the GUI).

Player18 is named after the group number I was assigned to for the project's first incarnation. The AI I've written decides which moves to make with a game tree. The min-max algorithm descends to a depth determined by the difficulty setting. At the maximum setting the game evaluates up to 262,144 potential moves, however in most cases it avoids evaluating unncessary paths with alpha-beta pruning. If you'd prefer to play the game against another person, just toggle the "2 Players" button. The multiplayer mode isn't online, you'd just be taking turns with someone sitting next to you at the same computer.

Connect4.java relies on JEventQueue.java and JBox.java. Both are libraries created by Mads Rosendahl, a professor at Roskilde University. I've never met him, but my professer at BU gave those two files to students so we could make our GUIs. It was my first time using an API and very helpful in putting together the visual component of my Connect 4.

The two files communicate through a method named PlayerOne (which represent the human). Player18.java has it's own internal board that is mirrored in Connect4.java. AI moves are passed from Player18.java to Connect4.java, and human moves are passed in the opposite direction. You can watch a demostration of me playing (and losing!) against the AI in the YouTube video below. You can play it for yourself by [downloading the jar file](https://github.com/mkennedm/Connect4/blob/master/Connect4NetBeans.jar).

https://youtu.be/kNcl9xK-4-s
