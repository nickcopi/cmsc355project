# fall-19-project-one


## Note
All of our tests are confirmed to pass on a virtual device with Pixel 2 API 27

## Iteration Two

During this iteration we got done a lot of upgrade related and item selling work. The game structure was also refactored greatly so that there is now a game object and there aren't just a bunch of things slapped onto the ingame activity.
The player can now sell items they pick up, see their money, and purchase speed and size upgradees with the acquired money.
We also made the harvestable items regenerate and fixed a lot of the previous tests actually work better for them.


We implemented and wrote tests for:
- As a player I want collected items to repopulate when all are collected so that I can continue playing.
- As a player looking to easier harvest items I want to become larger when I buy a size upgrade so that I can make more money.
- As a player looking to more quickly harvest items I want to move faster when I buy a speed upgrade so that I can make more money.
- As a player with some specific item I want to get money when I click the item’s sell button so that I can buy upgrades.

We used the Template Method design pattern. We have an AbstractUpgrade class that all of the specific upgrades extend. AbstractUpgrade has a method, buy, that relies on its inheritors to implement its abstract method getBonus. Because of this, buy acts as a template method, its functionality partially depending on how its inheritors implement an abstract method.

AbstractUpgrade has another template method, getDescription, which calls the abstract method getText which is implemented by inheritors.



## Iteration One
During this iteration we knocked out mostly essentials. Many of our user stories could not be satisfied without some essential code being done first, like making the activities communicate with each other which is what we worked on first. Then, we worked on the movement of the character, collecting the items, the main menu, and the upgrade menu, all of which satisfy user stories.

We implemented and wrote tests for:
- As a player that is moving I want to harvest items I collide with so that I have things to sell.
- As a player I want to not go off the games map so that I can continue to play the game.
- As a player that controls the character when I swipe I want to change directions so that I can control which way I move.
- As a player seeking fun I want to be able to access menus so that I can play the game.

Getting the initial Canvas SurfaceView and threading set up was a pain, as well as simulating touch input for the tests, but fortunately we were able to find code online to get us started with it.
Refactoring some of the tests and the way input is handled as well as adding a main game class may be in order for a future iteration.

