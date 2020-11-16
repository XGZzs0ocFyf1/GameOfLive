# GameOfLife
A training project to consolidate base skill of programming in java.

About game of life you can read here https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life;
My project is simple implementation of it.
To start first download or clone repo.

+  go to its folder: ```cd GameOfLive```

+  execute: ``` javac -d bin game/classed/GameFrame.java```
+  ```cd bin```

+ In bin create folder "config"
``` cd config```

+ create file game.properties via text editor, and put inside next configuration:
```
game.field.width=900
game.field.height=600
game.field.cell.size=2
game.field.refresh.delay=10
game.field.hasgrid=false
game.type=spawn

```
 
+ and execute ```java game.classed.GameFrame ```

type spawn - random generation of cells
type glider = create only one glider pattern
