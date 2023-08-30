
# Garde-manger (French for Pantry)

### Purpose

Garde-manger gives you a simplified way of making a shopping list specifically for the meals you 
are making in the coming days. Create meals and tick off the ingredients you have, then in one central
place you can see all the ingredients you need to by next time you're at the supermarket. This app is
suitable for anyone who cooks and unfortunately has to pay for their food.

### Development Process

1. I started development watching a youtube video about persistence and laying out my project in a clean way.
2. This led me to learn about hilt which helped to clean up my development.
3. I then made static screens for my whole app, followed by adding navigation to these screens.
4. Finally I added events and view models to implement the CRUD actions of the application.

### Grade Bearing Requirements

1. Home Screen, Pantry Screen, Meals List Screen, Add/Edit meal Screen
2. The meals screen has a cheeky little shortcut to browse for meal recipes in your browser of choice.
3. Meals List Screen, Pantry Screen and Add/Edit Meals screen all use Lazy Columns
4. Button, Text, TextField, Checkbox, Switch, IconButton, TopAppBar
5. Scaffold, Row, Column, Box
6. All pages are usable in both portrait and landscape mode
7. The home page explicitly changes from a Column to a row layout between orientations. 
8. All strings are from the resources strings files.
9. Supports French (Thanks Fabian)
10. The user gets a response in the form of a Toast when they save a meal
11. Filtering the pantry uses a slide in and out transition animation.
12. Room database API is used for persistence
13. Hilt Dependency Injection API for managing State 
