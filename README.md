# MazeGenerator

Randomely generates maze and allows user to solve the maze. Best solution is shows after and is found with a breadth first search traversal. The maze is represented as a disjoint set where each set represents a path. Sets are combined if paths are adjacent to eachother.  

### Baseline Generating Algorithm

Randomely chooses a coordinate from the maze to become a path till there is a path between the start and end coordinates. Start is at the upper left and the end is at the bottom right.

**Variant 1:**

<img width="350" alt="Screen Shot 2023-08-28 at 3 24 44 PM" src="https://github.com/arulh/MazeGenerator/assets/104797653/4df61182-f16c-4ed1-85bb-741cf9fd4de6">
<img width="350" alt="Screen Shot 2023-08-28 at 3 25 14 PM" src="https://github.com/arulh/MazeGenerator/assets/104797653/89ab4fa0-fe20-4a9b-be1e-069faa2d57d2">


**Variant 2:**

<img width="350" alt="Screen Shot 2023-08-28 at 4 19 26 PM" src="https://github.com/arulh/MazeGenerator/assets/104797653/319ec751-c92d-48c8-836b-f8d20ff6de59">
<img width="350" alt="Screen Shot 2023-08-28 at 4 20 03 PM" src="https://github.com/arulh/MazeGenerator/assets/104797653/da8d9cfc-479e-44f3-af6a-93b5302be01b">



